package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
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
    private PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
    }

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public void updatePost(Post post) {
        var existingPost = postRepository.findById(post.getId());

        if (existingPost.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        postRepository.save(post);
    }

    public void removePostById(UUID id) {
        var existingPost = postRepository.findById(id);

        if (existingPost.isEmpty()) {
            throw new NotFoundException(Constants.NO_SUCH_ENTITY);
        }

        postRepository.deleteById(id);
    }
}
