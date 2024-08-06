package com.example.likelion4teamtestproject.Post.Entity;


import com.example.likelion4teamtestproject.Answer.Entity.Answer;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column
    private String content;

    @Column
    private int likes;

    private int viewCount;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private LocalDate challengeStartDate;

    private LocalDate challengeEndDate;

    private String tag;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "post",orphanRemoval = true)
    private Collection<Answer> answers;

    @Column
    private String category;

    public static PostBuilder builder() {
        return new PostBuilder();
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setChallengeStartDate(LocalDate challengeStartDate) {
        this.challengeStartDate = challengeStartDate;
    }

    public void setChallengeEndDate(LocalDate challengeEndDate) {
        this.challengeEndDate = challengeEndDate;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public void addLike(){
        this.likes += 1;
    }

    public void setAnswers(Answer answer) {
        this.answers.add(answer);
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",id)
                .put("title",title)
                .put("content",content)
                .put("likes",likes)
                .put("viewCount",viewCount)
                .put("createDate",createdDate)
                .put("postType",postType)
                .put("userInfo",userInfo.getName())
                .put("category",category)
                .put("challengeStartDate",challengeStartDate)
                .put("challengeEndDate",challengeEndDate)
                .put("tag",tag);

        return jsonObject.toString();
    }



    public Post(UUID id, String title, String content, PostType postType, UserInfo userInfo, Collection<Answer> answers, String category,LocalDate challengeStartDate,LocalDate challengeEndDate,String tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.userInfo = userInfo;
        this.answers = answers;
        this.category = category;
        this.challengeStartDate = challengeStartDate;
        this.challengeEndDate = challengeEndDate;
        this.tag = tag;
    }


    public static class PostBuilder {
        private UUID id;
        private String title;
        private String content;
        private PostType postType;
        private UserInfo userInfo;
        private LocalDate challengeStartDate;
        private LocalDate challengeEndDate;
        private String tag;
        private Collection<Answer> answers = new ArrayList<>();
        private String category;

        PostBuilder() {
        }
        public PostBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public PostBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public PostBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public PostBuilder postType(final PostType postType) {
            this.postType = postType;
            return this;
        }

        public PostBuilder userInfo(final UserInfo userInfo) {
            this.userInfo = userInfo;
            return this;
        }

        public PostBuilder answer(final Answer answers) {
            this.answers.add(answers);
            return this;
        }

        public PostBuilder category(final String category) {
            this.category = category;
            return this;
        }

        public PostBuilder challengeStartDate(final LocalDate challengeStartDate) {
            this.challengeStartDate = challengeStartDate;
            return this;
        }
        public PostBuilder challengeEndDate(final LocalDate challengeEndDate) {
            this.challengeEndDate = challengeEndDate;
            return this;
        }

        public PostBuilder tag(final String tag) {
            this.tag = tag;
            return this;
        }

        public Post build() {
            return new Post(this.id,this.title, this.content, this.postType, this.userInfo, this.answers, this.category,this.challengeStartDate,this.challengeEndDate,this.tag);
        }

        public String toString() {
            return "Post.PostBuilder(title=" + this.title + ", content=" + this.content + ", postType=" + this.postType + ", userInfo=" + this.userInfo + ", answers=" + this.answers + ", category=" + this.category + ")";
        }
    }

}
