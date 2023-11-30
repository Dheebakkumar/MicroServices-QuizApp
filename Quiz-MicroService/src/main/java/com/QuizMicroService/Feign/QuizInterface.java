package com.QuizMicroService.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.QuizMicroService.DTO.QuizWrapperDto;
import com.QuizMicroService.DTO.ResponseDto;


@FeignClient("QUESTION-MICROSERVICE")
public interface QuizInterface {

	@GetMapping("question/generate/{category}/{numOfQuestions}")
	public ResponseEntity<List<Long>> getQuestionsForQuiz(@PathVariable String category, @PathVariable int numOfQuestions);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuizWrapperDto>> getQuestionById(@RequestBody List<Long> questionIds) ;
	
	@PostMapping("question/getScore")
	public ResponseEntity<String> getScore(@RequestBody List<ResponseDto> responses) ;
}
