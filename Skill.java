public class Skill { // Fixed: removed the second 'public'
    private String skillName;
    private String category;     // e.g., "Tutoring", "Repair"
    private String level;        // e.g., "Beginner", "Intermediate", "Expert"

    public Skill(String skillName, String category, String level) {
        this.skillName = skillName;
        this.category = category;
        this.level = level;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return skillName + " (" + category + ", " + level + ")";
    }
}

