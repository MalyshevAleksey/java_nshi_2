package com.PB.controller;

import com.PB.error.SongNotFoundException;
import com.PB.error.SongValidateException;
import com.PB.model.CreateSong;
import com.PB.model.Song;
import com.PB.model.Songs;
import com.PB.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/songs",
        produces = MimeTypeUtils.APPLICATION_JSON_VALUE
)
public class SongController {

    private SongService service;

    @Autowired
    public void setService(SongService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public Song getSong(@PathVariable int id){
        return service.get(id);
    }

    @GetMapping
    public Songs getAll(){
        return service.getAll();
    }

    @PostMapping
    public Song add(@RequestBody CreateSong nsong){
        Song song = new Song(nsong.getArtistName(), nsong.getName(), nsong.getAuditions());
        validate(song);
        return service.add(song);
    }

    @PutMapping(path = "/{id}")
    public Song update(@PathVariable int id,@RequestBody CreateSong nsong){
        Song song = new Song(nsong.getArtistName(), nsong.getName(), nsong.getAuditions());
        validate(song);
        return service.update(id, song);
    }

    @DeleteMapping(path = "/{id}")
    public Song delete(@PathVariable int id){
        return service.delete(id);
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(SongNotFoundException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(SongValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleNotFoundException(SongValidateException ex) {
        return new Error(ex.getMessage());
    }

    private void validate(Song song){
        if (song == null){
            throw new SongValidateException("song is null");
        }
        if(song.getArtistName() == null || song.getName() == null){
            throw new SongValidateException("field of song is null");
        }
        if(song.getName().replace(" ", "").equals("") || song.getArtistName().replace(" ", "").equals("") || song.getId() < 0){
            throw new SongValidateException("invalid field");
        }
    }
}
