package com.debbie.dojooverflow.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.debbie.dojooverflow.models.Tag;
import com.debbie.dojooverflow.repositories.TagRepo;

@Service
public class TagService {
	private TagRepo tagRepo;
	
	public TagService(TagRepo tagRepo) {
		this.tagRepo = tagRepo;
	}
	
	public Tag findTagById(Long id) {
		return tagRepo.findOne(id);
	}
	
	public void addTag(Tag tag) {
		tagRepo.save(tag);
	}
	
	public List<Tag> getTags() {
		return (List<Tag>) tagRepo.findAll();
	}
	
	public void updateTag(Tag tag) {
		tagRepo.save(tag);
	}
	
	public List<Tag> returnList(String tags) {
		String list = tags.toLowerCase();
		List<String> tagList = Arrays.asList(list.split("\\s*,\\s*"));
		if(tagList.size() > 3) {
			return null;
		} else {
			List<Tag> questionTags = new ArrayList<Tag>();
			for(int i = 0; i < tagList.size(); i++) {
				Tag search = tagRepo.findTagBySubject(tagList.get(i));
				if(search == null) {
					Tag newTag = new Tag();
					newTag.setSubject(tagList.get(i));
					tagRepo.save(newTag);
					questionTags.add(newTag);
				} else {
					questionTags.add(search);
				}
			}
			return questionTags;
		}
		
	}
	
}
