import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class generic_search {
	public static boolean goalTest(Map<Integer, int[]> coordinatesMap) {
		int[] firstCoordinates = null;
		for (Map.Entry<Integer, int[]> entry : coordinatesMap.entrySet()) {
			if (entry.getKey() > 0) { // Only consider microorganisms
				if (firstCoordinates == null) {
					firstCoordinates = entry.getValue();
				} else {
					int[] currentCoordinates = entry.getValue();
					if (firstCoordinates[0] != currentCoordinates[0] || firstCoordinates[1] != currentCoordinates[1]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean obstacleCheck(int[] organismCoordinates, Map<Integer, int[]> coordinatesMap) {
		for (Map.Entry<Integer, int[]> entry : coordinatesMap.entrySet()) {
			if (entry.getKey() == -1) { // Only consider obstacles
				int[] obstacleCoordinates = entry.getValue();
				if (obstacleCoordinates[0] == organismCoordinates[0]
						&& obstacleCoordinates[1] == organismCoordinates[1]) {
					return true;
				}
			}
		}
		return false;
	}

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
		return "Breadth-First Search result";
	}

	private static String depthFirstSearch() {
		return "Depth-First Search result";
	}

	private static String iterativeDeepeningSearch() {
		return "Iterative Deepening Search result";
	}

	private static String greedySearch1() {
		return "Greedy Search 1 result";
	}

	private static String greedySearch2() {
		return "Greedy Search 2 result";
	}

	private static String aStarSearch1() {
		return "A* Search 1 result";
	}

	private static String aStarSearch2() {
		return "A* Search 2 result";
	}

	public static void main(String[] args) {

	}
}
