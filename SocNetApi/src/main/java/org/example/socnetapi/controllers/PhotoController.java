package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.photodtos.AddPhotoDto;
import org.example.socnetapi.dtos.photodtos.GetPhotoDto;
import org.example.socnetapi.dtos.photodtos.UpdatePhotoDto;
import org.example.socnetapi.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos")
    public ResponseEntity<List<GetPhotoDto>> getPhotos() {
        var photos = photoService.getPhotos();

        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<GetPhotoDto> getPhotoById(@PathVariable UUID id) {
        var photo = photoService.getPhotoById(id);

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PostMapping("/photos")
    public ResponseEntity<Object> addPhoto(AddPhotoDto addPhotoDto) {
        photoService.addPhoto(addPhotoDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/photos")
    public ResponseEntity<Object> updatePhoto(UpdatePhotoDto updatePhotoDto) {
        photoService.updatePhoto(updatePhotoDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<Object> removePhotoById(@PathVariable UUID id) {
        photoService.removePhotoById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
