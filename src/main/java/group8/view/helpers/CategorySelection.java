package group8.view.helpers;

import group8.model.Enums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A dialog that allows the user to select categories from a list.
 */
public class CategorySelection extends JDialog {

    /**
     * The list of categories displayed in the dialog.
     */
    private final JList<Enums.Category> categoryList;

    /**
     * The OK button to confirm the selection.
     */
    private final JButton okButton;

    /**
     * The Cancel button to cancel the selection.
     */
    private final JButton cancelButton;

    /**
     * Indicates whether the selection was confirmed.
     */
    private boolean confirmed;

    /**
     * The list of selected categories.
     */
    private List<Enums.Category> selectedCategories;

    /**
     * Constructs a CategorySelection.
     *
     * @param parent the parent frame
     */
    public CategorySelection(JFrame parent) {
        super(parent, "Select Categories", true);
        setLayout(new BorderLayout());

        // Create a list of categories with multi-selection enabled
        categoryList = new JList<>(Enums.Category.values());
        categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(categoryList);

        // Create OK and Cancel buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 400);
        setLocationRelativeTo(parent);

        // Add action listeners
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (categoryList.getSelectedValuesList().size() <= 4) {
                    selectedCategories = categoryList.getSelectedValuesList();
                    confirmed = true;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(CategorySelection.this,
                            "Please select up to 4 categories.",
                            "Selection Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                setVisible(false);
            }
        });
    }

    /**
     * Shows the dialog and returns whether the selection was confirmed.
     *
     * @return true if the selection was confirmed, false otherwise
     */
    public boolean showDialog() {
        confirmed = false;
        setVisible(true);
        return confirmed;
    }

    /**
     * Gets the selected categories.
     *
     * @return the selected categories
     */
    public List<Enums.Category> getSelectedCategories() {
        return selectedCategories;
    }
}
