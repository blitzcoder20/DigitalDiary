package it.begear.digitaldiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.begear.digitaldiary.entities.User;
import it.begear.digitaldiary.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	public List<User> listAll(){
		return repo.findAll();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(long id) {
		return repo.findById(id).get();
	}
	
	public User findByUsername(String username) {
		List<User> queryResult=repo.findUserByUsername(username);
		if(queryResult.size()==0) {
			return null;
		}
		return queryResult.get(0);
	}
	
	public Boolean login(String username,String password) {
		
		User user= this.findByUsername(username);
		if(user == null) {
			return false;
		}
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
