package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.photodtos.AddPhotoDto;
import org.example.socnetapi.dtos.photodtos.GetPhotoDto;
import org.example.socnetapi.dtos.photodtos.UpdatePhotoDto;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.PhotoMapper;
import org.example.socnetapi.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;

    @Autowired
    public PhotoService(PhotoRepository photoRepository,
                        PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }

    public List<GetPhotoDto> getPhotos() {
        return photoRepository.findAll().stream().map(photoMapper::photoToGetPhotoDto).toList();
    }

    public GetPhotoDto getPhotoById(UUID id) {
        var photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return photoMapper.photoToGetPhotoDto(photo);
    }

    public void addPhoto(AddPhotoDto addPhotoDto, UUID authenticatedUserId) {
        if (!authenticatedUserId.toString().equals(addPhotoDto.getUserId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var photo = photoMapper.addPhotoDtoToPhoto(addPhotoDto);
        photoRepository.save(photo);
    }

    public void updatePhoto(UpdatePhotoDto updatePhotoDto, UUID authenticatedUserId) {
        var photo = photoRepository.findById(updatePhotoDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.toString().equals(photo.getUser().getId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        photo.setSource(updatePhotoDto.getSource());
        photoRepository.save(photo);
    }

    public void removePhotoById(UUID id, UUID authenticatedUserId) {
        var photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.toString().equals(photo.getUser().getId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        photoRepository.deleteById(id);
    }

    public List<GetPhotoDto> getPhotosByUserId(UUID userId) {
        return photoRepository.findByUserId(userId).stream().map(photoMapper::photoToGetPhotoDto).toList();
    }
}
