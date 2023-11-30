package com.QuizMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.QuizMicroService.DTO.QuizDtoRequest;
import com.QuizMicroService.DTO.QuizResponseDto;
import com.QuizMicroService.DTO.QuizWrapperDto;
import com.QuizMicroService.DTO.ResponseDto;
import com.QuizMicroService.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService service;
	
	@PostMapping("/create")
	public ResponseEntity<QuizResponseDto> createQuiz(@RequestBody QuizDtoRequest requestDto) {
		
		return service.createQuiz(requestDto);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuizWrapperDto>> getQuizQuestions(@PathVariable long id){
		
		return service.getQuizQuestions(id);
	}
	
	@PostMapping("/{id}/submit")
	public ResponseEntity<String> calculateResult(@PathVariable long id,@RequestBody List<ResponseDto> response) {
		
		return service.calculateResult(id, response);
	}

}
