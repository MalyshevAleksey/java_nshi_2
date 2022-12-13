package com.PB.controller;

import com.PB.error.SongNotFoundException;
import com.PB.error.SongValidateException;
import com.PB.model.ListenSong;
import com.PB.model.ListenSongs;
import com.PB.model.Song;
import com.PB.model.Songs;
import com.PB.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/songs")
public class ListenController {

    private SongService service;

    @Autowired
    public void setService(SongService service) {
        this.service = service;
    }

    @PostMapping("/{id}/listen")
    public Song play(@PathVariable int id, @RequestBody ListenSong lsong){
        Song song = service.get(id);
        song.setAuditions(song.getAuditions() + lsong.getAuditions());
        return song;
    }

    @GetMapping("/listen")
    public List<Song> getSortedSongsByAuditions(@RequestParam(name = "limit", defaultValue = "5") int limit){
        if (limit < 1) {
            throw new SongNotFoundException("limit can not be null or less than one");
        }

        Songs songs = service.getAll();
        songs.getSongs().sort((a, b) -> b.getAuditions() - a.getAuditions());
        return songs.getSongs().subList(0, Math.min(limit, songs.getSongs().size()));
    }

    @PostMapping("/listen")
    public List<Song> listenSongByIds(@RequestBody ListenSongs listenSongs){

        List<Song> songList = new ArrayList<>();
        for (int sng: listenSongs.getSongs()) {
            Song song;
            try {
                song = service.get(sng);
                song.setAuditions(song.getAuditions() + listenSongs.getAuditions());
                service.update(sng, song);
            } catch (SongNotFoundException ex) {
                continue;
            }

            songList.add(song);
        }

        return songList;
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public com.PB.responses.Error handleNotFoundException(SongNotFoundException ex) {
        return new com.PB.responses.Error(ex.getMessage());
    }

    @ExceptionHandler(SongValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.PB.responses.Error handleNotFoundException(SongValidateException ex) {
        return new com.PB.responses.Error(ex.getMessage());
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
