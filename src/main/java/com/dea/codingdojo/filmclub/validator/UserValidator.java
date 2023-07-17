package com.dea.codingdojo.filmclub.validator;

import com.dea.codingdojo.filmclub.models.User;
import com.dea.codingdojo.filmclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserRepository userRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        if (!user.getConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }

        if (userRepo.findByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "EmailTaken", "This email is already taken!");
    }
}
