package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.postdtos.AddPostDto;
import org.example.socnetapi.dtos.postdtos.GetPostDto;
import org.example.socnetapi.entities.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    GetPostDto postToGetPostDto(Post post);

    Post addPostDtoToPost(AddPostDto addPostDto);
}
