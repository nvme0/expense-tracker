package com.tracker.api.expense;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateExpenseDto {
  @Schema(required = true)
  private Integer cost;

  @Schema(required = true)
  private String item;

  @Schema(required = false)
  private Long timestamp;

  //
  // Constructors
  //

  public CreateExpenseDto() {
  }

  //
  // Getters & Setters
  //

  public Integer getCost() {
    return this.cost;
  }

  public void setCost(Integer expense) {
    this.cost = expense;
  }

  public String getItem() {
    return this.item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public Long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  //
  // Builder Methods
  //

  public CreateExpenseDto cost(Integer cost) {
    setCost(cost);
    return this;
  }

  public CreateExpenseDto item(String item) {
    setItem(item);
    return this;
  }

  public CreateExpenseDto timestamp(Long timestamp) {
    setTimestamp(timestamp);
    return this;
  }
}
