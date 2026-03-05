// You work for a national defense agency responsible for monitoring enemy ship 
// movements using advanced radar technology. The radar system generates a 
// 2D grid representation of the sea, where each cell represents a segment of the ocean.
// 	- The radar scans detect ships represented by 'X' in the grid.
// 	- Each ship can either be horizontal or vertical, and multiple ships 
// 	  can appear on the grid. 
// 	- Ships are separated by at least one empty space ('.'), which means 
// 	no two ships are adjacent to each other either horizontally or vertically.
	
// Your task is to determine the total number of ships on the radar grid based on 
// the scan results. The radar grid provides information about both ship positions 
// and empty spaces.

// Input Format:
// -------------
// The first line contains two integers M and N, size of radar image.
// Next M lines: N space-separated integers, A 2D grid[][], radar image.

// Output Format:
// --------------
// An integer, the total number of ships on the radar.


// Sample Input-1:
// ---------------
// 3 4
// X . . X
// . . . X
// . . . X
  

// Sample Output-1:
// ----------------
// 2

// Explanation:
// ------------
// There are 2 battleships. One is positioned horizontally (in the first row), 
// and the other is positioned vertically (in the last column).


// Sample Input-2:
// ---------------
// 4 5
// . X . . .
// . X . . .
// . X . . .
// X . X X X 

// Sample Output-2:
// ----------------
// 3

// Explanation:
// ------------
// 1: The X cells in the second column (at positions (1,1), (2,1), and (3,1)) form 
// a vertical battleship. 

// 2: The X cells in the fourth row (at positions (3,2), (3,3), and (3,4)) form a 
// horizontal battleship.

// 3: The X cells at position (3,0) and (3,2) are isolated and don't connect to 
// any other X cells.  So, the total count of battleships are 3.

