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
