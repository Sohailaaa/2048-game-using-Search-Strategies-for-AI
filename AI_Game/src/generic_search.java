import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class generic_search {
	public static int row = 1;
	public static int column = 1;
	public static int[][] grid = new int[1][1];
	public static ArrayList<Integer> organisms_id = new ArrayList<>();

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

	public static boolean goalTest(Node n) {
		if (n.organisms_id.size() == 1)
			return true;
		return false;
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

	public static boolean boundaryCheck(int[] coordinates) {
		if (coordinates[0] < 0 || coordinates[0] > row - 1) // checks rows x
			return true;
		else if (coordinates[1] < 0 || coordinates[1] > column - 1) // checks columns y
			return true;

		return false;

	}

	public static List<String> search(int[][] gridG, String strategy, boolean visualize) {
		grid = gridG;
		row = grid.length;
		column = grid[0].length;
		Map<Integer, int[]> coordinatesMap = reshape(grid);
		printCoordinatesMap(coordinatesMap);
		switch (strategy) {
		case "BF":
			return PathMapper(reconstructPath(breadthFirstSearch(coordinatesMap)));
		case "DF":
			// return depthFirstSearch();
		case "ID":
			// return iterativeDeepeningSearch();
		case "GR1":
			// return greedySearch1();
		case "GR2":
			// return greedySearch2();
		case "AS1":
			// return aStarSearch1();
		case "AS2":
			// return aStarSearch2();
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
					if (grid[i][j] != -1)
						organisms_id.add(grid[i][j]);
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

	public static List<Node> expand(Node node) {
		List<Node> children = new ArrayList<>();
		// Create a copy of organisms_id to iterate over
		List<Integer> organismsCopy = new ArrayList<>(node.organisms_id);

		for (int i = 0; i < organismsCopy.size(); i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print("hi" + node.grid);
				System.out.print("organisms" + node.organisms_id);
				System.out.print("i" + i);
				int[] new_coordinate = node.move(node.grid.get(organismsCopy.get(i)), j);
				if (!(boundaryCheck(new_coordinate) || obstacleCheck(new_coordinate, node.grid))) {
					int[] action = new int[] { organismsCopy.get(i), j };
					Node child = new Node(node.grid, node, action, node.organisms_id, node.cost);
					child.updateGrid();
					children.add(child);
				}
			}
		}
		return children;
	}

	private static Node breadthFirstSearch(Map<Integer, int[]> gridInitial) {
		Node root = new Node(gridInitial, null, null, organisms_id, 0);
		if (goalTest(root)) {
			return root;
		}
		Deque<Node> queue = new ArrayDeque<>();
		Set<Map<Integer, int[]>> visited = new HashSet<>();
		queue.add(root);
		visited.add(deepCopyGrid(root.grid)); // Assuming a method to deep copy the grid map

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (goalTest(node)) {
				return node;
			}
			for (Node child : expand(node)) {
				Map<Integer, int[]> childGridCopy = deepCopyGrid(child.grid);
				if (!visited.contains(childGridCopy)) {
					queue.add(child);
					visited.add(childGridCopy);
				}
			}
		}
		return null;
	}

	private static Map<Integer, int[]> deepCopyGrid(Map<Integer, int[]> original) {
		Map<Integer, int[]> copy = new HashMap<>();
		for (Map.Entry<Integer, int[]> entry : original.entrySet()) {
			copy.put(entry.getKey(), entry.getValue().clone());
		}
		return copy;
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
