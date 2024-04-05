package View;

import javax.swing.*;

import Model.Model;

import java.awt.*;
import java.io.File;
import java.util.List;

public class GUI {
    private JFrame window;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private Model model;

    public GUI(Model model) {
        this.model = model;
        initView();
    }

    private void initView() {
        window = new JFrame("Text Editor");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        window.add(scrollPane, BorderLayout.CENTER);

        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");

        openButton.addActionListener(e -> openFile());
        saveButton.addActionListener(e -> saveFile());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        window.add(buttonPanel, BorderLayout.NORTH);

        fileChooser = new JFileChooser();
    }

    private void openFile() {
        int result = fileChooser.showOpenDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                model.loadFile(file.getAbsolutePath());
                textArea.setText(model.getFileContent());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(window, "Error loading file: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        int result = fileChooser.showSaveDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                model.saveFile(file.getAbsolutePath(), textArea.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(window, "Error saving file: " + ex.getMessage());
            }
        }
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            window.setVisible(true);
        });
    }
}
