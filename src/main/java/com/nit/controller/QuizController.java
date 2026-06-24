package com.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.dto.QuizT;
import com.nit.entity.Quiz;
import com.nit.exception.NoQuetionFoundException;
import com.nit.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

	@Autowired
	private QuizService service;
	
	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(@RequestBody QuizT quizT) throws NoQuetionFoundException {
	    Quiz quiz = service.createQuiz(quizT.getTitle(), quizT.getCategory(), quizT.getDifficulty(), quizT.getCount());
	    return new ResponseEntity<>(quiz, HttpStatus.CREATED);
	}
	
	@GetMapping("/byid")
	public ResponseEntity<Quiz> getQuizById(@RequestParam Integer id) throws NoQuetionFoundException {
		 Quiz quizById = service.getQuizById(id);
		 return new ResponseEntity<Quiz>(quizById, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Quiz>> getAllQuiz() throws NoQuetionFoundException {
		List<Quiz> allQuiz = service.getAllQuiz();
		return new ResponseEntity<List<Quiz>>(allQuiz, HttpStatus.OK);
		 

	}

	@PostMapping("/submit")
	public ResponseEntity<String> submitQuiz(@RequestParam Integer quizId, @RequestBody List<String> responses) throws NoQuetionFoundException {
		 
		String submitQuiz = service.submitQuiz(quizId, responses);
		return new ResponseEntity<String>(submitQuiz, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteQuiz(@RequestParam Integer quizId) throws NoQuetionFoundException {
		 String deleteQuiz = service.deleteQuiz(quizId);
		 return new ResponseEntity<String>(deleteQuiz, HttpStatus.OK);
	}
}
