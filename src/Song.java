public class Song {
    private String songName;
    private String songGenre;
    private int songLength;
    private Artist artist;

    /**
     * A constructor for the Song object.
     * It takes in various details about a song and the creates an object based on it.
     * By using the setter methods in the constructor I was able to ensure all the details are valid from the start.
     *
     * @param songName name of Song
     * @param songGenre genre of Song
     * @param songLength the Length of a song.
     * @param artist the Artist that made the song.
     */
    public Song(String songName, String songGenre, int songLength, Artist artist) {
        setSongName(songName);
        setSongGenre(songGenre);
        setSongLength(songLength);
        setArtist(artist);
    }

    //===GETTER METHODS===
    public String getSongName() {return songName;}

    public String getSongGenre() {return songGenre;}

    public int getSongLength() {return songLength;}

    public Artist getArtist() {return artist;}


    /**
     * A setter for the songName.
     * It also includes the validation to ensure that a song name isn't longer than 30 characters.
     *
     * @param songName The name of the song thats being set.
     */
    public void setSongName(String songName) {
        if (songName.length() <= 30) {
            this.songName = songName;
        } else {
            this.songName = songName.substring(0,30);
        }
    }

    /**
     * A setter method which sets the songs genre.
     * It checks whether the inputted genre matches any of the valid genres.
     * If it does, it sets the songs genre. If it doesn't match, the song genre is set to "unknown".#
     *
     * @param songGenre The genre to be set.
     */
    public void setSongGenre(String songGenre) {
        if (isValidSongGenre(songGenre)) {
            this.songGenre = songGenre.toUpperCase();
        } else {
            if (!isValidSongGenre(songGenre)) {
                this.songGenre = "unknown";
            }
        }
    }

    /**
     * A setter method which sets the length of a song.
     * It also makes sure the song is between 10 and 1200 seconds long.
     *
     * @param songLength The length to be set.
     */
    public void setSongLength(int songLength) {
        if(validRange(songLength,10,1200)) {
            this.songLength = songLength;
        }
    }

    /**
     * The final setter method. This one sets the artist of a song.
     * First it checks whether the artist is not null and then sets it.
     *
     * @param artist Artist to be set.
     */
    public void setArtist(Artist artist) {
        if (artist != null) {
            this.artist = artist;
        } else {
            this.artist = new Artist("unknown","invalid format email","unknown");
        }
    }

    /**
     * A method which returns the song as a neatly formatted string.
     *
     * @return A string that gives the song name, artist, duration and genre.
     */
    public String toString1() {
        int minutes = songLength / 60;
        int seconds = songLength % 60;

        return getSongName() +
                ", Artist: " + artist.getArtistName() +
                " (" + minutes + ":" + seconds + ")" +
                ", Genre: " + getSongGenre();

    }


    /**
     * This method checks whether an inputted genre matches any of the valid genres within a list.
     *
     * It creates an array of Strings, each position representing a specific music genre.
     * It then uses a for loop to look over each genre within the array and checks whether
     * it matches with the inputted genre.
     *
     * @param genre The genre to be checked.
     * @return true if the given genre matches any of the genres within the array and false if it doesn't.
     */
    public static boolean isValidSongGenre(String genre) {
        String[] validGenres = {"Rock", "Pop", "Blues", "Rap", "Dance", "Classical"};
        for (int i = 0; i < validGenres.length; i++) {
            if (genre.equalsIgnoreCase(validGenres[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns true if the numberToCheck is between min and max (both inclusive)
     *
     * @param numberToCheck The number whose range is being checked.
     * @param min The minimum range number to check against (inclusive)
     * @param max The maximum range number to check against (inclusive)
     * @return Returns true if the numberToCheck is between min and max (both inclusive), false otherwise.
     */
    public static boolean validRange(int numberToCheck, int min, int max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

}
