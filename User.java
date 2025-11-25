import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;          // simple unique id, e.g., email or generated
    private String name;
    private String email;
    private String phone;
    private String password;        // for learning only, no hashing here
    private List<Skill> skillsOffered;
    private List<Skill> skillsNeeded;

    public User(String userId, String name, String email, String phone, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.skillsOffered = new ArrayList<>();
        this.skillsNeeded = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public List<Skill> getSkillsOffered() {
        return skillsOffered;
    }

    public List<Skill> getSkillsNeeded() {
        return skillsNeeded;
    }

    public void addOfferedSkill(Skill skill) {
        skillsOffered.add(skill);
    }

    public void addNeededSkill(Skill skill) {
        skillsNeeded.add(skill);
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}

