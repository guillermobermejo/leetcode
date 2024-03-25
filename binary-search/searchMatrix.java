/**
Programmer: Guillermo           M
Language: Java
Time Complexity: O(log m * n)
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 41.7mb (beats 52.13%)

DS: none

Algorithm:
    matrix[i][j]
    use two pointers approach for the binary search, l and r for the left and right indexes
    
    perform binary search on i indexes first to find a good candidate array of the 2d matrix
    if the 'middle' index has a range that CAN contain the target is met
        perform binary search on matrix[m]
            contains ?  return true
            not ?       return false
    if the middle is not a range that can contain target update middle index
        target > matrix[m][0] ? l = m+1
        target < matrix[m][0] ? r = m-1
        
    if we escape the loop 
    target NOT contained
    
Algorithm:
    Main function:              //iteration of the i's in matrix[i][j]
        declare & initialize:
            1 int value     name: l     value: 0                notes: the left pointer initialized to the start of the matrix (first index)
            1 int value     name: r     value: matrix.length-1  notes: the right pointer initialized to the end of the matrix (last index)
        
        while loop(l <= r):
            declare & initialize:
                1 int value     name: m     value: (r-l)/2      notes: the middle value of the current range of indexes of the matrix itself
            
            if check:
                if range of the current array at index m in matrix[m] CAN CONTAIN the target
                    true?
    !                   return binarySearchFlat(matrix[m], target)
                    false?
                        continue loop
            
            calculate l & r:    // (based on target and matrix[m][0])
                l = target > matrix[m][0] ? then m+1 else l
                r = target < matrix[m][0] ? then m-1 else l
        
        return:                 // if while loop is exited, target is NOT in matrix
            false
    
    binarySearchFlat function(int[] arr, int target):  //iteration of the j's in matrix[i][j] (the single array)
        declare & initialize:
            1 int value     name: l     value: 0                notes: the left pointer initialized to the start of the matrix array passed (first index)
            1 int value     name: r     value: arr.length-1     notes: the right pointer initialized to the end of the matrix array passed (last index)
        
        while loop(l <= r):
            declare & initialize:
                1 int value     name: m     value: (r-l)/2      notes: the middle value of the current range of indexes of the matrix array passed to be searched
            
            if check:
                if arr[m] == target
    !               true ? return true
                    false ? continue this iteration of loop
                
            calculate l & r:    // (based on target and arr[m])
                l = target > arr[m] ? then m+1 else l
                r = target < arr[m] ? then m-1 else l
                
        return:
            false                // if while loop is exited, target is NOT in matrix     

Visualization of algorithm:
    Given Scenario + extra (after m=1):
    [ 1, 3, 5, 7]   i = 0   // given
    [10,11,16,20]   i = 1   // given
    [23,30,34,60]   i = 2   // extra
    [61,62,63,64]   i = 3   // extra
    [65,66,67,68]   i = 4   // extra
    [69,70,71,72]   i = 5   // extra
    [73,74,75,76]   i = 6   // extra

    Tree Visualzation for parent nodes:
      0  1  2  3  4  5  6
     [1,10,23,61,65,69,73]

        i Tree:
                    1[.]                [ 1, 3, 5, 7]   i = 0
                  /      \              [10,11,16,20]   i = 1
                 /        \             [23,30,34,60]   i = 2
            10[.]          23[.]        [61,62,63,64]   i = 3
           /    \         /    \        [65,66,67,68]   i = 4
          /      \       /      \       [69,70,71,72]   i = 5
        61[.]  65[.]  69[.]    73[.]    [73,74,75,76]   i = 6
        
    Target: 3
    Location: matrix[0][1]

    1st loop:
        l = 0 
        r = 6 (matrix.length-1])
    
        m = 6-0/2 = 3           // (r-l)/2
        
     l  [ 1, 3, 5, 7]   i = 0
        [10,11,16,20]   i = 1
        [23,30,34,60]   i = 2
     m  [61,62,63,64]   i = 3
        [65,66,67,68]   i = 4
        [69,70,71,72]   i = 5
     r  [73,74,75,76]   i = 6
    
        matrix[3][0] (start) <= target <= matrix[3][matrix[3].length-1] (end)   // can the range of the array at matrix[3] contain target ?
        61 (start) <= 3 <= 64 (end)
        FALSE
        l = 3 > 61 (FALSE)-> l =   0
        r = 3 < 61 (TRUE) -> m-1 = 2
    
    second loop:
        l = 0 
        r = 2
    
        m = 2-0/2 = 1           // (r-l)/2
        
     l  [ 1, 3, 5, 7]   i = 0
     m  [10,11,16,20]   i = 1
     r  [23,30,34,60]   i = 2
        [61,62,63,64]   i = 3
        [65,66,67,68]   i = 4
        [69,70,71,72]   i = 5
        [73,74,75,76]   i = 6
    
        matrix[1][0] (start) <= target <= matrix[1][matrix[1].length-1] (end)   // can the range of the array at matrix[1] contain target ?
        10 (start) <= 3 <= 20 (end)
        FALSE
        l = 3 > 10 (FALSE)-> l =   0
        r = 3 < 10 (TRUE) -> m-1 = 1
        
    third loop:
        l = 0 
        r = 1
    
        m = 1-0/2 = 0           // (r-l)/2
        
     l/m[ 1, 3, 5, 7]   i = 0
     r  [10,11,16,20]   i = 1
        [23,30,34,60]   i = 2
        [61,62,63,64]   i = 3
        [65,66,67,68]   i = 4
        [69,70,71,72]   i = 5
        [73,74,75,76]   i = 6
    
        matrix[0][0] (start) <= target <= matrix[0][matrix[0].length-1] (end)   // can the range of the array at matrix[0] contain target ?
        1 (start) <= 3 <= 7 (end)
        TRUE
            RETURN RESULT OF: binary search on matrix[0]
                l = 0
                r = 3 (matrix[m].length-1)
                
                m = 3-0/2 = 1   // (r-l)/2
              
                  l  m     r
                  0  1  2  3
                [ 1, 3, 5, 7]
                
                matrix[0][1] == target ? 
                
                TRUE
    return true   
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0;
        int r = matrix.length-1;
        
        while (l <= r) {
            int m = (l+r)/2;
            
            if (target >= matrix[m][0] && target <= matrix[m][matrix[m].length-1]) {
                if (binarySearchFlat(matrix[m], target)) return true;
                else return false;
            }
            
            l = target > matrix[m][0] ? m+1 : l;
            r = target < matrix[m][0] ? m-1 : r;
        }

        return false;
    }
    
    public boolean binarySearchFlat(int[] arr, int target) {        
        int l = 0;
        int r = arr.length-1;
        
        while (l <= r) {
            int m = (l+r)/2;
            
            if (arr[m] == target)
                return true;
        
            l = target > arr[m] ? m+1 : l;
            r = target < arr[m] ? m-1 : r;
        }
        
        return false;
        
    }
}
