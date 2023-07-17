package com.dea.codingdojo.filmclub.repositories;

import org.apache.catalina.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository {
    List<Role> findAll();
    List<com.dea.codingdojo.filmclub.models.Role> findByName(String name);
}
