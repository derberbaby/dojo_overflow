package com.debbie.dojooverflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.debbie.dojooverflow.models.Answer;
import com.debbie.dojooverflow.models.Question;
import com.debbie.dojooverflow.models.Tag;
import com.debbie.dojooverflow.services.QuestionService;
import com.debbie.dojooverflow.services.TagService;

@Controller
public class QuestionController {
	private QuestionService questionService;
	private TagService tagService;
	
	public QuestionController(QuestionService questionService, TagService tagService) {
		this.questionService = questionService;
		this.tagService = tagService;
	}
	
	@RequestMapping("/questions")
	public String dashboard(Model model) {
		List<Question> questionList = questionService.allQuestions();
		model.addAttribute("questionList", questionList);
		
		return "dashboard";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("questionInfo") Question question) {
		return "newQuestion";
	}
	
	@PostMapping("/questions/new")
	public String createQuestion(RedirectAttributes redirectAttributes, Model model, @ModelAttribute("error") String error, @RequestParam("tagString") String tags, @Valid @ModelAttribute("questionInfo") Question question, BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("error", error);
			return "newQuestion";
		} else {
			List<Tag> questionTags = tagService.returnList(tags);
			
			if(questionTags == null) {
				redirectAttributes.addFlashAttribute("error", "You can only add up to 3 tags!");
				return "redirect:/questions/new";
			} else {
				question.setTags(questionTags);
				questionService.addQuestion(question);
				Long id = question.getId();
				return "redirect:/questions/" + id;
			}
		}
	}
	
	@RequestMapping("/questions/{id}")
	public String findQuestionById(Model model, @PathVariable("id") Long id, @ModelAttribute("newAnswer") Answer answer) {
		Question question = questionService.findQuestionById(id);
		if(question != null) {
			model.addAttribute("question", question);
			List<Tag> questionTags = question.getTags();
			model.addAttribute("questionTags", questionTags);
			List<Answer> answerList = question.getAnswers();
			model.addAttribute("answerList", answerList);
			return "showQuestion";
		} else {
			return "redirect:/questions";
		}
	}
	
}
