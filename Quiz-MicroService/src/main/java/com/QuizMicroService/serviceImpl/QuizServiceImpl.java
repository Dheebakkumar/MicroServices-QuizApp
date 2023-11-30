package com.QuizMicroService.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.QuizMicroService.DTO.QuizDtoRequest;
import com.QuizMicroService.DTO.QuizResponseDto;
import com.QuizMicroService.DTO.QuizWrapperDto;
import com.QuizMicroService.DTO.ResponseDto;
import com.QuizMicroService.Feign.QuizInterface;
import com.QuizMicroService.model.Quiz;
import com.QuizMicroService.repository.QuizRepo;
import com.QuizMicroService.service.QuizService;


@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuizInterface interfaceRepo;

	@Override
	public ResponseEntity<QuizResponseDto> createQuiz(QuizDtoRequest requestDto ) {

		List<Long> questions = interfaceRepo.getQuestionsForQuiz(requestDto.getCategory(),requestDto.getNumOfQus()).getBody();

		Quiz quiz = new Quiz();
		quiz.setQuizTitle(requestDto.getTitle());
		quiz.setQuestionIds(questions);
		
		Quiz qz = quizRepo.save(quiz);
		
		QuizResponseDto dto = new QuizResponseDto();
		
		dto.setId(qz.getId());
		dto.setTitle(qz.getQuizTitle());
		dto.setQuestionIds(qz.getQuestionIds());
		
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}

	
	@Override
	public ResponseEntity<List<QuizWrapperDto>> getQuizQuestions(long id) {

		Quiz quiz = quizRepo.findById(id).get();
		
		List<Long> questionIds = quiz.getQuestionIds();
		
		List<QuizWrapperDto> questions = interfaceRepo.getQuestionById(questionIds).getBody();
		
		return new ResponseEntity<>(questions,HttpStatus.OK);
		
	}

	
	@Override
	public ResponseEntity<String> calculateResult(long id, List<ResponseDto> responses) {

		String score = interfaceRepo.getScore(responses).getBody();
		
		return new ResponseEntity<>("Score : " + score,HttpStatus.OK);
		
	}

	

}
