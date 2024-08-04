package group8.model;

import group8.model.helpers.Filters;
import group8.model.helpers.Sort;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Abstract class representing a collection of trivia questions.
 */
public abstract class TriviaCollection implements ITriviaCollection {
    /**
     * The collection of trivia questions.
     */
    protected Set<TriviaQuestion> originalCollection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public TriviaCollection() {
        this.originalCollection = new TreeSet<>(Comparator.comparing(TriviaQuestion::question));
    }

    /**
     * Constructor that initializes the collection with a given set of trivia
     * questions.
     *
     * @param questions the initial collection of trivia questions to be added to
     *                  this collection
     */
    public TriviaCollection(Collection<TriviaQuestion> questions) {
        this.originalCollection = new TreeSet<>(Comparator.comparing(TriviaQuestion::question));
        this.originalCollection.addAll(questions);
    }

     /**
     * Get all trivia questions in the collection.
     *
     * @return a set of TriviaQuestion
     */
    public Set<TriviaQuestion> getAllQuestions() {
        return originalCollection;
    };

    /**
     * Add a trivia question to the collection.
     *
     * @param question the trivia question to add
     */
    public void addQuestion(TriviaQuestion question) {
        originalCollection.add(question);
    };

    /**
     * Remove a trivia question from the collection.
     *
     * @param question the trivia question to remove
     */
    public void removeQuestion(TriviaQuestion question) {
        originalCollection.remove(question);
    }

    /**
     * Filter the trivia questions in the collection based on a filter.
     *
     * @param filters the filter criteria
     */
    public Set<TriviaQuestion> filterQuestions(Filters filters) {
        return filters.applyFilters(originalCollection);
    }   

    /**
     * Sort the trivia questions in the collection based on a sort criterion.
     *
     * @param sort the sort criterion
     */
    public Set<TriviaQuestion> sortQuestions(Sort sort) {
        return originalCollection;
    }

    /**
     * Reset the trivia question collection to its initial state.
     */
    public void reset() {
        originalCollection = new TreeSet<>();
    }

    public boolean contains(TriviaQuestion question) {
        return originalCollection.contains(question);
    }

    /**
     * Gets a trivia question by its string representation.
     *
     * @param questionString the string representation of the trivia question
     * @return the trivia question, or null if not found
     */
    public TriviaQuestion getQuestionByString(String questionString) {
        for (TriviaQuestion question : originalCollection) {
            if (question.toString().equals(questionString)) {
                return question;
            }
        }
        return null;
    }

    /**
     * Displays collections of Trivia questions in Category: Questions manner.
     *
     * @param questions collection of Trivia questions
     * @return List of Strings with the proper format for display
     */
    public static List<String> getFormattedQuestions(Collection<TriviaQuestion> questions) {
        List<String> formattedQuestions = new ArrayList<>();

        // Iterates through each TriviaQuestion to convert to a list of strings of question for display
        // Format example: GENERAL_KNOWLEDGE: The retail disc of Tony Hawk's Pro Skater 5 only comes with the tutorial.
        for (TriviaQuestion question : questions) {
            String nonHtmlQuestion = htmlConverter(question.question());
            String formattedQuestion = question.category().toString() + ": \"" + nonHtmlQuestion + "\"";
            formattedQuestions.add(formattedQuestion);
        }

        return formattedQuestions;
    }

    /**
     * Helper method that converts HTML coding to string counterpart.
     * @param input String containing HTML coding
     * @return String with HTML coding convertered to its corresponding counterpart.
     */
    public static String htmlConverter(String input) {
        return input.replace("&quot;", "\"")
                .replace("&#039;", "'")
                .replace("&apos;", "'")
                .replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&ldquo;", "\u201C")
                .replace("&rdquo;", "\u201D")
                .replace("&lsquo;", "\u2018")
                .replace("&rsquo;", "\u2019");
    }

}
