package com.Eric.APIv2.Servece;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private Double amount;
    private String email;
    private String status;
    private String estabelecimento;
    private String uf;
    private LocalDateTime dataHora;

    private String token;

    public Transaction() {
        this.status = "Pendente";
        this.dataHora = LocalDateTime.now();
    }

    public Transaction(String cardNumber, Double amount, String email, String token) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.email = email;
        this.status = "Pendente";
        this.dataHora = LocalDateTime.now();
        this.token = token;
    }

    // Getters
    public Long getId() { 
        return id; }

    public String getCardNumber() { 
        return cardNumber; }

    public Double getAmount() { 
        return amount; }

    public String getEmail() { 
        return email; }

    public String getStatus() {
         return status; }

    public String getEstabelecimento() {
         return estabelecimento; }

    public String getUf() { 
        return uf; }

    public LocalDateTime getDataHora() {
         return dataHora; }

    public String getToken() {
         return token; }


    // Setters
    public void setId(Long id) { 
        this.id = id; }

    public void setCardNumber(String cardNumber) {
         this.cardNumber = cardNumber; }
    public void setAmount(Double amount) { 
        this.amount = amount; }

    public void setEmail(String email) { 
        this.email = email; }

    public void setStatus(String status) { 
        this.status = status; }

    public void setEstabelecimento(String estabelecimento) {
         this.estabelecimento = estabelecimento; }

    public void setUf(String uf) {
         this.uf = uf; }

    public void setDataHora(LocalDateTime dataHora) {
         this.dataHora = dataHora; }
         
    public void setToken(String token) { 
        this.token = token; }
}
