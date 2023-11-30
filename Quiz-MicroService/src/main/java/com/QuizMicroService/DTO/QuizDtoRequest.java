package com.QuizMicroService.DTO;



public class QuizDtoRequest {   

	private int numOfQus;
	
	private String title;
	
	private String category;

	public int getNumOfQus() {
		return numOfQus;
	}

	public void setNumOfQus(int numOfQus) {
		this.numOfQus = numOfQus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public QuizDtoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	
	
	
}
