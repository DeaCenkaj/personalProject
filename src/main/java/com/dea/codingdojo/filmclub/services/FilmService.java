package com.dea.codingdojo.filmclub.services;

import com.dea.codingdojo.filmclub.models.Film;
import com.dea.codingdojo.filmclub.repositories.FilmRepository;
import com.dea.codingdojo.filmclub.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FilmService {
 @Autowired
  FilmRepository filmRepository;
  public List<Film> allFilms(){return filmRepository.findAll();}
   public  Film createFilm(Film film){return filmRepository.save(film);}
    public Film findFilm(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public void deleteFilm(Long id){
        filmRepository.deleteById(id);
    }

    public Film update(Film film, Film oldFilm){
        List<User> userList =oldFilm.getUsers();
        film.setUsers(userList);

        return filmRepository.save(film);
    }


    public void addToFavorites(Film film, User user){
        film.getUsers().add(user);
        filmRepository.save(film);
    }

    public void removeFromFavorite(Film film, User user){
        film.getUsers().remove(user);
        filmRepository.save(film);
    }

}
