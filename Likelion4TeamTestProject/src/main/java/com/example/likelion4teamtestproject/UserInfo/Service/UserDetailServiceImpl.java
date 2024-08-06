package com.example.likelion4teamtestproject.UserInfo.Service;

import com.example.likelion4teamtestproject.Post.Entity.Post;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);

        return userInfoRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


    public UserInfo createUserInfo(JSONObject jsonObject) throws JSONException {
        String email = jsonObject.getString("email");

        Optional<UserInfo> userInfoTest = userInfoRepository.findByEmail(email);

        if (userInfoTest.isPresent())
            throw new UsernameNotFoundException(email);


        UserInfo userInfo = UserInfo.builder()
                    .email(jsonObject.getString("email"))
                    .password(passwordEncoder.encode(jsonObject.optString("password")))
                    .name(jsonObject.getString("name"))
                    .build();

        userInfoRepository.save(userInfo);

        return userInfo;
    }

    @Transactional
    public UserInfo updateUserInfo(UserInfo userInfo,JSONObject jsonObject) {
        userInfo.setEmail(jsonObject.getString("email"));
        userInfo.setName(jsonObject.getString("name"));
        return userInfo;
    }

    public void delete(UserInfo userInfo) {
        userInfoRepository.delete(userInfo);
    }

    public UserInfo getUserInfo(String email) throws UsernameNotFoundException {
        return userInfoRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(email));
    }

//    public UserInfo updateUserInfo() {
//
//    }



}
