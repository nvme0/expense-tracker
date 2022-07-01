package com.tracker.api.user;

public class User {
  private String email;
  private String name;

  //
  // Constructors
  //

  public User() {
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

  //
  // Builder Methods
  //

  public User name(String name) {
    setName(name);
    return this;
  }

  public User email(String email) {
    setEmail(email);
    return this;
  }

}
