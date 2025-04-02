package utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequestHandler {
	public static void deleteUser(String email) throws Exception {
		String BASE_URL = ConfigReader.getProperty("backend_url");
		String token = ConfigReader.getProperty("deleteUserToken");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/api/users/admin/delete/"+email))
                .DELETE()
                .header("Authorization", "Bearer "+ token)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}

