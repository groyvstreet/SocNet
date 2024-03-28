package org.example.socnetapi.services;

import org.example.socnetapi.entities.Post;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository _postRepository;

    public List<Post> getPosts() {
        return _postRepository.findAll();
    }

    public Post getPostById(UUID id) {
        return _postRepository.findById(id).orElseThrow(() -> new NotFoundException("no such post"));
    }

    public void addPost(Post post) {
        _postRepository.save(post);
    }

    public void updatePost(Post post) {
        var existingPost = _postRepository.findById(post.getId());

        if (existingPost.isEmpty()) {
            throw new NotFoundException("no such post");
        }

        _postRepository.save(post);
    }

    public void removePostById(UUID id) {
        var existingPost = _postRepository.findById(id);

        if (existingPost.isEmpty()) {
            throw new NotFoundException("no such post");
        }

        _postRepository.deleteById(id);
    }
    
}
