package dev.florinchristian.universitybackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
public class BasicController {

    @GetMapping("/")
    public String index() {
        return "University API is running.";
    }
}
