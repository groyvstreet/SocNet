package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.commentdtos.AddCommentDto;
import org.example.socnetapi.dtos.commentdtos.GetCommentDto;
import org.example.socnetapi.dtos.commentdtos.UpdateCommentDto;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.CommentMapper;
import org.example.socnetapi.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<GetCommentDto> getComments() {
        return commentRepository.findAll().stream().map(commentMapper::commentToGetCommentDto).toList();
    }

    public GetCommentDto getCommentById(UUID id) {
        var comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return commentMapper.commentToGetCommentDto(comment);
    }

    public void addComment(AddCommentDto addCommentDto, UUID authenticatedUserId) {
        if (authenticatedUserId != addCommentDto.getUserId()) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var comment = commentMapper.addCommentDtoToComment(addCommentDto);
        commentRepository.save(comment);
    }

    public void updateComment(UpdateCommentDto updateCommentDto, UUID authenticatedUserId) {
        var comment = commentRepository.findById(updateCommentDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (authenticatedUserId != comment.getUser().getId()) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        comment.setText(updateCommentDto.getText());
        commentRepository.save(comment);
    }

    public void removeCommentById(UUID id, UUID authenticatedUserId) {
        var comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (authenticatedUserId != comment.getUser().getId()) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        commentRepository.deleteById(id);
    }

    public List<GetCommentDto> getCommentsByPostId(UUID postId) {
        return commentRepository.findByPostId(postId).stream().map(commentMapper::commentToGetCommentDto).toList();
    }
}
