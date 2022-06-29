package com.tracker.api.config;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;

@Configuration
public class FirebaseConfig {

  @Value(value = "classpath:serviceAccountKey.json")
  private Resource serviceAccountResource;

  Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

  @Bean
  public FirebaseApp createFireBaseApp() throws IOException {
    try {
      return FirebaseApp.getInstance();
    } catch (Exception exception) {
      // Initialize default app if not created

      InputStream serviceAccount = serviceAccountResource.getInputStream();

      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .build();

      logger.info("Firebase config initialized");

      return FirebaseApp.initializeApp(options);
    }
  }

  @Bean
  @DependsOn(value = "createFireBaseApp")
  public StorageClient createFirebaseStorage() {
    return StorageClient.getInstance();
  }

  @Bean
  @DependsOn(value = "createFireBaseApp")
  public FirebaseAuth createFirebaseAuth() {
    return FirebaseAuth.getInstance();
  }
}
