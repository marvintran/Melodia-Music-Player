package comp3350.melodia.objects;

import java.io.File;

public class Song {
    private int songID;
    private String songName;
    private int songTime;
    private Artist artist;
    private Album album;
    private int trackNumber;
    private File songData;

    public Song(int songID, String songName, int songTime, Artist artist, Album album,
                int trackNumber, File songData) {
        this.songID = songID;
        this.songName = songName;
        this.songTime = songTime;
        this.artist = artist;
        this.album = album;
        this.trackNumber = trackNumber;
        this.songData = songData;
    }

    public Song(int songID, String songName, int songTime, Artist artist, Album album,
                int trackNumber, String songLocation) {
        this(songID, songName, songTime, artist, album, trackNumber,
                new File(songLocation));
    }

    public int getSongID(){return songID;}

    public String getSongName() {
        return songName;
    }

    public int getSongTime() {
        return songTime;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public File getSongData() {
        return songData;
    }

}


