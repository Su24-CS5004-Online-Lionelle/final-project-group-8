package group8.model;

import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;

import java.util.List;


/**
 * Question record to be used in collection objects.
 * 
 * @param type             the question type (multiple or true/false)
 * @param difficulty        the level of difficulty
 * @param category         the question category
 * @param question         the trivia question
 * @param correctAnswer    the correct answer
 * @param incorrectAnswers the incorrect answers
 */
public record TriviaQuestion(
        QuestionType type,
        Difficulty difficulty,
        Category category,
        String question,
        String correctAnswer,
        List<String> incorrectAnswers) {
        
        @Override
        public String toString() {
                return String.format("Category: %s, Difficulty: %s, Type: %s, Question: %s, correctAnswer: %s, incorrectAnswer: %s",
                                category, difficulty, type, question, correctAnswer, incorrectAnswers );
        }
}
