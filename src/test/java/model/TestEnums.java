package model;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEnums {

    @Test
    public void testQuestionTypeGetValue() {
        assertEquals("boolean", QuestionType.BOOLEAN.getValue());
        assertEquals("multiple", QuestionType.MULTIPLE.getValue());
    }

    @Test
    public void testQuestionTypeFromValue() {
        assertEquals(QuestionType.BOOLEAN, QuestionType.fromValue("boolean"));
        assertEquals(QuestionType.MULTIPLE, QuestionType.fromValue("multiple"));

        assertThrows(IllegalArgumentException.class, () -> {
            QuestionType.fromValue("unknown");
        });
    }

    @Test
    public void testDifficultyGetValue() {
        assertEquals("easy", Difficulty.EASY.getValue());
        assertEquals("medium", Difficulty.MEDIUM.getValue());
        assertEquals("hard", Difficulty.HARD.getValue());
    }

    @Test
    public void testDifficultyFromValue() {
        assertEquals(Difficulty.EASY, Difficulty.fromValue("easy"));
        assertEquals(Difficulty.MEDIUM, Difficulty.fromValue("medium"));
        assertEquals(Difficulty.HARD, Difficulty.fromValue("hard"));

        assertThrows(IllegalArgumentException.class, () -> {
            Difficulty.fromValue("unknown");
        });
    }

    @Test
    public void testCategoryGetValue() {
        assertEquals("Animals", Category.ANIMALS.getValue());
        assertEquals("Art", Category.ART.getValue());
        // Add more assertions for other categories as needed
        assertEquals("Vehicles", Category.VEHICLES.getValue());
    }

    @Test
    public void testCategoryFromValue() {
        assertEquals(Category.ANIMALS, Category.fromValue("Animals"));
        assertEquals(Category.ART, Category.fromValue("Art"));
        // Add more assertions for other categories as needed
        assertEquals(Category.VEHICLES, Category.fromValue("Vehicles"));

        assertThrows(IllegalArgumentException.class, () -> {
            Category.fromValue("unknown");
        });
    }

    @Test
    public void testFieldGetValue() {
        assertEquals("type", Field.TYPE.getValue());
        assertEquals("difficulty", Field.DIFFICULTY.getValue());
        assertEquals("category", Field.CATEGORY.getValue());
    }

    @Test
    public void testFieldFromValue() {
        assertEquals(Field.TYPE, Field.fromValue("type"));
        assertEquals(Field.DIFFICULTY, Field.fromValue("difficulty"));
        assertEquals(Field.CATEGORY, Field.fromValue("category"));

        assertThrows(IllegalArgumentException.class, () -> {
            Field.fromValue("unknown");
        });
    }
}