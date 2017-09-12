package com.debbie.dojooverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.debbie.dojooverflow.models.Answer;
import com.debbie.dojooverflow.repositories.AnswerRepo;

@Service
public class AnswerService {
	private AnswerRepo answerRepo;
	
	public AnswerService(AnswerRepo answerRepo) {
		this.answerRepo = answerRepo;
	}
	
	public void addAnswer(Answer answer) {
		answerRepo.save(answer);
	}
	
	public List<Answer> allAnswers() {
		return (List<Answer>) answerRepo.findAll();
	}
	
	public Answer findAnswerById(Long id) {
		return answerRepo.findOne(id);
	}
}
