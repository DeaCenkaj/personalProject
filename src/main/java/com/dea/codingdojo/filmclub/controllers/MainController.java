package com.dea.codingdojo.filmclub.controllers;

import com.dea.codingdojo.filmclub.models.Comment;
import com.dea.codingdojo.filmclub.models.Film;
import com.dea.codingdojo.filmclub.models.LoginUser;
import com.dea.codingdojo.filmclub.models.User;
import com.dea.codingdojo.filmclub.services.CommentService;
import com.dea.codingdojo.filmclub.services.FilmService;
import com.dea.codingdojo.filmclub.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin") User newLogin, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/dashboard";}

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());

        return "index1.jsp";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        User user = userService.register(newUser, result);

        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index1.jsp";
        }
        session.setAttribute("userId", user.getId());

        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {

        User user = userService.login(newLogin, result);

        if(result.hasErrors() || user==null) {
            model.addAttribute("newUser", new User());
            return "index1.jsp";
        }

        session.setAttribute("userId", user.getId());

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);

        model.addAttribute("user", user);
        model.addAttribute("unwatchedFilms", filmService.getUnwatchedFilms(user));
        model.addAttribute("watchedFilms", filmService.getWatchedFilms(user));

        return "dashboard.jsp";
    }

    @RequestMapping("/dashboard/watched/{id}")
    public String addToWatched(@PathVariable("id") Long id, HttpSession session, Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        Film film = filmService.findById(id);
        User user = userService.findById(userId);

        user.getFilms().add(film);
        userService.updateUser(user);

        model.addAttribute("user", user);
        model.addAttribute("unwatchedFilms", filmService.getUnwatchedFilms(user));
        model.addAttribute("watchedFilms", filmService.getWatchedFilms(user));

        return "redirect:/dashboard";
    }

    @RequestMapping("/dashboard/leave/{id}")
    public String unwatchFilm(@PathVariable("id") Long id, HttpSession session, Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        Film film = filmService.findById(id);
        User user = userService.findById(userId);

        user.getFilms().remove(film);
        userService.updateUser(user);

        model.addAttribute("user", user);
        model.addAttribute("unwatchedFilms", filmService.getUnwatchedFilms(user));
        model.addAttribute("watchedFilms", filmService.getWatchedFilms(user));

        return "redirect:/dashboard";
    }

    @GetMapping("/films/{id}")
    public String viewFilm(@PathVariable("id") Long id, HttpSession session, Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        Film film = filmService.findById(id);
        model.addAttribute("film", film);
        return "view_film.jsp";
    }

    @GetMapping("/films/edit/{id}")
    public String openEditFilm(@PathVariable("id") Long id, HttpSession session, Model model) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        Film film = filmService.findById(id);
        model.addAttribute("film", film);
        return "edit_film.jsp";
    }

    @PostMapping("/films/edit/{id}")
    public String editFilm(@PathVariable("id") Long id, @Valid @ModelAttribute("film") Film updatedFilm,
                           BindingResult result, HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        if (result.hasErrors()) {
            return "edit_film.jsp";
        } else {
            Film existingFilm = filmService.findById(id);
            if (existingFilm == null) {
                return "redirect:/dashboard";
            }

            Long userId = (Long) session.getAttribute("userId");
            User user = userService.findById(userId);

            existingFilm.setTitle(updatedFilm.getTitle());
            existingFilm.setDirector(updatedFilm.getDirector());
            existingFilm.setActors(updatedFilm.getActors());
            existingFilm.setDescription(updatedFilm.getDescription());
            existingFilm.setGenre(updatedFilm.getGenre());


            filmService.updateFilm(existingFilm);

            return "redirect:/dashboard";
        }
    }

    @RequestMapping("/films/delete/{id}")
    public String deleteFilm(@PathVariable("id") Long id, HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        Film film = filmService.findById(id);
        filmService.deleteFilm(film);

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("userId", null);
        return "redirect:/";
    }

    @GetMapping("/films/new")
    public String newProject(@ModelAttribute("film") Film film, HttpSession session, Model model) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("userId", userId);
        return "new_film.jsp";
    }

    @PostMapping("/films/new")
    public String addNewFilm(@Valid @ModelAttribute("film") Film film, BindingResult result, HttpSession session) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        if(result.hasErrors()) {
            return "new_film.jsp";
        }else {
            filmService.addFilm(film);

            Long userId = (Long) session.getAttribute("userId");
            User user = userService.findById(userId);
            user.getFilms().add(film);
            userService.updateUser(user);
            return "redirect:/dashboard";
        }


    }

    @GetMapping("/films/{id}/comments")
    public String viewFilmComments(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("comment") Comment comment) {

        if(session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }

        Film film = filmService.findById(id);
        model.addAttribute("film", film);
        model.addAttribute("comments", commentService.filmComments(id));
        return "comments.jsp";
    }



    @PostMapping("/films/{id}/comments")
    public String newFilmComment(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model,
            @Valid @ModelAttribute("comment") Comment comment, // Use "newComment" instead of "comment"
            BindingResult result) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("userId");

        Film film = filmService.findById(id);

        if (result.hasErrors()) {
            model.addAttribute("film", film);
            model.addAttribute("comments", commentService.filmComments(id)); // Use a different attribute name
            return "comments.jsp";
        } else {
            Comment newComment = new Comment(comment.getName());
            newComment.setFilm(film);
            newComment.setCreator(userService.findById(userId));
            commentService.addComment(newComment);
            return "redirect:/films/" + id + "/comments";
        }
    }


}


