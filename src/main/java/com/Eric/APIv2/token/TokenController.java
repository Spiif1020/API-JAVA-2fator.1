package com.Eric.APIv2.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Eric.APIv2.model.TokenFirebase;
import com.Eric.APIv2.repository.TokenRepository;

@RestController
@RequestMapping("/api/tokens")
@CrossOrigin("*") // libera acesso para o app Android
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    //  POST - recebe e salva token
    @PostMapping
    public ResponseEntity<Void> saveToken(@RequestBody TokenFirebase token) {
        tokenRepository.save(token);
        System.out.println(" Token salvo: " + token.getToken() + " para " + token.getEmail());
        return ResponseEntity.ok().build();
    }

    //  GET - lista tokens salvos
    @GetMapping
    public List<TokenFirebase> listarTokens() {
        return tokenRepository.findAll();
    }
}
