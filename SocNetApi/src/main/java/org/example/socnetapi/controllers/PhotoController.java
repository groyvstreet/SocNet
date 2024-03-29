package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.Photo;
import org.example.socnetapi.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/photos")
    public ResponseEntity<List<Photo>> getPhotos() {
        var photos = photoService.getPhotos();

        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable UUID id) {
        var photo = photoService.getPhotoById(id);

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PostMapping("/photos")
    public ResponseEntity<Object> addPhoto(Photo photo) {
        photoService.addPhoto(photo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/photos")
    public ResponseEntity<Object> updatePhoto(Photo photo) {
        photoService.updatePhoto(photo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<Object> removePhotoById(@PathVariable UUID id) {
        photoService.removePhotoById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
