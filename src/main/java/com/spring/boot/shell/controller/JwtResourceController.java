package com.spring.boot.shell.controller;

import com.spring.boot.shell.domain.User;
import com.spring.boot.shell.service.GenericService;
import com.spring.boot.shell.domain.RandomCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
@RestController
@RequestMapping("/springjwt")
public class JwtResourceController {
    @Autowired
    private GenericService userService;

    @RequestMapping(value ="/cities")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public List<RandomCity> getUser(){
        return userService.findAllRandomCities();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}
