package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nit.entity.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
