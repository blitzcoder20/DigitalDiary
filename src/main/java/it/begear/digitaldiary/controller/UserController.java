package it.begear.digitaldiary.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.begear.digitaldiary.entities.Post;
import it.begear.digitaldiary.entities.User;
import it.begear.digitaldiary.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/")
	public String viewLoginPage() {
		return "login";
	}
	
	@RequestMapping("posts")
	public String viewPostsPage(HttpSession session,Model model) {
		
		String username = (String) session.getAttribute("username");
		
		if(username == null){
			return "redirect:/"; 
		}
		User user=service.findByUsername(username);
		model.addAttribute("user",user);
		
		List<Post> posts=user.getPosts();
		Collections.reverse(posts);
		
		model.addAttribute("posts",posts);
		return "posts";
	}
	

	@RequestMapping("try-login")
	public String tryLogin(HttpSession session,@RequestParam("username") String username, @RequestParam("password") String password,RedirectAttributes redirectAttributes) {
		if(service.login(username,password)) {
			//User user=service.findByUsername(username);
			//redirectAttributes.addFlashAttribute("user",user);
			session.setAttribute("username", username);
			return "redirect:/posts";
		}
		return "redirect:/";
	}
}
