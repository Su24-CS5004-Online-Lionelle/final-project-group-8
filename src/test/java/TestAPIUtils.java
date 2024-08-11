import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.model.helpers.APIUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the APIUtils class.
 */
public class TestAPIUtils {

    /** Object Mapper. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Tests the getBatchedQuestions method for different scenarios.
     * 
     * @throws Exception if an error occurs during the API request or processing.
     */
    @Test
    public void testGetBatchedQuestions() throws Exception {
        APIUtils.requestToken();
        APIUtils.requestCategories();

        // Testing batch process of twenty questions, general knowledge, easy, multiple.
        int amount = 20;
        Enums.Category category = Enums.Category.GENERAL_KNOWLEDGE;
        Enums.Difficulty difficulty = Enums.Difficulty.EASY;
        Enums.QuestionType type = Enums.QuestionType.MULTIPLE;

        List<TriviaQuestion> questions = APIUtils.getBatchedQuestions(amount, category, difficulty, type);

        assertNotNull(questions);
        assertEquals(amount, questions.size());

        // Nullify everything except amount; should still work (randomly).
        questions = APIUtils.getBatchedQuestions(amount, null, null, null);

        assertNotNull(questions);
        assertEquals(amount, questions.size());

        // Testing batch process of fifty-one questions, general knowledge, easy, multiple.
        amount = 51;
        questions = APIUtils.getBatchedQuestions(amount, category, difficulty, type);
        
        assertNotNull(questions);
        assertEquals(amount, questions.size());

        // Testing batch process of negative one questions, general knowledge, easy, multiple.
        assertThrows(IllegalArgumentException.class, () -> {
            APIUtils.getBatchedQuestions(-1, category, difficulty, type);
        });
    }

    /**
     * Tests the getSingleQuestions method for different scenarios.
     * 
     * @throws Exception if an error occurs during the API request or processing.
     */
    @Test
    public void testGetSingleQuestions() throws Exception {
        APIUtils.requestToken();
        APIUtils.requestCategories();

        // Testing normal process of twenty questions, general knowledge, easy, multiple.
        int amount = 20;
        Enums.Category category = Enums.Category.GENERAL_KNOWLEDGE;
        Enums.Difficulty difficulty = Enums.Difficulty.EASY;
        Enums.QuestionType type = Enums.QuestionType.MULTIPLE;

        List<TriviaQuestion> questions = APIUtils.getSingleQuestions(amount, category, difficulty, type);

        assertNotNull(questions);
        assertEquals(amount, questions.size());

        // Nullify everything except amount; should still work (randomly).
        questions = APIUtils.getSingleQuestions(amount, null, null, null);

        assertNotNull(questions);
        assertEquals(amount, questions.size());

        // Testing normal process of fifty-one questions, general knowledge, easy, multiple.
        assertThrows(APIUtils.TriviaApiException.class, () -> {
            APIUtils.getSingleQuestions(51, category, difficulty, type);
        });
    }

    /**
     * Tests the convertQuestions method with valid and invalid JSON nodes.
     * 
     * @throws Exception if an error occurs during JSON processing.
     */
    @Test
    public void testConvertQuestions() throws Exception {
        // Valid JSON node.
        JsonNode mockJsonNode = createMockJsonNode();
        List<TriviaQuestion> questions = APIUtils.convertQuestions(mockJsonNode);

        assertNotNull(questions);
        assertEquals(1, questions.size());
        assertEquals("Question 1", questions.get(0).question());

        // Invalid JSON node.
        JsonNode invalidJsonNode = objectMapper.createObjectNode();
        assertThrows(IllegalArgumentException.class, () -> {
            APIUtils.convertQuestions(invalidJsonNode);
        });
    }

    /**
     * Tests the fetchQuestions method to ensure it returns a valid JSON response.
     * 
     * @throws Exception if an error occurs during the API request.
     */
    @Test
    public void testFetchQuestions() throws Exception {
        int amount = 10;
        String category = "9";
        String difficulty = "easy";
        String type = "multiple";

        JsonNode response = APIUtils.fetchQuestions(amount, category, difficulty, type);

        assertNotNull(response);
        assertEquals(0, response.get("response_code").asInt());
    }

    /**
     * Tests the requestToken method to ensure a session token is retrieved.
     * 
     * @throws Exception if an error occurs during the API request.
     */
    @Test
    void testRequestToken() throws Exception {
        // Ensure there is no session token on startup.
        assertNull(APIUtils.getSessionToken());

        // Request token.
        APIUtils.requestToken();

        // Ensure this token is no longer null.
        assertNotNull(APIUtils.getSessionToken());
    }

