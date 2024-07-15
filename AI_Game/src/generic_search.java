import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class generic_search {
	public static int row;
	public static int column;
	public static int[][] grid;
	public static ArrayList<Organism> organismList;
	public static HashMap<Integer, Integer> visited;
	public static int totalCost;
	public static int numOfNodesExpanded;

	static List<int[]> reconstructPath2(Node node) {
		List<int[]> path = new ArrayList<>();
		while (node != null) {
			if (node.action != null) {
				path.add(node.action);
			}
			node = node.parent;
		}
		Collections.reverse(path);

		return path;
	}

	static String reconstructPath(Node node, boolean visualize) {
		List<String> path = new ArrayList<>();
		List<Map<Integer, int[]>> gridSteps = new ArrayList<>();

		int row = 0;
		int col = 0;
		while (node != null) {
			int[] action = node.action;
			if (action != null) {
				int org_id = action[0];
				for (Organism o : node.organismList) {
					if (o.id == org_id) {
						row = o.row;
						col = o.column;
					}
				}

				String direction = directionMapper(action[1]);
				String step = direction + "_" + col + "_" + row;
				path.add(step);
			}
			if (visualize) {
				if (node.grid != null) {
					gridSteps.add(new HashMap<>(node.grid));
				}
			}
			node = node.parent;
		}
		Collections.reverse(path);

		Collections.reverse(gridSteps);

		StringBuilder result = new StringBuilder();
		for (String step : path) {

			result.append(step).append(",");
		}
		// Remove the last comma and add a semicolon
		if (result.length() > 0) {
			result.setLength(result.length() - 1);
			result.append(";");
		}

		result.append(totalCost).append(";").append(numOfNodesExpanded);
		if (visualize) {
			for (Map<Integer, int[]> gridStep : gridSteps) {
				printGridStep(gridStep);
			}
		}
		return result.toString();
	}

	private static void printGridStep(Map<Integer, int[]> gridStep) {
		int[][] tempGrid = new int[grid.length][grid[0].length];

		for (Map.Entry<Integer, int[]> entry : gridStep.entrySet()) {
			int id = entry.getKey();
			int[] coord = entry.getValue();
			tempGrid[coord[0]][coord[1]] = id;
		}

		for (int[] row : tempGrid) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void printGrid() {
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static String directionMapper(int direction) {
		switch (direction) {
		case 1:
			return "NORTH";
		case 2:
			return "SOUTH";
		case 3:
			return "EAST";
		case 4:
			return "WEST";
		default:
			return "INVALID DIRECTION";
		}
	}

	static List<String> PathMapper(List<int[]> path) {
//		System.out.println("path"+path.iter);

		String[] directionMapping = { "North", "South", "East", "West" };

		List<String> mappedPath = new ArrayList<>();
		if (path == null)
			return null;
		for (int[] point : path) {
			int row = point[0];
			int col = point[1];

			String organism = "Organism_" + (row);
			String direction = directionMapping[col - 1]; // Assuming col corresponds to direction index
			String state = " " + organism + "_" + direction + ", ";
			mappedPath.add(state);

		}

		return mappedPath;
	}

	public static boolean goalTest(Node n) {
		if (n.organismList.size() == 1)
			return true;
		return false;
	}

	public static int[][] stringToGrid(String string) {
		String[] dividedString = string.split(";");

		row = Integer.parseInt(dividedString[1]);
		column = Integer.parseInt(dividedString[0]);
		grid = new int[row][column];

		String[] organisms = dividedString[2].split(",");

		for (int i = 0; i < organisms.length; i += 2) {
			int idx = (i / 2) + 1;
			int row = Integer.parseInt(organisms[i + 1]);
			int col = Integer.parseInt(organisms[i]);

			grid[row][col] = idx;
		}

		String[] obstacles = dividedString[3].split(",");
		for (int i = 0; i < obstacles.length; i += 2) {
			int idx = (i / 2) + 1;
			int row = Integer.parseInt(obstacles[i + 1]);
			int col = Integer.parseInt(obstacles[i]);

			grid[row][col] = -idx;
		}

		return grid;
	}

	public static String search(String gridG, String strategy, boolean visualize) {

		organismList = new ArrayList<>();
		totalCost = 0;
		numOfNodesExpanded = 0;

		Map<Integer, int[]> coordinatesMap = reshape(stringToGrid(gridG));
		visited = new HashMap<>();
		// printCoordinatesMap(coordinatesMap);
		switch (strategy) {
		case "BF":
			return reconstructPath(breadthFirstSearch(coordinatesMap, 1), visualize);
		case "DF":
			return (reconstructPath(breadthFirstSearch(coordinatesMap, 2), visualize));
		case "ID":
			return (reconstructPath(iterativeDeepeningSearch(coordinatesMap, 1000), visualize));
		case "UC":
			return (reconstructPath(uniformCostSearch(coordinatesMap), visualize));

		case "GR1":
			return (reconstructPath(GreedySearch_numOfOrgs(coordinatesMap), visualize));
		case "GR2":
			return (reconstructPath(GreedySearch_SumMinDistanceBetweenOrgs(coordinatesMap), visualize));
		case "AS1":
			return (reconstructPath(AS_Search_numOfOrgs(coordinatesMap), visualize));
		case "AS2":
			return (reconstructPath(AS_Search_SumMinDistanceBetweenOrgs(coordinatesMap), visualize));

		default:
			throw new IllegalArgumentException("Unknown strategy: " + strategy);
		}
	}

	public static void printGrid(int[][] grid) {
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	// Transforms grid from 2D array of integers, into a map of
	// integers with their corresponding coordinates.
	public static Map<Integer, int[]> reshape(int[][] grid) {
		printGrid(grid);

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
		Integer hValue = n.hashCode();
		if (!visited.containsKey(hValue)) {
			visited.put(hValue, n.cost);
			return true;
		}
		if (n.cost < visited.get(hValue)) {
			visited.put(hValue, n.cost);
			return true;
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

	private static Node breadthFirstSearch(Map<Integer, int[]> gridInitial, int BfORDF) {
		System.out.println(organismList + "orgList");

		Node root = new Node(gridInitial, null, null, organismList, 0);
		if (goalTest(root)) {
			totalCost = root.cost;
			System.out.print("root");
			return root;
		}
		Deque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		checkAddState(root);
		while (!queue.isEmpty()) {
			Node node;
			if (BfORDF == 1) {

				node = queue.poll();
			} else {
				node = queue.pollLast();
			}
			numOfNodesExpanded++;
			for (Node child : expand(node)) {
				if (goalTest(child)) {
					totalCost = child.cost;

					return child;
				}
				if (!child.isDead && checkAddState(child)) {
					queue.add(child);

				}
			}

		}

		System.out.print("no Solution");
		return null;
	}

	private static Node iterativeDeepeningSearch(Map<Integer, int[]> gridInitial, int maxDepth) {
		System.out.println(organismList + "orgList");

		for (int depth = 1; depth <= maxDepth; depth++) {
			Node result = depthLimitedSearch(new Node(gridInitial, null, null, organismList, 0), depth);
			if (result != null) {
				return result;
			}
		}

		System.out.print("no Solution");
		return null;
	}

	private static Node depthLimitedSearch(Node node, int limit) {
		visited.clear();
		checkAddState(node);
		return depthLimitedSearchHelper(node, limit, 0);
	}

	private static Node depthLimitedSearchHelper(Node node, int limit, int depth) {
		if (goalTest(node)) {
			totalCost = node.cost;
			return node;
		}
		if (depth == limit) {
			return null;
		}

		for (Node child : expand(node)) {
			if (!child.isDead && checkAddState(child)) {
				numOfNodesExpanded++;
				Node result = depthLimitedSearchHelper(child, limit, depth + 1);
				if (result != null) {
					return result;
				}
			}
		}

		return null;
	}

	public static Node uniformCostSearch(Map<Integer, int[]> gridInitial) {
		System.out.println(organismList + "orgList");

		Node root = new Node(gridInitial, null, null, organismList, 0);
		if (goalTest(root)) {
			totalCost = root.cost;

			return root;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(root);
		checkAddState(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			numOfNodesExpanded++;
			for (Node child : expand(node)) {
				if (goalTest(child)) {
					totalCost = child.cost;

					return child;
				}
				if (!child.isDead && checkAddState(child)) {
					queue.add(child);
				}
			}
		}

		System.out.print("no Solution");
		return null;
	}

	private static Node GreedySearch_numOfOrgs(Map<Integer, int[]> coordinatesMap) {
		System.out.println(organismList + "orgList");

		Node root = new Node(coordinatesMap, null, null, organismList, 0);
		if (goalTest(root)) {
			totalCost = root.cost;

			System.out.print("root");
			return root;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>(
				(node1, node2) -> Integer.compare(node1.organismList.size(), node2.organismList.size()));

		queue.add(root);
		checkAddState(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			numOfNodesExpanded++;
			for (Node child : expand(node)) {
				if (goalTest(child)) {
					totalCost = child.cost;

					return child;
				}
				if (!child.isDead && checkAddState(child)) {
					queue.add(child);
				}
			}
		}

		System.out.print("no Solution");
		return null;
	}

	private static Node AS_Search_numOfOrgs(Map<Integer, int[]> coordinatesMap) {
		System.out.println(organismList + "orgList");

		Node root = new Node(coordinatesMap, null, null, organismList, 0);
		if (goalTest(root)) {
			totalCost = root.cost;

			System.out.print("root");
			return root;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>((node1, node2) -> Integer
				.compare(node1.cost + node1.organismList.size(), node2.cost + node2.organismList.size()));

		queue.add(root);
		checkAddState(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			numOfNodesExpanded++;
			for (Node child : expand(node)) {
				if (goalTest(child)) {
					totalCost = child.cost;

					return child;
				}
				if (!child.isDead && checkAddState(child)) {
					queue.add(child);
				}
			}
		}

		System.out.print("no Solution");
		return null;
	}
	/////////// 2nd Heuristic

	private static Node GreedySearch_SumMinDistanceBetweenOrgs(Map<Integer, int[]> coordinatesMap) {
		System.out.println(organismList + "orgList");

		Node root = new Node(coordinatesMap, null, null, organismList, 0);
		if (goalTest(root)) {
			totalCost = root.cost;

			System.out.print("root");
			return root;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>(
				(node1, node2) -> Integer.compare(heuristic(node1.organismList), heuristic(node2.organismList)));

		queue.add(root);
		checkAddState(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			numOfNodesExpanded++;
			for (Node child : expand(node)) {
				if (goalTest(child)) {
					totalCost = child.cost;

					return child;
				}
				if (!child.isDead && checkAddState(child)) {
					queue.add(child);
				}
			}
		}

		System.out.print("no Solution");
		return null;
	}

	// 1st Heuristic
	private static Node AS_Search_SumMinDistanceBetweenOrgs(Map<Integer, int[]> coordinatesMap) {
		System.out.println(organismList + "orgList");

		Node root = new Node(coordinatesMap, null, null, organismList, 0);
		if (goalTest(root)) {
			totalCost = root.cost;

			System.out.print("root");
			return root;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>((node1, node2) -> Integer
				.compare(node1.cost + heuristic(node1.organismList), node2.cost + heuristic(node2.organismList)));

		queue.add(root);
		checkAddState(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			numOfNodesExpanded++;
			for (Node child : expand(node)) {
				if (goalTest(child)) {
					totalCost = child.cost;

					return child;
				}
				if (!child.isDead && checkAddState(child)) {
					queue.add(child);
				}
			}
		}

		System.out.print("no Solution");
		return null;
	}

	private static int heuristic(ArrayList<Organism> organismList) {
		PriorityQueue<Integer> minDistances = new PriorityQueue<>();
		for (int i = 0; i < organismList.size(); i++) {
			int minDistance = Integer.MAX_VALUE;
			for (int j = i + 1; j < organismList.size(); j++) {
				if (organismList.get(i) != organismList.get(j)) {
					int dist = ManhattanDistance(organismList.get(i), organismList.get(j));
					minDistance = Math.min(minDistance, dist);
				}
			}
			if (minDistance != Integer.MAX_VALUE) {
				minDistances.add(minDistance);
			}

		}

		int sum = 0;
		while (minDistances.size() > 1) {
			sum += minDistances.poll();
		}
		return sum;
	}

	private static int ManhattanDistance(Organism o1, Organism o2) {
		return Math.abs(o1.row - o2.row) + Math.abs(o1.column - o2.column);
	}

	private static Map<Integer, int[]> deepCopyGrid(Map<Integer, int[]> original) {
		Map<Integer, int[]> copy = new HashMap<>();
		for (Map.Entry<Integer, int[]> entry : original.entrySet()) {
			copy.put(entry.getKey(), entry.getValue().clone());
		}
		return copy;
	}

}
