package student.model.helpers;

import student.model.APITriviaCollection;
import student.model.TriviaQuestion;
import student.model.UserTriviaCollection;

/**
 * Helper class for managing the exchange of trivia questions between
 * the API trivia collection and the user trivia collection.
 */
public class QuestionExchange {

    /** The API trivia collection. */
    private APITriviaCollection apiCollection;

    /** The user trivia collection. */
    private UserTriviaCollection userCollection;

    /**
     * Constructs a QuestionExchange instance with the specified API and user trivia collections.
     *
     * @param apiCollection  the API trivia collection
     * @param userCollection the user trivia collection
     */
    public QuestionExchange(APITriviaCollection apiCollection, UserTriviaCollection userCollection) {
        this.apiCollection = apiCollection;
        this.userCollection = userCollection;
    }

    /**
     * Moves a trivia question from the API collection to the user collection if
     * it exists in the API collection.
     *
     * @param question the trivia question to be moved
     */
    public synchronized void moveToUserCollection(TriviaQuestion question) {
        if (question == null) {
            return;
        }
        if (apiCollection.contains(question)) {
            apiCollection.removeQuestion(question);
            userCollection.addQuestion(question);
        }
    }

    /**
     * Moves a trivia question from the user collection to the API collection if
     * it exists in the user collection.
     *
     * @param question the trivia question to be moved
     */
    public synchronized void moveToApiCollection(TriviaQuestion question) {
        if (question == null) {
            return;
        }
        if (userCollection.contains(question)) {
            userCollection.removeQuestion(question);
            apiCollection.addQuestion(question);
        }
    }
}
