package com.QuestionMicroService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.QuestionMicroService.model.Question;


public interface QuestionRepo extends JpaRepository<Question, Long> {

	List<Question> findByCategory(String category);

	@Query(value ="SELECT q.question_id FROM questionsdb.question q Where q.category =:category ORDER BY RAND () limit :count",nativeQuery = true)
	List<Long> findRandomQuestionsByCategory(String category, int count);
 
}
