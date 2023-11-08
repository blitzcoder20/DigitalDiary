package it.begear.digitaldiary.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import it.begear.digitaldiary.entities.Post;
import it.begear.digitaldiary.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService service;
	
	@RequestMapping("/create-post/{userId}")
	public String createPost(@PathVariable(name="userId") Long userId,@RequestParam("text") String text) {
		if(text.isBlank()) {
			return "redirect:/posts";
		}
		Post post=new Post();
		post.setText(text);
		post.setIdUser(userId);
		post.setCreationDate(new Date());
		service.save(post);
		return "redirect:/posts";
	}
	
	@RequestMapping("/delete-post/{id}")
	public String deletePost(@PathVariable(name="id") Long postId) {
		service.delete(postId);
		return "redirect:/posts";
	}
	

	@RequestMapping("/edit-post/{id}")
	public ModelAndView editPost(@PathVariable(name="id") Long postId) {
		ModelAndView mv=new ModelAndView("edit-post");
		Post post=service.get(postId);
		mv.addObject("post",post);
		return mv;
	}
	
	@RequestMapping("/save-post")
	public String savePost(@ModelAttribute("post") Post post,@RequestParam("formattedDate") String formattedDate) throws ParseException {
		System.out.println(post.getId());
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		post.setCreationDate(sdf.parse(formattedDate));
		System.out.println(post.getFormattedDate());
		service.save(post);
		return "redirect:/posts";
	}
	
}
