package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
//	@Query("select distinct quiz_topic from quiz")
	 @Query("SELECT DISTINCT q.quizTopic FROM Quiz q")
	List<String> getAllDistinctObj();

	List<Quiz> findByQuizTopic(String quizTopic);

}
