package no.kevin.innlevering3.model;

public class Album
{
    private String tittel;
    private String artist;
    private int spor;
    private int utgitt;
    private String sjanger;

    public Album(String artist, String tittel, int spor, int utgitt, String sjanger)
    {
        setArtist(artist);
        setTittel(tittel);
        setUtgitt(utgitt);
        setSpor(spor);
        setSjanger(sjanger);
    }

    public Album()
    {
        this(null, null, 0, 0, null);
    }

    public void setTittel(String tittel)
    {
        this.tittel = tittel;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public void setSpor(int spor)
    {
        this.spor = spor;
    }

    public void setUtgitt(int utgitt)
    {
        this.utgitt = utgitt;
    }

    public void setSjanger(String sjanger)
    {
        this.sjanger = sjanger;
    }

    public String getTittel()
    {
        return tittel;
    }

    public String getArtist()
    {
        return artist;
    }

    public int getSpor()
    {
        return spor;
    }

    public int getUtgitt()
    {
        return utgitt;
    }

    public String getSjanger()
    {
        return sjanger;
    }

    public String toString()
    {
        return getTittel() + " (" + getArtist() + ")" + " Utgitt: " + getUtgitt();
    }
}