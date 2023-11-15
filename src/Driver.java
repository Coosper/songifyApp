import Utilities.EasyScanner;

import java.util.InputMismatchException;

public class Driver {

    private ArtistList artistList;
    private SongList songList;

    public Driver() {
        artistList = new ArtistList();
        songList = new SongList();

        runMenu();
    }

    public static void main(String [] args) {
        new Driver();
    }

    /**
     * A method which displays a menu and takes in a user choice.
     *
     * @return The number of the option to be run.
     */
    private int mainMenu() {
        int choice;
        System.out.println("Songify Menu");
        System.out.println("------------");
        System.out.println("  1) Add an Artist");
        System.out.println("  2) List all Artists");
        System.out.println("  3) Remove an Artist (by index)");
        System.out.println("------------");
        System.out.println("  4) Add a song");
        System.out.println("  5) List all Songs");
        System.out.println("  6) List all Songs in a Genre");
        System.out.println("  7) List all Songs by Artist");
        System.out.println("  8) List Song Statistics");
        System.out.println("  9) Remove a Song (by index)");
        System.out.println("------------");
        System.out.println("  10) Save to XML");
        System.out.println("  11) Load from XML");
        System.out.println("------------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        choice = EasyScanner.nextInt();
        return choice;
    }

    /**
     * This method takes in the choice from mainMenu() and runs whichever method the choice corelates to.
     *
     */
    private void runMenu() {
        int choice = mainMenu();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    addArtist();
                    break;
                case 2:
                    System.out.print(artistList.listOfArtists());
                    break;
                case 3:
                    deleteArtist();
                    break;
                case 4:
                    addSong();
                    break;
                case 5:
                    System.out.print(songList.listOfSongs());
                    break;
                case 6:
                    listSongByGenre();
                    break;
                case 7:
                    listSongByArtist();
                    break;
                case 8:
                    listSongStats();
                    break;
                case 9:
                    deleteSong();
                    break;
                case 10:
                    try {
                        save();
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        load();
                    } catch (Exception e) {
                        System.out.println("Error loading data: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + choice);
                    break;
            }

            System.out.println("\nPress any key to continue...");
            EasyScanner.nextString();

            choice = mainMenu();
        }
        System.out.println("Exiting... Toodles~!");
        System.exit(0);
    }

    /**
     * This method takes in the details of an Artist and creates a new Artist based on it, adding it to the ArrayList
     *
     * @return
     */
    private Artist addArtist() {
        System.out.println("Please enter the following artist details...");
        System.out.print("   Name (Max 30 Characters): ");
        String tempName = EasyScanner.nextString();
        System.out.print("   Email (Must contain @ and .): ");
        String tempEmail = EasyScanner.nextString();
        System.out.print("   Phone (Numbers only): ");
        String tempNumber = EasyScanner.nextString();

        Artist newArtist = new Artist(tempName,tempEmail,tempNumber);
        artistList.addArtist(newArtist);

        return newArtist;
    }

    /**
     * A method which deletes an Artist from the arraylist of Artists.
     */
    private void deleteArtist() {
        System.out.print(artistList.listOfArtists());
        System.out.println();

        if(artistList.numberOfArtists() > 0) {
            System.out.print("Index of artist to delete ==> ");
            int deletionChoice = EasyScanner.nextInt();

            if (artistList.removeArtist(deletionChoice)) {
                System.out.println("Artist Deleted Succesfully!");
            } else {
                System.out.println("There is no artist for this index number.");
            }
        }
    }

    /**
     * This method asks the user for various details of a song, then asks for
     * an artist to assign that song to.
     * Once it has all those details it creates a new Song based on those details and passes it into the Arraylist of Songs
     *
     * IF there are no artists already created, it prompts the user to create a new one then and there.
     *
     * The user can also input -1 for artists index number to create a brand new one.
     */
    private void addSong() {
        System.out.println("Please enter the following song details...");
        System.out.print("   Name (Max 30 Characters): ");
        String tempName = EasyScanner.nextString();
        System.out.print("   Genre (ROCK, POP, BLUES, RAP, DANCE, CLASSICAL): ");
        String tempGenre = EasyScanner.nextString();
        System.out.print("   Length of Song (Minutes): ");
        int tempMinutes = 0;
        try {
            tempMinutes = EasyScanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\rError! The Length of song (minutes) must be a number! Now exiting...");
            return;
        }
        System.out.print("   Length of Song (Seconds): ");

        int tempSeconds = 0;
        try {
            tempSeconds = EasyScanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\rError! The Length of Song (seconds) must be a number! Not exiting...");
            return;
        }
        System.out.println();

        int totalSongLength = (tempMinutes * 60) + tempSeconds;

        if(artistList.numberOfArtists() != 0) {
            System.out.println(artistList.listOfArtists());
            System.out.print("Choose the artist by index number or create a new artist (-1): ");
            int artistChoice = EasyScanner.nextInt();

            if (artistChoice == -1) {
                System.out.println("Creating new artist...");
                System.out.print("   Name (Max 30 Characters): ");
                String tempName2 = EasyScanner.nextString();
                System.out.print("   Email (Must contain @ and .): ");
                String tempEmail2 = EasyScanner.nextString();
                System.out.print("   Phone (Numbers only): ");
                String tempNumber2 = EasyScanner.nextString();

                Artist tempArtist = new Artist(tempName2, tempEmail2, tempNumber2);
                artistList.addArtist(tempArtist);
                songList.addSong(new Song(tempName, tempGenre, totalSongLength, tempArtist ));
                System.out.println();
                System.out.println("New artist and song have been added");
            } else {
                songList.addSong(new Song(tempName, tempGenre, totalSongLength, artistList.getArtist(artistChoice)));
                System.out.print("Song has been added.");
            }
        } else {
            System.out.println("No artists found. Please enter their details...");
            System.out.print("   Name (Max 30 Characters): ");
            String tempName1 = EasyScanner.nextString();
            System.out.print("   Email (Must contain @ and .): ");
            String tempEmail = EasyScanner.nextString();
            System.out.print("   Phone (Numbers only): ");
            String tempNumber = EasyScanner.nextString();

            Artist newArtist = new Artist(tempName1,tempEmail,tempNumber);
            artistList.addArtist(newArtist);

            songList.addSong(new Song(tempName, tempGenre, totalSongLength, newArtist));

            System.out.println();
            System.out.println("New artist and song have been added");
        }

    }

    /**
     * This method lets a user list all the songs by specific genre
     *
     */
    private void listSongByGenre() {
        System.out.println("Genres are ROCK, POP, BLUES, RAP, DANCE AND CLASSICAL");
        System.out.print("   Enter a Genre to Search by: ");
        String chosenGenre = EasyScanner.nextString();

        System.out.println(songList.listSongsBySpecificGenre(chosenGenre));
    }

    /**
     * This method allows the user to search songs by a specific artist.
     */
    private void listSongByArtist() {
        System.out.print("   Enter an artist to search by: ");
        String chosenArtist = EasyScanner.nextString();

        System.out.println(songList.listSongsBySpecificArtist(chosenArtist));
    }

    /**
     * This method lists out all the songs stats.
     */
    private void listSongStats() {
        if (songList.numberOfSongs() == 0) {
            System.out.println("There are no songs saved");
        } else {
            System.out.println("Average Song Length: (" + (songList.averageSongLength() / 60) + ":" + (songList.averageSongLength() % 60) + ")");
            System.out.println("Length of All Songs: (" + (songList.lengthOfAllSongs() / 60) + ":" + (songList.lengthOfAllSongs() % 60) + ")");
            System.out.println("Longest Song: " + songList.longestSong().toString1());
        }
    }

    /**
     * This method deletes a song from the ArrayList
     */
    private void deleteSong() {
        if(songList.numberOfSongs() != 0) {
            System.out.print(songList.listOfSongs());
            System.out.println();
            System.out.print("Index of song to delete ==> ");
            int songDeletionChoice = EasyScanner.nextInt();

            if(songList.removeSong(songDeletionChoice)) {
                System.out.println("Song deleted");
            } else {
                System.out.println("No song by that index");
            }
        } else {
            System.out.println("There are no songs saved.");
        }
    }

    private void save() throws Exception {
        songList.save();
        artistList.save();
        System.out.println();
        System.out.println("Save succesful!");
    }

    private void load() throws Exception {
        songList.load();
        artistList.load();
        System.out.println();
        System.out.println("Load succesful!");
    }








}
