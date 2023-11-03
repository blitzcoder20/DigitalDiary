package it.begear.digitaldiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.begear.digitaldiary.entities.User;
import it.begear.digitaldiary.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/")
	public String viewLoginPage() {
		return "login";
	}
	
	@RequestMapping("try-login")
	public ModelAndView tryLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		if(service.login(username,password)) {
			ModelAndView mv=new ModelAndView("posts");
			User user=service.findByUsername(username);
			mv.addObject("user",user);
			return mv;
		}
		return new ModelAndView("/login");
	}
}
