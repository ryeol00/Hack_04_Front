package com.example.likelion4teamtestproject.Post.Service;


import com.example.likelion4teamtestproject.Post.Entity.Post;
import com.example.likelion4teamtestproject.Post.Entity.PostRepository;
import com.example.likelion4teamtestproject.Post.Entity.PostType;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public Post createPOST(JSONObject jsonObject, PostType postType, UserInfo userInfo) {

        Post post = Post.builder()
                .title(jsonObject.getString("title"))
                .content(jsonObject.getString("content"))
                .userInfo(userInfo)
                .category(jsonObject.optString("category"))
                .postType(postType)
                .challengeStartDate((LocalDate) jsonObject.opt("challengeStartDate"))
                .challengeEndDate((LocalDate) jsonObject.opt("challengeEndDate"))
                .tag(jsonObject.optString("tag"))
                .build();

        log.info(post.toString());

        postRepository.save(post);

        return post;
    }


    public Post findPost(UUID id) {
        Optional<Post> opPost = postRepository.findById(id);

        if (opPost.isEmpty())
            return null;
        Post post = opPost.get();

        return post;
    }

    @Transactional
    public Post updateViewCount(Post post) {
        post.addViewCount();
        return post;
    }

    @Transactional
    public void update(JSONObject jsonObject,Post post) {

        post.setTitle(jsonObject.getString("title"));
        post.setContent(jsonObject.getString("content"));
        post.setChallengeEndDate((LocalDate) jsonObject.opt("challengeEndDate"));
        post.setChallengeStartDate((LocalDate) jsonObject.opt("challengeStartDate"));
        post.setTag(jsonObject.optString("tag"));
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findInfomationAll(PostType postType) {
        return postRepository.findAllByPostTypeOrderByCreatedDateDesc(postType);
    }

    public List<Post> findChallengeAll(String category) {
        return postRepository.findAllByCategoryOrderByCreatedDateDesc(category);
    }

    public List<Post> findUserPostAll(UserInfo userInfo) {
        return postRepository.findAllByUserInfoOrderByCreatedDateDesc(userInfo);
    }









}
