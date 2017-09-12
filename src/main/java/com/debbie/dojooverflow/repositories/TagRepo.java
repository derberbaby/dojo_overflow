package com.debbie.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;

import com.debbie.dojooverflow.models.Tag;

public interface TagRepo extends CrudRepository<Tag, Long> {
	Tag findTagBySubject(String search);
}
