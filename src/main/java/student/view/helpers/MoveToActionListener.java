package student.view.helpers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import student.controller.MainController;
import student.model.TriviaQuestion;
import student.view.MainView;

/**
 * MoveToActionListener handles the action of moving a selected TriviaQuestion
 * from one list to another (API list to User list or vice versa).
 */
public class MoveToActionListener implements ActionListener {

    /**
     * The main view of the application.
     */
    private MainView view;

    /**
     * The controller managing the interaction between the view and the model.
     */
    private MainController controller;

    /**
     * The list from which the TriviaQuestion is selected.
     */
    private JList<TriviaQuestion> sourceList;

    /**
     * Determines whether the selected TriviaQuestion is moving to the user list or the API list.
     * If true, the question is moved to the user list; if false, it is moved to the API list.
     */

    private boolean moveToUser; // true if moving to user, false if moving to API

    /**
     * Constructs a MoveToActionListener with the specified parameters.
     *
     * @param view       the main view of the application.
     * @param controller the controller managing the interaction between the view and the model.
     * @param sourceList the list from which the TriviaQuestion is selected.
     * @param moveToUser true if the question is being moved to the user list, false if to the API list.
     */
    public MoveToActionListener(MainView view, MainController controller, JList<TriviaQuestion> sourceList,
                                boolean moveToUser) {
        this.view = view;
        this.controller = controller;
        this.sourceList = sourceList;
        this.moveToUser = moveToUser;
    }

    /**
     * Handles the action of moving the selected TriviaQuestion from one list to another.
     * Updates the relevant list models and re-applies filters and sorting.
     *
     * @param e the action event that triggered this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TriviaQuestion selectedQuestion = sourceList.getSelectedValue();
        if (selectedQuestion != null) {
            if (moveToUser) {
                controller.moveToUserCollection(selectedQuestion);
            } else {
                controller.moveToApiCollection(selectedQuestion);
            }
            view.updateApiListModel(controller.getApiQuestions());
            view.updateUserListModel(controller.getUserQuestions());
            view.updateButtons();
            // Reapply filters after the move
            FilterActionListener filterActionListener = new FilterActionListener(view.getFrame(), view,
                    view.getCheckboxState(), controller);
            filterActionListener.applyFilters();
            SortActionListener sortActionListener = new SortActionListener(controller, view, view.getCheckboxState());
            sortActionListener.sortUserList();
        }
    }
}
