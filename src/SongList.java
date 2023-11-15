import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SongList {

    /**
     * A private arrayList of Song objects.
     */
    private ArrayList<Song> songs;

    /**
     * A method to initialize the empty arrayList of Songs.
     */
    public SongList() {
        this.songs = new ArrayList<Song>();
    }

    /**
     * This method takes in a Song object and adds it into the songs ArrayList.
     *
     * @param song The song to be added.
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * This method returns a Song object from the arraylist based on its index.
     *
     * @param index Index of the song to be returned.
     * @return The Song object at the index or null if the index is wrong.
     */
    public Song getSong(int index) {
        if (index >= 0 && index < songs.size()) {
            return songs.get(index);
        } else {
            return null;
        }
    }

    /**
     * This method removes a song at the specified index.
     *
     * @param index The index of the Song to be removed.
     * @return true if the song was removed succesfully or false if it failed.
     */
    public boolean removeSong(int index) {
        if (index >= 0 && index < songs.size()) {
            songs.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method which returns the total number of songs in an ArrayList.
     *
     * @return The total size of the arraylist.
     */
    public int numberOfSongs() {
        return songs.size();
    }

    /**
     * This method first checks whether there are any songs within the arraylist.
     * If they are it cycles through each song and displays their information in a list.
     *
     * @return The formatted list of all songs.
     */
    public String listOfSongs() {
        if (songs.size() == 0) {
            return "There are no songs in the list.";
        } else {
            String listofSongs = "";
            for (int i = 0; i < songs.size(); i++) {
                listofSongs = listofSongs + i + ": Song: " +
                        songs.get(i).getSongName() + ", Artist: " +
                        songs.get(i).getArtist() + "(" + (songs.get(i).getSongLength() / 60) + ":" + (songs.get(i).getSongLength() % 60) + ")" +
                        ", Genre: " + songs.get(i).getSongGenre() + "\n";

            }
            return listofSongs;
        }
    }

    /**
     * Lists all songs by the inputted genre.
     *
     * @param genre The genre by which to list all songs.
     * @return The details of all songs formatted as a neat string.
     */
    public String listSongsBySpecificGenre(String genre) {
        String specificGenreString = "";
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongGenre().equalsIgnoreCase(genre)) {
                specificGenreString = "Song: " +
                        songs.get(i).getSongName() + ", Artist: " +
                        songs.get(i).getArtist().getArtistName() + " (" + (songs.get(i).getSongLength() / 60) + ":" + (songs.get(i).getSongLength() % 60) + ")" +
                        ", Genre: " + songs.get(i).getSongGenre() + "\n";

            }
        }

        if (specificGenreString.equals("")) {
            if (songs.isEmpty()) {
                return "There are no songs stored in the list.";
            } else {
                return "There are no songs with the genre: " + genre;
            }


        }
        return specificGenreString;
    }

    /**
     * Lists all songs by the inputted genre.
     *
     * @param artist The artist by which to list all songs.
     * @return The details of all songs formatted as a neat string.
     */
    public String listSongsBySpecificArtist(String artist) {
        String specificArtistString = "";
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getArtist().getArtistName().toLowerCase().contains(artist)) {
                specificArtistString += "Song: " +
                        songs.get(i).getSongName() + ", Artist: " +
                        songs.get(i).getArtist() + "(" + (songs.get(i).getSongLength() / 60) + ":" + (songs.get(i).getSongLength() % 60) + ")" +
                        ", Genre: " + songs.get(i).getSongGenre() + "\n";

            }
        }

        if (specificArtistString.equals("")) {
            if (songs.isEmpty()) {
                return "There are no songs stored in the list.";
            } else {
                return "There are no songs for the artist: " + artist;
            }


        }
        return specificArtistString;
    }

    /**
     * A method which returns the longest song within the ArrayList.
     *
     * @return The longest song within the Arraylist
     */
    public Song longestSong() {
        if (songs.isEmpty()) {
            return null;
        }

        Song longestSong = songs.get(0);

        for (int i = 1; i < songs.size(); i++) {
            if (songs.get(i).getSongLength() > longestSong.getSongLength()) {
                longestSong = songs.get(i);
            }
        }
        return longestSong;
    }

    /**
     * A method which gets the average of all songs in the ArrayList.
     *
     * @return The average song length.
     */
    public double averageSongLength() {
        if (songs.isEmpty()) {
            return 0;
        }
        int totalSongLength = 0;
        for (int i = 0; i < songs.size(); i++) {
            totalSongLength += songs.get(i).getSongLength();
        }
        return (totalSongLength / songs.size());
    }

    /**
     * A method which adds up the length of all songs.
     *
     * @return The total length of all songs.
     */
    public double lengthOfAllSongs() {
        if (songs.isEmpty()) {
            return 0;
        }

        int totalSongLength = 0;
        for (int i = 0; i < songs.size(); i++) {
            totalSongLength += songs.get(i).getSongLength();
        }
        return totalSongLength;
    }

    public void load() throws Exception {
        Class<?>[] classes = new Class[]
                {
                        Song.class
                };

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("songs.xml"));
        songs = (ArrayList<Song>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("songs.xml"));
        out.writeObject(songs);
        out.close();
    }



}
