package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.commentdtos.AddCommentDto;
import org.example.socnetapi.dtos.commentdtos.GetCommentDto;
import org.example.socnetapi.dtos.commentdtos.UpdateCommentDto;
import org.example.socnetapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public ResponseEntity<List<GetCommentDto>> getComments() {
        var comments = commentService.getComments();

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<GetCommentDto> getCommentById(@PathVariable UUID id) {
        var comment = commentService.getCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addComment(@RequestBody AddCommentDto addCommentDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        commentService.addComment(addCommentDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateComment(@RequestBody UpdateCommentDto updateCommentDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        commentService.updateComment(updateCommentDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removeCommentById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        commentService.removeCommentById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<Object> getCommentsByPostId(@PathVariable UUID id) {
        var comments = commentService.getCommentsByPostId(id);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
