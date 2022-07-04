package com.tracker.api.expense;

import com.google.cloud.Timestamp;

public class Expense {
  private Integer cost;
  private String item;
  private Timestamp timestamp;

  //
  // Constructors
  //

  public Expense() {
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

  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  //
  // Builder Methods
  //

  public Expense cost(Integer cost) {
    setCost(cost);
    return this;
  }

  public Expense item(String item) {
    setItem(item);
    return this;
  }

  public Expense timestamp(Timestamp timestamp) {
    setTimestamp(timestamp);
    return this;
  }
}
