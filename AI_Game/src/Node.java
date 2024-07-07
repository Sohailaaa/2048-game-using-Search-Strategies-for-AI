import java.util.Map;

public class Node {
	Map<Integer, int[]> grid;
	Node parent;
	int[] action;
	int cost;

	public void updateGrid(Map<Integer, int[]> grid, int[] action) {
		int organism = action[0];
		int direction = action[1];
		int[] oldPositon = grid.get(organism);
		int[] newCoordinates = move(oldPositon, direction);
		grid.replace(organism, newCoordinates);
	}

	public static int[] move(int[] coordinates, int direction) {
		int[] newCoordinates = coordinates.clone();
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
