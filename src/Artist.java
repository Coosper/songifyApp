public class Artist {

    private String artistName;
    private String artistEmail;
    private String artistPhone;

    /**
     * The constructor for the Artist Object.
     *
     * @param artistName name of the artist.
     * @param artistEmail the artists email.
     * @param artistPhone
     */
    public Artist(String artistName, String artistEmail, String artistPhone) {
        setArtistName(artistName);
        setArtistEmail(artistEmail);
        setArtistPhone(artistPhone);
    }


    /**
     * A method which grabs the name of an Artist.
     *
     * @return The artists name
     */
    public String getArtistName() {return artistName;}

    /**
     * A method which grabs the Artist email.
     *
     * @return The artists email
     */
    public String getArtistEmail() {return artistEmail;}

    /**
     * A method to grab the Artists phone number.
     *
     * @return The artists phone number
     */
    public String getArtistPhone() {return artistPhone;}

    //===SETTER METHODS===

    /**
     * A method which changes the artists name
     *
     * @param artistName The name to change it to.
     */
    public void setArtistName(String artistName) {
        if (artistName.length() <= 30) {
            this.artistName = artistName;
        } else {
            this.artistName = artistName.substring(0,30);
        }
    }

    /**
     * A method to change the artists email.
     *
     * @param artistEmail The email to change it to.
     */
    public void setArtistEmail(String artistEmail) {
        if (artistEmail.contains("@") && artistEmail.contains(".")) {
            this.artistEmail = artistEmail;
        } else {
            this.artistEmail = "invalid format email";
        }
    }

    /**
     * A method to change the artists phone number.
     *
     * @param artistPhone The phone number to change it to.
     */
    public void setArtistPhone(String artistPhone) {
        if (artistPhone.matches("[0-9]+")) {
            this.artistPhone = artistPhone;
        } else {
            this.artistPhone ="unknown";
        }
    }

    /**
     * A method to nearly format an Artists name, email and phone into a single string.
     *
     * @return The artist name, email and phone in a single string.
     */
    public String toString() {
        return artistName +
                " [Email: " + artistEmail +
                ", Phone: " + artistPhone + "]";
    }

}
