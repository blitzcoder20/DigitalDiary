package it.begear.digitaldiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.digitaldiary.entities.Post;
import it.begear.digitaldiary.repository.PostRepo;


@Service
public class PostService {

	@Autowired
    private PostRepo repo;
	
	public List<Post> listAll() {
        return repo.findAll();
    }
     
    public void save(Post user) {
        repo.save(user);
    }
     
    public Post get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
    
}
