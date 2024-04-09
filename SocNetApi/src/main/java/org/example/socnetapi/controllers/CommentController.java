package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.comment.AddCommentDto;
import org.example.socnetapi.dtos.comment.UpdateCommentDto;
import org.example.socnetapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCommentById(@PathVariable UUID id) {
        var comment = commentService.getCommentById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addComment(@RequestBody AddCommentDto addCommentDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        commentService.addComment(addCommentDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateComment(@RequestBody UpdateCommentDto updateCommentDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        commentService.updateComment(updateCommentDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removeCommentById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        commentService.removeCommentById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Object> getCommentsByPostId(@RequestParam UUID postId) {
        var comments = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
