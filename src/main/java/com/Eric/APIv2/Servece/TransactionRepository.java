package com.Eric.APIv2.Servece;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Eric.APIv2.model.TokenFirebase;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
       // Buscar todas as transações de um usuário pelo token
    List<Transaction> findByToken(String token);

    // Buscar por email também
    List<Transaction> findByEmailIgnoreCase(String email);

    // Busca por numero do cartão
        TokenFirebase findByCardNumber(String cardNumber);
     Optional<TokenFirebase> findByCardNumberIgnoreCase(String cardNumber);
}

