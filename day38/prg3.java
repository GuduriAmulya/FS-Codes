/*
Mr Hacker has given a color code system as a grid of size R*C, the color codes 
are in the range of 1 to 10 and the grid is 0-indexed.

He has given a task to crack the original grid by using three values, Ri, Cj, Code.
The way to crack the original color codes in the grid is as follows:
    1. You have to start updating the color codes from (Ri,Cj) position in 
    the grid with the given color code, 'Code'.
    2. Updating color codes means, replace the color codes from the cell (Ri,Cj) 
      in the grid, and replace all the cells connected in 4 directions
      (Up, Down, Left, Right) and having same color-code of starting cell.
    3. Repeat the step-2 from the updated cells until no more cells to update.

Your task is to help Mr.Hacker to get the original color-code system grid
and print it.


Input Format:
-------------
Line-1: Two space sepearted integers, R and C.
Next R lines: C space separated integers, grid[][].
Last line: Three space sepaarted integers, Ri,Cj and Code.

Output Format:
--------------
Print the resultant grid.


Sample Input-1:
---------------
3 4
1 0 1 1
0 1 1 1
1 1 0 1
1 3 3

Sample Output-1:
----------------
1 0 3 3
0 3 3 3
3 3 0 3


Sample Input-2:
---------------
3 4
1 0 1 1
0 1 1 1
1 1 0 1
0 0 3

Sample Output-2:
----------------
3 0 1 1
0 1 1 1
1 1 0 1


*/
