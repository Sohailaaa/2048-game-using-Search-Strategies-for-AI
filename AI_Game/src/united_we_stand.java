
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class united_we_stand extends generic_search {
	public static void main(String[] args) {
//		 int[][] grid = genGrid();
//		int[][] grid = new int[][] { { -1, -2, -3 }, { 1, 0, 0 }, { 0, 0, 2 } };
		int[][] grid = stringToGrid(
				"8;6;2,1,4,3,4,2,2,0,4,4,5,2,2,3,2,4,5,5,4,5,2,2;6,4,6,2,7,5,7,2,6,3,3,0,3,5,7,0,3,1,3,3,5,1;");
//int[][] grid = stringToGrid("4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;");
		// int[][] grid =
		// stringToGrid("9;7;4,2,6,5,5,6,1,5,6,1,8,1,8,2,4,3,6,0,7,5;0,1,0,2,0,3,0,5,1,4,6,3,6,6,2,0,0,4,3,3,8,4,3,0,8,6,5,4,5,1,0,0,3,2,8,0,2,2,6,2,7,3,5,2,5,3,2,7,4,6,0,6,1,6,1,2,1,3,8,3;");
//		int[][] grid = stringToGrid("4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;");

		printGrid(grid);
		List<String> path = generic_search.search(grid, "UC", false);
		for (String s : path) {
			System.out.print(s);
		}

	}

	public static String gridToString(int[][] grid) {
		return "";
	}

	public static int[][] stringToGrid(String string) {
		String[] dividedString = string.split(";");

		int rows = Integer.parseInt(dividedString[1]);
		int cols = Integer.parseInt(dividedString[0]);
		int[][] grid = new int[rows][cols];

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

	public static int[][] genGrid() {
		Random random = new Random();
		int rows = 4; // Number of rows in the grid
		int cols = 4; // Number of columns in the grid
		int maxMicroOrganisms = 7; // Maximum number of microorganisms
		int maxObstacles = 4; // Maximum number of obstacles

		int[][] grid = new int[rows][cols];

		// Place obstacles
		int placedObstacles = 0;
		int id = -1;
		int add = 0;
		while (placedObstacles < maxObstacles) {
			int row = random.nextInt(rows);
			int col = random.nextInt(cols);
			if (grid[row][col] == 0) {
				grid[row][col] = id;
				id--;
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
