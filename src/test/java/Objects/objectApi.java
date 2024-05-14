package Objects;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.testng.annotations.Test;

public class objectApi {

    private static final String PUBLIC_KEY = "b6ac2510d99b0d6e1100021bf15af220";
    private static final String PRIVATE_KEY = "04b7dd47dd4c04114fc229f14eb3dce1d4e1ec63";
    private static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/comics/";
    private static final String urlPersonaje = "https://gateway.marvel.com:443/v1/public/";

    @Test
    public void obtieneTodosLosComics() throws NoSuchAlgorithmException {

        long timeStamp = Instant.now().getEpochSecond();
        String hash = generateHash(timeStamp, PRIVATE_KEY, PUBLIC_KEY);
        String apiUrl = "https://gateway.marvel.com/v1/public/comics?ts=" + timeStamp + "&apikey=" + PUBLIC_KEY
                + "&hash=" + hash;

        try {
            @SuppressWarnings("deprecation")
			java.net.URL url = new java.net.URL(apiUrl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == java.net.HttpURLConnection.HTTP_OK) {
                java.io.BufferedReader in = new java.io.BufferedReader(
                        new java.io.InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("Error al realizar la solicitud. Código de respuesta: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateHash(long timeStamp, String privateKey, String publicKey)
            throws NoSuchAlgorithmException {
        String input = timeStamp + privateKey + publicKey;
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public List<String> LlamadaDeComicsPorID(List<Integer> comicIds) {
        try {
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            long timestamp = Instant.now().getEpochSecond();
            String hash = generateHash(timestamp, PRIVATE_KEY, PUBLIC_KEY);

            List<String> responses = new ArrayList<>();

            for (Integer comicId : comicIds) {
                String url = BASE_URL + comicId + "?ts=" + timestamp + "&apikey=" + PUBLIC_KEY + "&hash=" + hash;
                java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                        .uri(new java.net.URI(url)).GET().build();
                java.net.http.HttpResponse<String> response = client.send(request,
                        java.net.http.HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && response.body().contains("\"id\":" + comicId)) {
                    responses.add(response.body());
                } else {
                    // Manejar cualquier error o respuesta no válida aquí
                    responses.add("Error obteniendo comic con ID " + comicId);
                }
            }

            return responses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String buscarPersonajePorNombre(List<String> nombres) {
        try {
            // Seleccionar un nombre aleatorio de la lista de nombres
            Random random = new Random();
            String nombreAleatorio = nombres.get(random.nextInt(nombres.size()));
            System.out.println("El nombre elegido es: " + nombreAleatorio);
            // Obtener el timestamp y hash
            long timestamp = Instant.now().getEpochSecond();
            String hash = generateHash(timestamp, PRIVATE_KEY, PUBLIC_KEY);

            
            String nombreCodificado = URLEncoder.encode(nombreAleatorio, "UTF-8");

            
            String apiUrl = urlPersonaje + "characters?name=" + nombreCodificado + "&ts=" + timestamp + "&apikey=" + PUBLIC_KEY
                    + "&hash=" + hash;

            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(new java.net.URI(apiUrl))
                    .GET().build();
            java.net.http.HttpResponse<String> response = client.send(request,
                    java.net.http.HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); 
            } else {
                return "Error al realizar la solicitud. Código de respuesta: " + response.statusCode();
            }
        } catch (IOException | InterruptedException | NoSuchAlgorithmException | URISyntaxException e) {
            e.printStackTrace();
            return "Error al buscar el personaje aleatorio";
        }}

    public List<String> obtenerSeriesEnCurso() {
        try {
            long timestamp = Instant.now().getEpochSecond();
            String hash = generateHash(timestamp, PRIVATE_KEY, PUBLIC_KEY);

            URI uri = new URIBuilder("https://gateway.marvel.com:443/v1/public/series")
                    .addParameter("ts", String.valueOf(timestamp))
                    .addParameter("apikey", PUBLIC_KEY)
                    .addParameter("hash", hash)
                    .addParameter("seriesType", "ongoing")
                    .build();

            org.apache.http.client.HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uri);
            org.apache.http.HttpResponse httpResponse = httpClient.execute(httpGet);

            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            JSONObject responseJson = new JSONObject(jsonResponse);

            if (responseJson.has("data")) {
                JSONArray resultsArray = responseJson.getJSONObject("data").getJSONArray("results");

                List<String> series = new ArrayList<>();
                for (int i = 0; i < resultsArray.length(); i++) {
                    series.add(resultsArray.getJSONObject(i).toString());
                }
                return series;
            } else {
                return Collections.singletonList("No se encontraron datos de series en curso.");
            }
        } catch (IOException | NoSuchAlgorithmException | URISyntaxException | JSONException e) {
            e.printStackTrace();
            return Collections.singletonList("Error al obtener las series en curso: " + e.getMessage());
        }
    }
}

