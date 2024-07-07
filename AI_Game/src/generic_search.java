import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class generic_search {
	public static int row = 1;
	public static int column = 1;
	public static int[][] grid = new int[1][1];

	static List<int[]> reconstructPath(Node node) {
		List<int[]> path = new ArrayList<>();
		while (node != null) {
			path.add(node.action);
			node = node.parent;
		}
		Collections.reverse(path);
		return path;
	}

	static List<String> PathMapper(List<int[]> path) {
		String[] directionMapping = { "North", "South", "East", "West" };

		List<String> mappedPath = new ArrayList<>();

		for (int[] point : path) {
			int row = point[0];
			int col = point[1];

			String organism = "Organism_" + (row);
			String direction = directionMapping[col - 1]; // Assuming col corresponds to direction index

			mappedPath.add(organism);
			mappedPath.add(direction);
		}

		return mappedPath;
	}

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

	public static String search(int[][] gridG, String strategy, boolean visualize) {
		grid = gridG;
		row = grid.length - 1;
		column = grid[0].length - 1;
		Map<Integer, int[]> coordinatesMap = reshape(grid);
		printCoordinatesMap(coordinatesMap);
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

	public static Map<Integer, int[]> reshape(int[][] grid) {
		Map<Integer, int[]> coordinatesMap = new HashMap<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != 0) {
					coordinatesMap.put(grid[i][j], new int[] { i, j });
				}
			}
		}

		return coordinatesMap;
	}

	public static void printCoordinatesMap(Map<Integer, int[]> coordinatesMap) {
		for (Map.Entry<Integer, int[]> entry : coordinatesMap.entrySet()) {
			int key = entry.getKey();
			int[] coordinates = entry.getValue();
			System.out.println("Key: " + key + " -> Coordinates: [" + coordinates[0] + ", " + coordinates[1] + "]");
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

	public static boolean boundaryCheck(int[] coordinates) {
		if (coordinates[0] < 0 || coordinates[0] > row - 1) // checks rows x
			return true;
		else if (coordinates[1] < 0 || coordinates[1] > column - 1) // checks columns y
			return true;

		return false;

	}

	public static void main(String[] args) {

	}
}
