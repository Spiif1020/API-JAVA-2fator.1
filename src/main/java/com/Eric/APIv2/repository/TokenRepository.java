package com.Eric.APIv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Eric.APIv2.model.TokenFirebase; // OU TokenEntity — depende de qual nome sua entidade realmente tem

@Repository
public interface TokenRepository extends JpaRepository<TokenFirebase, Long> {
   Optional<TokenFirebase>  findByToken(String token);
    TokenFirebase findByEmail(String email);
    TokenFirebase findByCardNumber(String cardNumber);
     Optional<TokenFirebase> findByCardNumberIgnoreCase(String cardNumber);
    
    Optional<TokenFirebase> findByEmailIgnoreCase(String email);

  
}
