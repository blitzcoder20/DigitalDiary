package it.begear.digitaldiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.begear.digitaldiary.entities.Post;
import it.begear.digitaldiary.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService service;
	
	@RequestMapping("create-post/{id}")
	public String createPost(@PathVariable(name="id") Long userId,@RequestParam("text") String text) {
		Post post=new Post();
		post.setText(text);
		post.setIdUser(userId);
		service.save(post);
		return "redirect:/posts";
	}
}
