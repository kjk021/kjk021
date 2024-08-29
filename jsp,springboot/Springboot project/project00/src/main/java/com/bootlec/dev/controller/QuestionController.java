package com.bootlec.dev.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.bootlec.dev.dto.AnswerForm;
import com.bootlec.dev.dto.QuestionForm;
import com.bootlec.dev.entity.Notice;
import com.bootlec.dev.entity.Question;
import com.bootlec.dev.service.NoticeService;
import com.bootlec.dev.service.QuestionService;
import com.bootlec.dev.user.SiteUser;
import com.bootlec.dev.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/q") //를사용하면 앞자리를 공통적으로 생략가능해진다
public class QuestionController {
	
	private final QuestionService questionService;
	private final NoticeService noticeService;
	private final UserService userService;
	  
	 @GetMapping("/list")	 
	 public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
	        Page<Question> paging = this.questionService.getList(page,kw);
	        List<Notice> notices = noticeService.getList(kw);
	        model.addAttribute("notices", notices);
	        model.addAttribute("paging", paging);
	        model.addAttribute("kw", kw);
	     
	        return "question_list";
	    }
	 @GetMapping(value = "/detail/{id}")
	    public String detail(Model model, @PathVariable(required = true, name = "id") Integer id, AnswerForm answerForm) {
		 Question question = this.questionService.getQuestion(id);
	        model.addAttribute("question", question);
	        return "question_detail";
	    }
	 @PreAuthorize("isAuthenticated()")
	  @GetMapping("/create")
	    public String questionCreate(QuestionForm questionForm) {
	        return "question_form";
	    }
	  
	    @PreAuthorize("isAuthenticated()")
	  @PostMapping("/create")
	  public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
	        if (bindingResult.hasErrors()) {
	            return "question_form";
	        }
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser);
	        return "redirect:/q/list";
	    }
	  
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
	        Question question = this.questionService.getQuestion(id);
	        if(!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        questionForm.setSubject(question.getSubject());
	        questionForm.setContent(question.getContent());
	        return "question_form";
	    }
	    

	    @PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
	            Principal principal, @PathVariable("id") Integer id) {
	        if (bindingResult.hasErrors()) {
	            return "question_form";
	        }
	        Question question = this.questionService.getQuestion(id);
	        if (!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
	        return String.format("redirect:/q/detail/%s", id);
	    }
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
	        Question question = this.questionService.getQuestion(id);
	        if (!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.questionService.delete(question);
	        return "redirect:/";
	    }
	    
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/vote/{id}")
	    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
	    	  String username = principal.getName();
	    	
	        Question question = this.questionService.getQuestion(id);
	        if (!"admin".equals(username)) {
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.questionService.vote(question, siteUser);
	        }
	        return String.format("redirect:/q/detail/%s", id);
	    }
	    
	    }


