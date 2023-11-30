package com.QuestionMicroService.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.QuestionMicroService.DTO.QuestionDto;
import com.QuestionMicroService.DTO.QuestionWrapperDto;
import com.QuestionMicroService.DTO.ResponseDto;
import com.QuestionMicroService.model.Question;
import com.QuestionMicroService.repository.QuestionRepo;
import com.QuestionMicroService.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionRepo questionRepo;

	@Override
	public QuestionDto addQuestion(QuestionDto questionDto) {
		
		Question qus = new Question();
		
		qus.setTitle(questionDto.getTitle());
		qus.setOption1(questionDto.getOption1());
		qus.setOption2(questionDto.getOption2());
		qus.setOption3(questionDto.getOption3());
		qus.setAnswer(questionDto.getAnswer());
		qus.setCategory(questionDto.getCategory());
		qus.setLevel(questionDto.getLevel());
		
		Question question = questionRepo.save(qus);
		
		return qusDto(question);
	}

	
	private QuestionDto qusDto(Question question) {

		QuestionDto dto = new QuestionDto();
		
		dto.setTitle(question.getTitle());
		dto.setOption1(question.getOption1());
		dto.setOption2(question.getOption2());
		dto.setOption3(question.getOption3());
		dto.setLevel(question.getLevel());
		dto.setAnswer(question.getAnswer());
		dto.setCategory(question.getCategory());
		dto.setId(question.getId());

		return dto;
	}


	@Override
	public List<QuestionDto> getAllQuestions() {

		List<Question> questions = questionRepo.findAll();
		
		List<QuestionDto> newList = new ArrayList();
		
		for(Question qus : questions) {
			
			QuestionDto questionDto = qusDto(qus);
			
			newList.add(questionDto);
		}
		
		return newList;
	}


	@Override
	public List<QuestionDto> getAllByCategory(String category) {

		List<Question> catQuestion = questionRepo.findByCategory(category);
		
		List<QuestionDto> newList = new ArrayList();
		
		for(Question cat : catQuestion) {
			
			QuestionDto questionDto = qusDto(cat);
			
			newList.add(questionDto);
		}
		
		return newList;	
	}


	@Override
	public ResponseEntity<List<Long>> getQuestionsForQuiz(String category, int numOfQuestions) {

		List<Long> questions = questionRepo.findRandomQuestionsByCategory(category, numOfQuestions);
		
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<QuestionWrapperDto>> getQuestionById(List<Long> questionIds) {
		
		List<QuestionWrapperDto> wrappers = new ArrayList<QuestionWrapperDto>();
		
		List<Question> questions = new ArrayList<Question>();
		
		for(Long id : questionIds) {
			
			questions.add(questionRepo.findById(id).get());
						
		}
				
		for(Question question : questions) {
			
			QuestionWrapperDto wrapper = new QuestionWrapperDto();
			
			wrapper.setId(question.getId());
			wrapper.setTitle(question.getTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> getScore(List<ResponseDto> responses) {
		
		int rightAnswer = 0;
				
		for(ResponseDto response : responses) {
			
			Question question = questionRepo.findById(response.getId()).get();
			
			if(response.getResponse().equals(question.getAnswer())){
				
				rightAnswer ++;
			}
		}
				
		return new ResponseEntity<>("Test Result Mark : " +rightAnswer,HttpStatus.OK);
	}

}
