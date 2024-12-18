# SongSearcher

SongSearcher is a simple command-line application that allows users to search for song lyrics using AZLyrics. You can enter the name of a song or artist, and the application will fetch and display the lyrics from AZLyrics.

## Features

- Search for song lyrics by song name or artist.
- Fetch lyrics from AZLyrics and display them in the terminal.
- Handles user input to search multiple songs or stop the application.

## Prerequisites

- Java 17 or later
- Maven for managing dependencies and building the project

## Setup

### Clone the repository
``` console
git clone https://github.com/DmytroPlmnnk/songSearcher.git
cd SongSearcher
```

## Build and run the project
Build the project using Maven:
``` console
mvn exec:java
```

## Dependencies
- JSoup: A Java library for working with HTML.
- JUnit: For unit testing (included but not used in the main application).

## How to Use
- Run the program with the following command:
``` console
mvn exec:java
```

- When prompted, enter the name of the song or artist you want to search for.

- The program will retrieve the song's lyrics from AZLyrics and display them in the terminal.

- After displaying the lyrics, you can choose to search for another song or exit the program.

- To stop, type 1 when asked whether you want to continue, or 0 to search for another song.

## Example
``` console
==========================================================
Enter the name of the song and/or arist:
skepta

Song link: https://www.azlyrics.com/lyrics/skepta/skepta.html
Song text:
==========================================================
"Skepta"

 It's me yo

 See me on the TV yo

 I know you hear me on the radio

 Skepta, I make the crowd go crazy yo

 Come again

 It's me yo

 And I came to party so

 Boy Better Know
 ...
==========================================================
```