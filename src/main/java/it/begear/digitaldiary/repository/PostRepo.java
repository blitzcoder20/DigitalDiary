package it.begear.digitaldiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.begear.digitaldiary.entities.Post;

public interface PostRepo extends JpaRepository<Post, Long>{

}
