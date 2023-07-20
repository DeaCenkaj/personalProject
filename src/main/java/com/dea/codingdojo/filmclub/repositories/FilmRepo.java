package com.dea.codingdojo.filmclub.repositories;

import com.dea.codingdojo.filmclub.models.Film;
import com.dea.codingdojo.filmclub.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FilmRepo extends CrudRepository<Film, Long> {
    List<Film> findAll();
    Film findByIdIs(Long id);
    List<Film> findAllByUsers(User user);
    List<Film> findByUsersNotContains(User user);
}
