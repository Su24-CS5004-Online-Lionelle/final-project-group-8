package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import student.model.APITriviaCollection;
import student.model.Enums;
import student.model.TriviaQuestion;
import student.model.UserTriviaCollection;
import student.model.helpers.QuestionExchange;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuestionExchange {

    private APITriviaCollection apiCollection;
    private UserTriviaCollection userCollection;
    private QuestionExchange questionExchange;

    private static final List<TriviaQuestion> STATIC_QUESTIONS = Arrays.asList(
            new TriviaQuestion(Enums.QuestionType.BOOLEAN, Enums.Difficulty.EASY, Enums.Category.HISTORY, "Adolf Hitler was a german soldier in World War I.", "True", Arrays.asList("False")),
            new TriviaQuestion(Enums.QuestionType.MULTIPLE, Enums.Difficulty.MEDIUM, Enums.Category.HISTORY, "Which of his six wives was Henry VIII married to the longest?", "Catherine of Aragon", Arrays.asList("Anne Boleyn", "Jane Seymour", "Catherine Parr")),
            new TriviaQuestion(Enums.QuestionType.MULTIPLE, Enums.Difficulty.MEDIUM, Enums.Category.HISTORY, "What year did the Boxing Day earthquake & tsunami occur in the Indian Ocean?", "2004", Arrays.asList("2006", "2008", "2002")),
            new TriviaQuestion(Enums.QuestionType.MULTIPLE, Enums.Difficulty.MEDIUM, Enums.Category.HISTORY, "The Herero genocide was perpetrated in Africa by which of the following colonial nations?", "Germany", Arrays.asList("Britain", "Belgium", "France")),
            new TriviaQuestion(Enums.QuestionType.BOOLEAN, Enums.Difficulty.MEDIUM, Enums.Category.HISTORY, "Theodore Roosevelt Jr. was the only General involved in the initial assault on D-Day.", "True", Arrays.asList("False"))
    );

    @BeforeEach
    public void setUp() {
        apiCollection = new APITriviaCollection();
        userCollection = new UserTriviaCollection();
        questionExchange = new QuestionExchange(apiCollection, userCollection);

        // Add static questions to the API collection
        apiCollection.addQuestions(STATIC_QUESTIONS);
    }

    private void printCollections(String stage) {
        System.out.println(stage);
        System.out.println("API Collection: ");
        apiCollection.getAllQuestions().forEach(System.out::println);
        System.out.println("User Collection: ");
        userCollection.getAllQuestions().forEach(System.out::println);
        System.out.println("------------------------------------------------");
    }

    @Test
    public void testMoveToUserCollection() {
        printCollections("Before testMoveToUserCollection");
        TriviaQuestion question = STATIC_QUESTIONS.get(0);
        questionExchange.moveToUserCollection(question);
        printCollections("After testMoveToUserCollection");

        assertTrue(userCollection.getAllQuestions().contains(question));
        assertTrue(!apiCollection.getAllQuestions().contains(question));
    }

    @Test
    public void testMoveToApiCollection() {
        TriviaQuestion question = STATIC_QUESTIONS.get(1);
        questionExchange.moveToUserCollection(question);
        printCollections("Before testMoveToApiCollection");

        questionExchange.moveToApiCollection(question);
        printCollections("After testMoveToApiCollection");

        assertTrue(apiCollection.getAllQuestions().contains(question));
        assertTrue(!userCollection.getAllQuestions().contains(question));
    }

    @Test
    public void testMoveNonExistentQuestionToUser() {
        TriviaQuestion nonExistentQuestion = new TriviaQuestion(
                Enums.QuestionType.BOOLEAN, Enums.Difficulty.HARD, Enums.Category.GEOGRAPHY,
                "Is the earth flat?", "False", Arrays.asList("True")
        );

        questionExchange.moveToUserCollection(nonExistentQuestion);

        assertFalse(userCollection.getAllQuestions().contains(nonExistentQuestion));
        assertFalse(apiCollection.getAllQuestions().contains(nonExistentQuestion));
    }

    @Test
    public void testMoveNonExistentQuestionToApi() {
        TriviaQuestion nonExistentQuestion = new TriviaQuestion(
                Enums.QuestionType.BOOLEAN, Enums.Difficulty.HARD, Enums.Category.SCIENCE_NATURE,
                "Does water freeze at 0°C?", "True", Arrays.asList("False")
        );

        questionExchange.moveToApiCollection(nonExistentQuestion);

        assertFalse(userCollection.getAllQuestions().contains(nonExistentQuestion));
        assertFalse(apiCollection.getAllQuestions().contains(nonExistentQuestion));
    }

    @Test
    public void testMoveNullQuestionToUser() {

        questionExchange.moveToUserCollection(null);

        assertEquals(STATIC_QUESTIONS.size(), apiCollection.getAllQuestions().size());
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    @Test
    public void testMoveNullQuestionToApi() {

        questionExchange.moveToApiCollection(null);

        assertEquals(STATIC_QUESTIONS.size(), apiCollection.getAllQuestions().size());
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    @Test
    public void testMoveSameQuestionMultipleTimes() {
        TriviaQuestion question = STATIC_QUESTIONS.get(2);

        // Move the question to the user collection
        questionExchange.moveToUserCollection(question);

        // Attempt to move it again (it should no longer be in the API collection)
        questionExchange.moveToUserCollection(question);

        // Verify that the question is in the user collection and not in the API collection
        assertTrue(userCollection.getAllQuestions().contains(question));
        assertFalse(apiCollection.getAllQuestions().contains(question));

        // Move the question back to the API collection
        questionExchange.moveToApiCollection(question);

        // Attempt to move it again (it should no longer be in the user collection)
        questionExchange.moveToApiCollection(question);

        // Verify that the question is in the API collection and not in the user collection
        assertTrue(apiCollection.getAllQuestions().contains(question));
        assertFalse(userCollection.getAllQuestions().contains(question));
    }
}