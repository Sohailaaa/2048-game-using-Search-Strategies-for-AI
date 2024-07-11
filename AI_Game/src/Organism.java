import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Node {

	Map<Integer, int[]> grid;
	ArrayList<Organism> organismList;
	Node parent;
	int[] action;
	int cost = 0;
	boolean isDead = false;

	public Node(Map<Integer, int[]> grid, Node parent, int[] action, ArrayList<Organism> organismList, int cost) {
		this.grid = grid;
		this.parent = parent;
		this.organismList = organismList;
		this.action = action;
		this.cost = cost;
	}
///////

	public Node(Node other) {
		this.grid = deepCopyGrid(other.grid);
		this.parent = other.parent;
		this.organismList = deepCopyOrganismList(other.organismList);
		this.action = other.action != null ? other.action.clone() : null;
		this.cost = other.cost;
		this.isDead = other.isDead;
	}

	private Map<Integer, int[]> deepCopyGrid(Map<Integer, int[]> originalGrid) {
		Map<Integer, int[]> copy = new HashMap<>();
		for (Map.Entry<Integer, int[]> entry : originalGrid.entrySet()) {
			copy.put(entry.getKey(), entry.getValue().clone());
		}
		return copy;
	}

	private ArrayList<Organism> deepCopyOrganismList(ArrayList<Organism> originalList) {
		ArrayList<Organism> copy = new ArrayList<>();
		for (Organism org : originalList) {
			copy.add(org.clone()); // Using the copy constructor of Organism
		}
		return copy;
	}

	//////////

	public int organismCheck(int org_id, int[] new_coordinates, Map<Integer, int[]> coordinatesMap) {
		for (Organism o : organismList) {
			if (o.id == org_id)
				continue;
			if (o.row == new_coordinates[0] && o.column == new_coordinates[1]) {
				return o.id;
			}
		}
		return -1;
	}

	public static boolean obstacleCheck(int[] organismCoordinates, Map<Integer, int[]> coordinatesMap) {
		for (Map.Entry<Integer, int[]> entry : coordinatesMap.entrySet()) {
			if (entry.getKey() < 0) { // Only consider obstacles
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
		if (coordinates[0] < 0 || coordinates[0] > generic_search.row - 1) // checks rows x
			return true;
		else if (coordinates[1] < 0 || coordinates[1] > generic_search.column - 1) // checks columns y
			return true;

		return false;

	}
	
	public void move(int[] coordinates, int[] action) {

		int currentRow = coordinates[0];
		int currentCol = coordinates[1];
		int[][] modify = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		while (true) {
			int actionIndex = action[1] - 1;

			int nextRow = currentRow + modify[actionIndex][0];
			int nextCol = currentCol + modify[actionIndex][1];

			int[] nextCoordinates = new int[] { nextRow, nextCol };

			// Check if in bounds
			if (boundaryCheck(nextCoordinates)) {
				// Invalid Node.
				this.isDead = true;
				break;
			}

			// Check if hit obstacle
			if (obstacleCheck(nextCoordinates, grid)) {
				int organismCount = 0;
				for (Organism o : organismList) {
					if (o.id == action[0]) {
						o.row = currentRow;
						o.column = currentCol;
						organismCount = o.size;
						break;
					}
				}
				
				int cellMovement = Math.abs(coordinates[0] - currentRow) + Math.abs(coordinates[1] - currentCol);
				cost += cellMovement * organismCount;

				grid.put(action[0], new int[] { currentRow, currentCol });
				
				System.out.println("Organism " + action[0] + " Has Collided with Obstacle after moving in direction " + action[1]);
				break;
			}

			// Check if hit an organism
			int result = organismCheck(action[0], nextCoordinates, grid);
			if (result != -1) {
				
				
				int removedOrganismIndex = -1;
				int removedSize = -1;
				for (int i = 0; i < organismList.size(); i++) {
					if (organismList.get(i).id == result) {
						removedOrganismIndex = i;
						removedSize = organismList.get(i).size;
						break;
					}
				}

				organismList.remove(removedOrganismIndex);
				grid.remove(result);

				
				int organismCount = 0;
				for (Organism o : organismList) {
					if (o.id == action[0]) {
						organismCount = o.size;
						o.row = nextRow;
						o.column = nextCol;
						o.size += removedSize;
						break;
					}
				}
				
				
				
				int cellMovement = Math.abs(coordinates[0] - nextRow) + Math.abs(coordinates[1] - nextCol);
				cost += cellMovement * organismCount;
				
				
				System.out.println("Organism " + action[0] + " has collided with organism " + result + 
						" Organism " + action[0] + " will now remain at " + nextCoordinates[0] + ", " + nextCoordinates[1] + " and has gained " + removedSize + " organisms");

				grid.put(action[0], nextCoordinates);
				break;
			}

			currentRow = nextRow;
			currentCol = nextCol;
		}

//		
//		
//		
//		int[] newCoordinates = new int[] { coordinates[0], coordinates[1] };
//		int organismId = action[0];
//		int direction = action[1];
//		int noOfCombinedOrganism = 0;
//		int moves = 0;
//
//		// Find the size of the organism
//		for (Organism org : this.organismList) {
//			if (org.getId() == organismId) {
//				noOfCombinedOrganism = org.size;
//				break;
//			}
//		}
//
//		while (true) {
//			switch (direction) {
//			case 1:
//				newCoordinates[1]--; // Move north (up)
//				break;
//			case 2:
//				newCoordinates[1]++; // Move south (down)
//				break;
//			case 3:
//				newCoordinates[0]++; // Move east (right)
//				break;
//			case 4:
//				newCoordinates[0]--; // Move west (left)
//				break;
//			default:
//				return; // Invalid direction
//			}
//			
//
//			// Check if the new coordinates are within the boundary
//			if (generic_search.boundaryCheck(newCoordinates)) {
//				this.isDead = true;
//				return;
//			}
//
//			// Check if the new coordinates hit an obstacle
//			else if (generic_search.obstacleCheck(newCoordinates, this.grid)) {
//				System.out.println("Obstacle detected");
//				// Undo the move
//				switch (direction) {
//				case 1:
//					newCoordinates[1]++; // Undo move north (up)
//					break;
//				case 2:
//					newCoordinates[1]--; // Undo move south (down)
//					break;
//				case 3:
//					newCoordinates[0]--; // Undo move east (right)
//					break;
//				case 4:
//					newCoordinates[0]++; // Undo move west (left)
//					break;
//				}
//				break;
//			}
//
//			// Check for collisions with other organisms
//			else if (this.checkOrganismsCollision(organismId, newCoordinates)) {
//				// Update the organism's position in the grid
//				this.grid.replace(organismId, newCoordinates);
//				moves++;
//				break;
//			}
//
//			moves++;
//		}
//
//		// Calculate the cost
//		this.cost = moves * noOfCombinedOrganism;
	}

}
