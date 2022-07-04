package com.tracker.api.expense;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ExpenseService {

  public List<ExpenseDto> listExpenses() throws InterruptedException, ExecutionException {
    Firestore firestore = FirestoreClient.getFirestore();

    CollectionReference collectionReference = firestore.collection("expenses");
    ApiFuture<QuerySnapshot> apiFuture = collectionReference.get();
    QuerySnapshot collection = apiFuture.get();

    List<QueryDocumentSnapshot> documents = collection.getDocuments();

    List<ExpenseDto> expenses = documents.stream().map((QueryDocumentSnapshot snapshot) -> {
      return mapExpenseToExpenseDto(snapshot.getId(), snapshot.toObject(Expense.class));
    }).collect(Collectors.toList());

    return expenses;
  }

  public ExpenseDto createExpense(CreateExpenseDto createExpenseDto) throws InterruptedException, ExecutionException {
    Firestore firestore = FirestoreClient.getFirestore();

    Timestamp timestamp = createExpenseDto.getTimestamp() != null
        ? Timestamp.of(new Date(createExpenseDto.getTimestamp()))
        : Timestamp.now();

    Expense expense = new Expense()
        .cost(createExpenseDto.getCost())
        .item(createExpenseDto.getItem())
        .timestamp(timestamp);

    ApiFuture<DocumentReference> apiFuture = firestore.collection("expenses").add(expense);
    DocumentReference document = apiFuture.get();

    return this.mapExpenseToExpenseDto(document.getId(), expense);
  }

  public ExpenseDto updateExpense(String id, UpdateExpenseDto updateExpenseDto)
      throws InterruptedException, ExecutionException, ResponseStatusException {
    Firestore firestore = FirestoreClient.getFirestore();

    Timestamp timestamp = Timestamp.of(new Date(updateExpenseDto.getTimestamp()));

    ApiFuture<DocumentSnapshot> apiFuture = firestore.document("expenses/" + id).get();
    DocumentSnapshot documentSnapshot = apiFuture.get();
    if (!documentSnapshot.exists()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    Expense expense = documentSnapshot.toObject(Expense.class)
        .cost(updateExpenseDto.getCost())
        .item(updateExpenseDto.getItem())
        .timestamp(timestamp);

    firestore.document("expenses/" + id).set(expense);

    return this.mapExpenseToExpenseDto(id, expense);
  }

  public void deleteExpense(String id) {
    Firestore firestore = FirestoreClient.getFirestore();
    firestore.document("expenses/" + id).delete();
  }

  private ExpenseDto mapExpenseToExpenseDto(String id, Expense expense) {
    return new ExpenseDto()
        .id(id)
        .cost(expense.getCost())
        .item(expense.getItem())
        .timestamp(expense.getTimestamp().toDate().getTime());
  }
}
