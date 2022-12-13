package com.PB.model;

public class Song {
    private String artistName;
    private String name;
    private int auditions;
    private int id;

    public Song(String artistName, String name, int auditions) {
        this.artistName = artistName;
        this.name = name;
        this.auditions = auditions;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuditions() {
        return auditions;
    }

    public void setAuditions(int auditions) {
        this.auditions = auditions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
