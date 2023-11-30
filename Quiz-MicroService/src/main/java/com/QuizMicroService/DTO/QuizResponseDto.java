package com.QuizMicroService.DTO;

import java.util.List;

public class QuizResponseDto {

	private long id;
	
	private String title;
	
	private List<Long> questionIds;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Long> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Long> questionIds) {
		this.questionIds = questionIds;
	}

	public QuizResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
