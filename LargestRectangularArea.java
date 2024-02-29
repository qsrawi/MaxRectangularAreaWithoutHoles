import java.util.Scanner;
import java.util.Stack;

public class LargestRectangularArea {
    private static Stack<Integer[]> stackCol = new Stack<>();
    private static Stack<Integer[]> stackRow = new Stack<>();
    private static int countOfLandsWithPerimeter = 0;
    private static int row = 0;
    private static int col = 0;
    private static int entryPointOfI = 0;
    private static int entryPointOfJ = 0;

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

        for (int i = 0; i < m - 1 ; i++) {
            for (int j = 0; j < n - 1; j++) {
                stackCol = new Stack<>();
                stackRow = new Stack<>();
                row = m;
                col = n;
                entryPointOfI = i;
                entryPointOfJ = j;
                countOfLandsWithPerimeter = 0;
                if(grid[i][j] == 'x')
                    continue;
                if(j + 1 != n && grid[i][j + 1] != 'x') {
                    currentPerimeter = moveRight(grid, i, j);
                    if(currentPerimeter > maxPerimeter)
                        maxPerimeter = currentPerimeter;
                } else 
                    continue;
            }
        }
        
        return (maxPerimeter == 0) ? "impossible" : String.valueOf(maxPerimeter);
    }

    private static int moveRight(char[][] grid, int i, int j){
        if(j != col - 1 && grid[i][j + 1] != 'x'){
            countOfLandsWithPerimeter ++;
            stackCol.push(new Integer[]{i, j, countOfLandsWithPerimeter});
            return moveRight(grid, i, j + 1);
        }

        else {
            if(grid[i + 1][j] == 'x'){
                if(!stackCol.isEmpty()) {
                    Integer[] lastValidCol = stackCol.pop();
                    if(lastValidCol[0] == entryPointOfI && lastValidCol[1] == entryPointOfJ)
                        return 0;
                    countOfLandsWithPerimeter = lastValidCol[2];
                    return moveBottom(grid, lastValidCol[0] + 1, lastValidCol[1]);
                    
                }
                else 
                    return 0;
            }
            return moveBottom(grid, i, j);
        }
    }

    private static int moveBottom(char[][] grid, int i, int j){
        if(i != row - 1 && grid[i + 1][j] != 'x'){
            countOfLandsWithPerimeter ++;
            stackRow.push(new Integer[]{i, j, countOfLandsWithPerimeter});
            return moveBottom(grid, i + 1, j);
        }

        else {
            return moveLeft(grid, i, j);
        }
    }

    private static int moveLeft(char[][] grid, int i, int j){  
        if(i == entryPointOfI) {
            if(!stackCol.isEmpty()){
                Integer[] lastValidCol = stackCol.pop();
                countOfLandsWithPerimeter = lastValidCol[2];
                return moveBottom(grid, lastValidCol[0] + 1, lastValidCol[1]);
            }
            else {
                return 0;
            }
        }
            
        if(j != entryPointOfJ && grid[i][j - 1] == 'x'){
            if(!stackRow.isEmpty()){
            Integer[] lastValidRow = stackRow.pop();
            if(lastValidRow[0] == entryPointOfI && lastValidRow[1] == entryPointOfJ)
                return 0;
            countOfLandsWithPerimeter = lastValidRow[2];
            return moveLeft(grid, lastValidRow[0], lastValidRow[1] - 1);
            } else return 0;
        }

        if(j != entryPointOfJ && grid[i][j - 1] != 'x'){
            countOfLandsWithPerimeter ++;
            return moveLeft(grid, i, j - 1);
        } 

        else {
            return moveTop(grid, i, j);
        }
    }

    private static int moveTop(char[][] grid, int i, int j){
        if(grid[i - 1][j] == 'x')
            return 0;

        if(i - 1 != entryPointOfI){
            countOfLandsWithPerimeter ++;
            return moveTop(grid, i - 1, j);
        }

        else {
            countOfLandsWithPerimeter ++;
            return countOfLandsWithPerimeter;
        }
    }
}