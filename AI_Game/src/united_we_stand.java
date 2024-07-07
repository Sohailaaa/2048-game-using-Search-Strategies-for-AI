
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class united_we_stand {
    public static void main(String[] args) {
        int[][] grid = genGrid();
        printGrid(grid);

        Map<Integer, int[]> coordinatesMap = reshape(grid);
        printCoordinatesMap(coordinatesMap);
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

    public static Map<Integer, int[]> reshape(int[][] grid) {
        Map<Integer, int[]> coordinatesMap = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    coordinatesMap.put(grid[i][j], new int[]{i, j});
                }
            }
        }

        return coordinatesMap;
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void printCoordinatesMap(Map<Integer, int[]> coordinatesMap) {
        for (Map.Entry<Integer, int[]> entry : coordinatesMap.entrySet()) {
            int key = entry.getKey();
            int[] coordinates = entry.getValue();
            System.out.println("Key: " + key + " -> Coordinates: [" + coordinates[0] + ", " + coordinates[1] + "]");
        }
    }
}
