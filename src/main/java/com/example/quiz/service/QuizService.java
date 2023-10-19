package com.example.quiz.service;

import com.example.quiz.entity.*;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.quiz.repository.QuizRepository;
import com.example.quiz.repository.UserRepository;

@Service
public class QuizService {
	@Autowired
	QuizRepository ref;

	@Autowired
	UserRepository userRepository;

	public Quiz addQuestion(Quiz quiz) {
		return ref.save(quiz);
	}

	public String deleteQuiz(int id) {
		ref.deleteById(id);
		return "Successfully Deleted" ;
	}

	public List<Quiz> addQuestions(List<Quiz> quiz) {
		return ref.saveAll(quiz);

	}

	public List<Quiz> getAllQuestion() {
		return ref.findAll();
	}

	public List<Quiz> getQuestionByTopic(String quizTopic) {
//		return ref.(quiz_topc);
		return ref.findByQuizTopic(quizTopic);
	}

	public int getScore(List<String> listQuestion, List<String> userSubmitAns, int userId, String quizTopic) {
		List<Quiz> questUnderTopic = ref.findByQuizTopic(quizTopic);
		
		String email=null;
		String userName=null;
		
		Optional<UserInfo> userOpt = userRepository.findById(userId);
		
		if (userOpt.isPresent()) {
			UserInfo user = userOpt.get();
			email=user.getEmail();
			userName=user.getuserName();

		}

		System.out.println();
		int correctAns = 0;
		int totalQuestion = questUnderTopic.size();
		for (int i = 0; i < questUnderTopic.size(); i++) {
			Quiz question = questUnderTopic.get(i);
			String actualAns = String.valueOf(question.getcorrectAns());
			if (userSubmitAns.get(i).equals(actualAns)) {
				System.out.println("Correct ans for question " + listQuestion.get(i));
				correctAns++;
			} else {
				System.out.println("Wrong ans for this Question " + listQuestion.get(i));
			}
		}
		
		sendReportMail(email, userName, quizTopic, totalQuestion, correctAns);

		return 0;
	}

	public void sendReportMail(String toEmail, String userName, String quizName, int totalQuestions,
			int correctAnswers) {
		// Sender's email address and password
		final String fromEmail = "pankaj.a.joshi@slrtce.in";
		final String password = "uzdm eipw skgb svub"; // Your email password

		int incorrectAnswers = totalQuestions - correctAnswers;

		double percentageScore = (double) correctAnswers / totalQuestions * 100.0;

		String template = "Hi %s,\n\nCongratulations on completing the %s quiz! Here are your results:\n\n"
				+ "* Total number of questions: %d\n" + "* Number of correct answers: %d\n"
				+ "* Number of incorrect answers: %d\n" + "* Percentage score: %.2f%%\n\n"
				+ "We hope you enjoyed the quiz!\n\nSincerely,\nPankaj Joshi-Project Team Lead";

		String dynamicString = String.format(template, userName, quizName, totalQuestions, correctAnswers,
				incorrectAnswers, percentageScore);

		System.out.println(dynamicString);

		// Setup properties for the SMTP server
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com"); // For Gmail
		properties.put("mail.smtp.port", "587"); // Port for TLS

		// Create a Session object with the email credentials
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {
			// Create a MimeMessage object
			Message message = new MimeMessage(session);

			// Set the sender's email address
			message.setFrom(new InternetAddress(fromEmail));

			// Set the recipient's email address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

			// Set the subject of the email
			message.setSubject(" Quiz Report for " + userName);

			// Set the content of the email
			message.setText(dynamicString);

			// Send the email
			Transport.send(message);

			System.out.println("Email sent successfully!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public List<String> topic() {
	
		return ref.getAllDistinctObj();
	}
}
