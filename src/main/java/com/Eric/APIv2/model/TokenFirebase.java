package com.Eric.APIv2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tokens_firebase")
public class TokenFirebase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    
    private String email;

    @Column(columnDefinition = "TEXT")
    private String token;

    @Column(nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public TokenFirebase() {}

    public TokenFirebase(String email, String token,String cardNumber) {
        this.email = email;
        this.token = token;
        this.cardNumber = cardNumber;
    }

    // Getters e Setters
    public Long getId() { 
        return id; }

    public String getEmail() { 
        return email; }

    public String getToken() { 
        return token; }

    public LocalDateTime getDataCadastro() { 
        return dataCadastro; }

    public String getCartao() {
         return cardNumber; }

    public void setEmail(String email) { 
        this.email = email; }
        
    public void setToken(String token) { 
        this.token = token; }

    public void setCartao(String cartao) {
         this.cardNumber = cartao; }
}
