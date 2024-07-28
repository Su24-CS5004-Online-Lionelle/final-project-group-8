package group8.model;

import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;

import java.util.List;


/**
 * Primary record to pass around between objects.
 * 
 * @param type             the question type (multiple or true/false)
 * @param difficulty       the level of difficulty
 * @param category         the question category
 * @param questionText     the text of the question
 * @param correctAnswer    the correct answer
 * @param incorrectAnswers the incorrect answers
 */
public record TriviaQuestion(
        QuestionType type,
        Difficulty difficulty,
        Category category,
        String questionText,
        String correctAnswer,
        List<String> incorrectAnswers) {
}
