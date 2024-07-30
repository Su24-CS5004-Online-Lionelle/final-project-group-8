package group8.model.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for interacting with the Open Trivia Database API.
 */
public class APIUtils {

    /** URL for Open Trivia database. */
    private static final String BASE_URL = "https://opentdb.com/";

    /** API call limit. */
    private static final int BATCH_SIZE = 50; // Number of questions per batch

    /** Object mapper used for binding record to JSON. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /** Session token for API requests. */
    private static String sessionToken = null;    

    /**
     * Fetches trivia questions in batches with a 5 second delay between batches,
     * and returns a combined list of questions based on the requested amount.
     * 
     * Typically used for requests over the call limit (i.e., 50).
     *
     * @param amount Number of questions to fetch.
     * @param category Category of questions.
     * @param difficulty Difficulty level of questions.
     * @param type Type of questions (e.g., multiple choice).
     * @return Combined list of trivia questions.
     * @throws Exception if an error occurs during the requests or conversion.
     */
    public static List<TriviaQuestion> getBatchedQuestions(int amount, String category, String difficulty, String type) throws Exception {

        // Check for valid argument.
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Question Amount: Requires positive integer.");
        }

        List<TriviaQuestion> allQuestions = new ArrayList<>();
        int totalFetched = 0;

        // Calculate the number of batches needed.
        int numberOfBatches = (int) Math.ceil((double) amount / BATCH_SIZE);

        for (int batch = 0; batch < numberOfBatches; batch++) {
            // Calculate the number of questions needed for this batch.
            int batchAmount = Math.min(BATCH_SIZE, amount - totalFetched);

            // Fetch and add questions for the current batch.
            List<TriviaQuestion> batchQuestions = getQuestions(batchAmount, category, difficulty, type);
            allQuestions.addAll(batchQuestions);
            totalFetched += batchQuestions.size();

            // Wait for 5 seconds before fetching the next batch.
            if (numberOfBatches > 1) {
                TimeUnit.SECONDS.sleep(5);
            }
        }

        return allQuestions;
    }

    /**
     * Fetches and converts trivia questions; handles API errors based on the response code.
     *
     * @param amount Number of questions to fetch.
     * @param category Category of questions.
     * @param difficulty Difficulty level of questions.
     * @param type Type of questions (e.g., multiple choice).
     * @return List of TriviaQuestion records.
     * @throws Exception if an error occurs during the request or conversion.
     */
    public static List<TriviaQuestion> getQuestions(int amount, String category, String difficulty, String type) throws Exception {

        if (sessionToken == null) {
            throw new TriviaApiException("Token Not Set: Request token to start new session.");
        }

        JsonNode questionsJsonNode = fetchQuestions(amount, category, difficulty, type);
        int responseCode = questionsJsonNode.get("response_code").asInt();

        switch (responseCode) {
            case 0:
                return convertQuestions(questionsJsonNode);
            case 1:
                throw new TriviaApiException("No Results: The API doesn't have enough questions for your query.");
            case 2:
                throw new TriviaApiException("Invalid Parameter: Contains an invalid parameter.");
            case 3:
                throw new TriviaApiException("Token Not Found: Session Token does not exist.");
            case 4:
                throw new TriviaApiException("Token Empty: Session Token has returned all possible questions for the specified query. Resetting the Token is necessary."); // Unlikely, but should handle.
            case 5:
                throw new TriviaApiException("Rate Limit: Too many requests have occurred. Each IP can only access the API once every 5 seconds.");
            default:
                throw new TriviaApiException("Unknown Error: An unknown error occurred with response code " + responseCode);
        }

    }

    /**
     * Converts a JsonNode containing trivia questions to a list of TriviaQuestion records.
     *
     * @param jsonNode JsonNode containing the API response.
     * @return List of TriviaQuestion records.
     * @throws Exception if an error occurs during conversion.
     */
    public static List<TriviaQuestion> convertQuestions(JsonNode jsonNode) throws Exception {

        JsonNode resultsNode = jsonNode.get("results");
        if (resultsNode == null || !resultsNode.isArray()) {
            throw new IllegalArgumentException("Invalid JSON format: 'results' field is missing or not an array");
        }
        return objectMapper.readValue(resultsNode.toString(), new TypeReference<List<TriviaQuestion>>() {});

    }

    /**
     * Fetches trivia questions from the Open Trivia Database API.
     *
     * @param amount Number of questions to fetch.
     * @param category Category of questions.
     * @param difficulty Difficulty level of questions.
     * @param type Type of questions (e.g., multiple choice).
     * @return JsonNode containing the API response.
     * @throws Exception if an error occurs during the request.
     */
    public static JsonNode fetchQuestions(int amount, String category, String difficulty, String type) throws Exception {

        String urlString = BASE_URL + "api.php?amount=" + amount +
                            (category != null ? "&category=" + category : "") +
                            (difficulty != null ? "&difficulty=" + difficulty : "") +
                            (type != null ? "&type=" + type : "") +
                            (sessionToken != null ? "&token=" + sessionToken : "");;
        return sendGetRequest(urlString);

    }

    /**
     * Resets the current session token in the Open Trivia Database API.
     *
     * @throws Exception if an error occurs during the request.
     */
    public static void resetToken() throws Exception {
        if (sessionToken == null) {
            throw new TriviaApiException("No session token to reset. Please request a new token first.");
        }

        String urlString = BASE_URL + "api_token.php?command=reset&token=" + sessionToken;
        JsonNode response = sendGetRequest(urlString);
        int responseCode = response.get("response_code").asInt();

        if (responseCode == 0) {
            sessionToken = response.get("token").asText();
        } else {
            throw new TriviaApiException("Failed to reset token. Response code: " + responseCode);
        }
    }

    /**
     * Requests a new session token from the Open Trivia Database API.
     *
     * @throws Exception if an error occurs during the request.
     */
    public static void requestNewToken() throws Exception {
        String urlString = BASE_URL + "api_token.php?command=request";
        JsonNode response = sendGetRequest(urlString);
        int responseCode = response.get("response_code").asInt();

        if (responseCode == 0) {
            sessionToken = response.get("token").asText();
        } else {
            throw new TriviaApiException("Failed to request new token. Response code: " + responseCode);
        }
    }

    /**
     * Sends a GET request to the specified URL and returns the response as a JsonNode.
     *
     * @param urlString URL to send the GET request to.
     * @return JsonNode containing the response.
     * @throws Exception if an error occurs during the request.
     */
    private static JsonNode sendGetRequest(String urlString) throws Exception {

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return objectMapper.readTree(content.toString());
        } else {
            throw new RuntimeException("Failed: HTTP error code: " + responseCode);
        }
    }

    /**
     * Record to represent each trivia question.
     */
    public static record TriviaQuestion(

        String type,
        String difficulty,
        String category,
        String question,
        @JsonProperty("correct_answer") String correctAnswer,
        @JsonProperty("incorrect_answers") List<String> incorrectAnswers) {}

    /**
     * Custom exception for API-related errors.
     */
    public static class TriviaApiException extends Exception {

        public TriviaApiException(String message) {
            super(message);
        }
    }

    /** 
     * Main method for testing.
     */
    public static void main(String[] args) {
        
        try {
            requestNewToken();
            System.out.println(sessionToken);
            List<TriviaQuestion> questions = getBatchedQuestions(1, null, null, null);
            System.out.println(questions.size());
            requestNewToken();
            System.out.println(sessionToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}