package com.tracker.api.expense;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateExpenseDto {
  @Schema(required = true)
  private Integer cost;

  @Schema(required = true)
  private String item;

  @Schema(required = true)
  private Long timestamp;

  //
  // Constructors
  //

  public UpdateExpenseDto() {
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

  public UpdateExpenseDto cost(Integer cost) {
    setCost(cost);
    return this;
  }

  public UpdateExpenseDto item(String item) {
    setItem(item);
    return this;
  }

  public UpdateExpenseDto timestamp(Long timestamp) {
    setTimestamp(timestamp);
    return this;
  }
}
