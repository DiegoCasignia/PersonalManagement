package view;

import controller.UserService;
import model.User;
import model.UserSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserUI extends JFrame {
    private final UserService userService = new UserService();
    private JTextField nameField, emailField, passwordField, searchField;
    private JTextArea resultArea;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserUI() {
        setTitle("User Management");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de Registro
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Register User"));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerUser());
        formPanel.add(registerButton);

        // Panel de Búsqueda
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search User"));
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchUser());

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        resultArea = new JTextArea(3, 30);
        resultArea.setEditable(false);
        searchPanel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Tabla de Usuarios
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        userTable = new JTable(tableModel);
        JButton loadUsersButton = new JButton("View All Users");
        loadUsersButton.addActionListener(e -> loadAllUsers());

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Users"));
        tablePanel.add(new JScrollPane(userTable), BorderLayout.CENTER);
        tablePanel.add(loadUsersButton, BorderLayout.SOUTH);

        // Agregar Paneles a la Ventana
        add(formPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.SOUTH);
    }

    private void registerUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(((JPasswordField) passwordField).getPassword());
        UserSettings settings = new UserSettings();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User newUser = new User(name, email, password, settings);
            userService.createUser(newUser);
            JOptionPane.showMessageDialog(this, "User registered successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchUser() {
        String query = searchField.getText();
        if (query.isEmpty()) {
            resultArea.setText("Enter ID or Email to search");
            return;
        }

        userService.getUserById(query).ifPresentOrElse(
                user -> resultArea.setText(user.toString()),
                () -> userService.getUserByEmail(query).ifPresentOrElse(
                        user -> resultArea.setText(user.toString()),
                        () -> resultArea.setText("User not found"))
        );
    }

    private void loadAllUsers() {
        List<User> users = userService.getAllUsers();
        tableModel.setRowCount(0);
        users.forEach(user -> tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getEmail()}));
    }

}
