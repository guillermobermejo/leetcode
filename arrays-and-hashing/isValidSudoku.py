class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        quadrants = [
            [0,0,0,1,1,1,2,2,2],
            [0,0,0,1,1,1,2,2,2],
            [0,0,0,1,1,1,2,2,2],
            [3,3,3,4,4,4,5,5,5],
            [3,3,3,4,4,4,5,5,5],
            [3,3,3,4,4,4,5,5,5],
            [6,6,6,7,7,7,8,8,8],
            [6,6,6,7,7,7,8,8,8],
            [6,6,6,7,7,7,8,8,8]
            ]

        column_map = [[0] * 9 for _ in range(9)]
        row_map = [[0] * 9 for _ in range(9)]
        quadrant_map = [[0] * 9 for _ in range(9)] 

        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] != ".":
                    quadrant = quadrants[i][j]
                    value = ord(board[i][j]) - ord("1")

                    if row_map[i][value] != 0:
                        return False
                    
                    row_map[i][value] += 1

                    if column_map[j][value] != 0:
                        return False
                    
                    column_map[j][value] += 1

                    if quadrant_map[quadrant][value] != 0:
                        return False
                    
                    quadrant_map[quadrant][value] += 1

        return True
