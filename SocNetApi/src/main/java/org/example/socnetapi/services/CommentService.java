package org.example.socnetapi.services;

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
    private CommentRepository _commentRepository;

    public List<Comment> getComments() {
        return _commentRepository.findAll();
    }

    public Comment getCommentById(UUID id) {
        return _commentRepository.findById(id).orElseThrow(() -> new NotFoundException("no such comment"));
    }

    public void addComment(Comment comment) {
        _commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        var existingComment = _commentRepository.findById(comment.getId());

        if (existingComment.isEmpty()) {
            throw new NotFoundException("no such comment");
        }

        _commentRepository.save(comment);
    }

    public void removeCommentById(UUID id) {
        var existingComment = _commentRepository.findById(id);

        if (existingComment.isEmpty()) {
            throw new NotFoundException("no such comment");
        }

        _commentRepository.deleteById(id);
    }
    
}
