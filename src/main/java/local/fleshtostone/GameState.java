package local.fleshtostone;

import java.util.HashSet;
import java.util.List;

public class GameState {
    private HashSet<String> flags = new HashSet<>();

    public void addFlag(String flag) {
        flags.add(flag);
    }

    public void removeFlag(String flag) {
        flags.remove(flag);
    }

    public boolean hasFlag(String flag) {
        return flags.contains(flag);
    }

    public void applyFlags(List<String> flags) {
        if (flags == null) {
            return;
        }

        for (String flag : flags) {
            if (flag.startsWith("-")) {
                removeFlag(flag.substring(1));
            } else {
                addFlag(flag);
            }
        }
    }

    public boolean meetsConditions(List<String> conditions) {
        if (conditions == null) {
            return true;
        }

        for (String condition : conditions) {
            if (condition.startsWith("!")) {
                if (hasFlag(condition.substring(1))) {
                    return false;
                }
            } else if (!hasFlag(condition)) {
                return false;
            }
        }

        return true;
    }
}
