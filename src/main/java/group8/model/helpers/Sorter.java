package group8.model.helpers;

import java.util.List;
import java.util.Set;

import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.TriviaQuestion;

public final class Sorter {
    private static Field field;
    private static Boolean direction;

    /**
     * Private constructor to prevent instantiation.
     */
    public Sorter(Field field, Boolean direction) {
        this.field = field;
        this.direction = direction;
    }

    /**
     * Sorts a list of BoardGame objects based on the specified sort type in
     * ascending order by default.
     *
     * @param games    The list of BoardGame objects to be sorted.
     * @param sortType The type of sorting to be applied. This should correspond to
     *                 a value in the GameData enum.
     * @return A new list of BoardGame objects sorted based on the specified type in
     *         ascending order.
     */
    // public static List<TriviaQuestion> sortQuestions(Set<TriviaQuestion> questions) {
    //     return sortQuestions(questions);
    // }

    public List<TriviaQuestion> sortCollection(Set<TriviaQuestion> questions) {
        List<TriviaQuestion> sorted = 
            questions.stream().sorted(QuestionSortStrategy.getSort(field, direction)).toList();

        return sorted;
    }

}
    
