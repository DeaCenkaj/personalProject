package com.dea.codingdojo.filmclub.services;

import com.dea.codingdojo.filmclub.models.Film;
import com.dea.codingdojo.filmclub.models.User;
import com.dea.codingdojo.filmclub.repositories.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class FilmService {
    @Autowired
    private FilmRepo filmRepo;



    public List<Film> allFilms(){
        return filmRepo.findAll();
    }

    public Film updateFilm(Film film) {
        return filmRepo.save(film);
    }

    public List<Film> getWatchedFilms(User user){
        return filmRepo.findAllByUsers(user);
    }

    public List<Film> getUnwatchedFilms(User user){
        return filmRepo.findByUsersNotContains(user);
    }

    public Film addFilm(Film film) {
        return filmRepo.save(film);
    }

    public void deleteFilm(Film film) {
        filmRepo.delete(film);
    }

    public Film findById(Long id) {
        Optional<Film> optionalFilm = filmRepo.findById(id);
        if(optionalFilm.isPresent()) {
            return optionalFilm.get();
        }else {
            return null;
        }
    }

}
