package com.playlist;

public class Song {
    private int id;
    private String title;
    private String artist;

    public Song(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }

    @Override
    public String toString() {
        return id + ". " + title + " - " + artist;
    }
}
