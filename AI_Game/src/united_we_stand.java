
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class united_we_stand extends generic_search {
	public static void main(String[] args) {
		// String grid = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;";
		// String grid = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0,1,1;";
		// 95 Gr1,15s// 101 Gr2 //55 As1//55 As2
		// String grid =
		// "9;7;4,2,6,5,5,6,1,5,6,1,8,1,8,2,4,3,6,0,7,5;0,1,0,2,0,3,0,5,1,4,6,3,6,6,2,0,0,4,3,3,8,4,3,0,8,6,5,4,5,1,0,0,3,2,8,0,2,2,6,2,7,3,5,2,5,3,2,6,4,6,0,6,1,6,1,2,1,3,8,3;";

		// 96 Gr1 // 69 Gr2 //27 As1//27 As2
		String grid = "8;6;2,1,4,3,4,2,2,0,4,4,5,2,2,3,2,4,5,5,4,5,2,2;6,4,6,2,7,5,7,2,6,3,3,0,3,5,7,0,3,1,3,3,5,1;";
		// String grid = "6;6;2,1,2,0;0,5,0,2,3,0,3,5,0,0,3,1,3,3,5,1;";

		String path = generic_search.search(grid, "GR2", true);
		//System.out.print(genGrid());
		System.out.print(path);


	}

	public static String gridToString(int[][] grid) {
		return "";
	}

	public static String genGrid() {
		Random random = new Random();
		int rows = 10; // Number of rows in the grid
		int cols = 10; // Number of columns in the grid
		int maxMicroOrganisms = 20; // Maximum number of microorganisms
		int maxObstacles = 4; // Maximum number of obstacles

		int[][] grid = new int[rows][cols];

		StringBuilder organisms = new StringBuilder();
		StringBuilder obstacles = new StringBuilder();

		// Place obstacles
		int placedObstacles = 0;
		int id = -1;
		while (placedObstacles < maxObstacles) {
			int row = random.nextInt(rows);
			int col = random.nextInt(cols);
			if (grid[row][col] == 0) {
				grid[row][col] = id;
				obstacles.append(col).append(",").append(row).append(",");
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
				organisms.append(col).append(",").append(row).append(",");
				placedMicroOrganisms++;
			}
		}

		// Remove the trailing commas
		if (organisms.length() > 0) {
			organisms.setLength(organisms.length() - 1);
		}
		if (obstacles.length() > 0) {
			obstacles.setLength(obstacles.length() - 1);
		}

		// Construct the final string
		String gridString = cols + ";" + rows + ";" + organisms.toString() + ";" + obstacles.toString() + ";";

		return gridString;
	}

}
