package com.volodymyr.pletnov.blogbe.controller;

import com.volodymyr.pletnov.blogbe.exception.NotFoundException;
import com.volodymyr.pletnov.blogbe.model.Post;
import com.volodymyr.pletnov.blogbe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;

/**
 * There is no specific logic, so I skip service layer
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
@CrossOrigin
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/all")
    public Page<Post> getAll(@PageableDefault Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @GetMapping
    public Post findById(@RequestParam("id") Long id) {
        return postRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        post.setCreationDate(LocalDateTime.now());
       return postRepository.save(post);
    }

    @PutMapping
    public Post update(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id")Long id) {
        postRepository.deleteById(id);
    }
}
