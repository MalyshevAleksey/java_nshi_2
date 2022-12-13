package com.PB.model;

import java.util.List;

public class ListenSongs {
    private int auditions;
    private List<Integer> songs;

    public int getAuditions() {
        return auditions;
    }

    public void setAuditions(int auditions) {
        this.auditions = auditions;
    }

    public List<Integer> getSongs() {
        return songs;
    }

    public void setSongs(List<Integer> songs) {
        this.songs = songs;
    }
}
