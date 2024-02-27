# MaxRectangularAreaWithoutHoles

## Problem Description
A farmer has a plot of land with some holes. The farmer wants to build a paling
around the largest possible rectangular area that does not contain any holes. What is the
perimeter of this largest rectangular area? 

## Input:
The input consists of two lines. The first line contains two space-separated integers, m and n,
the number of rows and columns in the grid, respectively. The second line contains m strings,
each of length n, representing the grid. Each character in the grid is either a '.' (representing
good land) or an 'x' (representing a hole).

## Output:
The output consists of a single integer, the perimeter of the largest possible rectangular area
that does not contain any holes. If such an area cannot be constructed, output the string
"impossible".
Constraints:
1≤m,n≤200
samples:
______________________________________________________________________________
### Sample Input:
4 5
.....
.x.x.
.....
.....
### Sample Output:
14
______________________________________________________________________________
### Sample Input:
2 2
.x
x.
### Sample Output:
Impossible
______________________________________________________________________________
### Sample Input:
 2 5
.....
xxxx.
###Sample Output:
Impossible