package com.tracker.api.user;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("{uid}")
  public UserDto get(@PathVariable String uid) {
    UserDto userDto;
    try {
      userDto = this.userService.getUser(uid);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    if (userDto.equals(null)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return userDto;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@RequestBody final CreateUserDto createUserDto) {
    // TODO add validation for attributes passed in are correct, otherwise return a
    // 400 Bad Request
    UserDto userDto;
    try {
      userDto = this.userService.createUser(createUserDto);
    } catch (FirebaseAuthException e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.resolve(e.getHttpResponse().getStatusCode()), e.getMessage());
    }

    return userDto;
  }
}
