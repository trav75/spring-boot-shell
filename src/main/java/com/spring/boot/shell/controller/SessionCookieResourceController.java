package com.spring.boot.shell.controller;

import com.spring.boot.shell.domain.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SessionCookieResourceController {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Person test() {
        Person model = new Person();
        model.setName("Jason");
        model.setAge(42);
        return model;
    }
}
