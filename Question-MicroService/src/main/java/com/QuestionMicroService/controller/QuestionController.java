package com.QuestionMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.QuestionMicroService.DTO.QuestionDto;
import com.QuestionMicroService.DTO.QuestionWrapperDto;
import com.QuestionMicroService.DTO.ResponseDto;
import com.QuestionMicroService.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService service;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/add")
	public QuestionDto addQuestion(@RequestBody QuestionDto questionDto) {
		
		return service.addQuestion(questionDto);
	}
	
	@GetMapping("/getAll")
	public List<QuestionDto> getAllQuestions(){
				
		return service.getAllQuestions();
	}
	
	@GetMapping("/getCategory")
	public List<QuestionDto> getCategory(@RequestParam String category){
		
		return service.getAllByCategory(category);
	}
	
	@GetMapping("/generate/{category}/{numOfQuestions}")
	public ResponseEntity<List<Long>> getQuestionsForQuiz(@PathVariable String category, @PathVariable int numOfQuestions){
		
		return service.getQuestionsForQuiz(category, numOfQuestions);
		
	}
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapperDto>> getQuestionById(@RequestBody List<Long> questionIds) {
	
		System.out.println("current server : "+environment.getProperty("local.server.port"));

		return service.getQuestionById(questionIds);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<String> getScore(@RequestBody List<ResponseDto> responses) {

		return service.getScore(responses);
	}
	
}
