package local.fleshtostone;

import local.fleshtostone.schema.*;

import java.util.List;

public class DecisionRunner {
    DecisionRepository repo;
    OutputManager output;
    GameState state;

    DecisionNode current = null;

    public DecisionRunner(DecisionRepository repo, OutputManager output, GameState state) {
        this.repo = repo;
        this.output = output;
        this.state = state;
    }

    public void setCurrent(final String nodeId) {
        current = repo.resolveDecisionNode(nodeId);
    }

    void traverseDecesisions() {
        while (current != null) {

            String prompt = current.text();
            List<DecisionChoice> choices = current.choices().stream().filter(n -> state.meetsConditions(n.required()))
                    .toList();

            int choice = output.getDecision(prompt, choices.stream().map(n -> n.text()).toList());

            state.applyFlags(choices.get(choice).flags());

            setCurrent(choices.get(choice).next());
        }
    }

}
