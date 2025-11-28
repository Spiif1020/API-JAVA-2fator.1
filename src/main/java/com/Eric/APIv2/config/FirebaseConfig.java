package com.Eric.APIv2.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = null;

        
            String firebaseConfigEnv = System.getenv("FIREBASE_CONFIG");
            if (firebaseConfigEnv != null && !firebaseConfigEnv.isBlank()) {
                System.out.println("Detectado ambiente Render → carregando Firebase via variável de ambiente...");
                serviceAccount = new ByteArrayInputStream(firebaseConfigEnv.getBytes(StandardCharsets.UTF_8));
            }

            // Inicializa o Firebase se ainda não estiver ativo
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase inicializado com sucesso!");
            } else {
                System.out.println("Firebase já estava inicializado — ignorando nova inicialização.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

