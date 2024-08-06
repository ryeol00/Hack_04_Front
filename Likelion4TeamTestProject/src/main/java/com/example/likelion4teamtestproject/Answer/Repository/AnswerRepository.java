package com.example.likelion4teamtestproject.Answer.Repository;

import com.example.likelion4teamtestproject.Answer.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {



}
