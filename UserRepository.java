import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_NAME = "users.txt";
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        loadUsersFromFile();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    public void updateUsers() {
        saveUsersToFile();
    }

    public User findByEmailAndPassword(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public boolean emailExists(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private void loadUsersFromFile() {
        users.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // userId|name|email|phone|password|skillsOfferedString|skillsNeededString
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                String userId = parts[0];
                String name = parts[1];
                String email = parts[2];
                String phone = parts[3];
                String password = parts[4];

                User user = new User(userId, name, email, phone, password);

                if (parts.length >= 6 && !parts[5].isEmpty()) {
                    List<Skill> offered = parseSkills(parts[5]);
                    for (Skill s : offered) user.addOfferedSkill(s);
                }
                if (parts.length >= 7 && !parts[6].isEmpty()) {
                    List<Skill> needed = parseSkills(parts[6]);
                    for (Skill s : needed) user.addNeededSkill(s);
                }

                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsersToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (User u : users) {
                StringBuilder offered = new StringBuilder();
                for (Skill s : u.getSkillsOffered()) {
                    if (offered.length() > 0) offered.append(",");
                    // skillName:category:level
                    offered.append(s.getSkillName()).append(":")
                           .append(s.getCategory()).append(":")
                           .append(s.getLevel());
                }

                StringBuilder needed = new StringBuilder();
                for (Skill s : u.getSkillsNeeded()) {
                    if (needed.length() > 0) needed.append(",");
                    needed.append(s.getSkillName()).append(":")
                           .append(s.getCategory()).append(":")
                           .append(s.getLevel());
                }

                String line = u.getUserId() + "|" +
                              u.getName() + "|" +
                              u.getEmail() + "|" +
                              u.getPhone() + "|" +
                              u.getPassword() + "|" +
                              offered.toString() + "|" +
                              needed.toString();

                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Skill> parseSkills(String skillsStr) {
        List<Skill> list = new ArrayList<>();
        String[] skillTokens = skillsStr.split(",");
        for (String token : skillTokens) {
            String[] parts = token.split(":");
            if (parts.length == 3) {
                Skill s = new Skill(parts[0], parts[1], parts[2]);
                list.add(s);
            }
        }
        return list;
    }
}

