package group8.view.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * MainViewState class represents the state of filter selections in the trivia
 * generator application.
 */
public class MainViewState {
    private Map<String, Boolean> categorySelectedMap;
    private boolean difficultyEasySelected = false;
    private boolean difficultyMediumSelected = false;
    private boolean difficultyHardSelected = false;
    private boolean typeMultipleChoiceSelected = false;
    private boolean typeTrueFalseSelected = false;

    public MainViewState() {
        categorySelectedMap = new HashMap<>();
    }

    public void addCategory(String category) {
        categorySelectedMap.putIfAbsent(category, false);
    }

    public boolean isCategorySelected(String category) {
        System.out.println(categorySelectedMap);
        return categorySelectedMap.getOrDefault(category, false);
    }

    public void setCategorySelected(String category, boolean selected) {
        System.out.println(categorySelectedMap);
        categorySelectedMap.put(category, selected);
    }

    public boolean isDifficultyEasySelected() {
        return difficultyEasySelected;
    }

    public void setDifficultyEasySelected(boolean difficultyEasySelected) {
        this.difficultyEasySelected = difficultyEasySelected;
    }

    public boolean isDifficultyMediumSelected() {
        return difficultyMediumSelected;
    }

    public void setDifficultyMediumSelected(boolean difficultyMediumSelected) {
        this.difficultyMediumSelected = difficultyMediumSelected;
    }

    public boolean isDifficultyHardSelected() {
        return difficultyHardSelected;
    }

    public void setDifficultyHardSelected(boolean difficultyHardSelected) {
        this.difficultyHardSelected = difficultyHardSelected;
    }

    public boolean isTypeMultipleChoiceSelected() {
        return typeMultipleChoiceSelected;
    }

    public void setTypeMultipleChoiceSelected(boolean typeMultipleChoiceSelected) {
        this.typeMultipleChoiceSelected = typeMultipleChoiceSelected;
    }

    public boolean isTypeTrueFalseSelected() {
        return typeTrueFalseSelected;
    }

    public void setTypeTrueFalseSelected(boolean typeTrueFalseSelected) {
        this.typeTrueFalseSelected = typeTrueFalseSelected;
    }

    public Map<String, Boolean> getCategorySelectedMap() {
        return categorySelectedMap;
    }
}