package com.example.likelion4teamtestproject.UserInfo.Entity;


import com.example.likelion4teamtestproject.Answer.Entity.Answer;
import com.example.likelion4teamtestproject.Post.Entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo implements UserDetails {

    @Id
    private String email;

    @JsonIgnore
    @JsonProperty("password")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "userInfo")
    private Collection<Post> posts = new ArrayList<>();


    @Builder
    public UserInfo(String email, String password, String name, LocalDateTime createDate, Collection<Post> posts) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.createDate = createDate;
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserInfo userInfo))
            return false;
        return Objects.equals(getEmail(), userInfo.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        JSONObject userInfo = new JSONObject();

        return userInfo.put("email",email)
                .put("name",name)
                .put("createDate",createDate)
                .put("posts",posts)
                .toString();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class UserInfoBuilder {
        private String email;
        private String password;
        private String name;
        private LocalDateTime createDate;
        private Collection<Post> posts;
        private Collection<Answer> answers;

        UserInfoBuilder() {
        }

        public UserInfoBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UserInfoBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserInfoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserInfoBuilder createDate(final LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public UserInfoBuilder posts(final Collection<Post> posts) {
            this.posts = posts;
            return this;
        }

        public UserInfoBuilder answers(final Collection<Answer> answers) {
            this.answers = answers;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this.email, this.password, this.name, this.createDate, this.posts);
        }

        public String toString() {
            return "UserInfo.UserInfoBuilder(email=" + this.email + ", password=" + this.password + ", name=" + this.name + ", createDate=" + this.createDate + ", posts=" + this.posts + ")";
        }
    }


}
