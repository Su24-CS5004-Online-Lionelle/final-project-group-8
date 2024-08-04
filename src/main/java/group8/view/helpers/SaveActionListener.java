package group8.view.helpers;

import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.model.FileUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Action listener for saving the user collection to a file.
 */
public class SaveActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;


    // for testing. to delete
    private List<TriviaQuestion> questions;
    // for testing. to delete


    /**
     * Constructs a SaveActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public SaveActionListener(DefaultListModel<TriviaQuestion> userListModel) {
        this.userListModel = userListModel;


        // Hardcoded questions for testing. to delete
        this.questions = new ArrayList<>();
        questions.add(new TriviaQuestion(
                Enums.QuestionType.BOOLEAN,
                Enums.Difficulty.EASY,
                Enums.Category.SPORTS,
                "The sky is blue.",
                "True",
                List.of("False")
        ));
        questions.add(new TriviaQuestion(
                Enums.QuestionType.MULTIPLE,
                Enums.Difficulty.MEDIUM,
                Enums.Category.ART,
                "What is the chemical symbol for gold?",
                "Au",
                List.of("Ag", "Fe", "Cu")
        ));
        //Hardcoded questions for testing. to delete


    }

    /**
     * Invoked when an action occurs. Opens a file chooser to select a file
     * and saves the user collection to the selected file.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Captures user selected file path location
            File selectedFolder = fileChooser.getSelectedFile();
            String folderPath = selectedFolder.getAbsolutePath();

            try { // Calls FileUtilities to save out trivia game
                FileUtilities.saveTrivia((Collection<TriviaQuestion>) questions, folderPath);
                JOptionPane.showMessageDialog(null, "Trivia saved successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving trivia: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
