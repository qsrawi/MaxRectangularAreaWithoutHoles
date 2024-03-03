package V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LargestRectangularPerimeterV2 {
    private static int rectangularPerimeter = 0;

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

        List<int[]> rectangles = findAllRectangles(grid, m, n);
        int LargestRectangularPerimeter = 0;

        for (int[] rect : rectangles) {
            if (!containsHole(grid, rect[0], rect[1], rect[2], rect[3])) {
                if (rectangularPerimeter > LargestRectangularPerimeter)
                LargestRectangularPerimeter = rectangularPerimeter;
            }
            rectangularPerimeter = 0;
        }

        if (LargestRectangularPerimeter == 0)
            System.out.println("impossible");
        else
            System.out.println(LargestRectangularPerimeter);
    }

    public static List<int[]> findAllRectangles(char[][] grid, int m, int n) {
        List<int[]> rectangles = new ArrayList<>();

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (grid[i][j] == 'x' || grid[i][j + 1] == 'x' || grid[i + 1][j] == 'x')
                    continue;
                for (int x = i + 1; x < m; x++) {
                    for (int y = j + 1; y < n; y++) {
                        if (grid[x][y] == 'x')
                            continue;
                        rectangles.add(new int[]{i, j, x, y});
                    }
                }
            }
        }

        return rectangles;
    }

    public static boolean containsHole(char[][] grid, int topLeftRow, int topLeftCol, int bottomRightRow, int bottomRightCol) {
        for (int i = topLeftRow; i <= bottomRightRow; i++) {
            for (int j = topLeftCol; j <= bottomRightCol; j++) {
                if (i != topLeftRow && j != topLeftCol &&  j != bottomRightCol &&  i != bottomRightRow ) {
                    continue;
                }
                if (grid[i][j] == 'x') {
                    return true;
                }
                else rectangularPerimeter ++;
            }
        }

        return false;
    }
}
