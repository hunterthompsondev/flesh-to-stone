package local.fleshtostone;

import local.fleshtostone.schema.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Provide the path of directory containing the decision files");
            return;
        }

        DecisionRepository decisionRepo = new DecisionRepository();

        // Override for ad hoc testing
        // decisionRepo.loadDirectory(args[0]);
        decisionRepo.loadDirectory("decision-files/test/");

        DecisionNode node = decisionRepo.resolveDecisionNode("test.exit_door");

        OutputManager output = new OutputManager();

        int decision = output.getDecision(node.text(),
                node.choices().stream().map(n -> n.text()).collect(Collectors.toList()));
        System.out.println("Decision: " + node.choices().get(decision).text());
    }

}
