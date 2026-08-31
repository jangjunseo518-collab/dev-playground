package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
    UserResponse user = userService.createUser(request.nickname(), request.email());
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> findId(@PathVariable Long id) {
    UserResponse byId = userService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(byId);
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> findByAll() {
    List<UserResponse> all = userService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(all);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
