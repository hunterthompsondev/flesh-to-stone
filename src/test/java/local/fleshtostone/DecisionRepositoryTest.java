package local.fleshtostone;

import local.fleshtostone.schema.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DecisionRepositoryTest {

    @Test
    public void countDecisionNodes() {
        DecisionRepository decisionRepo = new DecisionRepository();
        int decisionCount = decisionRepo.loadDirectory("src/test/resources/decision-files");

        assertEquals(decisionCount, 6);
    }

    @Test
    public void correctDeicionNode() {
        DecisionRepository decisionRepo = new DecisionRepository();
        decisionRepo.loadDirectory("src/test/resources/decision-files");

        DecisionNode node = decisionRepo.resolveDecisionNode("test.exit_door");

        assertEquals(node.text(), "You reach the heavy iron door. Cold night air seeps through the keyhole.");
        assertEquals(node.choices().size(), 3);
        assertTrue(node.choices().get(0).text().equals("Unlock the door with the key ring"));
    }

    @Test
    public void cleanStoryHasNoBrokenLinks() {
        DecisionRepository repo = new DecisionRepository();
        repo.loadDirectory("src/test/resources/decision-files");

        assertTrue(repo.findBrokenLinks().isEmpty());
    }

    @Test
    public void storyWithMissingTargetHasBrokenLinks() {
        DecisionRepository repo = new DecisionRepository();
        repo.loadDirectory("src/test/resources/broken-links");

        List<String> broken = repo.findBrokenLinks();

        assertEquals(1, broken.size());
        assertEquals("broken.start -> broken.missing", broken.get(0));
    }

}
