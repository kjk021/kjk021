package com.bootlec.dev.controller;

import java.security.Principal;
import java.util.List;

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

import com.bootlec.dev.dto.NoticeForm;
import com.bootlec.dev.entity.Notice;
import com.bootlec.dev.entity.Question;
import com.bootlec.dev.service.NoticeService;
import com.bootlec.dev.user.SiteUser;
import com.bootlec.dev.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
@RequestMapping("/n") 
public class NoticeController {
	private final NoticeService noticeService;
	private final UserService userService;
	
	
	 @GetMapping(value = "/detail/{id}")
	    public String detail(Model model, @PathVariable(required = true, name = "id") Integer id) {
		 Notice notice = this.noticeService.getNotice(id);
	        model.addAttribute("notice", notice);
	        return "notice_detail";
	    }
	 
	 @PreAuthorize("isAuthenticated()")
	  @GetMapping("/create")
	    public String noticeCreate(NoticeForm noticeForm) {
	        return "notice_form";
	    }
	 //"#principal != null and #principal.username == 'admin'"
	  
	    @PreAuthorize("isAuthenticated()")
	  @PostMapping("/create")
	  public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal) {
	        if (bindingResult.hasErrors()) {
	            return "notice_form";
	        }	        
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.noticeService.create(noticeForm.getSubject(), noticeForm.getContent(),siteUser);
	        return "redirect:/q/list";
	    }
	  
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String noticeModify(Model model, @PathVariable("id") Integer id, Principal principal) {
	        Notice notice = this.noticeService.getNotice(id);
	        if (!notice.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        NoticeForm noticeForm = new NoticeForm(); // 수정 폼 객체 생성
	        noticeForm.setSubject(notice.getSubject());
	        noticeForm.setContent(notice.getContent());
	        model.addAttribute("noticeForm", noticeForm); // 수정 폼을 모델에 추가
	        return "notice_form"; // 수정 폼으로 이동
	    }
            
      
	    
	    @PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String noticeModify(@Valid NoticeForm noticeForm, BindingResult bindingResult, 
	            Principal principal, @PathVariable("id") Integer id) {
	        if (bindingResult.hasErrors()) {
	            return "notice_form"; // 입력 유효성 검사 에러가 있을 경우 다시 수정 폼을 보여줍니다.
	        }
	        Notice notice = this.noticeService.getNotice(id);
	        if (!notice.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        this.noticeService.modify(notice, noticeForm.getSubject(), noticeForm.getContent());
	        return String.format("redirect:/n/detail/%s", id); // 수정된 공지 상세 페이지로 리다이렉트합니다.
	    }
	    
	              
		 
		           
		        
		      
	    
	    
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String noticeDelete(Principal principal, @PathVariable("id") Integer id) {
	        Notice notice = this.noticeService.getNotice(id);
	        if (!notice.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.noticeService.delete(notice);
	        return "redirect:/";
	    }

}
