package local.fleshtostone;

import java.util.HashSet;

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
}
