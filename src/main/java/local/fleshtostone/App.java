package local.fleshtostone;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Provide the path of directory containing the decision files");
            return;
        }

        DecisionRepository decisionRepo = new DecisionRepository();
        decisionRepo.loadDirectory(args[0]);
    }

}
