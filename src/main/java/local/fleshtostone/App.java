package local.fleshtostone;

import local.fleshtostone.schema.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Provide the path of directory containing the decision files");
            return;
        }

        DecisionRepository repo = new DecisionRepository();
        OutputManager output = new OutputManager();
        GameState state = new GameState();

        DecisionRunner runner = new DecisionRunner(repo, output, state);

        // Override for ad hoc testing
        // decisionRepo.loadDirectory(args[0]);
        repo.loadDirectory("decision-files/test/");
        runner.setCurrent("test.start");
        runner.traverseDecesisions();

        if (state.hasFlag("win")) {
            System.out.println("You win");
        }
        System.out.println("You lost");
    }

}
