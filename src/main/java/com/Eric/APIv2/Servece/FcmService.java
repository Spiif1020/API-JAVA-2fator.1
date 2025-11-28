package com.Eric.APIv2.Servece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Eric.APIv2.Servece.Transaction;
import com.Eric.APIv2.repository.TokenRepository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FcmService {

    @Autowired
    private TokenRepository tokenRepository;

    

    // 1. Notificação de nova compra
    public void sendPurchaseNotification(Transaction tx, String token) {
        String title = "Nova Transação Detectada";
        String body = String.format(
                "Compra de R$ %.2f em %s (%s)",
                tx.getAmount(),
                tx.getEstabelecimento(),
                tx.getUf()
        );

        sendNotification(title, body, token, tx.getId(), "Pendente");
    }

    // 2. Notificação de decisão

    public void sendDecisionNotification(Transaction tx, String token) {
        String title = "";
        if (tx.getStatus().equalsIgnoreCase("Aprovado")) {
            System.out.println("Compra Aprovada");
                } else {
                System.out.println("Compra Rejeitada");
                    }
        String body = String.format(
                "Transação de R$ %.2f foi %s",
                tx.getAmount(),
                tx.getStatus()
        );

        sendNotification(title, body, token, tx.getId(), tx.getStatus());
    }


    // 3. Método central de envio e remove tokens inválidos
    private void sendNotification(String title, String body, String token, Long txId, String status) {
        try {
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(
                            Notification.builder()
                                    .setTitle(title)
                                    .setBody(body)
                                    .build()
                    )
                    // Extras enviados para o app Android
                    .putData("transactionId", String.valueOf(txId))
                    .putData("status", status)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(" Notificação enviada! ID: " + response);

        } catch (FirebaseMessagingException e) {
            System.err.println(" Erro ao enviar notificação: " + e.getMessage());

            String code = "";

            if (e.getMessagingErrorCode() != null) {
             code = e.getMessagingErrorCode().name();
                 } else {
                  code = "";
                }
    }
}
}