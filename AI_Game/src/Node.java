import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Node {
	Map<Integer, int[]> grid;
	ArrayList<Integer> organisms_id;
	Node parent;
	int[] action;
	int cost;

	public Node(Map<Integer, int[]> grid, Node parent, int[] action, ArrayList<Integer> organisms_id, int cost) {
		this.grid = grid;
		this.parent = parent;
		this.organisms_id = organisms_id;
		this.action = action;
		this.cost = cost;
	}

	public void checkOrganimsCollision(int org_id, int[] new_coordinates, Map<Integer, int[]> grid,
			ArrayList<Integer> organisms_id) {

		Iterator<Map.Entry<Integer, int[]>> iterator = grid.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, int[]> entry = iterator.next();
			int otherOrgId = entry.getKey();
			int[] otherCoordinates = entry.getValue();

			// Check if the new coordinates match with any other organism's coordinates
			if (otherOrgId != org_id && otherCoordinates[0] == new_coordinates[0]
					&& otherCoordinates[1] == new_coordinates[1]) {
				// Handle collision logic here (e.g., mark collision, remove both entries, etc.)
				System.out.println("Collision detected between organism " + org_id + " and organism " + otherOrgId);
				// Example: Remove the other organism from the grid and organisms_id
				iterator.remove(); // Safely remove the current entry
				organisms_id.remove(Integer.valueOf(otherOrgId));
			}
		}

	}

	public void updateGrid() {
		Map<Integer, int[]> grid = this.grid;
		int[] action = this.action;
		ArrayList<Integer> organisms_id = this.organisms_id;
		int organism = action[0];
		int direction = action[1];
		int[] oldPositon = grid.get(organism);
		int[] newCoordinates = move(oldPositon, direction);
		checkOrganimsCollision(organism, newCoordinates, grid, organisms_id);
		grid.replace(organism, newCoordinates);
	}

	public static int[] move(int[] coordinates, int direction) {
		int[] newCoordinates = new int[] { coordinates[0], coordinates[1] };
		switch (direction) {
		case 1:
			newCoordinates[0]--; // Move north (up)
			break;
		case 2:
			newCoordinates[0]++; // Move south (down)
			break;
		case 3:
			newCoordinates[1]++; // Move east (right)
			break;
		case 4:
			newCoordinates[1]--; // Move west (left)
			break;
		}
		return newCoordinates;
	}
}
