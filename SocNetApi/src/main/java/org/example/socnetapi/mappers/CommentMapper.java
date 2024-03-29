package org.example.socnetapi.mappers;

import org.example.socnetapi.dtos.commentdtos.AddCommentDto;
import org.example.socnetapi.dtos.commentdtos.GetCommentDto;
import org.example.socnetapi.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    GetCommentDto commentToGetCommentDto(Comment comment);

    Comment addCommentDtoToComment(AddCommentDto addCommentDto);
}
