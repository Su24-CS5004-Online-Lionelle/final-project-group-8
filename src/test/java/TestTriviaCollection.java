import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group8.model.APITriviaCollection;
import group8.model.TriviaQuestion;
import group8.model.UserTriviaCollection;
import group8.model.helpers.Filters;
import group8.model.Enums;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;
import group8.model.Enums.Field;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class TestTriviaCollection {
    private APITriviaCollection apiCollection;
    private UserTriviaCollection userCollection;
    private TriviaQuestion question1;
    private TriviaQuestion question2;
    private TriviaQuestion question3;

    @BeforeEach
    void setUp() {
        question1 = new TriviaQuestion(
                QuestionType.BOOLEAN,
                Difficulty.HARD,
                Category.HISTORY,
                "The Berlin Wall fell in 1989.",
                "True",
                List.of("False"));

        question2 = new TriviaQuestion(
                QuestionType.MULTIPLE,
                Difficulty.EASY,
                Category.SPORTS,
                "Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?",
                "Mike Tyson",
                List.of("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"));

        question3 = new TriviaQuestion(
                QuestionType.MULTIPLE,
                Difficulty.MEDIUM,
                Category.ART,
                "The painting 'The Starry Night' by Vincent van Gogh was part of which art movement?",
                "Post-Impressionism",
                List.of("Romanticism", "Neoclassical", "Impressionism"));

        apiCollection = new APITriviaCollection();
        userCollection = new UserTriviaCollection();
    }

    // Test APITriviaCollection

    @Test
    void testDefaultConstructorAPI() {
        assertTrue(apiCollection.getAllCategories().isEmpty());
    }

    @Test
    void testConstructorWithCollectionAPI() {
        apiCollection = new APITriviaCollection(Arrays.asList(question1, question2));

        assertEquals(2, apiCollection.getAllCategories().size());
        assertTrue(apiCollection.getAllCategories().contains(Enums.Category.SPORTS));
        assertTrue(apiCollection.getAllCategories().contains(Enums.Category.HISTORY));
    }

    @Test
    void testAddQuestionsAPI() {
        apiCollection.addQuestions(Arrays.asList(question1, question2));
        assertEquals(2, apiCollection.getAllCategories().size());

        apiCollection.addQuestions(Collections.singletonList(question3));
        assertEquals(3, apiCollection.getAllCategories().size());
    }

    @Test
    void testAddQuestionsWithNullCollectionAPI() {
        apiCollection.addQuestions(null);
        assertTrue(apiCollection.getAllCategories().isEmpty());
    }

    @Test
    void testAddQuestionsWithEmptyCollectionAPI() {
        apiCollection.addQuestions(Collections.emptyList());
        assertTrue(apiCollection.getAllCategories().isEmpty());
    }

    @Test
    void testGetAllCategoriesAPI() {
        apiCollection.addQuestions(Arrays.asList(question1, question2, question3));

        Set<Enums.Category> expectedCategories = new HashSet<>(
                Arrays.asList(Enums.Category.SPORTS, Enums.Category.HISTORY,
                        Enums.Category.ART));
        assertEquals(expectedCategories, apiCollection.getAllCategories());
    }

    @Test
    void testGetAllCategoriesWithEmptyCollectionAPI() {
        assertTrue(apiCollection.getAllCategories().isEmpty());
    }

    // Test UserTriviaCollection

    @Test
    void testDefaultConstructorUser() {
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    @Test
    void testConstructorWithCollectionUser() {
        userCollection = new UserTriviaCollection(Arrays.asList(question1, question2));

        assertEquals(2, userCollection.getAllQuestions().size());
        assertTrue(userCollection.contains(question1));
        assertTrue(userCollection.contains(question2));
    }

    @Test
    void testAddQuestionsUser() {
        userCollection.addQuestions(Arrays.asList(question1, question2));
        assertEquals(2, userCollection.getAllQuestions().size());

        userCollection.addQuestions(Collections.singletonList(question3));
        assertEquals(3, userCollection.getAllQuestions().size());
    }

    @Test
    void testAddQuestionsWithNullCollectionUser() {
        userCollection.addQuestions(null);
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    @Test
    void testAddQuestionsWithEmptyCollectionUser() {
        userCollection.addQuestions(Collections.emptyList());
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    // Shared Tests for methods inherited from TriviaCollection

    @Test
    void testAddQuestion() {
        apiCollection.addQuestion(question1);
        assertTrue(apiCollection.contains(question1));

        userCollection.addQuestion(question1);
        assertTrue(userCollection.contains(question1));
    }

    @Test
    void testAddNullQuestion() {
        apiCollection.addQuestion(null);
        assertTrue(apiCollection.getAllCategories().isEmpty());

        userCollection.addQuestion(null);
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    @Test
    void testRemoveQuestion() {
        apiCollection.addQuestion(question1);
        apiCollection.removeQuestion(question1);
        assertFalse(apiCollection.contains(question1));

        userCollection.addQuestion(question1);
        userCollection.removeQuestion(question1);
        assertFalse(userCollection.contains(question1));
    }

    @Test
    void testReset() {
        apiCollection.addQuestion(question1);
        apiCollection.addQuestion(question2);
        apiCollection.reset();
        assertTrue(apiCollection.getAllQuestions().isEmpty());

        userCollection.addQuestion(question1);
        userCollection.addQuestion(question2);
        userCollection.reset();
        assertTrue(userCollection.getAllQuestions().isEmpty());
    }

    @Test
    void testContainsQuestion() {
        apiCollection.addQuestion(question1);
        assertTrue(apiCollection.contains(question1));
        assertFalse(apiCollection.contains(question2));

        userCollection.addQuestion(question1);
        assertTrue(userCollection.contains(question1));
        assertFalse(userCollection.contains(question2));
    }

    @Test
    void testFilterQuestions() {
        apiCollection.addQuestion(question1);
        apiCollection.addQuestion(question2);
        apiCollection.addQuestion(question3);

        Set<QuestionType> typeFilters = Set.of();
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.EASY);
        Set<Category> categoryFilters = Set.of();

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = apiCollection.filterQuestions(filters);

        assertEquals(1, filteredSet.size());
        assertTrue(filteredSet.contains(question2));

        userCollection.addQuestion(question1);
        userCollection.addQuestion(question2);
        userCollection.addQuestion(question3);

        Set<TriviaQuestion> filteredSetUser = userCollection.filterQuestions(filters);
        assertEquals(1, filteredSetUser.size());
        assertTrue(filteredSetUser.contains(question2));
    }

    @Test
    void testSortQuestions() {
        apiCollection.addQuestion(question1);
        apiCollection.addQuestion(question2);
        apiCollection.addQuestion(question3);
        List<TriviaQuestion> sortedQuestionsAPI = apiCollection.sortQuestions(Field.CATEGORY, true);
        assertEquals(question3, sortedQuestionsAPI.get(0));

        userCollection.addQuestion(question1);
        userCollection.addQuestion(question2);
        userCollection.addQuestion(question3);
        List<TriviaQuestion> sortedQuestionsUser = userCollection.sortQuestions(Field.CATEGORY, true);
        assertEquals(question3, sortedQuestionsUser.get(0));
    }
}
