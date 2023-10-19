package com.example.quiz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Quiz { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String quizQuestion;
	private String option_1;
	private String option_2;

	private String option_3;
	private String option_4;
	private int correctAns;
	private String quizTopic ;
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Quiz( String quizQuestion, String option_1, String option_2, String option_3, String option_4,
			int  correctAns, String quizTopic) {
		super();
//		this.id = id;
		this.quizQuestion = quizQuestion;
		this.option_1 = option_1;
		this.option_2 = option_2;
		this.option_3 = option_3;
		this.option_4 = option_4;
		this. correctAns =  correctAns;
		this.quizTopic = quizTopic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getquizQuestion() {
		return quizQuestion;
	}
	public void setquizQuestion(String quizQuestion) {
		this.quizQuestion = quizQuestion;
	}
	public String getOption_1() {
		return option_1;
	}
	public void setOption_1(String option_1) {
		this.option_1 = option_1;
	}
	public String getOption_2() {
		return option_2;
	}
	public void setOption_2(String option_2) {
		this.option_2 = option_2;
	}
	public String getOption_3() {
		return option_3;
	}
	public void setOption_3(String option_3) {
		this.option_3 = option_3;
	}
	public String getOption_4() {
		return option_4;
	}
	public void setOption_4(String option_4) {
		this.option_4 = option_4;
	}
	public int getcorrectAns() {
		return  correctAns;
	}
	public void setcorrectAns(int  correctAns) {
		this. correctAns =  correctAns;
	}
	public String getquizTopic() {
		return quizTopic;
	}
	public void setquizTopic(String quizTopic) {
		this.quizTopic = quizTopic;
	}
	
	
	

}
