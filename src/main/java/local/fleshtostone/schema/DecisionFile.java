package local.fleshtostone.schema;

import java.util.List;

public record DecisionFile(
        String room,
        List<DecisionNode> decisions) {
};
