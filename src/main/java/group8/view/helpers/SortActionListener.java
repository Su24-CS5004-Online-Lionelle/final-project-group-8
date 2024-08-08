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
    private JToggleButton orderToggleButton;
    private DefaultListModel<TriviaQuestion> userListModel;

    public SortActionListener(MainController controller, MainView mainView, DefaultListModel<TriviaQuestion> userListModel, JToggleButton orderToggleButton) {
        this.userListModel = userListModel;
        this.controller = controller;
        this.mainView = mainView;
        this.orderToggleButton = orderToggleButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleSortAction((JComboBox<String>) e.getSource());
    }

    public void handleSortAction(JComboBox<String> comboBox) {
        String selectedSort = (String) comboBox.getSelectedItem();
        Enums.Field selectedOption = SortMapping.sortLabelMap.get(selectedSort);

        if (selectedOption != null) {
            boolean sortOrder = orderToggleButton.isSelected();
            List<TriviaQuestion> questions = controller.getFormattedUserQuestions(selectedOption, sortOrder);
            mainView.updateUserListModel(questions);
        }
    }

    public void handleToggleAction() {
        String selectedSort = (String) ((JComboBox) orderToggleButton.getParent().getComponent(1)).getSelectedItem();
        Enums.Field selectedOption = SortMapping.sortLabelMap.get(selectedSort);

        if (selectedOption != null) {
            boolean sortOrder = orderToggleButton.isSelected();
            orderToggleButton.setText(sortOrder ? "\u2193" : "\u2191"); // Toggle arrow
            List<TriviaQuestion> questions = controller.getFormattedUserQuestions(selectedOption, sortOrder);
            mainView.updateUserListModel(questions);
        }
    }

    public static class SortMapping {
        public static final Map<String, Enums.Field> sortLabelMap = new HashMap<>();

        static {
            sortLabelMap.put("Sort by...", null);
            sortLabelMap.put("Category", Field.CATEGORY);
            sortLabelMap.put("Difficulty", Field.DIFFICULTY);
            sortLabelMap.put("Question Type", Field.TYPE);
        }
    }
}
