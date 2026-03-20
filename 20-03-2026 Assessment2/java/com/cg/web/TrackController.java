package com.cg.web;

import com.cg.entity.Track;
import com.cg.repo.ITrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private ITrackRepository repository;

    // 1 Add Track
    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        repository.save(track);
        return new ResponseEntity<>("Track added successfully", HttpStatus.CREATED);
    }

    // 2 Get All Tracks
    @GetMapping
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 3️ Get Tracks By Title
    @GetMapping("/bytitle/{title}")
    public ResponseEntity<List<Track>> getTracksByTitle(@PathVariable String title) {
        List<Track> list = repository.findByTitle(title);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 4️ Get Track By ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Long id) {
        Optional<Track> track = repository.findById(id);

        if (track.isPresent()) {
            return new ResponseEntity<>(track.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }
}