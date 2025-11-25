import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private MainFrame mainFrame;
    private UserRepository userRepository;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterPanel(MainFrame mainFrame, UserRepository userRepository) {
        this.mainFrame = mainFrame;
        this.userRepository = userRepository;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(5, 2, 10, 10));
        center.add(new JLabel("Name:"));
        nameField = new JTextField();
        center.add(nameField);

        center.add(new JLabel("Email:"));
        emailField = new JTextField();
        center.add(emailField);

        center.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        center.add(phoneField);

        center.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        center.add(passwordField);

        center.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        center.add(confirmPasswordField);

        JButton registerButton = new JButton("Register");
        JButton backToLogin = new JButton("Back to Login");

        JPanel bottom = new JPanel();
        bottom.add(registerButton);
        bottom.add(backToLogin);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        registerButton.addActionListener(e -> doRegister());
        backToLogin.addActionListener(e -> mainFrame.showLogin());
    }

    private void doRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        }

        if (userRepository.emailExists(email)) {
            JOptionPane.showMessageDialog(this, "Email already registered");
            return;
        }

        String userId = "U" + System.currentTimeMillis();
        User user = new User(userId, name, email, phone, password);
        userRepository.addUser(user);

        JOptionPane.showMessageDialog(this, "Registration successful");
        mainFrame.showLogin();
    }
}

