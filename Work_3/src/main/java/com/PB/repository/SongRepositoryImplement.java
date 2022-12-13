package com.PB.repository;

import com.PB.error.SongNotFoundException;
import com.PB.model.Song;
import com.PB.model.Songs;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SongRepositoryImplement implements SongRepository{
    private final Map<Integer, Song> data;
    private final AtomicInteger autoId = new AtomicInteger(1);

    public SongRepositoryImplement() {
        data = new HashMap<>();
    }

    @Override
    public Songs findAll() {
        return new Songs (new ArrayList<>(data.values()));
    }

    @Override
    public Song add(@NonNull Song song){
        int id = autoId.getAndIncrement();
        song.setId(id);
        data.put(id, song);
        return song;
    }

    @Override
    public Song get(int id){
        Song song = data.get(id);
        if (song == null) {
            throw new SongNotFoundException("song not found");
        }
        return song;
    }

    @Override
    public Song update(int id, Song song){
        if(data.get(id) == null){
            throw new SongNotFoundException("song not found");
        }
        song.setId(id);
        data.put(id, song);
        return song;
    }

    @Override
    public Song delete(int id){
        Song song = data.remove(id);
        if (song == null) {
            throw new SongNotFoundException("song not found");
        }
        return song;
    }
}
