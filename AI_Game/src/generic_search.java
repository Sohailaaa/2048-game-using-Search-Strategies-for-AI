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
	public static int row;
	public static int column;
	public static int[][] grid;
	public static ArrayList<Organism> organismList;
	public static ArrayList<Node> reached = new ArrayList<>();

	static List<int[]> reconstructPath(Node node) {
		List<int[]> path = new ArrayList<>();
		while (node != null) {
			if (node.action != null) {
				path.add(node.action);
			}
			node = node.parent;
		}
		Collections.reverse(path);
		for (int[] p : path) {
			System.out.print("recons" + p[0] + "." + p[1]);
		}
		return path;
	}

	static List<String> PathMapper(List<int[]> path) {
//		System.out.println("path"+path.iter);
		for (int[] p : path) {
			System.out.print("recons" + p[0] + "." + p[1]);
		}
		String[] directionMapping = { "North", "South", "East", "West" };

		List<String> mappedPath = new ArrayList<>();
		if (path == null)
			return null;
		for (int[] point : path) {
			int row = point[0];
			int col = point[1];

			String organism = "Organism_" + (row);
			String direction = directionMapping[col - 1]; // Assuming col corresponds to direction index
			String state = "" + organism + "_" + direction + ",";
			mappedPath.add(organism);
			mappedPath.add(direction);
		}

		return mappedPath;
	}

	public static boolean goalTest(Node n) {
		if (n.organismList.size() == 1)
			return true;
		return false;
	}

	public static List<String> search(int[][] gridG, String strategy, boolean visualize) {
		row = gridG.length;
		column = gridG[0].length;
		organismList = new ArrayList<>();
		Map<Integer, int[]> coordinatesMap = reshape(gridG);
		// printCoordinatesMap(coordinatesMap);
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

	// Transforms grid from 2D array of integers, into a map of
	// integers with their corresponding coordinates.
	public static Map<Integer, int[]> reshape(int[][] grid) {
		Map<Integer, int[]> coordinatesMap = new HashMap<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != 0) {

					coordinatesMap.put(grid[i][j], new int[] { i, j });

				}
				if (grid[i][j] > 0) {
					Organism org = new Organism(grid[i][j], 1, i, j);
					organismList.add(org);

				}

			}
		}
		System.out.println("hashmap" + coordinatesMap);
		return coordinatesMap;
	}

	public static void printCoordinatesMap(Map<Integer, int[]> coordinatesMap) {
		for (Map.Entry<Integer, int[]> entry : coordinatesMap.entrySet()) {
			int key = entry.getKey();
			int[] coordinates = entry.getValue();
			System.out.println(
					"Hashmap: " + "Key: " + key + " -> Coordinates: [" + coordinates[0] + ", " + coordinates[1] + "]");
		}
	}

	public static boolean checkSameState(Node a, Node b) {
		if (a.action == null || b.action == null)
			return false;
		int org1 = b.action[0];

		if ((a.grid.get(org1)[0] == b.grid.get(org1)[0]) && (a.grid.get(org1)[1] == a.grid.get(org1)[1])) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<Organism> organismListClone(ArrayList<Organism> original) {
		ArrayList<Organism> clone = new ArrayList<>();

		for (Organism o : original) {
			clone.add(o.clone());
		}

		return clone;
	}

	public static boolean checkAddState(Node n) {
		for (Node pastNode : reached) {
			if (n.compareTo(pastNode) < 0) {
				return true;
			}
		}
		return false;
	}

	public static List<Node> expand(Node node) {
		List<Node> children = new ArrayList<>();
		ArrayList<Organism> organismsCopy = organismListClone(node.organismList);

		for (int i = 0; i < organismsCopy.size(); i++) {
			for (int j = 1; j <= 4; j++) {
				int[] action = new int[] { organismsCopy.get(i).getId(), j };
				// Create a deep copy of the grid
				Map<Integer, int[]> childGrid = new HashMap<>(node.grid);
				ArrayList<Organism> childOrganismList = organismListClone(node.organismList);

				Node child = new Node(childGrid, node, action, childOrganismList, node.cost);
				// System.out.print("nodeGrid " + node.grid);
				int id = organismsCopy.get(i).getId();
				int[] coordinates = childGrid.get(id);
				child.move(coordinates, action);
				if (!child.isDead)
					children.add(child);
			}
		}
		return children;
	}

	private static Node breadthFirstSearch(Map<Integer, int[]> gridInitial) {
		System.out.println(organismList + "orgList");

		Node root = new Node(gridInitial, null, null, organismList, 0);
		if (goalTest(root)) {
			System.out.print("root");
			return root;
		}
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		reached.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();

			for (Node child : expand(node)) {
				if (goalTest(child)) {
					System.out.print(child.organismList.get(0).getId() + "heeeeeeey" + child.organismList.get(0).size
							+ "  " + child.grid + "  with cost: " + child.cost);

					return child;
				}
				if (checkAddState(child)) {
					reached.add(child);
					queue.add(child);
				}

			}
		}

		System.out.print("no Solution");
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
