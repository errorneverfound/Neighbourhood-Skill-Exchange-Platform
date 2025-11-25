import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private UserRepository userRepository;
    private User currentUser;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private DashboardPanel dashboardPanel;

    public MainFrame() {
        userRepository = new UserRepository();

        setTitle("Neighborhood Skill Exchange");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this, userRepository);
        registerPanel = new RegisterPanel(this, userRepository);
        dashboardPanel = new DashboardPanel(this, userRepository);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(dashboardPanel, "dashboard");

        add(mainPanel);
        showLogin();
    }

    public void showLogin() {
        cardLayout.show(mainPanel, "login");
    }

    public void showRegister() {
        cardLayout.show(mainPanel, "register");
    }

    public void showDashboard(User user) {
        this.currentUser = user;
        dashboardPanel.setCurrentUser(user);
        cardLayout.show(mainPanel, "dashboard");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

