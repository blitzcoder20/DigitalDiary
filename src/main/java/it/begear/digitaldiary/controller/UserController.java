package it.begear.digitaldiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("posts")
	public String viewPostsPage(Model model) {

		if(!model.containsAttribute("user")){
			return "redirect:/"; 
		}
		//System.out.println(user.getId());
		return "posts";
	}
	

	@RequestMapping("try-login")
	public String tryLogin(@RequestParam("username") String username, @RequestParam("password") String password,RedirectAttributes redirectAttributes) {
		if(service.login(username,password)) {
			User user=service.findByUsername(username);
			redirectAttributes.addFlashAttribute("user",user);
			return "redirect:/posts";
		}
		return "redirect:/";
	}
}
