package training.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class Hello {

    public static void main(String[] args) {
        SpringApplication.run(Hello.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Hello Spring boot web application.";
    }
}