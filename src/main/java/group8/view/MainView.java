package group8.view;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame {
    private JFrame frame;
    private JList<String> apiList;
    private JList<String> userList;
    private DefaultListModel<String> apiListModel;
    private DefaultListModel<String> userListModel;
    private JButton toUserButton;
    private JButton toApiButton;

    // Checkbox states
    private boolean category1Selected = false;
    private boolean category2Selected = false;
    private boolean category3Selected = false;
    private boolean difficultyEasySelected = false;
    private boolean difficultyMediumSelected = false;
    private boolean difficultyHardSelected = false;
    private boolean typeMultipleChoiceSelected = false;
    private boolean typeTrueFalseSelected = false;

    public MainView() {
        // Set the FlatLaf Dark look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Frame creation
        frame = new JFrame("Trivia Generator 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Welcome to Trivia Generator 1.0", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1;

        // Left Panel (API Collection)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
        apiListModel = new DefaultListModel<>();
        apiList = new JList<>(apiListModel);
        apiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        apiList.addListSelectionListener(e -> updateButtons());
        JScrollPane apiScrollPane = new JScrollPane(apiList);

        // Top left panel
        JPanel apiTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton filterButton = createIconButton("src/main/java/group8/view/icons/filter.png", 20, 20);
        JButton resetFilterButton = createIconButton("src/main/java/group8/view/icons/reload.png", 20, 20);
        apiTopPanel.add(filterButton);
        apiTopPanel.add(resetFilterButton);

        // Bottom left panel
        JPanel apiBottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton apiPullButton = new JButton("Generate New List");
        apiBottomPanel.add(apiPullButton);

        // Layout for Left Panel
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.gridx = 0;
        leftGbc.gridy = 0;
        leftGbc.weightx = 1;
        leftGbc.weighty = 0;
        leftPanel.add(apiTopPanel, leftGbc);

        leftGbc.gridy = 1;
        leftGbc.weighty = 1;
        leftGbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(apiScrollPane, leftGbc);

        leftGbc.gridy = 2;
        leftGbc.weighty = 0;
        leftPanel.add(apiBottomPanel, leftGbc);

        // Right Panel (User Collection)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addListSelectionListener(e -> updateButtons());
        JScrollPane userScrollPane = new JScrollPane(userList);

        // Right top panel
        JPanel userTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Sort by...", "Category", "Difficulty", "Question Type"});
        userTopPanel.add(sortComboBox);

        // Right bottom panel
        JPanel userBottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        userBottomPanel.add(saveButton);
        userBottomPanel.add(loadButton);

        // Layout for Right Panel
        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.gridx = 0;
        rightGbc.gridy = 0;
        rightGbc.weightx = 1;
        rightGbc.weighty = 0;
        rightPanel.add(userTopPanel, rightGbc);

        rightGbc.gridy = 1;
        rightGbc.weighty = 1;
        rightGbc.fill = GridBagConstraints.BOTH;
        rightPanel.add(userScrollPane, rightGbc);

        rightGbc.gridy = 2;
        rightGbc.weighty = 0;
        rightPanel.add(userBottomPanel, rightGbc);

        // Center Panel for Arrows
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints arrowGbc = new GridBagConstraints();
        arrowGbc.gridx = 0;
        arrowGbc.gridy = GridBagConstraints.RELATIVE;
        arrowGbc.anchor = GridBagConstraints.CENTER;
        arrowGbc.insets = new Insets(10, 0, 10, 0);
        toUserButton = new JButton(">>");
        toApiButton = new JButton("<<");
        toUserButton.setEnabled(false);
        toApiButton.setEnabled(false);
        centerPanel.add(toUserButton, arrowGbc);
        centerPanel.add(toApiButton, arrowGbc);

        // Add action listeners
        filterButton.addActionListener(new FilterActionListener());
        resetFilterButton.addActionListener(e -> resetFilters());
        apiPullButton.addActionListener(new ApiPullActionListener());
        sortComboBox.addActionListener(new SortActionListener());
        saveButton.addActionListener(new SaveActionListener());
        loadButton.addActionListener(new LoadActionListener());

        // Add panels to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(leftPanel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(rightPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        mainPanel.add(centerPanel, gbc);

        // Add main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Update the state of the arrow buttons based on list selections
    private void updateButtons() {
        toUserButton.setEnabled(apiList.getSelectedIndex() != -1);
        toApiButton.setEnabled(userList.getSelectedIndex() != -1);
    }

    // Resizes icon images for filter/reset buttons
    private JButton createIconButton(String path, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (img != null) {
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(scaledImg));
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setMargin(new Insets(0, 0, 0, 0));
            return button;
        } else {
            return new JButton(); // Return an empty button if the image fails to load
        }
    }

    private void resetFilters() {
        // Logic to reset filters
    }

    // Filter checkboxes
    private class FilterActionListener implements ActionListener {
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

            JCheckBox categoryOption1 = new JCheckBox("Category 1");
            JCheckBox categoryOption2 = new JCheckBox("Category 2");
            JCheckBox categoryOption3 = new JCheckBox("Category 3");
            categoryOption1.setSelected(category1Selected);
            categoryOption2.setSelected(category2Selected);
            categoryOption3.setSelected(category3Selected);
            filterOptionsPanel.add(categoryOption1);
            filterOptionsPanel.add(categoryOption2);
            filterOptionsPanel.add(categoryOption3);

            // Difficulty Filters
            JLabel difficultyLabel = new JLabel("Difficulty");
            difficultyLabel.setFont(new Font("Arial", Font.BOLD, 16));
            difficultyLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
            filterOptionsPanel.add(difficultyLabel);

            JCheckBox difficultyOption1 = new JCheckBox("Easy");
            JCheckBox difficultyOption2 = new JCheckBox("Medium");
            JCheckBox difficultyOption3 = new JCheckBox("Hard");
            difficultyOption1.setSelected(difficultyEasySelected);
            difficultyOption2.setSelected(difficultyMediumSelected);
            difficultyOption3.setSelected(difficultyHardSelected);
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
            typeOption1.setSelected(typeMultipleChoiceSelected);
            typeOption2.setSelected(typeTrueFalseSelected);
            filterOptionsPanel.add(typeOption1);
            filterOptionsPanel.add(typeOption2);

            JButton applyFiltersButton = new JButton("Apply Filters");
            applyFiltersButton.addActionListener(ev -> {
                // Collect filter parameters
                category1Selected = categoryOption1.isSelected();
                category2Selected = categoryOption2.isSelected();
                category3Selected = categoryOption3.isSelected();
                difficultyEasySelected = difficultyOption1.isSelected();
                difficultyMediumSelected = difficultyOption2.isSelected();
                difficultyHardSelected = difficultyOption3.isSelected();
                typeMultipleChoiceSelected = typeOption1.isSelected();
                typeTrueFalseSelected = typeOption2.isSelected();

                // Logic to apply filters based on checkbox selections
                filterDialog.dispose();
            });

            filterDialog.add(filterOptionsPanel, BorderLayout.CENTER);
            filterDialog.add(applyFiltersButton, BorderLayout.SOUTH);

            filterDialog.setLocationRelativeTo(frame);
            filterDialog.setVisible(true);
        }
    }

    // API listener
    private class ApiPullActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logic to pull questions from API and add to apiListModel
        }
    }

    // Sort listener
    private class SortActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedSort = (String) comboBox.getSelectedItem();
            // Logic to sort the user collection based on selectedSort
        }
    }

    // Save listener
    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Logic to save the user collection to the selected file
            }
        }
    }

    // Load listener
    private class LoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                // Logic to load the user collection from the selected file
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
