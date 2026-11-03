package local.fleshtostone;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.util.List;

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

        @Test
    public void emptyConditionsAreMet() {
        GameState state = new GameState();

        assertTrue(state.meetsConditions(List.of()));
        assertTrue(state.meetsConditions(null));
    }

    @Test
    public void requiredFlag() {
        GameState state = new GameState();

        assertFalse(state.meetsConditions(List.of("hairpin")));

        state.addFlag("hairpin");
        assertTrue(state.meetsConditions(List.of("hairpin")));
    }

    @Test
    public void negatedFlag() {
        GameState state = new GameState();

        assertTrue(state.meetsConditions(List.of("!key")));

        state.addFlag("key");
        assertFalse(state.meetsConditions(List.of("!key")));
    }

    @Test
    public void allConditionsMustBeMet() {
        GameState state = new GameState();
        state.addFlag("hairpin");

        assertTrue(state.meetsConditions(List.of("hairpin", "!key")));

        state.addFlag("key");
        assertFalse(state.meetsConditions(List.of("hairpin", "!key")));
    }

    @Test
    public void applyAddsFlag() {
        GameState state = new GameState();

        state.applyFlags(List.of("key"));

        assertTrue(state.hasFlag("key"));
    }

    @Test
    public void applyRemovesFlag() {
        GameState state = new GameState();
        state.addFlag("key");

        state.applyFlags(List.of("-key"));
        assertFalse(state.hasFlag("key"));
    }

    @Test
    public void applyAddsAndRemovesFlags() {
        GameState state = new GameState();
        state.addFlag("key");

        state.applyFlags(List.of("-key", "hairpin"));
        assertFalse(state.hasFlag("key"));
        assertTrue(state.hasFlag("hairpin"));
    }

    @Test
    public void applyNullDoesNothing() {
        GameState state = new GameState();
        state.addFlag("key");

        state.applyFlags(null);
        state.applyFlags(List.of());
        assertTrue(state.hasFlag("key"));
    }
}
