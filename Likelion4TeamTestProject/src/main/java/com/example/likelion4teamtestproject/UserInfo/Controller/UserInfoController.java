package com.example.likelion4teamtestproject.UserInfo.Controller;

import com.example.likelion4teamtestproject.Post.Entity.Post;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import com.example.likelion4teamtestproject.UserInfo.Service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserInfoController {

    private final UserDetailServiceImpl userDetailsService;

    @PostMapping(value = "/user/signUp",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrSetUserInfo(@RequestBody Map<String,Object> json) {
        JSONObject jsonObject = new JSONObject(json);

        UserInfo userInfo = userDetailsService.createUserInfo(jsonObject);

        return new ResponseEntity<>(userInfo.toString(), HttpStatus.CREATED);
    }


    @GetMapping(value = "/myPage/info",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userInfoLoad(@AuthenticationPrincipal UserInfo userInfo) {
        return new ResponseEntity<>(userInfo.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/myPage/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUserInfo(@AuthenticationPrincipal UserInfo userInfo,@RequestBody Map<String,Object> json) {
        JSONObject jsonObject = new JSONObject(json);
        userDetailsService.updateUserInfo(userInfo,jsonObject);

        return new ResponseEntity<>(HttpStatus.OK);
    }




//    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> mainPage() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION,"/main");
//
//        return new ResponseEntity<>(headers,HttpStatus.CREATED);
//    }

    @DeleteMapping(value = "/myPage/delete")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal UserInfo userInfo) {
        userDetailsService.delete(userInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
