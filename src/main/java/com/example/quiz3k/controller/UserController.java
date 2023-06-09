package com.example.quiz3k.controller;

import com.example.quiz3k.model.UserResponse;
import com.example.quiz3k.model.dto.User;
import com.example.quiz3k.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.CacheRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController (UserService userService){
        this.userService= userService;
    }

    @PostMapping("/users")
    public void createUser (@RequestBody User user) {
        userService.createUser(user);
    }
   @GetMapping("/users")
   public List<UserResponse> getAllUsers() {
       return userService.getAllUsers();
   }
   @GetMapping("/users-user")
   public UserResponse getUser(Principal principal) {
       String userName = principal.getName();
       return userService.getUser(userName);
   }
   @GetMapping("/activate-user/{confirmation-token}")
   public void activateUser(@PathVariable("confirmation-token") String confirmationToken){
       userService.activateUser(confirmationToken);
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
