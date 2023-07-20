package com.dea.codingdojo.filmclub.services;

import com.dea.codingdojo.filmclub.models.Comment;
import com.dea.codingdojo.filmclub.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;



    public List<Comment> allTasks(){
        return commentRepo.findAll();
    }

    public List<Comment> filmComments(Long filmId){
        return commentRepo.findByFilmIdIs(filmId);
    }

    public Comment addComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepo.delete(comment);
    }


}
