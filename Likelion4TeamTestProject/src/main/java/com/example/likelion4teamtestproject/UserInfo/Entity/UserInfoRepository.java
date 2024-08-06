package com.example.likelion4teamtestproject.UserInfo.Entity;

import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    Optional<UserInfo> findByEmail(String email);

}
