package group8.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class APITriviaCollection extends TriviaCollection {

    /**
     * A set that holds the collection of trivia questions retrieved from the API.
     */
    private Set<TriviaQuestion> apiCollection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public APITriviaCollection() {
        super();
        this.apiCollection = this.originalCollection;
    }

    /**
     * Constructor that initializes the collection with a given set of trivia
     * questions.
     *
     * @param questions the initial collection of trivia questions to be added to
     *                  this collection
     */
    public APITriviaCollection(Collection<TriviaQuestion> questions) {
        super(questions);
        this.apiCollection = this.originalCollection;
    }

    /**
     * Add a trivia questions to the collection.
     *
     * @param questions the trivia questions to add
     */
    @Override
    public void addQuestions(Collection<TriviaQuestion> questions) {
        if (questions == null) {
            return;
        }
        apiCollection.addAll(questions);
    }

    /**
     * Retrieves all unique categories from the trivia questions in the collection.
     *
     * @return a set of all unique categories in the collection
     */
    public Set<Enums.Category> getAllCategories() {
        Set<Enums.Category> categories = new HashSet<>();
        for (TriviaQuestion question : apiCollection) {
            categories.add(question.category());
        }
        return categories;
    }

}
