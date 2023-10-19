package com.example.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.UserInfo;
import com.example.quiz.entity.UserSubmit;
import com.example.quiz.service.QuizService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class QuizController {
	@Autowired
	QuizService quizService;

	@PostMapping("/addQuestion")
	public Quiz addQuestion(@RequestBody Quiz quiz) {

		return quizService.addQuestion(quiz);
	}

	@PostMapping("/addQuestionList")
	public List<Quiz> addQuestionList(@RequestBody List<Quiz> quiz) {

		for (Quiz q : quiz) {
			System.out.println();
			System.out.println(q.getquizQuestion());
			System.out.println(q.getcorrectAns());
			System.out.println(q.getquizTopic());
		}
		return quizService.addQuestions(quiz);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@DeleteMapping("/deleteQuiz/{id}")
	public ResponseEntity deleteQuiz(@PathVariable int id) {
		System.out.println("In Delet");
		// Assuming the operation was successful, return a success message
		String successMessage = quizService.deleteQuiz(id);

		// You can also return a specific HTTP status code (e.g., 200 OK)
		return ResponseEntity.status(HttpStatus.OK).body(successMessage);
	}

	@GetMapping("/getAllQuestion")
	public List<Quiz> getAllQuestion() {

		return quizService.getAllQuestion();
	}

//	@GetMapping("/getQuestionByTopic/{quizTopic}")
//	public List<Quiz> getQuestionByTopic(@PathVariable String quizTopic){
//		System.out.println("Quize");
//		return quizService.getQuestionByTopic(quizTopic);
//		
//	}
	@GetMapping("/getQuestionByTopic")
	public List<Quiz> getQuestionByTopic(@RequestParam("quizTopic") String quiz) {
		System.out.println("Quize");
		return quizService.getQuestionByTopic(quiz);

	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/submit", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> handleOptionsRequest() {
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/submit")
	public ResponseEntity<String> submitQuestion(@RequestBody UserSubmit userSubmit) {
		quizService.getScore(userSubmit.getListQuestion(), userSubmit.getListAnswer(), userSubmit.getUserinfo().getId(),
				userSubmit.getQuestionTopic());

		// Assuming the operation was successful, return a success message
		String successMessage = "Quiz submitted successfully.";

		// You can also return a specific HTTP status code (e.g., 200 OK)
		return ResponseEntity.status(HttpStatus.OK).body(successMessage);
	}

	@GetMapping("/getAllTopic")
	public List<String> questionAllTopic() {

		return quizService.topic();
	}

}
