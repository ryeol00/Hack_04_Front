package com.example.likelion4teamtestproject.Answer.Entity;

import com.example.likelion4teamtestproject.Post.Entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String content;

//    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreationTimestamp
    private LocalDateTime createdDate;


    private String userName;


//    @ManyToOne(fetch = FetchType.LAZY)
//    private Answer parentAnswer;

//    @OneToMany(mappedBy = "parentAnswer",cascade = {CascadeType.PERSIST},orphanRemoval = true)
//    private Collection<Answer> children;


    public Answer(Post post,LocalDateTime createdDate,String content,String userName) {
        this.post = post;
        this.createdDate = createdDate;
        this.content = content;
        this.userName = userName;
//        this.parentAnswer = parentAnswer;
//        this.children = children;
    }

//    public void addChildren(Answer ch) {
//        ch.parentAnswer = this;
//        this.children.add(ch);
//    }


    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",id)
                .put("content",content);

        return jsonObject.toString();
    }

    public static AnswerBuilder builder() {
        return new AnswerBuilder();
    }

    public static class AnswerBuilder {
        private Post post;
        private LocalDateTime createdDate;
//        private Answer parentAnswer;
//        private Collection<Answer> children;
        private String content;
        private String userName;

        AnswerBuilder() {
        }

        public AnswerBuilder post(final Post post) {
            this.post = post;
            return this;
        }

        public AnswerBuilder createdDate(final LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public AnswerBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }


//        public AnswerBuilder parentAnswer(final Answer parentAnswer) {
//            this.parentAnswer = parentAnswer;
//            return this;
//        }

//        public AnswerBuilder children(final Collection<Answer> children) {
//            this.children = children;
//            return this;
//        }

        public AnswerBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public Answer build() {
            return new Answer(this.post, this.createdDate,this.content,this.userName);
        }

        public String toString() {
            return "Answer.AnswerBuilder(post=" + this.post + ", createdDate=" + this.createdDate + ", answerContent=" +", content=" + this.content + ")";
        }
    }

}
