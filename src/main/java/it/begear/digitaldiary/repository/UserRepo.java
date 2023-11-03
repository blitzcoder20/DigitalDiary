package it.begear.digitaldiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.begear.digitaldiary.entities.User;


public interface UserRepo extends JpaRepository<User, Long>{
	List<User> findUserByUsername(String username);
}
