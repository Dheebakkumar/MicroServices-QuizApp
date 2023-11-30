package com.QuestionMicroService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.QuestionMicroService.DTO.QuestionDto;
import com.QuestionMicroService.DTO.QuestionWrapperDto;
import com.QuestionMicroService.DTO.ResponseDto;


public interface QuestionService {

	public QuestionDto addQuestion(QuestionDto questionDto);
	public List<QuestionDto> getAllQuestions();
	public List<QuestionDto> getAllByCategory(String category);

	
	public ResponseEntity<List<Long>> getQuestionsForQuiz(String category, int numOfQuestions);
	public ResponseEntity<List<QuestionWrapperDto>> getQuestionById(List<Long> questionIds);
	public ResponseEntity<String> getScore(List<ResponseDto> responses);
}
