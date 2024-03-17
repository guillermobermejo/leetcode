/**
Programmer: Guillermo       M	(int[][] visited)
Language: Java
Time Complexity: O(n*m)
Space Complexity: O(n*m)
Runtime: 4ms (beats 48.62%)
Memory: 48.1mb (beats 79.08%)

DS: int[][]	a visited map for the land that has been previously visited
Advantage:  O(1) Insertion
            O(1) Access
            O(n*m) algorithm apporach

Approach:
    use backtracking approach to traverse the current land the moment it has been encountered
	function will be named traverseLand() that will take 4 inputs, the grid, visited structure and current row / col
	
	main function: 
		set up visited[][] structure and variable to track current number of islands
		traverse all of grid in a standard for loop
			if a row col position in grid is '1' and visited at row col == 0 it is a new piece of land TRAVERSE
				increment number of islands after traversal
			(if a row col position in grid is '1' and visited '1' the land had been previously traversed)
		
		return number of islands
	
	traverseLand() function: void
		check that the current row / col position is not out of bounds of the grid
			if true return 
		
		check if the current row / col position is on water or has previously been visited
			if true return
		
		// at this point the current row / col position is a new unvisited piece of land that must be traversed 
		backtracking...
		set the current row / col position in visited to '1'
		recursively call the function on the right side of the current position
		recursively call the function on the lower side of the current position
		recursively call the function on the left side of the current position
		recursively call the function on the upper side of the current position

Algorithm:
    main function: 
		declare & initialize: 
			1 int[][] matrix	name: visited	size: [grid.length][grid[0].length]		notes: keeps track of land visited
			1 int variable		name: numIslands	value: 0	notes: keeps track of all traversed pieces of land
			
		for loop: (int row = 0; row < grid.length; row++)			// traverse all rows of grid
			for loop: (int col = 0; col < grid[0].length; col++)	// traverse all colums of grid
				if check: 
					if grid[row][col] == '1' && visited[row][col] == 0	// if current position is land and UNVISITED
						traverseLand(grid, visited, row, col)			// traverse all land connected with UNVISITED land
						numIslands++									// register new island found
		
		return:
			numIslands
		
	traverseLand() function: void
		if check: 
			if grid[row][col] out of bounds	// out of bounds is row >= grid.length || row < 0 || col >= grid[0].length || col < 0
				true ? return 
			
			if grid[row][col] == '0' || visited[row][col] == '1' // if on water or land has been visited return to previous state
				true ? return
	
		// at this point the current row / col position is a new unvisited piece of land that must be traversed 
		visited[row][col] == '1' 				// mark visited
		traverseLand(grid, visited, row, col+1)	// traverse newly registered visited land rightward
		traverseLand(grid, visited, row+1, col)	// traverse newly registered visited land downward
		traverseLand(grid, visited, row, col-1)	// traverse newly registered visited land leftward
		traverseLand(grid, visited, row-1, col)	// traverse newly registered visited land upward
*/

class Solution {
    public int numIslands(char[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        int numIslands = 0;
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1' && visited[row][col] == 0) {
                    traverseLand(grid, visited, row, col);
                    numIslands++;
                }
                // traversed past all pieces of land connected to grid[row][col] at this point
            }
        }
        
        return numIslands;
    }
    
    public void traverseLand(char[][] grid, int[][] visited, int row, int col) {
        // bounds check
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0)
            return;
        
        // on water or piece of land already visited check
        if (grid[row][col] == '0' || visited[row][col] == 1)
            return;
        
        // register unvisited piece of land of island as visited
        visited[row][col] = 1;
        
        // traverse right
        traverseLand(grid, visited, row, col+1);
        // traverse down
        traverseLand(grid, visited, row+1, col);
        // traverse left
        traverseLand(grid, visited, row, col-1);
        // traverse up
        traverseLand(grid, visited, row-1, col);
    }
}
