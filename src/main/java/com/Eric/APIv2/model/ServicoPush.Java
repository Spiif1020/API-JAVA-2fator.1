import okhttp3.*;
import org.json.JSONObject;
import javax.swing.jOptionPane;

public class PushService {
    private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "8hmi0X2OIUdZz_sbJZQe1oJe0xSV5IaRJBzOSAK7J1U";

    public static void enviarNotificacao(String tokenDispositivo, String titulo, String mensagem) {
        OkHttpClient client = new OkHttpClient();

        try {
            JSONObject json = new JSONObject();
            json.put("to", tokenDispositivo); 
            
            JSONObject notificacao = new JSONObject();
            notificacao.put("title", titulo);
            notificacao.put("body", mensagem);

            json.put("notification", notificacao);

            RequestBody body = RequestBody.create(
                    json.toString(),
                    MediaType.get("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(FCM_URL)
                    .post(body)
                    .addHeader("Authorization", "key=" + SERVER_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println("Resposta FCM: " + response.body().string());

        } catch (Exception e) {
            jOptionPane.ShowMessageDialog(null,"Erro deu problema no serviço puxe")
        }
    }
}
