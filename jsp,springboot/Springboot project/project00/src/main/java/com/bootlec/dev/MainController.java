package com.bootlec.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	 @GetMapping("/")
	
	    public String index() {
		 return "redirect:/index";
	 }
	 
	 @GetMapping("/index")	
	    public String indexpage() {
		 return "index";
	    }
	 
	 
   //루트 url코드 이걸쓰면 8080[기본값] 입력시 특정페이지로 보내는것이 가능
	 /* @GetMapping("/")
	    public String root() {
	        return "redirect:/q/list";
	    }*/
}
