package group8.model.helpers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.Enums.Category;

//controller will get a 

/**
 * Filters class provides methods to filter TriviaQuestion objects based on
 * different criteria.
 */
public final class Filters {

    private Set<QuestionType> typeFilters;
    private Set<Difficulty> difficultyFilters;
    private Set<Category> categoryFilters;
    
    public Filters(Set<QuestionType> typeFilters, Set<Difficulty> difficultyFilters, Set<Category> categoryFilters) {
        this.typeFilters = typeFilters;
        this.difficultyFilters = difficultyFilters;
        this.categoryFilters = categoryFilters;

    }

    public List<TriviaQuestion> applyFilters(List<TriviaQuestion> questions) {
    return questions.stream()
            .filter(this::matchesType)
            .filter(this::matchesDifficulty)
            .filter(this::matchesCategory)
            .collect(Collectors.toList());
    }

    private boolean matchesType(TriviaQuestion question) {
        return typeFilters.isEmpty() || typeFilters.contains(question.type());
    }

    private boolean matchesDifficulty(TriviaQuestion question) {
        return difficultyFilters.isEmpty() || difficultyFilters.contains(question.difficulty());
    }

    private boolean matchesCategory(TriviaQuestion question) {
        return categoryFilters.isEmpty() || categoryFilters.contains(question.category());
    }
}
