package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.post.AddPostDto;
import org.example.socnetapi.dtos.post.UpdatePostDto;
import org.example.socnetapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getPostById(@PathVariable UUID id) {
        var post = postService.getPostById(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addPost(@RequestBody AddPostDto addPostDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        postService.addPost(addPostDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updatePost(@RequestBody UpdatePostDto updatePostDto, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        postService.updatePost(updatePostDto, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removePostById(@PathVariable UUID id, Principal principal) {
        var authenticatedUserId = UUID.fromString(principal.getName());
        postService.removePostById(id, authenticatedUserId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Object> getPostsByUserId(@RequestParam UUID userId) {
        var posts = postService.getPostsByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
