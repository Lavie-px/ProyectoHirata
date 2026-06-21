package Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OpenRouteService {

    private static final String API_KEY = "eyJvcmciOiI1YjNjZTM1OTc4NTExMTAwMDFjZjYyNDgiLCJpZCI6ImRlNzNmMTkwNjM2NDRhMzdhNjNmNDU5ZTdmZWYxYmQyIiwiaCI6Im11cm11cjY0In0=";

    // =========================================
    // OBTENER COORDENADAS
    // =========================================
    public double[] obtenerCoordenadas(
        String direccion) {

            try {

                String direccionCodificada= URLEncoder.encode(direccion,"UTF-8");

                String endpoint = "https://api.openrouteservice.org/geocode/search?" + "api_key=" + API_KEY + "&text=" + direccionCodificada;

                URL url = new URL(endpoint);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder response= new StringBuilder();

                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);

                }

                br.close();

                ObjectMapper mapper = new ObjectMapper();

                JsonNode root = mapper.readTree(response.toString());

                if (root.path("features").size() == 0) {
                    return null;
                }

                JsonNode coords = root.path("features").get(0).path("geometry").path("coordinates");

                double longitud = coords.get(0).asDouble();

                double latitud = coords.get(1).asDouble();

                return new double[]{
                    latitud,
                    longitud
                };

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }
    }
    
    // CALCULAR DISTANCIA
    public double calcularDistancia(
        String origen,
        String destino) {

        try {

            double[] inicio = obtenerCoordenadas(origen);

            double[] fin = obtenerCoordenadas(destino);

            if (inicio == null || fin == null) {
                return -1;

            }

            URL url = new URL("https://api.openrouteservice.org/v2/directions/driving-hgv");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization",API_KEY);

            conn.setRequestProperty("Content-Type","application/json");

            conn.setDoOutput(true);

            String jsonBody
                    = "{ \"coordinates\": ["
                    + "[" + inicio[1] + "," + inicio[0] + "],"
                    + "[" + fin[1] + "," + fin[0] + "]"
                    + "]}";

            OutputStream os = conn.getOutputStream();

            os.write(jsonBody.getBytes());

            os.flush();

            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);

            }

            br.close();

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response.toString());

            double distanciaMetros = root.path("routes").get(0).path("summary").path("distance").asDouble();

            return distanciaMetros / 1000;

        } catch (Exception e) {

            e.printStackTrace();
            return -1;
        }
    }
}
