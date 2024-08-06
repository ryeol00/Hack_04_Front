package com.example.likelion4teamtestproject.Post.Entity;

import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post,UUID> {

    Optional<Post> findByTitle(String Title);


    Optional<Post> findById(UUID uuid);

    List<Post> findAllByPostTypeOrderByCreatedDateDesc(PostType postType);

    List<Post> findAllByCategoryOrderByCreatedDateDesc(String category);

    List<Post> findAllByUserInfoOrderByCreatedDateDesc(UserInfo userInfo);


    @Override
    void deleteById(UUID uuid);
}
