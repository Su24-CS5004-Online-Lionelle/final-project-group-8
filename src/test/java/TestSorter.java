import group8.model.TriviaQuestion;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Category;
import group8.model.helpers.Sorter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSorter {
    private static List<TriviaQuestion> questions;

    @BeforeAll
    public static void setUpOnce() {
        questions = new ArrayList<>();

        questions.add(new TriviaQuestion(
                QuestionType.BOOLEAN, Difficulty.HARD, Category.HISTORY,
                "The Berlin Wall fell in 1989.", "True", List.of("False")));

        questions.add(new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.EASY, Category.SPORTS,
                "Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?",
                "Mike Tyson", List.of("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis")));

        questions.add(new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.MEDIUM, Category.ART,
                "The painting 'The Starry Night' by Vincent van Gogh was part of which art movement?",
                "Post-Impressionism", List.of("Romanticism", "Neoclassical", "Impressionism")));

        questions.add(new TriviaQuestion(
                QuestionType.BOOLEAN, Difficulty.MEDIUM, Category.GEOGRAPHY,
                "Tokyo is the capital of Japan.", "True", List.of("False")));

        questions.add(new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.EASY, Category.HISTORY,
                "Who was the first President of the United States?",
                "George Washington", List.of("Thomas Jefferson", "John Adams", "James Madison")));

        questions.add(new TriviaQuestion(
                QuestionType.BOOLEAN, Difficulty.EASY, Category.ART,
                "Leonardo da Vinci painted the Mona Lisa.", "True", List.of("False")));

        questions.add(new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.HARD, Category.GEOGRAPHY,
                "What is the capital of Iceland?", "Reykjavik",
                List.of("Oslo", "Helsinki", "Stockholm")));

        questions.add(new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.MEDIUM, Category.SPORTS,
                "Which NBA player won Most Valuable Player for the 1999-2000 season?",
                "Shaquille O'Neal", List.of("Allen Iverson", "Kobe Bryant", "Paul Pierce")));

        questions.add(new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.HARD, Category.HISTORY,
                "In what year did the Titanic sink?", "1912",
                List.of("1905", "1915", "1920")));

        questions.add(new TriviaQuestion(
                QuestionType.BOOLEAN, Difficulty.EASY, Category.GEOGRAPHY,
                "Australia is both a country and a continent.", "True", List.of("False")));
    }

    @Test
    public void testSortByDifficultyAscending() {
        questions.sort(Sorter.getSort(Field.DIFFICULTY, true));
        assertEquals(Difficulty.EASY, questions.get(0).difficulty());
        assertEquals(Difficulty.EASY, questions.get(1).difficulty());
        assertEquals(Difficulty.EASY, questions.get(2).difficulty());
        assertEquals(Difficulty.EASY, questions.get(3).difficulty());
        assertEquals(Difficulty.MEDIUM, questions.get(4).difficulty());
        assertEquals(Difficulty.MEDIUM, questions.get(5).difficulty());
        assertEquals(Difficulty.MEDIUM, questions.get(6).difficulty());
        assertEquals(Difficulty.HARD, questions.get(7).difficulty());
        assertEquals(Difficulty.HARD, questions.get(8).difficulty());
        assertEquals(Difficulty.HARD, questions.get(9).difficulty());
    }

    @Test
    public void testSortByCategoryAscending() {
        questions.sort(Sorter.getSort(Field.CATEGORY, true));
        assertEquals(Category.ART, questions.get(0).category());
        assertEquals(Category.ART, questions.get(1).category());
        assertEquals(Category.GEOGRAPHY, questions.get(2).category());
        assertEquals(Category.GEOGRAPHY, questions.get(3).category());
        assertEquals(Category.GEOGRAPHY, questions.get(4).category());
        assertEquals(Category.HISTORY, questions.get(5).category());
        assertEquals(Category.HISTORY, questions.get(6).category());
        assertEquals(Category.HISTORY, questions.get(7).category());
        assertEquals(Category.SPORTS, questions.get(8).category());
        assertEquals(Category.SPORTS, questions.get(9).category());
    }

    @Test
    public void testSortByCategoryDescending() {
        questions.sort(Sorter.getSort(Field.CATEGORY, false));
        assertEquals(Category.SPORTS, questions.get(0).category());
        assertEquals(Category.SPORTS, questions.get(1).category());
        assertEquals(Category.HISTORY, questions.get(2).category());
        assertEquals(Category.HISTORY, questions.get(3).category());
        assertEquals(Category.HISTORY, questions.get(4).category());
        assertEquals(Category.GEOGRAPHY, questions.get(5).category());
        assertEquals(Category.GEOGRAPHY, questions.get(6).category());
        assertEquals(Category.GEOGRAPHY, questions.get(7).category());
        assertEquals(Category.ART, questions.get(8).category());
        assertEquals(Category.ART, questions.get(9).category());
    }

    @Test
    public void testSortByDifficultyDescending() {
        questions.sort(Sorter.getSort(Field.DIFFICULTY, false));
        assertEquals(Difficulty.HARD, questions.get(0).difficulty());
        assertEquals(Difficulty.HARD, questions.get(1).difficulty());
        assertEquals(Difficulty.HARD, questions.get(2).difficulty());
        assertEquals(Difficulty.MEDIUM, questions.get(3).difficulty());
        assertEquals(Difficulty.MEDIUM, questions.get(4).difficulty());
        assertEquals(Difficulty.MEDIUM, questions.get(5).difficulty());
        assertEquals(Difficulty.EASY, questions.get(6).difficulty());
        assertEquals(Difficulty.EASY, questions.get(7).difficulty());
        assertEquals(Difficulty.EASY, questions.get(8).difficulty());
        assertEquals(Difficulty.EASY, questions.get(9).difficulty());
    }

    @Test
    public void testSortByTypeAscending() {
        questions.sort(Sorter.getSort(Field.TYPE, true));
        assertEquals(QuestionType.BOOLEAN, questions.get(0).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(1).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(2).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(3).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(4).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(5).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(6).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(7).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(8).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(9).type());
    }

    @Test
    public void testSortByTypeDescending() {
        questions.sort(Sorter.getSort(Field.TYPE, false));
        questions.forEach(q -> System.out.println(q.difficulty())); // Debugging statement

        assertEquals(QuestionType.MULTIPLE, questions.get(0).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(1).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(2).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(3).type());
        assertEquals(QuestionType.MULTIPLE, questions.get(4).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(5).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(6).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(7).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(8).type());
        assertEquals(QuestionType.BOOLEAN, questions.get(9).type());
    }
}
