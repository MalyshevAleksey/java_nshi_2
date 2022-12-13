package com.PB.repository;

import com.PB.error.SongNotFoundException;
import com.PB.model.Song;

import java.util.List;

public interface SongRepository {
    List<Song> findAll() throws SongNotFoundException;

    Song add(Song song);

    Song get(int id) throws SongNotFoundException;

    Song update(int id, Song song) throws SongNotFoundException;

    Song delete(int id) throws SongNotFoundException;
}
