package com.QuizMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuizMicroService.model.Quiz;


public interface QuizRepo extends JpaRepository<Quiz, Long>{

}
