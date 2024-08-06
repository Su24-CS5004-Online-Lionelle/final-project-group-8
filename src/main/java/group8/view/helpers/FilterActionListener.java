package group8.view.helpers;

import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.view.MainView;
import group8.controller.MainController;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;


public class FilterActionListener implements ActionListener {
    /** The main application frame. */
    private final JFrame frame;
    /** The main view of the application. */
    private final MainView mainView;
    /** The state of the main view containing filter selections. */
    private final MainViewState state;
    /** The API list model containing trivia questions. */
    private final MainController controller;
    private final DefaultListModel<TriviaQuestion> apiListModel;
    /** A map to store the dynamically generated checkboxes for categories. */
    private Map<String, JCheckBox> categoryCheckboxMap;

    /**
     * Constructs a FilterActionListener with the specified frame, main view, state, and API list model.
     *
     * @param frame the main application frame
     * @param mainView the main view of the application
     * @param state the state of the main view containing filter selections
     * @param apiListModel the API list model containing trivia questions
     */
    public FilterActionListener(JFrame frame, MainView mainView, MainViewState state, MainController controller, DefaultListModel<TriviaQuestion> apiListModel) {
        this.frame = frame;
        this.mainView = mainView;
        this.controller = controller;
        this.state = state;
        this.apiListModel = apiListModel;
        this.categoryCheckboxMap = new HashMap<>();
    }

    /**
     * Invoked when an action occurs. Displays the filter dialog and applies the selected filters.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog filterDialog = new JDialog(frame, "Filter Options", true);
        filterDialog.setSize(300, 400);
        filterDialog.setLayout(new BorderLayout());

        JPanel filterOptionsPanel = new JPanel();
        filterOptionsPanel.setLayout(new BoxLayout(filterOptionsPanel, BoxLayout.Y_AXIS));

        // Category Filters
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        categoryLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
        filterOptionsPanel.add(categoryLabel);

        Set<String> uniqueCategories = extractUniqueCategories(apiListModel);

        if (uniqueCategories.isEmpty()) {
            JLabel noCategoriesLabel = new JLabel("No categories available");
            filterOptionsPanel.add(noCategoriesLabel);
        } else {
            for (String category : uniqueCategories) {
                JCheckBox categoryOption;
                categoryOption = new JCheckBox(category);
                if (categoryCheckboxMap.containsKey(category)) {
                    // Update existing checkbox
                    categoryOption = categoryCheckboxMap.get(category);
                } else {
                    // Create new checkbox
                    categoryCheckboxMap.put(category, categoryOption);
                }
                // Set the selected state
                categoryOption.setSelected(state.isCategorySelected(category));
                filterOptionsPanel.add(categoryOption);

            }
        }

        // Difficulty Filters
        JLabel difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        difficultyLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
        filterOptionsPanel.add(difficultyLabel);

        JCheckBox difficultyOption1 = new JCheckBox("Easy");
        JCheckBox difficultyOption2 = new JCheckBox("Medium");
        JCheckBox difficultyOption3 = new JCheckBox("Hard");
        difficultyOption1.setSelected(state.isDifficultyEasySelected());
        difficultyOption2.setSelected(state.isDifficultyMediumSelected());
        difficultyOption3.setSelected(state.isDifficultyHardSelected());
        filterOptionsPanel.add(difficultyOption1);
        filterOptionsPanel.add(difficultyOption2);
        filterOptionsPanel.add(difficultyOption3);

        // Question Type Filters
        JLabel typeLabel = new JLabel("Question Type");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        typeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
        filterOptionsPanel.add(typeLabel);

        JCheckBox typeOption1 = new JCheckBox("Multiple Choice");
        JCheckBox typeOption2 = new JCheckBox("True/False");
        typeOption1.setSelected(state.isTypeMultipleChoiceSelected());
        typeOption2.setSelected(state.isTypeTrueFalseSelected());
        filterOptionsPanel.add(typeOption1);
        filterOptionsPanel.add(typeOption2);

        JButton applyFiltersButton = new JButton("Apply Filters");
        applyFiltersButton.addActionListener(ev -> {
            // Collect filter parameters
            for (Map.Entry<String, JCheckBox> entry : categoryCheckboxMap.entrySet()) {
                state.setCategorySelected(entry.getKey(), entry.getValue().isSelected());
            }
            state.setDifficultyEasySelected(difficultyOption1.isSelected());
            state.setDifficultyMediumSelected(difficultyOption2.isSelected());
            state.setDifficultyHardSelected(difficultyOption3.isSelected());
            state.setTypeMultipleChoiceSelected(typeOption1.isSelected());
            state.setTypeTrueFalseSelected(typeOption2.isSelected());

            // Logic to apply filters based on checkbox selections
            filterDialog.dispose();
        });

        filterDialog.add(filterOptionsPanel, BorderLayout.CENTER);
        filterDialog.add(applyFiltersButton, BorderLayout.SOUTH);

        filterDialog.setLocationRelativeTo(frame);
        filterDialog.setVisible(true);

        Set<Enums.Category> selectedCategories = new HashSet<>();
        for (Map.Entry<String, JCheckBox> entry : categoryCheckboxMap.entrySet()) {
            if (entry.getValue().isSelected()) {
                selectedCategories.add(Category.fromValue(entry.getKey()));
            }
        }
        Set<Enums.Difficulty> selectedDifficulties = new HashSet<>();
        if (difficultyOption1.isSelected())
            selectedDifficulties.add(Difficulty.EASY);
        if (difficultyOption2.isSelected())
            selectedDifficulties.add(Difficulty.MEDIUM);
        if (difficultyOption3.isSelected())
            selectedDifficulties.add(Difficulty.HARD);

        Set<Enums.QuestionType> selectedTypes = new HashSet<>();
        if (typeOption1.isSelected())
            selectedTypes.add(QuestionType.MULTIPLE);
        if (typeOption2.isSelected())
            selectedTypes.add(QuestionType.BOOLEAN);
        

        List<TriviaQuestion> questions = controller.getFormattedApiQuestions(selectedTypes, selectedDifficulties, selectedCategories);
        mainView.updateApiListModel(questions);
    
    }

    private Set<String> extractUniqueCategories(DefaultListModel<TriviaQuestion> apiListModel) {
        Set<String> uniqueCategories = new HashSet<>();
        for (int i = 0; i < apiListModel.getSize(); i++) {
            TriviaQuestion question = apiListModel.getElementAt(i);
            uniqueCategories.add(question.category().getValue()); // Assuming getCategory() returns the category of the question
        }
        return uniqueCategories;
    }
}

