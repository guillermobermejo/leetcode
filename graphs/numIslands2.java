class Solution {
    public int numIslands(char[][] grid) {
		
        int numIslands = 0;
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    traverseLand(grid, row, col);
                    numIslands++;
                }
                // traversed past all pieces of land connected to grid[row][col] at this point
            }
        }
        
        return numIslands;
    }
    
    public void traverseLand(char[][] grid, int row, int col) {
        // bounds check
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0)
            return;
        
        // on water or piece of land already visited check
        if (grid[row][col] == '0' || grid[row][col] == '2')
            return;
        
        // register unvisited piece of land of island as visited in-place in grid 
        grid[row][col] = '2';
        
        // traverse right
        traverseLand(grid, row, col+1);
        // traverse down
        traverseLand(grid, row+1, col);
        // traverse left
        traverseLand(grid, row, col-1);
        // traverse up
        traverseLand(grid, row-1, col);
    }
}
