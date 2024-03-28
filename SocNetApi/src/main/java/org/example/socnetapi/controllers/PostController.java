package org.example.socnetapi.controllers;

import org.example.socnetapi.entities.Post;
import org.example.socnetapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    @Autowired
    private PostService _postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        var posts = _postService.getPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable UUID id) {
        var post = _postService.getPostById(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Object> addPost(Post post) {
        _postService.addPost(post);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/posts")
    public ResponseEntity<Object> updatePost(Post post) {
        _postService.updatePost(post);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> removePostById(@PathVariable UUID id) {
        _postService.removePostById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
