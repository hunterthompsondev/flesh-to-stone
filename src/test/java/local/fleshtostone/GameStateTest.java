package local.fleshtostone;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class GameStateTest {

    @Test
    public void startWithNoFlags() {
        GameState state = new GameState();

        assertFalse(state.hasFlag("flag"));
    }

    @Test
    public void addFlag() {
        GameState state = new GameState();

        state.addFlag("a");

        assertTrue(state.hasFlag("a"));
        assertFalse(state.hasFlag("b"));
    }

    @Test
    public void removeFlag() {
        GameState state = new GameState();

        state.addFlag("a");
        assertTrue(state.hasFlag("a"));

        state.removeFlag("a");
        assertFalse(state.hasFlag("a"));
    }

    @Test
    public void removeNonexistantFlag() {
        GameState state = new GameState();

        state.removeFlag("a");
        assertFalse(state.hasFlag("a"));
    }

    @Test
    public void addExistingFlag() {
        GameState state = new GameState();

        state.addFlag("a");
        assertTrue(state.hasFlag("a"));

        state.addFlag("a");
        assertTrue(state.hasFlag("a"));
    }
}
