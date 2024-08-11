package group8.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import group8.model.helpers.Filters;
import group8.model.Enums.Field;

/**
 * Interface representing a collection of trivia questions.
 */
public interface ITriviaCollection {

    /**
     * Get all trivia questions in the collection.
     *
     * @return a set of TriviaQuestion
     */
    Set<TriviaQuestion> getAllQuestions();

    /**
     * Add a trivia question to the collection.
     *
     * @param question the trivia question to add
     */
    void addQuestion(TriviaQuestion question);

    /**
     * Add a collection of trivia questions to the collection.
     *
     * @param questions the trivia question to add
     */
    void addQuestions(Collection<TriviaQuestion> questions);

    /**
     * Remove a trivia question from the collection.
     *
     * @param question the trivia question to remove
     */
    void removeQuestion(TriviaQuestion question);

    /**
     * Filter the trivia questions in the collection based on a filter.
     *
     * @param filter the filter criteria
     * @return a set of filtered TriviaQuestion
     */
    Set<TriviaQuestion> filterQuestions(Filters filter);

    /**
     * Sort the trivia questions in the collection based on a sort criterion.
     *
     * @param field     The field to sort by.
     * @param ascending If true, sorts in ascending order; if false, in descending
     *                  order.
     * @return a list of sorted TriviaQuestion
     */
    List<TriviaQuestion> sortQuestions(Field field, boolean ascending);

    /**
     * Reset the trivia question collection to its initial state.
     */
    void reset();
}
