package com.example.likelion4teamtestproject.Post;


import com.example.likelion4teamtestproject.Answer.AnswerService;
import com.example.likelion4teamtestproject.Answer.Entity.Answer;
import com.example.likelion4teamtestproject.Post.Entity.Post;
import com.example.likelion4teamtestproject.Post.Entity.PostType;
import com.example.likelion4teamtestproject.Post.Service.PostService;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import io.netty.util.internal.shaded.org.jctools.queues.MpmcArrayQueue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageWriter;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final AnswerService answerService;

    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> savePost(@RequestParam String postType, @RequestBody Map<String, Object> json, @AuthenticationPrincipal UserInfo userInfo){
        JSONObject jsonObject = new JSONObject(json);

        Post post = postService.createPOST(jsonObject,PostType.valueOf(postType),userInfo);

        return new ResponseEntity<>(post.toString(),HttpStatus.CREATED);
    }

    @GetMapping(value = "/view",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPost(@RequestParam UUID uuid) {

        Post post = postService.findPost(uuid);

        if (post == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        postService.updateViewCount(post);

        JSONObject jsonObject = new JSONObject(post.toString());
        jsonObject.put("Answers",post.getAnswers());

        return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
    }

    @PostMapping(value = "/view/answer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addAnswer(@RequestParam UUID uuid, @RequestBody Map<String,Object> json) {

        JSONObject jsonObject = new JSONObject(json);

        Post post = postService.findPost(uuid);

        if (post == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        post.setAnswers(answerService.jsonObjectToEntity(jsonObject,post));

        postService.savePost(post);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/view/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePost(@RequestParam UUID uuid,@RequestBody Map<String,Object> json,@AuthenticationPrincipal UserInfo userInfo){
        JSONObject jsonObject = new JSONObject(json);

        Post post = postService.findPost(uuid);

        if (!post.getUserInfo().equals(userInfo))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        postService.update(jsonObject,post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/view/like")
    public ResponseEntity<String> likePost(@RequestParam UUID uuid) {
        Post post = postService.findPost(uuid);
        post.addLike();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/view/delete")
    public ResponseEntity<String> deletePost(@RequestParam UUID uuid,@AuthenticationPrincipal UserInfo userInfo) {
        Post post = postService.findPost(uuid);

        if (post.getUserInfo().equals(userInfo))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        postService.delete(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/challenge/all")
    public ResponseEntity<String> findChallengePostAll(@RequestParam String category) {
         return new ResponseEntity<>(postService.findChallengeAll(category).toString(),HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<String> findPostAll(@RequestParam PostType postType) {
        return new ResponseEntity<>(postService.findInfomationAll(postType).toString(),HttpStatus.OK);
    }


    @GetMapping(value = "/myPage/posts")
    public ResponseEntity<String> findByUserInfoPostAll(@AuthenticationPrincipal UserInfo userInfo) {
        return new ResponseEntity<>(postService.findUserPostAll(userInfo).toString(),HttpStatus.OK);
    }
}
