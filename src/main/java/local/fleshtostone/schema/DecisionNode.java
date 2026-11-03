package local.fleshtostone.schema;

import java.util.List;

public record DecisionNode(
        String id,
        String text,
        List<DecisionChoice> choices) {

    @Override
    public String toString() {
        return id + ":" + text;
    }
}
