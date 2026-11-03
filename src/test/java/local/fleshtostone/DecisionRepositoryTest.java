package local.fleshtostone;

import local.fleshtostone.schema.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

}
