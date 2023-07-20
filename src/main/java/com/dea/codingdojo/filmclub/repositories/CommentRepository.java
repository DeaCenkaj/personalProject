package com.dea.codingdojo.filmclub.repositories;

import com.dea.codingdojo.filmclub.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAll();
    List<Comment> findByFilmIdIs(Long id);
}
