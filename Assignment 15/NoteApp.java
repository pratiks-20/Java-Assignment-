import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NoteApp extends JFrame {
    private DefaultListModel<String> noteListModel;
    private JList<String> noteList;
    private JTextField noteInput;
    private JButton addButton, deleteButton, updateButton;

    public NoteApp() {
        // Frame setup
        setTitle("Note App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        noteListModel = new DefaultListModel<>();
        noteList = new JList<>(noteListModel);
        noteInput = new JTextField();
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");

        // Panel for input and buttons
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(noteInput, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add to main frame
        add(new JScrollPane(noteList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> {
            String text = noteInput.getText().trim();
            if (!text.isEmpty()) {
                noteListModel.addElement(text);
                noteInput.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = noteList.getSelectedIndex();
            if (selectedIndex != -1) {
                noteListModel.remove(selectedIndex);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedIndex = noteList.getSelectedIndex();
            String newText = noteInput.getText().trim();
            if (selectedIndex != -1 && !newText.isEmpty()) {
                noteListModel.set(selectedIndex, newText);
                noteInput.setText("");
            }
        });

        // Fill input when item is clicked
        noteList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = noteList.getSelectedValue();
                if (selected != null) {
                    noteInput.setText(selected);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NoteApp().setVisible(true));
    }
}
