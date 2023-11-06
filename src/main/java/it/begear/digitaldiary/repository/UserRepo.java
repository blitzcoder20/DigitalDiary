package it.begear.digitaldiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.begear.digitaldiary.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	List<User> findUserByUsername(String username);
}
