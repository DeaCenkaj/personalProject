package com.dea.codingdojo.filmclub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Title required")
    private String title;
    @NotBlank(message="Director required")
    private String director;

    @NotBlank(message="Thoughts required")
    private String thoughts;


    @Column(updatable=false)
    @DateTimeFormat(pattern="MM/DD/YY HH:MM")
    private Date createdAt;
    @DateTimeFormat(pattern="MM/DD/YY HH:MM")
    private Date updatedAt;

    public Film() {

    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_favourite_films",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Film(Long id, String title, String director, String thoughts, Date createdAt, Date updatedAt, User creator, List<User> users) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.thoughts = thoughts;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creator = creator;
        this.users = users;
    }
    public String getCreatedAtFormated(){
        String pattern = "MMM dd , yyyy @ HH:mm";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        return simpleDateFormat.format(createdAt);
    }
    public String getUpdatedAtFormated(){
        if(updatedAt == null)
            return "--";
        String pattern = "MMM dd , yyyy @ HH:mm";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        return simpleDateFormat.format(updatedAt);
    }
}
