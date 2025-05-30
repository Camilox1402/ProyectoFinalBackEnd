package co.edu.unbosque.utils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.entity.RawgGame;
import co.edu.unbosque.entity.RawgGameResponse;
@Service
public class RawgIntegrationService {

    private static final String RAWG_API_KEY = "41ee0786c4114141b09f67b3cf7cae8a";
    private static final String RAWG_BASE_URL = "https://api.rawg.io/api/games";

    public RawgGame fetchGameData(String gameName) {
        try {
            String apiUrl = RAWG_BASE_URL + "?key=" + RAWG_API_KEY + "&search=" + gameName.replace(" ", "%20");
            String jsonResponse = ExternalHTTPRequestHandler.doGetAndParse(apiUrl);

            Gson gson = new Gson();
            RawgGameResponse response = gson.fromJson(jsonResponse, RawgGameResponse.class);

            if (response.getResults() != null && !response.getResults().isEmpty()) {
                return response.getResults().get(0);
            }
        } catch (Exception e) {
            System.out.println("Error fetching game data from RAWG: " + e.getMessage());
        }
        return null;
    }
}