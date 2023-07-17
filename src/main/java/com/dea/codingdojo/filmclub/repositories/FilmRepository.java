package com.dea.codingdojo.filmclub.repositories;

import com.dea.codingdojo.filmclub.models.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {
List<Film> findAll();
}
