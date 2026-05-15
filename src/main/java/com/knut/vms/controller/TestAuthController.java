package com.knut.vms.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @GetMapping("/hello")

    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello-secured")

    public String helloSecured() {
        return "Hello World Secured";
    }

    @GetMapping("/hello-secured2")
    public String helloSecured2() { return "Hello World secured2";}
}
