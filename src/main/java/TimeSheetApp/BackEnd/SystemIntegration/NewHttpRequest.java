package TimeSheetApp.BackEnd.SystemIntegration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class NewHttpRequest {
    private HttpClient client;
    private HttpRequest request;
    private  CompletableFuture<HttpResponse<String>> responseFuture;

    public void newHttpRequest(){}
    public String sendHttpRequest(String uri) {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        return responseFuture.thenApply(HttpResponse::body).join();
    }

    public String getAddressInfo (String cep){
        String uri = "http://viacep.com.br/ws/" + cep +"/json/";
        return sendHttpRequest(uri);
    }
}
