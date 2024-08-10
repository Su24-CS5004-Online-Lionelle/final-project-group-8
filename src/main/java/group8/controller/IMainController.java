package group8.controller;

import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.TriviaQuestion;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * IMainController defines the contract for the MainController of the trivia application.
 * Handles the interaction between the model and view, managing user and API trivia collections.
 */
public interface IMainController {

    /**
     * Generates a list of trivia questions from the API based on the selected categories.
     *
     * @param selectedCategories the list of selected categories to fetch questions from.
     * @throws Exception if there is an error during the process of fetching questions.
     */
    void generateApiList(List<Category> selectedCategories) throws Exception;

    /**
     * Retrieves all categories available from the API trivia collection.
     *
     * @return a set of all categories.
     */
    Set<Category> getAllCategories();

    /**
     * Gets a list of API collection questions for the view.
     *
     * @param typeFilters       set of question types to filter by
     * @param difficultyFilters set of difficulties to filter by
     * @param categoryFilters   set of categories to filter by
     * @return a list of trivia questions.
     */
    List<TriviaQuestion> getFormattedApiQuestions(Set<QuestionType> typeFilters,
                                                  Set<Difficulty> difficultyFilters,
                                                  Set<Category> categoryFilters);

    /**
     * Gets a list of all trivia questions from the API collection without any filters.
     *
     * @return a list of all trivia questions.
     */
    List<TriviaQuestion> getFormattedApiQuestions();

    /**
     * Gets a sorted list of user trivia questions.
     *
     * @param field      the field to sort by
     * @param sortOrder  the order of sorting (ascending and descending)
     * @return a sorted list of user trivia questions.
     */
    List<TriviaQuestion> getFormattedUserQuestions(Field field, Boolean sortOrder);

    /**
     * Loads User saved JSON collection into the user trivia collection.
     *
     * @param filePath File Path of the JSON trivia collection.
     * @return The list of Trivia objects.
     */
    List<TriviaQuestion> loadTriviaQuestions(String filePath);

    /**
     * Saves the user's trivia collection to a specified folder.
     *
     * @param folderPath the path to save the trivia collection
     * @throws IOException if there is an error saving the trivia
     */
    void saveTrivia(String folderPath) throws IOException;

    /**
     * Moves a question from the API collection to the user collection.
     *
     * @param question the question to move
     */
    void moveToUserCollection(TriviaQuestion question);

    /**
     * Moves a question from the user collection to the API collection.
     *
     * @param question the question to move
     */
    void moveToApiCollection(TriviaQuestion question);

    /**
     * Gets all questions from the API collection.
     *
     * @return a list of all API trivia questions
     */
    List<TriviaQuestion> getApiQuestions();

    /**
     * Gets all questions from the user collection.
     *
     * @return a list of all user trivia questions
     */
    List<TriviaQuestion> getUserQuestions();
}
