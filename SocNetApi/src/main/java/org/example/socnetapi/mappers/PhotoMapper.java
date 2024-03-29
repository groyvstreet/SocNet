package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.photodtos.AddPhotoDto;
import org.example.socnetapi.dtos.photodtos.GetPhotoDto;
import org.example.socnetapi.entities.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    GetPhotoDto photoToGetPhotoDto(Photo photo);

    Photo addPhotoDtoToPhoto(AddPhotoDto addPhotoDto);
}
