package com.nit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Quiz;
import com.nit.exception.NoQuetionFoundException;
import com.nit.fiegn.QuestionFiegn;
import com.nit.repository.QuizRepo;

@Service
public class QuizService {

	@Autowired
	private QuizRepo repo;

	@Autowired
	private QuestionFiegn fiegn;

	/*
	 * POST /quiz/create GET /quiz/getbyid?id=1 GET /quiz/all POST /quiz/submit
	 * DELETE /quiz/delete?id=1
	 */

	public Quiz createQuiz(String title, String category, String difficulty, Integer count)
			throws NoQuetionFoundException {
		if (count <= 0) {
			throw new IllegalArgumentException("Count must be greate than zero");

		}

		List<Integer> quetions = fiegn.generateQuestions(category, difficulty, count);

		if (quetions.isEmpty()) {
			throw new NoQuetionFoundException("No Quetion Found");
		}

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setCategory(category);
		quiz.setLevel(difficulty);
		quiz.setQuestionIds(quetions);
		return 	repo.save(quiz);

	}

	public Quiz getQuizById(Integer id) throws NoQuetionFoundException {
		if (repo.existsById(id)) {
			Quiz quiz = repo.findById(id).get();
			return quiz;
		} else {
			throw new NoQuetionFoundException("No Quetion found ");
		}

	}

	public List<Quiz> getAllQuiz() throws NoQuetionFoundException {

		if (repo.count() != 0) {
			return repo.findAll();
		}
		throw new NoQuetionFoundException("NO Quetion found");

	}

	public String submitQuiz(Integer quizId, List<String> responses) throws NoQuetionFoundException {
		int score = 0;
		Quiz quiz = getQuizById(quizId);

		List<String> correctAnswers = fiegn.getCorrectAnswers(
	            quiz.getQuestionIds());

	    for (int i = 0; i < correctAnswers.size(); i++) {

	        if (correctAnswers.get(i).equalsIgnoreCase(responses.get(i))) {
	            score++;
	        }
	    }

	    return "your score is : " +  score;

	}

	public String deleteQuiz(Integer quizId) throws NoQuetionFoundException {
		if(repo.existsById(quizId)) {
			repo.deleteById(quizId);
			return "Quiz deleted Successfully";
		}
		else {
			throw new NoQuetionFoundException("No id is found");
		}
	}
}
