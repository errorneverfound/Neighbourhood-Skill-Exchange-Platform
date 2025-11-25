import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {
    private UserRepository userRepository;
    private User currentUser;

    private JLabel welcomeLabel;
    private JTextArea infoArea;

    public DashboardPanel(MainFrame mainFrame, UserRepository userRepository) {
        this.userRepository = userRepository;

        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton manageSkillsBtn = new JButton("Manage Skills");
        JButton findMatchesBtn = new JButton("Find Matches");
        JButton logoutBtn = new JButton("Logout");
        buttonPanel.add(manageSkillsBtn);
        buttonPanel.add(findMatchesBtn);
        buttonPanel.add(logoutBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        add(new JScrollPane(infoArea), BorderLayout.CENTER);

        manageSkillsBtn.addActionListener(e -> showSkillsInfo());
        findMatchesBtn.addActionListener(e -> showMatches());
        logoutBtn.addActionListener(e -> mainFrame.showLogin());
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getName());
        refreshInfo();
    }

    private void refreshInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Offered Skills:\n");
        for (Skill s : currentUser.getSkillsOffered()) {
            sb.append(" - ").append(s.toString()).append("\n");
        }
        sb.append("\nYour Needed Skills:\n");
        for (Skill s : currentUser.getSkillsNeeded()) {
            sb.append(" - ").append(s.toString()).append("\n");
        }
        infoArea.setText(sb.toString());
    }

    private void showSkillsInfo() {
        JOptionPane.showMessageDialog(this,
                "You can now implement a separate SkillsManagementPanel\n" +
                "to add/remove offered and needed skills.");
    }

    private void showMatches() {
        List<User> all = userRepository.getAllUsers();
        List<User> matches = MatchEngine.findMatches(currentUser, all);
        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No matches found yet.");
            return;
        }
        StringBuilder sb = new StringBuilder("Matches:\n");
        for (User u : matches) {
            sb.append(u.getName())
              .append(" - Email: ").append(u.getEmail())
              .append(", Phone: ").append(u.getPhone())
              .append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}

