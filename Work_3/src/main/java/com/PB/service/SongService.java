package com.PB.service;

import com.PB.error.SongNotFoundException;
import com.PB.model.Song;
import com.PB.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository repo;

    public Song get(int id) throws SongNotFoundException {
        return repo.get(id);
    }

    public List<Song> getAll(){
        return repo.findAll();
    }

    public Song add(Song song){
        return repo.add(song);
    }

    public Song update(int id, Song song) throws SongNotFoundException{
        return repo.update(id, song);
    }

    public Song delete(int id){
        return repo.delete(id);
    }
}
