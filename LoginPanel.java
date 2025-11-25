import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private UserRepository userRepository;

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame, UserRepository userRepository) {
        this.mainFrame = mainFrame;
        this.userRepository = userRepository;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3, 2, 10, 10));
        center.add(new JLabel("Email:"));
        emailField = new JTextField();
        center.add(emailField);

        center.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        center.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton goToRegister = new JButton("Register");

        JPanel bottom = new JPanel();
        bottom.add(loginButton);
        bottom.add(goToRegister);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> doLogin());
        goToRegister.addActionListener(e -> mainFrame.showRegister());
    }

    private void doLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful");
            mainFrame.showDashboard(user);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password");
        }
    }
}

