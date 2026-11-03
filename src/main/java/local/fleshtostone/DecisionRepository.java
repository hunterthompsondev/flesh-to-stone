package local.fleshtostone;

import local.fleshtostone.schema.*;

import java.util.List;
import java.util.HashMap;
import java.util.stream.Stream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import tools.jackson.databind.json.JsonMapper;

public class DecisionRepository {
    private HashMap<String, DecisionNode> decisions = new HashMap<>();

    private int loadDecisionFile(final Path file) {
        JsonMapper mapper = JsonMapper.builder().build();
        DecisionFile decisionFile = mapper.readValue(file, DecisionFile.class);
        for (final DecisionNode decision : decisionFile.decisions()) {
            decisions.put(decision.id(), decision);
        }

        return decisionFile.decisions().size();
    }

    public int loadDirectory(final String directoryPath) {
        System.out.println("Loading decisions in " + directoryPath);

        Path dir = Paths.get(directoryPath);
        List<Path> files;

        try (Stream<Path> stream = Files.list(dir)) {
            files = stream.filter(Files::isRegularFile).toList();
            System.out.println("Found " + files.size() + " file(s)");
        } catch (IOException e) {
            return -1;
        }

        boolean valid = true;
        int decisionCount = 0;
        for (final Path file : files) {
            int newDecisions = loadDecisionFile(file);
            valid &= newDecisions != -1;
            decisionCount += newDecisions;
        }

        if (valid) {
            System.out.println("Loaded " + decisionCount + " decision(s)");
        }

        return valid ? decisionCount : -1;
    }

    public DecisionNode resolveDecisionNode(String nodeId) {
        return decisions.get(nodeId);
    }
}
