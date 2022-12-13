package com.PB.repository;

import com.PB.error.SongNotFoundException;
import com.PB.model.Song;
import com.PB.model.Songs;

import java.util.List;

public interface SongRepository {
    Songs findAll() throws SongNotFoundException;

    Song add(Song song);

    Song get(int id) throws SongNotFoundException;

    Song update(int id, Song song) throws SongNotFoundException;

    Song delete(int id) throws SongNotFoundException;
}
