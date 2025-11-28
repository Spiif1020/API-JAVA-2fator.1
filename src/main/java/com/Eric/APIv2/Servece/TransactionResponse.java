package com.Eric.APIv2.Servece;

public class TransactionResponse {
    private Long id;
    private Double amount;
    private String status;
    private String data;
    private String hora;
    private String estabelecimento;
    private String uf;

    public TransactionResponse(Long id, Double amount, String status, String data, String hora, String estabelecimento, String uf) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.data = data;
        this.hora = hora;
        this.estabelecimento = estabelecimento;
        this.uf = uf;
    }

    // Getters
    public Long getId() { 
        return id; }

    public Double getAmount() {
         return amount; }

    public String getStatus() { 
        return status; }

    public String getData() {
         return data; }

    public String getHora() {
         return hora; }

    public String getEstabelecimento() {
        return estabelecimento; }
        
    public String getUf() { 
        return uf; }
}