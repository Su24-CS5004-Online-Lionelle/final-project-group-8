package group8.view.helpers;

import group8.controller.MainController;
import group8.model.TriviaQuestion;
import group8.view.MainView;
import group8.model.Enums;
import group8.model.Enums.Field;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action listener for sorting the user collection based on the selected criteria.
 */
public class SortActionListener implements ActionListener {
    private MainController controller;
    private MainView mainView;

    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /**
     * Constructs a SortActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public SortActionListener(MainController controller, MainView mainView, DefaultListModel<TriviaQuestion> userListModel ) {
        this.userListModel = userListModel;
        this.controller = controller;
        this.mainView = mainView;
    }

    /**
     * Invoked when an action occurs. Sorts the user collection based on the selected criteria.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String selectedSort = (String) comboBox.getSelectedItem();
        Enums.Field selectedOption = SortMapping.sortLabelMap.get(selectedSort);

        if (selectedOption != null) {
            //get new 
            List<TriviaQuestion> questions = controller.getFormattedUserQuestions(selectedOption);
            mainView.updateUserListModel(questions);
        }


        // Logic to sort the user collection based on selectedSort
        //example logic from filter:

            // List<TriviaQuestion> questions = controller.getFormattedApiQuestions(selectedTypes, selectedDifficulties, selectedCategories);
            // mainView.updateApiListModel(questions);
            
    }
    public class SortMapping {
        public static final Map<String, Enums.Field> sortLabelMap = new HashMap<>();

        static {
            sortLabelMap.put("Sort by...", null);
            sortLabelMap.put("Category", Field.CATEGORY);
            sortLabelMap.put("Difficulty", Field.DIFFICULTY);
            sortLabelMap.put("Question Type", Field.TYPE);
        }
}
}
