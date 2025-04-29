import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoListApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> todoList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton completeButton;

    public TodoListApp() {
        setTitle("To-Do List App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        todoList = new JList<>(listModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        taskField = new JTextField(20);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();
                if (!task.isEmpty()) {
                    listModel.addElement(task);
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(TodoListApp.this,
                            "Please enter a task.", "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(TodoListApp.this,
                            "Select a task to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        completeButton = new JButton("Complete");
        completeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = listModel.getElementAt(selectedIndex);
                    if (!task.contains("[Completed]")) {
                        listModel.set(selectedIndex, task + " [Completed]");
                    } else {
                        JOptionPane.showMessageDialog(TodoListApp.this,
                                "Task is already marked as completed", "Already Completed", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(TodoListApp.this,
                            "Select a task to mark as complete", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(completeButton);

        panel.add(new JScrollPane(todoList), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TodoListApp().setVisible(true);
            }
        });
    }
}
