package API.Interceptor.Middleware2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping
    public String hello(){
        return "welcome on my website!";
    }
}