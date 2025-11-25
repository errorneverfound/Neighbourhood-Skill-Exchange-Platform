import java.util.ArrayList;
import java.util.List;

public class MatchEngine {

    public static List<User> findMatches(User current, List<User> allUsers) {
        List<User> matches = new ArrayList<>();

        for (User other : allUsers) {
            if (other == current) continue;

            boolean found = false;
            for (Skill needed : current.getSkillsNeeded()) {
                for (Skill offered : other.getSkillsOffered()) {
                    if (needed.getSkillName().equalsIgnoreCase(offered.getSkillName())) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            if (found) {
                matches.add(other);
            }
        }

        return matches;
    }
}

