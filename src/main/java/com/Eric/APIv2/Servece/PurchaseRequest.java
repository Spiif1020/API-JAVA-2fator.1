package com.Eric.APIv2.Servece;

public class PurchaseRequest {
    private String cardNumber;
    private Double amount;
    private String email;
    private String estabelecimento;
    private String uf;

    // Getters e Setters
    public String getCardNumber() {
         return cardNumber; }
         
    public Double getAmount() {
         return amount; }

    public String getEmail() {
         return email; }

    public String getEstabelecimento() {
         return estabelecimento; }

    public String getUf() {
         return uf; }

    public void setCardNumber(String cardNumber) {
         this.cardNumber = cardNumber; }

    public void setAmount(Double amount) { 
        this.amount = amount; }

    public void setEmail(String email) {
         this.email = email; }

    public void setEstabelecimento(String estabelecimento) {
         this.estabelecimento = estabelecimento; }
         
    public void setUf(String uf) { 
        this.uf = uf; }
}
