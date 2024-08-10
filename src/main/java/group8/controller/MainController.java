package group8.controller;

import group8.model.*;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.helpers.APIUtils;
import group8.model.helpers.Filters;
import group8.model.helpers.QuestionExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * MainController handles the interaction between the model and view.
 * It manages the user's trivia collection and the API's trivia collection.
 */
public class MainController implements IMainController {

    /**
     * The trivia collection for the user.
     */
    private ITriviaCollection user;

    /**
     * The trivia collection from the API.
     */
    private ITriviaCollection api;
    private QuestionExchange questionExchange;

    /**
     * Constructs a MainController and initializes the user and API trivia collections.
     * Requests a token and categories from the API upon initialization.
     *
     * @throws Exception if there is an error during the initialization process.
     */
    public MainController() throws Exception {
        this.user = new UserTriviaCollection();
        this.api = new APITriviaCollection();
        this.questionExchange = new QuestionExchange((APITriviaCollection) api, (UserTriviaCollection) user);
        APIUtils.requestToken();
        APIUtils.requestCategories();
    }

    /**
     * Generates a list of trivia questions from the API based on the selected categories.
     * Resets the API trivia collection before adding new questions.
     *
     * @param selectedCategories the list of selected categories to fetch questions from.
     * @throws Exception if there is an error during the process of fetching questions.
     */
    public void generateApiList(List<Enums.Category> selectedCategories) throws Exception {
        api.reset();
        for (Enums.Category category : selectedCategories) {
            List<TriviaQuestion> questions = APIUtils.getBatchedQuestions(25, category, null, null);
            for (TriviaQuestion question : questions) {
                api.addQuestion(question);
            }
        }
    }

    /**
     * Retrieves all categories available from the API trivia collection.
     *
     * @return a set of all categories.
     */
    public Set<Enums.Category> getAllCategories() {
        return ((APITriviaCollection) api).getAllCategories();
    }

    /**
     * Gets a list of api collection questions for the view.
     *
     * @param typeFilters       set of question types to filter by
     * @param difficultyFilters set of difficulties to filter by
     * @param categoryFilters   set of categories to filter by
     * @return a list of trivia questions.
     */
    public List<TriviaQuestion> getFormattedApiQuestions(Set<QuestionType> typeFilters,
        Set<Difficulty> difficultyFilters, Set<Category> categoryFilters) {
        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = api.filterQuestions(filters);
        ITriviaCollection filteredCollection = new APITriviaCollection(filteredSet);
        return filteredCollection.sortQuestions(Field.CATEGORY, true);
    }

    /**
     * Gets a list of all trivia questions from the API collection without any
     * filters.
     *
     * @return a list of all trivia questions.
     */
    public List<TriviaQuestion> getFormattedApiQuestions() {
        return getFormattedApiQuestions(Set.of(), Set.of(), Set.of());
    }

    /**
     * Gets a sorted list of user trivia questions.
     *
     * @param field      the field to sort by
     * @param sortOrder  the order of sorting (ascending and descending)
     * @return a sorted list of user trivia questions.
     */
    public List<TriviaQuestion> getFormattedUserQuestions(Field field, Boolean sortOrder) {
        return user.sortQuestions(field, sortOrder);
    }

    /**
     * Loads User saved JSON collection into the user trivial collection.
     *
     * @param filePath File Path of the JSON trivia collection.
     * @return The list of Trivia objects.
     */
    public List<TriviaQuestion> loadTriviaQuestions(String filePath) {
        try {
            // Loading to a list of Trivia questions via file utilities
            List<TriviaQuestion> loadedQuestions = FileUtilities.loadTrivia(filePath);
            // Counter for loaded vs skipped games
            int addedCount = 0;
            int duplicateCount = 0;
            // Cycles through each question and only loads non-duplicated objects
            // If questions are the same, items are considered duplicates
            for (TriviaQuestion question : loadedQuestions) {
                if (!isDuplicateQuestion(question)) {
                    user.addQuestion(question);
                    addedCount++;
                } else {
                    duplicateCount++;
                }
            }

            System.out.printf("Loaded %d new questions. %d duplicates were skipped.%n", addedCount, duplicateCount);
            return new ArrayList<>(user.getAllQuestions());
        } catch (IOException e) {
            // Return original list if unable to load additional Trivia
            System.err.println("Error loading trivia: " + e.getMessage());
            return new ArrayList<>(user.getAllQuestions());
        }
    }

    /**
     * Helper function that checks for duplicate Trivia questions.
     *
     * @param newQuestion The new question to be checked.
     * @return Boolean, True if new question is duplicate, False otherwise.
     */
    private boolean isDuplicateQuestion(TriviaQuestion newQuestion) {
        return user.getAllQuestions().stream()
                .anyMatch(q -> q.question().equals(newQuestion.question()));
    }

    /**
     * Saves the user's trivia collection to a specified folder.
     *
     * @param folderPath the folder path to save the trivia collection
     * @throws IOException if there is an error saving the trivia
     */
    public void saveTrivia(String folderPath) throws IOException {
        FileUtilities.saveTrivia(user.getAllQuestions(), folderPath);
    }

    /**
     * Moves a question from the API collection to the user collection.
     *
     * @param question the question to move
     */
    public void moveToUserCollection(TriviaQuestion question) {
        questionExchange.moveToUserCollection(question);
    }

    /**
     * Moves a question from the user collection to the API collection.
     *
     * @param question the question to move
     */
    public void moveToApiCollection(TriviaQuestion question) {
        questionExchange.moveToApiCollection(question);
    }

    /**
     * Gets all questions from the API collection.
     *
     * @return a list of all API trivia questions
     */
    public List<TriviaQuestion> getApiQuestions() {
        return api.getAllQuestions().stream().toList();
    }

    /**
     * Gets all questions from the user collection.
     *
     * @return a list of all user trivia questions
     */
    public List<TriviaQuestion> getUserQuestions() {
        return user.getAllQuestions().stream().toList();
    }
}

