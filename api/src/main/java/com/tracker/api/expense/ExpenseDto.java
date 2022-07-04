package com.tracker.api.expense;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExpenseDto {

  @Schema(required = true)
  private String id;

  @Schema(required = true)
  private Integer cost;

  @Schema(required = true)
  private String item;

  @Schema(required = true)
  private Long timestamp;

  //
  // Constructors
  //

  public ExpenseDto() {
  }

  //
  // Getters & Setters
  //

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public ExpenseDto id(String id) {
    setId(id);
    return this;
  }

  public ExpenseDto cost(Integer cost) {
    setCost(cost);
    return this;
  }

  public ExpenseDto item(String item) {
    setItem(item);
    return this;
  }

  public ExpenseDto timestamp(Long timestamp) {
    setTimestamp(timestamp);
    return this;
  }
}
