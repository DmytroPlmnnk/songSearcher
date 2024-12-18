package com.github.DmytroPlmnnk;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class App
{
    public static void main( String[] args ){
        boolean stop = true;
        Scanner sc = new Scanner(System.in);
        String songName;

        while(stop){
            System.out.println("==========================================================");
            System.out.println("Enter the name of the song and/or arist: ");
            songName = sc.nextLine();
            System.out.println();
            searchSongText(songName);
            System.out.println();
            System.out.println("Stop? 1 - yes, 0-no");
            try {
                stop = sc.nextInt() != 1;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter 1 to stop or 0 to continue.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

        }


    }


    /*
    Searches for a song by its name or artist.
    Sends a request to AZLyrics, retrieves the song's URL, and parses its lyrics.
    Finally, prints the formatted song text to the console.
     */
    public static void searchSongText(String songName){


        try{
            //Creates request url and forms a request
            //.replaceAll(" ", "+")+"&x=dcfd80b4b26344ad698f1f424b8e3930e2ddb8f83c8ea565ae8d4927802059b8"
            String searchUrl = "https://search.azlyrics.com/search.php?q=" + songName.replaceAll(" ", "+")+"&x=d395c388cfa6c643e517a06074f3a1d0c6d6968bb17fd9652d35954701aee9da";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(searchUrl))
                    .GET()
                    .build();

            //Send a request and receive a response as a string
            //Parse this string into a document
            //Get the first link to the lyrics from this page
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Document searchPage = Jsoup.parse(response.body());
            Element firstLink = searchPage.selectFirst("a[href^=https://www.azlyrics.com/lyrics/]");



            if (firstLink != null) {

                //Parse a first song text from search
                String songUrl = firstLink.attr("href");
                System.out.println("Song link: " + songUrl);
                request = HttpRequest.newBuilder()
                        .uri(URI.create(songUrl))
                        .GET()
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                Document songPage = Jsoup.parse(response.body());

                //Search for song text on songPage
                String lyrics = Objects.requireNonNull(songPage.selectFirst("div.col-xs-12.col-lg-8.text-center>div:not([class])")).html();


                System.out.println("Song text:");
                System.out.println("==========================================================");


                //Find the name of the song and print it
                String name = Objects.requireNonNull(songPage.selectFirst("div.col-xs-12.col-lg-8.text-center>b")).html();
                System.out.println(name+"\n");

                //Formats string
                String formattedLyrics = lyrics.replaceFirst("<!-- Usage of azlyrics.com content by any third-party lyrics provider is prohibited by our licensing agreement. Sorry about that. -->", "")
                        .replaceAll("<br>", "");



                //Split the string and print it
                String[] paragraphs = formattedLyrics.split("\n");
                for (String paragraph : paragraphs) {
                    System.out.println(paragraph);
                }
                System.out.println("==========================================================");
            } else {
                System.out.println("The requested song could not be found.");
            }  } catch (Exception e) {
            System.out.println("An error occurred while executing the request: " + e.getMessage());
        }

    }
}
