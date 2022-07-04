package com.tracker.api.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUserDto {

  @Schema(required = true)
  private String email;

  @Schema(required = true)
  private String password;

  @Schema(required = true)
  private String name;

  //
  // Constructors
  //

  public CreateUserDto() {
  }

  //
  // Getters & Setters
  //

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  //
  // Builder Methods
  //

  public CreateUserDto name(String name) {
    setName(name);
    return this;
  }

  public CreateUserDto email(String email) {
    setEmail(email);
    return this;
  }

  public CreateUserDto password(String password) {
    setPassword(password);
    return this;
  }

}
