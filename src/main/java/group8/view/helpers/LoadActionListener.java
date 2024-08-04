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

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            try {
                List<TriviaQuestion> loadedQuestions = FileUtilities.loadTrivia(filePath);


                // Testing function
            System.out.println("Loaded objects via LoadedQuestions");
            for (TriviaQuestion q : loadedQuestions) {
                System.out.println(q.question());
            }
                // Testing function


                // Display function here.

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading trivia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
