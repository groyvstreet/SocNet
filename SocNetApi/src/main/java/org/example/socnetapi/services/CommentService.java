package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.entities.Comment;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        var existingComment = commentRepository.findById(comment.getId());

        if (existingComment.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        commentRepository.save(comment);
    }

    public void removeCommentById(UUID id) {
        var existingComment = commentRepository.findById(id);

        if (existingComment.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        commentRepository.deleteById(id);
    }
}
