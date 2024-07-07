
public class generic_search {
	public static String search(String strategy) {
        switch (strategy) {
            case "BF":
                return breadthFirstSearch();
            case "DF":
                return depthFirstSearch();
            case "ID":
                return iterativeDeepeningSearch();
            case "GR1":
                return greedySearch1();
            case "GR2":
                return greedySearch2();
            case "AS1":
                return aStarSearch1();
            case "AS2":
                return aStarSearch2();
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
    }

    private static String breadthFirstSearch() {
        // Implement the breadth-first search algorithm here
        return "Breadth-First Search result";
    }

    private static String depthFirstSearch() {
        // Implement the depth-first search algorithm here
        return "Depth-First Search result";
    }

    private static String iterativeDeepeningSearch() {
        // Implement the iterative deepening search algorithm here
        return "Iterative Deepening Search result";
    }

    private static String greedySearch1() {
        // Implement the first greedy search algorithm here
        return "Greedy Search 1 result";
    }

    private static String greedySearch2() {
        // Implement the second greedy search algorithm here
        return "Greedy Search 2 result";
    }

    private static String aStarSearch1() {
        // Implement the first A* search algorithm here
        return "A* Search 1 result";
    }

    private static String aStarSearch2() {
        // Implement the second A* search algorithm here
        return "A* Search 2 result";
    }

    public static void main(String[] args) {
     
    }
}
