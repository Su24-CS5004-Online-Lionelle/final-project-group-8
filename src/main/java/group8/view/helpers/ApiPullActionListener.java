package group8.view.helpers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for pulling questions from an API and adding them to the API list model.
 */
public class ApiPullActionListener implements ActionListener {
    /** The list model for the API questions. */
    private DefaultListModel<String> apiListModel;

    /**
     * Constructs an ApiPullActionListener with the specified API list model.
     *
     * @param apiListModel the list model for the API questions
     */
    public ApiPullActionListener(DefaultListModel<String> apiListModel) {
        this.apiListModel = apiListModel;
    }

    /**
     * Invoked when an action occurs. Pulls questions from the API and adds them to the API list model.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Logic to pull questions from API and add to apiListModel
    }
}
