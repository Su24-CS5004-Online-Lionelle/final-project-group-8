package group8.view.helpers;

import group8.model.FileUtilities;
import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Action listener for loading the user collection from a file.
 */
public class LoadActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /**
     * Constructs a LoadActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public LoadActionListener(DefaultListModel<TriviaQuestion> userListModel) {
        this.userListModel = userListModel;
    }

    /**
     * Invoked when an action occurs. Opens a file chooser to select a file
     * and loads the user collection from the selected file.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        // Restricts user to select JSON files only
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.
                FileNameExtensionFilter("JSON Files", "json"));

        int result = fileChooser.showOpenDialog(null);
        // Pulls in Trivia questions from loaded file.
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            try {
                // Save incoming questions as loadedQuestions
                List<TriviaQuestion> loadedQuestions = FileUtilities.loadTrivia(filePath);
                // Counters to display loaded vs skipped questions
                int addedQuestionCount = 0;
                int duplicateQuestionCount = 0;
                // Iterates and checks if questions are duplicate
                for (TriviaQuestion question : loadedQuestions) {
                    if (!isDuplicate(question, userListModel)) {
                        userListModel.addElement(question);
                        addedQuestionCount++;
                    } else {
                        duplicateQuestionCount++;
                    }
                }
                // Message of loaded vs skipped questions
                String loadMessage = String.format("Loaded %d new questions. %d duplicates were skipped.",
                        addedQuestionCount, duplicateQuestionCount);
                // Success Message
                JOptionPane.showMessageDialog(null, loadMessage, "Load Complete", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                // Failed message
                JOptionPane.showMessageDialog(null, "Error loading trivia: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Helper method to detect if incoming questions are duplicates by question name only.
     * @param newQuestion Incoming Trivial question.
     * @param currentQuestions Currently loaded Trivial Question.
     * @return Boolean True if question is duplicate, false otherwise.
     */
    private boolean isDuplicate(TriviaQuestion newQuestion, DefaultListModel<TriviaQuestion> currentQuestions) {
        for (int i = 0; i < currentQuestions.getSize(); i++) {
            TriviaQuestion existingQuestion = currentQuestions.getElementAt(i);
            if (existingQuestion.question().equals(newQuestion.question())) {
                return true;
            }
        }
        return false;
    }
}
