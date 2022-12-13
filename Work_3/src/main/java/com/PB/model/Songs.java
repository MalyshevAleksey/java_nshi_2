package com.PB.model;

import java.util.List;

public class Songs {
    List<Song> songs;

    public Songs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}