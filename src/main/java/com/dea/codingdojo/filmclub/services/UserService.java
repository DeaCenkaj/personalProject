package com.dea.codingdojo.filmclub.services;

import com.dea.codingdojo.filmclub.models.Film;
import com.dea.codingdojo.filmclub.models.User;
import com.dea.codingdojo.filmclub.repositories.RoleRepository;
import com.dea.codingdojo.filmclub.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private BCryptPasswordEncoder bCryptPwEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder bCryptPwEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bCryptPwEncoder = bCryptPwEncoder;
    }

    public void newUser(User user, String role) {
        user.setPassword(bCryptPwEncoder.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName(role));
        userRepo.save(user);
    }


    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public User findByUsername(String email) {
        return userRepo.findByUsername(email);
    }

    public List<User> allUsers(){
        return userRepo.findAll();
    }



    public User findById(Long id) {
        Optional<User> potentialUser = userRepo.findById(id);
        if(potentialUser.isPresent()) {
            return potentialUser.get();
        }
        return null;
    }
    public List<User> getAllByFavorites(Film film){
        return userRepo.findAllByfavoriteFilm(film);
    }

}