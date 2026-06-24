package com.nit.fiegn;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuestionFiegn {

	    @GetMapping("/quetions/generate")
	    List<Integer> generateQuestions(@RequestParam String category,
	    								@RequestParam String difficulty,
	    								@RequestParam Integer count);

	    @GetMapping("/quetions/correctans")
	    List<String> getCorrectAnswers(@RequestParam List<Integer> questionIds);
	
	
}
