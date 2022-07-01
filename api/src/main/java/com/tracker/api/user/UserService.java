package com.tracker.api.user;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

@Service
public class UserService {

  @Autowired
  private FirebaseAuth auth;

  @Autowired
  private Firestore firestore;

  public UserDto getUser(String uid) throws InterruptedException, ExecutionException {
    DocumentReference documentReference = firestore.document("user/" + uid);
    ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
    DocumentSnapshot document = apiFuture.get();

    User user;
    if (document.exists()) {
      user = document.toObject(User.class);
      return mapUserToUserDto(uid, user);
    }
    return null;
  }

  public UserDto createUser(CreateUserDto dto) throws FirebaseAuthException {
    UserRecord userRecord = auth.createUser(
        new CreateRequest()
            .setEmail(dto.getEmail())
            .setPassword(dto.getPassword()));

    User user = new User()
        .email(dto.getEmail())
        .name(dto.getName());

    firestore.document("user/" + userRecord.getUid()).create(user);

    return this.mapUserToUserDto(userRecord.getUid(), user);
  }

  private UserDto mapUserToUserDto(String uid, User user) {
    return new UserDto()
        .uid(uid)
        .email(user.getEmail())
        .name(user.getName());
  }
}
