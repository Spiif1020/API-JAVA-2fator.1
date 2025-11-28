package com.Eric.APIv2.Servece;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.Eric.APIv2.model.TokenFirebase;
import com.Eric.APIv2.Servece.Transaction;
import com.Eric.APIv2.Servece.PurchaseRequest;
import com.Eric.APIv2.Servece.TransactionResponse;
import com.Eric.APIv2.repository.TokenRepository;
import com.Eric.APIv2.Servece.TransactionRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private FcmService fcmService;

 
    // Inicia compra → envia push
    @PostMapping("/purchase")
    public Transaction purchase(@RequestBody PurchaseRequest request) {
        System.out.println("\nNova requisição de compra recebida!");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Valor: " + request.getAmount());

    String token = tokenRepository.findByCardNumberIgnoreCase(request.getCardNumber())
        .map(TokenFirebase::getToken)
        .or(() -> tokenRepository.findByEmailIgnoreCase(request.getEmail()).map(TokenFirebase::getToken))
        .orElse(null);

        Transaction tx = new Transaction(
                request.getCardNumber(),
                request.getAmount(),
                request.getEmail(),
                token
        );

        tx.setEstabelecimento(request.getEstabelecimento());
        tx.setUf(request.getUf());
        tx.setDataHora(LocalDateTime.now());
        tx.setStatus("Pendente");

        transactionRepository.save(tx);

        if (token != null) {
            System.out.println("Token encontrado! Enviando notificação...");
            fcmService.sendPurchaseNotification(tx, token);
        } else {
            System.out.println("Nenhum token encontrado para o email: " + request.getEmail());
        }

        return tx;
    }

    // Usuário aprova ou rejeita

  @PostMapping("/transactions/{id}/decision")
public Transaction decide(@PathVariable Long id, @RequestParam("aprovado") boolean approved) {

        Transaction tx = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));

        tx.setStatus(approved ? "Aprovado" : "Rejeitado");
        transactionRepository.save(tx);

        System.out.println(" Transação " + id + " foi " + tx.getStatus());

        // Busca token do usuário
        tokenRepository.findByCardNumberIgnoreCase(tx.getCardNumber())
                .map(TokenFirebase::getToken)
                .ifPresentOrElse(
                        token -> fcmService.sendDecisionNotification(tx, token),
                        () -> System.out.println("Nenhum token FCM encontrado para o email: " + tx.getEmail())
                );

        return tx;
    }

 
    // Lista transações
    @GetMapping("/transactions")
    public List<TransactionResponse> list() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter hf = DateTimeFormatter.ofPattern("HH:mm");

        return transactionRepository.findAll().stream()
                .map(tx -> new TransactionResponse(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getStatus(),
                        tx.getDataHora().format(df),
                        tx.getDataHora().format(hf),
                        tx.getEstabelecimento(),
                        tx.getUf()
                ))
                .toList();
    }

    // Buscar por token

    @GetMapping("/transactions/byToken")
    public List<Transaction> getByToken(@RequestParam String token) {
        return transactionRepository.findByToken(token);
    }

 
    // Criar transação manual

    @PostMapping("/transactions")
    public Transaction create(@RequestBody Transaction tx) {
        tx.setDataHora(LocalDateTime.now());
        return transactionRepository.save(tx);
    }


    // DEBUG — lista tokens

    @GetMapping("/debug/tokens")
    public List<TokenFirebase> getTokens() {
        return tokenRepository.findAll();
    }
}
