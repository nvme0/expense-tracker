package com.tracker.api.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDto {

  @Schema(required = true)
  private String uid;

  @Schema(required = true)
  private String email;

  @Schema(required = true)
  private String name;

  //
  // Constructors
  //

  public UserDto() {
  }

  //
  // Getters & Setters
  //

  public String getUid() {
    return this.uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

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

  public UserDto uid(String uid) {
    setUid(uid);
    return this;
  }

  public UserDto name(String name) {
    setName(name);
    return this;
  }

  public UserDto email(String email) {
    setEmail(email);
    return this;
  }
}
