package com.tracker.api.expense;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

  @Autowired
  private ExpenseService expenseService;

  @GetMapping()
  public List<ExpenseDto> list() {
    List<ExpenseDto> expenseDtos;

    try {
      expenseDtos = this.expenseService.listExpenses();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return expenseDtos;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ExpenseDto create(@RequestBody CreateExpenseDto createExpenseDto) {
    // TODO add validation for attributes passed in are correct, otherwise return a
    // 400 Bad Request
    ExpenseDto expenseDto;
    try {
      expenseDto = this.expenseService.createExpense(createExpenseDto);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return expenseDto;
  }

  @PutMapping("{id}")
  public ExpenseDto update(@PathVariable String id, @RequestBody final UpdateExpenseDto updateExpenseDto) {
    // TODO add validation for attributes passed in are correct, otherwise return a
    // 400 Bad Request
    ExpenseDto expenseDto;
    try {
      expenseDto = this.expenseService.updateExpense(id, updateExpenseDto);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return expenseDto;
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable String id) {
    this.expenseService.deleteExpense(id);
  }
}
