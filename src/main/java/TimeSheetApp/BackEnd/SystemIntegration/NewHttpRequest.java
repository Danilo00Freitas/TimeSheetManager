package TimeSheetApp.BackEnd.SystemIntegration;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class NewHttpRequest {
    private HttpClient client;
    private HttpRequest request;
    private  CompletableFuture<HttpResponse<String>> responseFuture;
    private Gson gson;

    public void newHttpRequest(){}
    public String sendHttpRequest(String uri) {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return responseFuture.thenApply(HttpResponse::body).join();
    }

    public CepInformationRec getAddressInfo (String cep){
        String uri = "http://viacep.com.br/ws/" + cep +"/json/";
        String cepRequest = sendHttpRequest(uri);
        this.gson = new Gson();
        return gson.fromJson(cepRequest,CepInformationRec.class);
    }

    public String getCpfInfo(String cpf, String birthDay){
        String uri = "https://api.infosimples.com/api/v2/consultas/receita-federal/" +
                "cpf?token=YjsJFYmL8N9saXaGJ3IMh2vmblAKQGOs-sVabLPR&timeout=600&cpf=" +
                cpf + "&birthdate=" + birthDay + "&origem=web";
        return sendHttpRequest(uri);
    }
}
