package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.photo.AddPhotoDto;
import org.example.socnetapi.dtos.photo.UpdatePhotoDto;
import org.example.socnetapi.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getPhotoById(@PathVariable UUID id) {
        var photo = photoService.getPhotoById(id);

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addPhoto(@RequestBody AddPhotoDto addPhotoDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        photoService.addPhoto(addPhotoDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updatePhoto(@RequestBody UpdatePhotoDto updatePhotoDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        photoService.updatePhoto(updatePhotoDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removePhotoById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        photoService.removePhotoById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Object> getPhotosByUserId(@RequestParam UUID userId) {
        var photos = photoService.getPhotosByUserId(userId);

        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
}
