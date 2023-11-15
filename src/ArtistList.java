import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArtistList {

    /**
     * This is a private ArrayList which stores Artist Objects
     */
    private ArrayList<Artist> artists;


    /**
     * Initializes the empty Arraylist of Artists.
     */
    public ArtistList() {
        this.artists = new ArrayList<Artist>();
    }


    /**
     * A method which adds an Artist object into the ArrayList
     *
     * @param artist The artist object thats to be added.
     */
    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    /**
     * Returns an Artist Object from the Arraylist based on its index.
     *
     * @param index The index of the Artist that's to be returned.
     * @return The artist object at the index or null if the index is wrong.
     */
    public Artist getArtist(int index) {
        if (index >= 0 && index < artists.size()) {
            return artists.get(index);
        } else {
            return null;
        }
    }

    /**
     * A method to return the total number of artists in the Arraylist
     *
     * @return Returns the total size of the Arraylist called artists.
     */
    public int numberOfArtists() {
        return artists.size();
    }

    /**
     *  A method which removes an artist at the specified index.
     *
     * @param index The specific index of the Artist to be removed.
     * @return True if the artist was removed succesfully or false if it failed.
     */
    public boolean removeArtist(int index) {
        if (index >= 0 && index < artists.size()) {
            artists.remove(index);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method first checks whether there are any artists within the arraylist.
     * If there are, it cycles through each artist,
     * displaying their position in the arraylist, name, email and phone number.
     *
     * @return The formatted list of all the artists.
     */
    public String listOfArtists() {
        if (artists.size() == 0) {
            return "There are no artists in the list.";
        } else {
            String listofArtists = "";
            for (int i = 0; i < artists.size(); i++) {
                listofArtists = listofArtists + i + ": " +
                        artists.get(i).getArtistName() + " [Email: " +
                        artists.get(i).getArtistEmail() + ", Phone: " +
                        artists.get(i).getArtistPhone() + "]" + "\n";

            }
            return listofArtists;
        }
    }

    public void load() throws Exception {
        Class<?>[] classes = new Class[]
                {
                        Artist.class
                };

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("artists.xml"));
        artists = (ArrayList<Artist>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("artists.xml"));
        out.writeObject(artists);
        out.close();
    }



}
