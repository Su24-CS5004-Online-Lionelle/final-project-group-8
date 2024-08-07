package group8.controller;

import group8.model.*;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.helpers.APIUtils;
import group8.model.helpers.Filters;

import java.util.List;
import java.util.Set;

/**
 * MainController handles the interaction between the model and view.
 * It manages the user's trivia collection and the API's trivia collection.
 */
public class MainController {
    /**
     * The trivia collection for the user.
     */
    private ITriviaCollection user;

    /**
     * The trivia collection from the API.
     */
    private ITriviaCollection api;

    /**
     * Constructs a MainController and initializes the user and API trivia collections.
     * Requests a token and categories from the API upon initialization.
     *
     * @throws Exception if there is an error during the initialization process.
     */
    public MainController() throws Exception {
        this.user = new UserTriviaCollection();
        this.api = new APITriviaCollection();
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

    public Set<Enums.Category> getAllCategories() {
        return ((APITriviaCollection) api).getAllCategories();
    }

    /**
     * Gets a list of api collection questions for the view.
     *
     * @return a list of trivia questions.
     */
    public List<TriviaQuestion> getFormattedApiQuestions(Set<QuestionType> typeFilters,
        Set<Difficulty> difficultyFilters, Set<Category> categoryFilters) {
        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = api.filterQuestions(filters);
        ITriviaCollection filteredCollection = new APITriviaCollection(filteredSet);
        return filteredCollection.sortQuestions(Field.CATEGORY, true);
    }

    public List<TriviaQuestion> getFormattedApiQuestions() {
        return getFormattedApiQuestions(Set.of(), Set.of(), Set.of());
    }
}
