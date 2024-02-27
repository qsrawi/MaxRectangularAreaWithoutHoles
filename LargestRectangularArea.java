import java.util.Scanner;

public class LargestRectangularArea {
    private static int countOfLandWithPerimeter = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        char[][] grid = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        
        System.out.println(findLargestRectanglePerimeter(grid, m, n));
    }

    private static String findLargestRectanglePerimeter(char[][] grid, int m, int n) {
        int maxPerimeter = 0;
        int currentPerimeter = 0;
        int entryPointOfI = 0;
        int entryPointOfJ = 0;

        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n; j++) {
                entryPointOfI = i;
                entryPointOfJ = j;
                countOfLandWithPerimeter = 0;
                if(i == m - 1)
                    continue;
                if(j + 1 != n && grid[i][j + 1] != 'x') {
                    currentPerimeter = moveRight(grid, i, j, m - 1, n - 1, entryPointOfI, entryPointOfJ);
                    if(currentPerimeter > maxPerimeter)
                        maxPerimeter = currentPerimeter;
                } else 
                    continue;
            }
        }
        
        return (maxPerimeter == 0) ? "impossible" : String.valueOf(maxPerimeter);
    }

    private static int moveRight(char[][] grid, int i, int j, int m, int n, int entryPointOfI, int entryPointOfJ){
        if(j != n && grid[i][j + 1] != 'x'){
            countOfLandWithPerimeter ++;
            return moveRight(grid, i, j + 1, m, n, entryPointOfI, entryPointOfJ);
        }
        else {
            if(grid[i + 1][j] == 'x')
                return 0;

            return moveBottom(grid, i, j, m, n, entryPointOfI, entryPointOfJ);
        }
    }

    private static int moveBottom(char[][] grid, int i, int j, int m, int n, int entryPointOfI, int entryPointOfJ){
        if(i != m && grid[i + 1][j] != 'x'){
            countOfLandWithPerimeter ++;
            return moveBottom(grid, i + 1, j, m, n, entryPointOfI, entryPointOfJ);
        }
        else {
            if(grid[i][j - 1] == 'x')
                return 0;
            return moveLeft(grid, i, j, m, n, entryPointOfI, entryPointOfJ);
        }
    }

    private static int moveLeft(char[][] grid, int i, int j, int m, int n, int entryPointOfI, int entryPointOfJ){
        if(j != entryPointOfJ && grid[i][j - 1] != 'x'){
            countOfLandWithPerimeter ++;
            return moveLeft(grid, i, j - 1, m, n, entryPointOfI, entryPointOfJ);
        }
        else {
            if(grid[i - 1][j] == 'x')
                return 0;
            return moveTop(grid, i, j, m, n, entryPointOfI, entryPointOfJ);
        }
    }

    private static int moveTop(char[][] grid, int i, int j, int m, int n, int entryPointOfI, int entryPointOfJ){
        if(grid[i - 1][j] == 'x')
            return 0;
        if(i - 1 != entryPointOfI){
            countOfLandWithPerimeter ++;
            return moveTop(grid, i - 1, j, m, n, entryPointOfI, entryPointOfJ);
        }
        else {
            countOfLandWithPerimeter ++;
            return countOfLandWithPerimeter;
        }
    }
}