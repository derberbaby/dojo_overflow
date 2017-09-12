package com.debbie.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;

import com.debbie.dojooverflow.models.Question;

public interface QuestionRepo extends CrudRepository<Question, Long> {

}
