package local.fleshtostone.schema;

import java.util.List;

public record DecisionChoice(
        String text,
        List<String> required,
        List<String> flags,
        String next) {
}
