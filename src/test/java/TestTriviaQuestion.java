import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;
import group8.model.TriviaQuestion;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTriviaQuestion {

    private TriviaQuestion question1;
    private TriviaQuestion question2;
    private TriviaQuestion question3;

    @BeforeEach
    public void setUp() {
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
    }

    @Test
    public void testTriviaQuestionGetters() {
        assertEquals(QuestionType.MULTIPLE, question2.type());
        assertEquals(Difficulty.EASY, question2.difficulty());
        assertEquals(Category.SPORTS, question2.category());
        assertEquals("Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?",
                question2.question());
        assertEquals("Mike Tyson", question2.correctAnswer());
        assertEquals(List.of("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"),
                question2.incorrectAnswers());

        assertEquals(QuestionType.BOOLEAN, question1.type());
        assertEquals(Difficulty.HARD, question1.difficulty());
        assertEquals(Category.HISTORY, question1.category());
        assertEquals("The Berlin Wall fell in 1989.", question1.question());
        assertEquals("True", question1.correctAnswer());
        assertEquals(List.of("False"), question1.incorrectAnswers());
    }

    @Test
    public void testTriviaQuestionCompareTo() {
        assertTrue(question1.compareTo(question3) < 0);
        assertTrue(question3.compareTo(question2) < 0);
        assertTrue(question2.compareTo(question1) > 0);
        assertEquals(0, question1.compareTo(question1));
    }

    @Test
    public void testTriviaQuestionToString() {
        String expected = "Category: HISTORY, Difficulty: HARD, Type: BOOLEAN, Question: The Berlin Wall fell in 1989., correctAnswer: True, incorrectAnswer: [False]";
        assertEquals(expected, question1.toString());
    }

    @Test
    public void testTriviaQuestionWithNullValues() {
        TriviaQuestion nullQuestion = new TriviaQuestion(
            null,
            null,
            null,
            null,
            null,
            null);

        assertNull(nullQuestion.type());
        assertNull(nullQuestion.difficulty());
        assertNull(nullQuestion.category());
        assertNull(nullQuestion.question());
        assertNull(nullQuestion.correctAnswer());
        assertNull(nullQuestion.incorrectAnswers());
    }

    @Test
    public void testTriviaQuestionWithEmptyValues() {
        TriviaQuestion emptyQuestion = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.EASY,
            Category.SPORTS,
            "",
            "",
            List.of());

        assertEquals("", emptyQuestion.question());
        assertEquals("", emptyQuestion.correctAnswer());
        assertTrue(emptyQuestion.incorrectAnswers().isEmpty());
    }
}
