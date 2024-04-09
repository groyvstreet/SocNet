package org.example.socnetapi.services;

import org.example.socnetapi.constants.Constants;
import org.example.socnetapi.dtos.post.AddPostDto;
import org.example.socnetapi.dtos.post.GetPostDto;
import org.example.socnetapi.dtos.post.UpdatePostDto;
import org.example.socnetapi.exceptions.ForbiddenException;
import org.example.socnetapi.exceptions.NotFoundException;
import org.example.socnetapi.mappers.PostMapper;
import org.example.socnetapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<GetPostDto> getPosts() {
        return postRepository.findAll().stream().map(postMapper::postToGetPostDto).toList();
    }

    public GetPostDto getPostById(UUID id) {
        var post = postRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        return postMapper.postToGetPostDto(post);
    }

    public void addPost(AddPostDto addPostDto, UUID authenticatedUserId) {
        if (!authenticatedUserId.toString().equals(addPostDto.getUserId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        var post = postMapper.addPostDtoToPost(addPostDto);
        postRepository.save(post);
    }

    public void updatePost(UpdatePostDto updatePostDto, UUID authenticatedUserId) {
        var post = postRepository.findById(updatePostDto.getId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.toString().equals(post.getUser().getId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        post.setText(updatePostDto.getText());
        postRepository.save(post);
    }

    public void removePostById(UUID id, UUID authenticatedUserId) {
        var post = postRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.toString().equals(post.getUser().getId().toString())) {
            throw new ForbiddenException(Constants.FORBIDDEN);
        }

        postRepository.deleteById(id);
    }

    public List<GetPostDto> getPostsByUserId(UUID userId) {
        return postRepository.findByUserId(userId).stream().map(postMapper::postToGetPostDto).toList();
    }
}
