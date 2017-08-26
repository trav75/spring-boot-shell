package com.spring.boot.shell.service;

import com.spring.boot.shell.domain.RandomCity;
import com.spring.boot.shell.domain.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

    List<RandomCity> findAllRandomCities();
}
