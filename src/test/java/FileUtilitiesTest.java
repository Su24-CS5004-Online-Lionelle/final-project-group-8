package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;
import group8.model.TriviaQuestion;
import group8.model.FileUtilities;

class FileUtilitiesTest {

    // List to hold test trivia questions
    private List<TriviaQuestion> testQuestions;
    // Stream to capture console output for testing
    private ByteArrayOutputStream consoleOutput;
    // Store the original System.out for restoration after tests
    private final PrintStream originalOut = System.out;

    @TempDir
    Path tempDir;

    //Set up Trivia questions and captures output before each test.
    @BeforeEach
    public void setUp() {
        // Create sample trivia questions
        TriviaQuestion question1 = new TriviaQuestion(
                QuestionType.BOOLEAN, Difficulty.HARD, Category.HISTORY,
                "The Berlin Wall fell in 1989.", "True", List.of("False"));

        TriviaQuestion question2 = new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.EASY, Category.SPORTS,
                "Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?",
                "Mike Tyson", List.of("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"));

        TriviaQuestion question3 = new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.MEDIUM, Category.ART,
                "The painting 'The Starry Night' by Vincent van Gogh was part of which art movement?",
                "Post-Impressionism", List.of("Romanticism", "Neoclassical", "Impressionism"));

        testQuestions = Arrays.asList(question1, question2, question3);

        // Set up console output capture
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
    }

    //Restore the original System.out after each test.
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Tests Saving questions
    @Test
    void testSaveTrivia() throws IOException {
        String folderName = tempDir.toString();

        // Save trivia questions
        FileUtilities.saveTrivia(testQuestions, folderName);

        // Define paths for created files
        Path jsonPath = tempDir.resolve("trivia.json");
        Path questionsPath = tempDir.resolve("questions.txt");
        Path answersPath = tempDir.resolve("answers.txt");

        // Read contents of created files
        String jsonContent = Files.readString(jsonPath);
        String questionsContent = Files.readString(questionsPath);
        String answersContent = Files.readString(answersPath);

        // Checking contents of questions file
        assertTrue(questionsContent.contains("TRIVIA QUESTIONS:"));
        assertTrue(questionsContent.contains("1. The Berlin Wall fell in 1989."));
        assertTrue(questionsContent.contains("2. Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?"));
        assertTrue(questionsContent.contains("3. The painting 'The Starry Night' by Vincent van Gogh was part of which art movement?"));

        // Checking contents of answers file
        assertTrue(answersContent.contains("TRIVIA ANSWERS:"));
        assertTrue(answersContent.contains("1. Correct Answer: True"));
        assertTrue(answersContent.contains("   Incorrect Answers: False"));
        assertTrue(answersContent.contains("2. Correct Answer: Mike Tyson"));
        assertTrue(answersContent.contains("   Incorrect Answers: Roy Jones Jr., Evander Holyfield, Lennox Lewis"));
        assertTrue(answersContent.contains("3. Correct Answer: Post-Impressionism"));
        assertTrue(answersContent.contains("   Incorrect Answers: Romanticism, Neoclassical, Impressionism"));

        // Checking contents of JSON file
        assertTrue(jsonContent.contains("\"type\" : \"boolean\""));
        assertTrue(jsonContent.contains("\"difficulty\" : \"hard\""));
        assertTrue(jsonContent.contains("\"category\" : \"History\""));
        assertTrue(jsonContent.contains("\"question\" : \"The Berlin Wall fell in 1989.\""));
        assertTrue(jsonContent.contains("\"correct_answer\" : \"True\""));
    }

    // Test saving null trivia questions.
    @Test
    void testSaveTriviaNull() {
        String folderName = tempDir.toString();

        testQuestions = null;
        // Checking that error is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            FileUtilities.saveTrivia(testQuestions, folderName);
        });
    }

    // Test loading trivia questions from a file.
    @Test
    void testLoadTrivia() throws IOException {
        String folderName = tempDir.toString();

        // Save trivia questions
        FileUtilities.saveTrivia(testQuestions, folderName);

        // Load trivia questions
        List<TriviaQuestion> loadedQuestions = FileUtilities.loadTrivia(folderName + File.separator + "trivia.json");

        // Checking first loaded question
        TriviaQuestion firstQuestion = loadedQuestions.get(0);
        assertEquals(QuestionType.BOOLEAN, firstQuestion.type());
        assertEquals(Difficulty.HARD, firstQuestion.difficulty());
        assertEquals(Category.HISTORY, firstQuestion.category());
        assertEquals("The Berlin Wall fell in 1989.", firstQuestion.question());
        assertEquals("True", firstQuestion.correctAnswer());
        assertEquals(List.of("False"), firstQuestion.incorrectAnswers());

        // Checking second loaded question
        TriviaQuestion secondQuestion = loadedQuestions.get(1);
        assertEquals(QuestionType.MULTIPLE, secondQuestion.type());
        assertEquals(Difficulty.EASY, secondQuestion.difficulty());
        assertEquals(Category.SPORTS, secondQuestion.category());
        assertEquals("Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?", secondQuestion.question());
        assertEquals("Mike Tyson", secondQuestion.correctAnswer());
        assertEquals(Arrays.asList("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"), secondQuestion.incorrectAnswers());
    }

    // Test loading trivia questions from an empty file.
    @Test
    void testLoadTriviaEmptyFile() throws IOException {
        Path emptyFilePath = tempDir.resolve("trivia.json");
        Files.createFile(emptyFilePath);

        assertThrows(IllegalArgumentException.class, () -> {
            FileUtilities.loadTrivia(emptyFilePath.toString());
        });
    }
}