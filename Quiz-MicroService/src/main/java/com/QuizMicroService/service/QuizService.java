package com.QuizMicroService.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.QuizMicroService.DTO.QuizDtoRequest;
import com.QuizMicroService.DTO.QuizResponseDto;
import com.QuizMicroService.DTO.QuizWrapperDto;
import com.QuizMicroService.DTO.ResponseDto;


public interface QuizService {

	public ResponseEntity<QuizResponseDto> createQuiz( QuizDtoRequest requestDto);
	
	public ResponseEntity<List<QuizWrapperDto>> getQuizQuestions(long id);

	ResponseEntity<String> calculateResult(long id, List<ResponseDto> responses);

	
}
