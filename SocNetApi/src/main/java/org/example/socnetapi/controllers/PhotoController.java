package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.photodtos.AddPhotoDto;
import org.example.socnetapi.dtos.photodtos.GetPhotoDto;
import org.example.socnetapi.dtos.photodtos.UpdatePhotoDto;
import org.example.socnetapi.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addPhoto(@RequestBody AddPhotoDto addPhotoDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        photoService.addPhoto(addPhotoDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/photos")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updatePhoto(@RequestBody UpdatePhotoDto updatePhotoDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        photoService.updatePhoto(updatePhotoDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/photos/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removePhotoById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        photoService.removePhotoById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{id}/photos")
    public ResponseEntity<Object> getPhotosByUserId(@PathVariable UUID id) {
        var photos = photoService.getPhotosByUserId(id);

        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
}
