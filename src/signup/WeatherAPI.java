package signup;

import org.jfree.data.json.impl.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
public class WeatherAPI {
    private static final String API_KEY = "IPI23Hw4SWeWR55b2mT3TpQ2oqS4WHLF";
    private static final String API_URL = "https://api.tomorrow.io/v4/timelines?location=40.75872069597532,-73.98529171943665&fields=temperature&timesteps=1h&units=metric&apikey=IPI23Hw4SWeWR55b2mT3TpQ2oqS4WHLF";

    //import org.json.JSONObject;

 // ...

    public static String getWeatherInfo(String location) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(("https://api.tomorrow.io/v4/weather/realtime?location=" + location + "&apikey=IPI23Hw4SWeWR55b2mT3TpQ2oqS4WHLF" )))
                    .header("accept", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
