package group8.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import group8.model.Enums.Field;
import group8.model.helpers.Sorter;


public class APITriviaCollection extends TriviaCollection{
    private Set<TriviaQuestion> collection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public APITriviaCollection() {
        super();
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
    }

    /**
     * Add a trivia questions to the collection.
     *
     * @param questions the trivia questions to add
     */
    public void addQuestions(Collection<TriviaQuestion> questions) {
        collection.addAll(questions);
    }

    @Override
    public List<TriviaQuestion> sortQuestions(Field field, boolean ascending) {
        System.out.println("TESTING" + originalCollection.stream()
                .sorted(Sorter.getSort(field, ascending))
                .collect(Collectors.toList()));
        return originalCollection.stream()
                .sorted(Sorter.getSort(field, ascending))
                .collect(Collectors.toList());
    }

}
