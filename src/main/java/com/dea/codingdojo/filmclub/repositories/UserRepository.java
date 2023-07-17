package com.dea.codingdojo.filmclub.repositories;

import com.dea.codingdojo.filmclub.models.Film;
import com.dea.codingdojo.filmclub.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findAll();


    List<User> findAllByfavoriteFilm(Film film);
    }
