package student;

import javax.swing.*;

import student.controller.MainController;
import student.view.MainView;

/**
 * TriviaApp is the entry point for the Trivia Generator application.
 * It initializes the main controller and main view of the application.
 */
public class TriviaApp {

    /**
     * The main method to start the application.
     *
     * @param args command-line arguments (not used). 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainController controller = new MainController();
                new MainView(controller);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
