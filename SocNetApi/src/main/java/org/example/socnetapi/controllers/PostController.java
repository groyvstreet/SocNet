package org.example.socnetapi.controllers;

import org.example.socnetapi.dtos.postdtos.AddPostDto;
import org.example.socnetapi.dtos.postdtos.GetPostDto;
import org.example.socnetapi.dtos.postdtos.UpdatePostDto;
import org.example.socnetapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<GetPostDto>> getPosts() {
        var posts = postService.getPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<GetPostDto> getPostById(@PathVariable UUID id) {
        var post = postService.getPostById(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/posts")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addPost(AddPostDto addPostDto) {
        postService.addPost(addPostDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/posts")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updatePost(UpdatePostDto updatePostDto) {
        postService.updatePost(updatePostDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> removePostById(@PathVariable UUID id) {
        postService.removePostById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<GetPostDto>> getPostsByUserId(@PathVariable UUID id) {
        var posts = postService.getPostsByUserId(id);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
