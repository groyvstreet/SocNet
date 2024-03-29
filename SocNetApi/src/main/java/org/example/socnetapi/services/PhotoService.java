package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
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
    private PhotoRepository photoRepository;

    public List<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(UUID id) {
        return photoRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addPhoto(Photo photo) {
        photoRepository.save(photo);
    }

    public void updatePhoto(Photo photo) {
        var existingPhoto = photoRepository.findById(photo.getId());

        if (existingPhoto.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        photoRepository.save(photo);
    }

    public void removePhotoById(UUID id) {
        var existingPhoto = photoRepository.findById(id);

        if (existingPhoto.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        photoRepository.deleteById(id);
    }
}
