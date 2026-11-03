package local.fleshtostone.schema;

import java.util.List;

public record DecisionChoice(
        String text,
        String post,
        List<String> required,
        List<String> flags,
        String next) {
}
