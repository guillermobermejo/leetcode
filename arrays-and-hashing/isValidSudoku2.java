/**
Programmer: Guillermo           M       (int[][] quad)
Language: Java
Time Complexity: O(n^2) 
Space Complexity: O(n^2)
Runtime: 1ms (beats 100%)
Memory: 44.2mb (beats 52.16%)

DS: int[][][]
Advantage:  O(1) Insertion
            O(1) Access
            contains ALL current values of a section  i.e., row, column, quadrant // where i: row, j: column, c: quadrants
            
Visualization of sections:                                 c:
    board:                                                  |
              c:0  c:1  c:2  c:3  c:4  c:5  c:6  c:7  c:8   |     j:   0    1    2    3    4    5    6    7    8
        r:0 [ q:0, q:0, q:0, q:1, q:1, q:1, q:2, q:2, q:2]  |  i: 0 [ "5", "3", ".", ".", "7", ".", ".", ".", "."]
        r:1 [ q:0, q:0, q:0, q:1, q:1, q:1, q:2, q:2, q:2]  V     1 [ "6", ".", ".", "1", "9", "5", ".", ".", "."]
        r:2 [ q:0, q:0, q:0, q:1, q:1, q:1, q:2, q:2, q:2]        2 [ ".", "9", "8", ".", ".", ".", ".", "6", "."]
        r:3 [ q:3, q:3, q:3, q:4, q:4, q:4, q:5, q:5, q:5]        3 [ "8", ".", ".", ".", "6", ".", ".", ".", "3"]
        r:4 [ q:3, q:3, q:3, q:4, q:4, q:4, q:5, q:5, q:5]        4 [ "4", ".", ".", "8", ".", "3", ".", ".", "1"]
        r:5 [ q:3, q:3, q:3, q:4, q:4, q:4, q:5, q:5, q:5]        5 [ "7", ".", ".", ".", "2", ".", ".", ".", "6"]
        r:6 [ q:6, q:6, q:6, q:7, q:7, q:7, q:8, q:8, q:8]        6 [ ".", "6", ".", ".", ".", ".", "2", "8", "."]
        r:7 [ q:6, q:6, q:6, q:7, q:7, q:7, q:8, q:8, q:8]        7 [ ".", ".", ".", "4", "1", "9", ".", ".", "5"]
        r:8 [ q:6, q:6, q:6, q:7, q:7, q:7, q:8, q:8, q:8]        8 [ ".", ".", ".", ".", "8", ".", ".", "7", "9"]
        
        r: - - ->                                                   i.e., board[i][j] -> board[0][4] -> 7
                                                                                in: row: 0    column: 4       quadrant: 1
        where:                                                                  add: maps[0][i] maps[1][j] maps[2][get_quadrants(i,j)]
        maps[?][][]:            maps[][?][]:                    maps:[][][?]
            0: rows                 0-8: 1 of the rows              0-8: the KEYs i.e.,
            1: columns                   columns or                      indexes that represents
            2: quadrants                 quadrants                       the possible values of board (1-9)
            
Approach:
    use iterative approach
    
    traverse the entire board 2d array in O(n^2)
    at every i,j the value is part of a row, column and quadrant
    create a 3d array to store maps for each row, column and quadrant
    calculate ASCII value - '1' from i,j    <- THIS IS INDEX
    if ascii calc is >= 0 (1-9) increment the value of the approriate row column and quadrant to register this number
        maps[0][i][c]++
        maps[1][j][c]++
        maps[2][get_quadrants(i,j)][c]++
    if at any point it is NOT 0 then it is present and NOT valid
    
Algorithm:
    MAIN FUNCTION:
        declare & initialize:
            1 int[][][] array   name: maps        value: new int[][][]  notes: the map of all rows, columns and quadrants
            
        for loop (int i=0; i < board.length; i++):      // the rows
            for loop (int j=0; j < board.length; j++):  // the columns
                declare & initialize:
                    1 int value     name: c  value: board[i][j] - '1'   notes: the INDEX of the third dimension of maps
                    
                if check:
                    if c is >= 0 then it is a value 0-8 (represents 1-9)
                    true?
                        if check: if c is in the current row i.e.,      maps[0][i][c]++ != 0
                            return false
                        if check: if c is in the current column i.e.,   maps[1][j][c]++ != 0
                            return false
                        if check: if c is in the current quadrant i.e., maps[2][quadrants[i][j]][c]++ != 0
                            return false
        return:
            true    // at this point it is valid
    
    get_quadrant function(int i, int j):
        if check:
            i <= 2 ?
                j<=2    return 0
                j<=5    return 1
                j<=8    return 2
        if check:
            i <= 5 ?
                j<=2    return 3
                j<=5    return 4
                j<=8    return 5
        if check:
            i <= 8 ?
                j<=2    return 6
                j<=5    return 7
                j<=8    return 8
                
        return -1
    
Visualization of algorithm:
    board:
            j:   0    1    2    3    4    5    6    7    8
         i: 0 [ "5", "5", "3", ".", "7", ".", ".", ".", "."]
            1 [ "6", ".", ".", "1", "9", "5", ".", ".", "."]
            2 [ ".", "9", "8", ".", ".", ".", ".", "6", "."]
            3 [ "8", ".", ".", ".", "6", ".", ".", ".", "3"]
            4 [ "4", ".", ".", "8", ".", "3", ".", ".", "1"]
            5 [ "7", ".", ".", ".", "2", ".", ".", ".", "6"]
            6 [ ".", "6", ".", ".", ".", ".", "2", "8", "."]
            7 [ ".", ".", ".", "4", "1", "9", ".", ".", "5"]
            8 [ ".", ".", ".", ".", "8", ".", ".", "7", "9"]
            
    outer loop (i=0):       // ROWS
        inner loop: (j=0)   // COLUMNS
            c = board[i][j] - '1'
            c = board[0][0] - '1'
            c = 5 - '1'
            c = 53 - 49
            c = 4
            
            check & increment:
                                0 ------> 1
                IF maps[0][i][c]++ != 0  // maps[0][0][4] if the value at index 4 of the first row in rows != 0
                    return false
                        
                                0 ------> 1
                IF maps[1][j][c]++ != 0  // maps[1][0][4] if the value at index 4 of the first column in columns != 0
                    return false
                    
                                0 ------> 1
                IF maps[2][get_quadrants(i,j)][c]++ != 0   // maps[2][0][4] if the value at index 4 of the first quadrant 
                    return false                        // in quadrants != 0
                    
                5 is now recognized to be first row, first column AND first quadrant in maps i.e., val at i=4 is 1
                
        inner loop: (j=1)   // COLUMNS
            c = board[i][j] - '1'
            c = board[0][1] - '1'
            c = 5 - '1'
            c = 53 - 49
            c = 4
            
            check & increment:
                                1 ------> 2
                IF maps[0][i][c]++ != 0  //  maps[0][0][4] = 1 -> FIRST ROW HAS A 5 ALREADY AS VAL AT INDEX 4 IS 1    
                    
    RETURN FALSE
*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][][] maps = new int[3][9][9];

        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                int c = board[i][j] - '1';
                if (c >= 0) {
                    if (maps[0][i][c]++ != 0) return false;    
                    if (maps[1][j][c]++ != 0) return false;
                    if (maps[2][get_quadrant(i,j)][c]++ != 0) return false;
                }
            }
        }
        return true;
    }
    
    int get_quadrant(int i, int j) {
        if (i <= 2) {
            if (j <= 2) return 0;
            else if (j <= 5) return 1;
            else if (j <= 8) return 2;
        }
        else if (i <= 5) {
            if (j <= 2) return 3;
            else if (j <= 5) return 4;
            else if (j <= 8) return 5;
        }
        else if (i <= 8) {
            if (j <= 2) return 6;
            else if (j <= 5) return 7;
            else if (j <= 8) return 8;
        }
        return -1;
    }
}
