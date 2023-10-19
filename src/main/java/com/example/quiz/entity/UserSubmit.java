package com.example.quiz.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.quiz.entity.UserInfo;

public class UserSubmit {
	UserInfo userinfo;
	private String questionTopic;
	private List<String> listQuestion;
	private List<String> listAnswer;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getQuestionTopic() {
		return questionTopic;
	}

	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}

	public List<String> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<String> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public List<String> getListAnswer() {
		return listAnswer;
	}

	public void setListAnswer(List<String> listAnswer) {
		this.listAnswer = listAnswer;
	}

}
