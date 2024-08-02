package group8.model.helpers;

import java.util.Comparator;

import group8.model.Enums.Field;
import group8.model.TriviaQuestion;

public final class QuestionSortStrategy {
    /**
     * Private constructor to prevent instantiation.
    */
    public QuestionSortStrategy() {
    }

    /**
     * Returns a comparator for sorting BoardGame objects based on the specified
     * column in ascending order.
     *
     * @param  field The field to sort by.
     * @return A comparator for sorting BoardGame objects by the specified column in
     *         ascending order.
     */
    public static Comparator<TriviaQuestion> getSort(Field field) {
        return getSort(field, true);
    };

    /**
     * Returns a comparator for sorting BoardGame objects based on the specified
     * column and order.
     *
     * @param column    The column to sort by.
     * @param ascending If true, the comparator sorts in ascending order; if false,
     *                  in descending order.
     * @return A comparator for sorting BoardGame objects by the specified column in
     *         the specified order.
     */
    public static Comparator<TriviaQuestion> getSort(Field field, boolean ascending) {
        switch (field) {
            case CATEGORY:
                return ascending ? new CategoryAscending() : new CategoryDescending();
            case DIFFICULTY:
                return ascending ? new DifficultyAscending() : new DifficultyDescending();
            case TYPE:
                return ascending ? new TypeAscending() : new TypeDescending();
            default: 
                //FIX THIS TO BE MORE SPECIFIC
                return Comparator.comparingInt(g -> 0);
        }
    }

    /**
     * Comparator for sorting TriviaQuestion objects by difficulty in ascending
     * order.
     */
    public static class CategoryAscending implements Comparator<TriviaQuestion> {
        @Override
        public int compare(TriviaQuestion o1, TriviaQuestion o2) {
            return o1.category().compareTo(o2.category());
        }
    }

    /**
     * Comparator for sorting TriviaQuestion objects by difficulty in descending
     * order.
     */
    public static class CategoryDescending implements Comparator<TriviaQuestion> {
        @Override
        public int compare(TriviaQuestion o1, TriviaQuestion o2) {
            return o2.category().compareTo(o1.category());
        }
    }


    /**
     * Comparator for sorting TriviaQuestion objects by difficulty in ascending order.
     */
    public static class DifficultyAscending implements Comparator<TriviaQuestion> {
        @Override
        public int compare(TriviaQuestion o1, TriviaQuestion o2) {
            return o1.difficulty().compareTo(o2.difficulty());
        }
    }

    /**
     * Comparator for sorting TriviaQuestion objects by difficulty in descending order.
     */
    public static class DifficultyDescending implements Comparator<TriviaQuestion> {
        @Override
        public int compare(TriviaQuestion o1, TriviaQuestion o2) {
            return o2.difficulty().compareTo(o1.difficulty());
        }
    }

    /**
     * Comparator for sorting TriviaQuestion objects by difficulty in ascending
     * order.
     */
    public static class TypeAscending implements Comparator<TriviaQuestion> {
        @Override
        public int compare(TriviaQuestion o1, TriviaQuestion o2) {
            return o1.type().compareTo(o2.type());
        }
    }

    /**
     * Comparator for sorting TriviaQuestion objects by difficulty in descending
     * order.
     */
    public static class TypeDescending implements Comparator<TriviaQuestion> {
        @Override
        public int compare(TriviaQuestion o1, TriviaQuestion o2) {
            return o2.type().compareTo(o1.type());
        }
    }
}