    /**
     * Tests the resetToken method to ensure the session token is reset properly.
     * 
     * @throws Exception if an error occurs during the API request.
     */
    @Test
    void testResetToken() throws Exception {
        APIUtils.requestToken();
        APIUtils.requestCategories();

        // Art only has 33 questions in the tank; therefore, a session reset and re-pull should not return an error.
        APIUtils.getBatchedQuestions(33, Enums.Category.ART, null, null);
        APIUtils.resetToken();
        APIUtils.getBatchedQuestions(33, Enums.Category.ART, null, null);
    }

    /**
     * Tests the requestCategories method to ensure the categories are correctly retrieved.
     * 
     * @throws Exception if an error occurs during the API request
     */
    @Test
    void testRequestCategories() throws Exception {
        // Request categories on startup.
        APIUtils.requestCategories();

        Map<String, String> actualCategoryMap = APIUtils.getCategoryMap();
        HashMap<String, String> expectedCategoryMap = new HashMap<>();

        // List out expected values.
        expectedCategoryMap.put("General Knowledge", "9");
        expectedCategoryMap.put("Entertainment: Books", "10");
        expectedCategoryMap.put("Entertainment: Film", "11");
        expectedCategoryMap.put("Entertainment: Music", "12");
        expectedCategoryMap.put("Entertainment: Musicals & Theatres", "13");
        expectedCategoryMap.put("Entertainment: Television", "14");
        expectedCategoryMap.put("Entertainment: Video Games", "15");
        expectedCategoryMap.put("Entertainment: Board Games", "16");
        expectedCategoryMap.put("Science & Nature", "17");
        expectedCategoryMap.put("Science: Computers", "18");
        expectedCategoryMap.put("Science: Mathematics", "19");
        expectedCategoryMap.put("Mythology", "20");
        expectedCategoryMap.put("Sports", "21");
        expectedCategoryMap.put("Geography", "22");
        expectedCategoryMap.put("History", "23");
        expectedCategoryMap.put("Politics", "24");
        expectedCategoryMap.put("Art", "25");
        expectedCategoryMap.put("Celebrities", "26");
        expectedCategoryMap.put("Animals", "27");
        expectedCategoryMap.put("Vehicles", "28");
        expectedCategoryMap.put("Entertainment: Comics", "29");
        expectedCategoryMap.put("Science: Gadgets", "30");
        expectedCategoryMap.put("Entertainment: Japanese Anime & Manga", "31");
        expectedCategoryMap.put("Entertainment: Cartoon & Animations", "32");

        // Ensure maps are identical.
        assertEquals(expectedCategoryMap, actualCategoryMap);
    }

    /**
     * Tests the htmlConverter method to ensure HTML encoding is correctly converted.
     */
    @Test
    void testHtmlConverter() {
        // Sample HTML encoding.
        String htmlString = "&quot;API&quot; &amp; &lt;Trivia&gt;";
        String expected = "\"API\" & <Trivia>";
        String result = APIUtils.htmlConverter(htmlString);
        assertEquals(expected, result);
    }

    /**
     * Tests the sendGetRequest method to ensure it handles retries correctly.
     * 
     * @throws Exception if an error occurs during the request.
     */
    @Test
    void testSendGetRequest() throws Exception {

        // Make first request foo questions.
        APIUtils.sendGetRequest("https://opentdb.com/api.php?amount=" + 25);

        // Capture the time after the first request
        long firstEndTime = System.currentTimeMillis();

        // Make immediate second request of questions.
        // Will return 429 error in background but should NOT fail due to retry logic.
        APIUtils.sendGetRequest("https://opentdb.com/api.php?amount=" + 25);

        // Capture time after the second request.
        long secondEndTime = System.currentTimeMillis();

        // Ensure at least 5.5 seconds has passed between the first and second requests
        assertTrue(secondEndTime - firstEndTime >= 5500);

    }

    /**
     * Tests the TriviaApiException class to ensure it handles custom exceptions correctly.
     */
    @Test
    void testTriviaApiException() {
        try {
            throw new APIUtils.TriviaApiException("Test exception");
        } catch (APIUtils.TriviaApiException e) {
            assertEquals("Test exception", e.getMessage());
        }
    }

    /**
     * Helper method to create a mock JSON node for testing.
     * 
     * @return a JsonNode with mock data.
     * @throws Exception if an error occurs during JSON creation.
     */
    private JsonNode createMockJsonNode() throws Exception {
        String jsonString = "{ \"results\": ["
                + "{ "
                + "\"category\": \"General Knowledge\", "
                + "\"type\": \"multiple\", "
                + "\"difficulty\": \"easy\", "
                + "\"question\": \"Question 1\", "
                + "\"correct_answer\": \"Correct Answer\", "
                + "\"incorrect_answers\": ["
                + "\"Incorrect Answer 1\", "
                + "\"Incorrect Answer 2\", "
                + "\"Incorrect Answer 3\""
                + "] "
                + "} "
                + "], "
                + "\"response_code\": 0 "
                + "}";
        return objectMapper.readTree(jsonString);
    }
}
