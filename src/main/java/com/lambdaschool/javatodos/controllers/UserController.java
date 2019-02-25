package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = {"/users"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController
{
    @Autowired
    UserRepository userrepos;

    @GetMapping("")
    List<User> findAllUsers()
    {
        return userrepos.findAll();
    }

    @GetMapping("/userid/{userid}")
    public User findUserById(@PathVariable long userid)
    {
        var userById = userrepos.findById(userid);
        return userById.isPresent() ? userById.get() : null;
    }

    @GetMapping("/username/{username}")
    public User findUserByUsername(@PathVariable String username)
    {
        var userByUsername = userrepos.findUserByUsernameLike(username);
        return userByUsername != null ? userByUsername : null;
    }

    @PostMapping("")
    public User addNewUser(@RequestBody User newUser) throws URISyntaxException
    {
        return userrepos.save(newUser);
    }

    @PutMapping("/userid/{userid}")
    public User updateUserById(@RequestBody User updatedUser, @PathVariable long userid)
    {
        var userById = userrepos.findById(userid);
        if (userById.isPresent())
        {
            updatedUser.setUserid(userid);
            return updatedUser;
        }
        else
        {
            return null;
        }
    }

    //DOES NOT WORK WHEN DELETING DATA WITH RELATIONAL DATA
    @DeleteMapping("/userid/{userid}")
    public User deleteUserById(@PathVariable Long userid)
    {
        var userById = userrepos.findById(userid);
        if (userById.isPresent())
        {
            userrepos.deleteById(userid);
            return userById.get();
        }
        else
        {
            return null;
        }
    }

}
