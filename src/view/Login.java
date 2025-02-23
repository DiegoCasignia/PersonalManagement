package view;

import controller.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
    private final UserService userService = new UserService();
    private JTextField emailField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createTitledBorder("User Login"));

        loginPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        loginPanel.add(emailField);

        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::authenticateUser);
        loginPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> openRegisterWindow());
        loginPanel.add(registerButton);

        add(loginPanel, BorderLayout.CENTER);
    }

    private void openRegisterWindow(){
        new UserUI().setVisible(true);
        dispose();
    }

    private void authenticateUser(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        userService.getUserByEmail(email).ifPresentOrElse(
                user -> {
                    if (user.getPassword().equals(password)) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        new UserUI().setVisible(true); ///// aquiii va a pagina de inicio
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                },
                () -> JOptionPane.showMessageDialog(this, "User not found", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

}
