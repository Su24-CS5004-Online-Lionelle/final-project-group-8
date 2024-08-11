package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import student.controller.MainController;
import student.model.Enums;
import student.model.TriviaQuestion;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMainController {
    private MainController mainController;

    @BeforeEach
    public void setUp() throws Exception {
        mainController = new MainController();
    }

// API PULLS ARE COMMENTED OUT TO PROVIDE A QUICKER BUILD TIME

//    @Test
//    public void testGenerateApiList() throws Exception {
//        List<Enums.Category> categories = List.of(Enums.Category.HISTORY, Enums.Category.SCIENCE_NATURE);
//
//        mainController.generateApiList(categories);
//
//        List<TriviaQuestion> apiQuestions = mainController.getApiQuestions();
//        assertFalse(apiQuestions.isEmpty(), "API question list should not be empty after generating questions");
//        for (TriviaQuestion question : apiQuestions) {
//            assertTrue(categories.contains(question.category()), "Question should belong to one of the selected categories");
//        }
//    }

//    @Test
//    public void testGetAllCategories() throws Exception {
//        List<Enums.Category> categories = List.of(Enums.Category.ART, Enums.Category.CELEBRITIES);
//        mainController.generateApiList(categories);
//        Set<Enums.Category> controllerCategories = mainController.getAllCategories();
//        assertNotNull(controllerCategories, "Categories should not be null");
//        assertFalse(controllerCategories.isEmpty(), "Categories set should contain elements");
//
//        // Verify that specific known categories are present
//        assertTrue(controllerCategories.contains(Enums.Category.ART), "Category set should contain HISTORY");
//        assertTrue(controllerCategories.contains(Enums.Category.CELEBRITIES), "Category set should contain SCIENCE_NATURE");
//    }

//    @Test
//    public void testGetFormattedApiQuestionsWithFilters() throws Exception {
//        // Generate API list with matching questions
//        mainController.generateApiList(List.of(Enums.Category.HISTORY));
//
//        Set<Enums.QuestionType> typeFilters = Set.of();
//        Set<Enums.Difficulty> difficultyFilters = Set.of(Enums.Difficulty.EASY);
//        Set<Enums.Category> categoryFilters = Set.of(Enums.Category.HISTORY);
//
//        List<TriviaQuestion> filteredQuestions = mainController.getFormattedApiQuestions(typeFilters, difficultyFilters, categoryFilters);
//
//        assertFalse(filteredQuestions.isEmpty(), "Filtered question list should not be empty after generating matching questions");
//
//        // Verify that all questions match the filters
//        for (TriviaQuestion question : filteredQuestions) {
//            assertEquals(Enums.Difficulty.EASY, question.difficulty(), "Question should match the EASY difficulty filter");
//            assertEquals(Enums.Category.HISTORY, question.category(), "Question should belong to the HISTORY category");
//        }
//    }

//    @Test
//    public void testGetFormattedApiQuestionsWithoutFilters() throws Exception {
//        mainController.generateApiList(List.of(Enums.Category.GEOGRAPHY, Enums.Category.MYTHOLOGY));
//
//        List<TriviaQuestion> questions = mainController.getFormattedApiQuestions();
//        assertNotNull(questions, "Questions should not be null");
//        assertFalse(questions.isEmpty(), "Questions list should not be empty");
//
//        // Verify that all questions belong to the generated categories
//        for (TriviaQuestion question : questions) {
//            assertTrue(Set.of(Enums.Category.GEOGRAPHY, Enums.Category.MYTHOLOGY).contains(question.category()), "Question should belong to one of the generated categories");
//        }
//    }

//    @Test
//    public void testGetFormattedUserQuestions() throws Exception {
//        List<Enums.Category> categories = List.of(Enums.Category.SPORTS, Enums.Category.ANIMALS);
//        mainController.generateApiList(categories);
//
//        List<TriviaQuestion> apiQuestions = mainController.getFormattedApiQuestions();
//        TriviaQuestion question1 = apiQuestions.get(0);
//        TriviaQuestion question2 = apiQuestions.get(30);
//
//        mainController.moveToUserCollection(question1);
//        mainController.moveToUserCollection(question2);
//
//        // Sort by category in ascending order
//        List<TriviaQuestion> sortedQuestions = mainController.getFormattedUserQuestions(Enums.Field.CATEGORY, true);
//        assertNotNull(sortedQuestions, "Sorted questions should not be null");
//
//        // Verify the order of questions
//        assertEquals(Enums.Category.ANIMALS, sortedQuestions.get(0).category(), "First question should belong to ANIMALS category");
//        assertEquals(Enums.Category.SPORTS, sortedQuestions.get(1).category(), "Second question should belong to SPORTS category");
//    }

    @Test
    public void testLoadTriviaQuestions() throws IOException {
        String filePath = "src/test/resources/sample_trivia.json";

        // Ensure loading from a valid JSON file
        List<TriviaQuestion> loadedQuestions = mainController.loadTriviaQuestions(filePath);
        assertNotNull(loadedQuestions, "Loaded questions should not be null");
        assertFalse(loadedQuestions.isEmpty(), "Loaded question list should not be empty");

        // Verify that specific questions are loaded
        TriviaQuestion sampleQuestion = new TriviaQuestion(
                Enums.QuestionType.BOOLEAN,
                Enums.Difficulty.EASY,
                Enums.Category.ART,
                "Leonardo da Vinci's Mona Lisa does not have eyebrows.",
                "True",
                List.of("False")
        );
        assertTrue(loadedQuestions.contains(sampleQuestion), "Loaded questions should contain the sample question");

        // Test loading from a non-existent file and empty file
        assertThrows(IllegalArgumentException.class, () -> mainController.loadTriviaQuestions("invalid/path/to/file.json"), "Expected an IllegalArgumentException for a non-existent file");

        String emptyFilePath = "src/test/resources/empty_trivia.json";
        assertThrows(IllegalArgumentException.class, () -> mainController.loadTriviaQuestions(emptyFilePath), "Expected an IllegalArgumentException for an empty file");
    }

    @Test
    public void testSaveTrivia() throws IOException {
        String folderPath = "src/test/resources/trivia_save";
        String sampleFilePath = "src/test/resources/sample_trivia.json";

        // Load trivia questions from a sample JSON file
        List<TriviaQuestion> sampleQuestions = mainController.loadTriviaQuestions(sampleFilePath);
        assertFalse(sampleQuestions.isEmpty(), "Sample questions should not be empty");

        // Move one question to the user collection
        TriviaQuestion questionToMove = sampleQuestions.get(0);
        mainController.moveToUserCollection(questionToMove);

        // Save the user trivia collection to a folder
        mainController.saveTrivia(folderPath);

        // Load the saved trivia and check its content
        List<TriviaQuestion> savedQuestions = mainController.loadTriviaQuestions(folderPath + "/trivia.json");
        assertNotNull(savedQuestions, "Saved questions should not be null");
        assertFalse(savedQuestions.isEmpty(), "Saved questions should not be empty");
        assertTrue(savedQuestions.contains(questionToMove), "Saved trivia should contain the question that was added to the user collection");
    }

//    @Test
//    public void testMoveToUserCollection() throws Exception {
//        // Generate some API questions
//        mainController.generateApiList(List.of(Enums.Category.HISTORY));
//        List<TriviaQuestion> apiQuestions = mainController.getApiQuestions();
//        TriviaQuestion question = apiQuestions.get(0);
//
//        // Move the question to the user collection
//        mainController.moveToUserCollection(question);
//
//        // Verify that the question is now in the user collection and not in the API collection
//        assertTrue(mainController.getUserQuestions().contains(question), "Question should be in the user collection");
//        assertFalse(mainController.getApiQuestions().contains(question), "Question should not be in the API collection");
//    }

//    @Test
//    public void testMoveToApiCollection() throws Exception {
//        // Generate some API questions
//        mainController.generateApiList(List.of(Enums.Category.HISTORY));
//        List<TriviaQuestion> apiQuestions = mainController.getApiQuestions();
//        TriviaQuestion question = apiQuestions.get(0);
//
//        // Move the question to the user collection
//        mainController.moveToUserCollection(question);
//
//        mainController.moveToApiCollection(question);
//
//        // Verify that the question is now in the API collection and not in the user collection
//        assertTrue(mainController.getApiQuestions().contains(question), "Question should be in the API collection");
//        assertFalse(mainController.getUserQuestions().contains(question), "Question should not be in the user collection");
//    }

    @Test
    public void testIsDuplicateQuestion() throws IOException {
        // Load trivia questions from a sample JSON file
        List<TriviaQuestion> userCollection = mainController.loadTriviaQuestions("src/test/resources/sample_trivia.json");
        assertFalse(userCollection.isEmpty(), "Sample questions should not be empty");

        // Record the number of questions after the first load
        int initialSize = userCollection.size();

        // Load the same trivia questions again
        List<TriviaQuestion> duplicateLoad = mainController.loadTriviaQuestions("src/test/resources/sample_trivia.json");
        int newSize = duplicateLoad.size();

        // Test that the collection size has not increased after loading duplicates
        assertEquals(initialSize, newSize, "User collection size should not increase when loading duplicates");

        // Verify that all questions are still in the collection and no duplicates were added
        assertEquals(mainController.getUserQuestions().size(), initialSize, "User collection should only contain unique questions");

    }

}
