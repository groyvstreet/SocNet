package org.example.socnetapi.services;

import org.example.socnetapi.entities.Photo;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository _photoRepository;

    public List<Photo> getPhotos() {
        return _photoRepository.findAll();
    }

    public Photo getPhotoById(UUID id) {
        return _photoRepository.findById(id).orElseThrow(() -> new NotFoundException("no such photo"));
    }

    public void addPhoto(Photo photo) {
        _photoRepository.save(photo);
    }

    public void updatePhoto(Photo photo) {
        var existingPhoto = _photoRepository.findById(photo.getId());

        if (existingPhoto.isEmpty()) {
            throw new NotFoundException("no such photo");
        }

        _photoRepository.save(photo);
    }

    public void removePhotoById(UUID id) {
        var existingPhoto = _photoRepository.findById(id);

        if (existingPhoto.isEmpty()) {
            throw new NotFoundException("no such photo");
        }

        _photoRepository.deleteById(id);
    }
    
}
