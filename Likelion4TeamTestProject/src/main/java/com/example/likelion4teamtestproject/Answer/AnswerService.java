package com.example.likelion4teamtestproject.Answer;


import com.example.likelion4teamtestproject.Answer.Entity.Answer;
import com.example.likelion4teamtestproject.Answer.Repository.AnswerRepository;
import com.example.likelion4teamtestproject.Post.Entity.Post;
import com.example.likelion4teamtestproject.UserInfo.Entity.UserInfo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer jsonObjectToEntity(JSONObject jsonObject, Post post) {
        System.out.println(post);


        Answer answer = Answer.builder()
                .post(post)
                .content(jsonObject.getString("content"))
                .build();

        answerRepository.save(answer);

        return answer;
    }


}
