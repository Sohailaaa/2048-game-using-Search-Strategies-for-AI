package code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UnitedWeStandSearch extends GenericSearch {
	public static void main(String[] args) {
//		 int[][] grid = genGrid();
		// String grid = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;";
		// String grid = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0,1,1;";
		// 95 Gr1,15s// 101 Gr2 //55 As1//55 As2
		// String grid =
		// "9;7;4,2,6,5,5,6,1,5,6,1,8,1,8,2,4,3,6,0,7,5;0,1,0,2,0,3,0,5,1,4,6,3,6,6,2,0,0,4,3,3,8,4,3,0,8,6,5,4,5,1,0,0,3,2,8,0,2,2,6,2,7,3,5,2,5,3,2,6,4,6,0,6,1,6,1,2,1,3,8,3;";

		// 96 Gr1 // 69 Gr2 //27 As1//27 As2
		String grid = "8;6;2,1,4,3,4,2,2,0,4,4,5,2,2,3,2,4,5,5,4,5,2,2;6,4,6,2,7,5,7,2,6,3,3,0,3,5,7,0,3,1,3,3,5,1;";
		// String grid = "6;6;2,1,2,0;0,5,0,2,3,0,3,5,0,0,3,1,3,3,5,1;";
		String grid0 = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;";
		String grid1 = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0,1,1;";
		String grid2 = "9;7;4,2,6,5,5,6,1,5,6,1,8,1,8,2,4,3,6,0,7,5;0,1,0,2,0,3,0,5,1,4,6,3,6,6,2,0,0,4,3,3,8,4,3,0,8,6,5,4,5,1,0,0,3,2,8,0,2,2,6,2,7,3,5,2,5,3,2,6,4,6,0,6,1,6,1,2,1,3,8,3;";
		String grid3 = "8;6;2,1,4,3,4,2,2,0,4,4,5,2,2,3,2,4,5,5,4,5,2,2;6,4,6,2,7,5,7,2,6,3,3,0,3,5,7,0,3,1,3,3,5,1;";
		// String path = GenericSearch.search(grid2, "GR1", false);
		//System.out.println("grid" + genGrid());
		System.out.println("Answer" +search(grid3,"BF",true));
		// System.out.print(path);

	}

	public static String solve(String grid, String strategy, boolean visualize) {
		UnitedWeStandSearch searchAgent = new UnitedWeStandSearch();
		String results = search(grid, strategy, visualize);

		return results;
	}

	public static String gridToString(int[][] grid) {
		return "";
	}

	public static int[][] stringToGrid(String string) {
		String[] dividedString = string.split(";");

		row = Integer.parseInt(dividedString[1]);
		column = Integer.parseInt(dividedString[0]);
		int[][] grid = new int[row][column];

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

	public static String genGrid() {
	    Random random = new Random();
	    int rows = random.nextInt(10) + 1; // Number of rows in the grid
	    int cols = random.nextInt(10) + 1; // Number of columns in the grid
	    int maxMicroOrganisms = 20; // Maximum number of microorganisms
	    int numOfOrganism = 0;
	    int numOfObstacles = 0;

	    int max = (rows * cols > 20) ? 20 : rows * cols;
	    numOfOrganism = random.nextInt(max) + 1;
	    int availableSpaces = rows * cols - numOfOrganism;

	    numOfObstacles = random.nextInt(availableSpaces + 1);

	    int[][] grid = new int[rows][cols];

	    // Place microorganisms
	    int placedMicroOrganisms = 0;
	    while (placedMicroOrganisms < numOfOrganism) {
	        int row = random.nextInt(rows);
	        int col = random.nextInt(cols);
	        if (grid[row][col] == 0) {
	            grid[row][col] = placedMicroOrganisms + 1;
	            placedMicroOrganisms++;
	        }
	    }

	    // Place obstacles
	    int placedObstacles = 0;
	    int id = -1;
	    while (placedObstacles < numOfObstacles) {
	        int row = random.nextInt(rows);
	        int col = random.nextInt(cols);
	        if (grid[row][col] == 0) {
	            grid[row][col] = id;
	            id--;
	            placedObstacles++;
	        }
	    }

	    // Convert grid to string format
	    StringBuilder organisms = new StringBuilder();
	    StringBuilder obstacles = new StringBuilder();

	    for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < cols; c++) {
	            if (grid[r][c] > 0) {
	                organisms.append(c).append(",").append(r).append(",");
	            } else if (grid[r][c] < 0) {
	                obstacles.append(c).append(",").append(r).append(",");
	            }
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
