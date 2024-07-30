package group8.model;

import java.util.Collection;
import java.util.Set;


public class APITriviaCollection extends TriviaCollection{
    private Set<TriviaQuestion> collection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public APITriviaCollection() {
        super();
    }

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public APITriviaCollection(Collection<TriviaQuestion> questions) {
        super(questions);
    }

    /**
     * Add a trivia questions to the collection.
     *
     * @param questions the trivia questions to add
     */
    public void addQuestions(Collection<TriviaQuestion> questions) {
        collection.addAll(questions);
    }

}
