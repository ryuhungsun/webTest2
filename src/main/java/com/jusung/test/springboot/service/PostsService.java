package com.jusung.test.springboot.service;

import com.jusung.test.springboot.domain.posts.Posts;
import com.jusung.test.springboot.domain.posts.PostsRepository;
import com.jusung.test.springboot.web.dto.PostsResponseDto;
import com.jusung.test.springboot.web.dto.PostsSaveRequestDto;
import com.jusung.test.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        posts.update( requestDto.getTitle(), requestDto.getContent() );

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        return  new PostsResponseDto(entity);
    }

}
