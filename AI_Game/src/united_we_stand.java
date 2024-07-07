
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class united_we_stand extends generic_search {
	public static void main(String[] args) {
		int[][] grid = genGrid();
		printGrid(grid);
		System.out.print("");
		

	}

	public static int[][] genGrid() {
		int rows = 6; // Number of rows in the grid
		int cols = 6; // Number of columns in the grid
		int maxMicroOrganisms = 5; // Maximum number of microorganisms
		int maxObstacles = 3; // Maximum number of obstacles

		int[][] grid = new int[rows][cols];
		Random random = new Random();

		// Place obstacles
		int placedObstacles = 0;
		while (placedObstacles < maxObstacles) {
			int row = random.nextInt(rows);
			int col = random.nextInt(cols);
			if (grid[row][col] == 0) {
				grid[row][col] = -1;
				placedObstacles++;
			}
		}

		// Place microorganisms
		int placedMicroOrganisms = 0;
		while (placedMicroOrganisms < maxMicroOrganisms) {
			int row = random.nextInt(rows);
			int col = random.nextInt(cols);
			if (grid[row][col] == 0) {
				grid[row][col] = placedMicroOrganisms + 1;
				placedMicroOrganisms++;
			}
		}

		return grid;
	}



	public static void printGrid(int[][] grid) {
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	
}
