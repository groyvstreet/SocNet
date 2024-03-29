package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.Comment;
import org.example.socnetapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments() {
        var comments = commentService.getComments();

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable UUID id) {
        var comment = commentService.getCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Object> addComment(Comment comment) {
        commentService.addComment(comment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/comments")
    public ResponseEntity<Object> updateComment(Comment comment) {
        commentService.updateComment(comment);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Object> removeCommentById(@PathVariable UUID id) {
        commentService.removeCommentById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
