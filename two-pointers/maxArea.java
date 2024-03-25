/**
Programmer: Guillermo           M
Language: Java
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 4ms (beats 88.28%)
Memory: 57.1mb (beats 47.91%)

DS: none

Approach:
    use 2 pointer approach
    
    two pointers l and r (left and right pointers) will traverse height[] in O(n)
    pointers will start off max range i=0 to i=height.length-1
    calculate area of current endpoints, if greater than max area adjust max area
    
    MAXIMIZE THE MINIMUM END POINT
    MOVE which ever endpoint is the lesser of the two, if EQUAL move right left

Algorithm:
    declare:
        1 int value     name: max_area      value: -1       notes: 
        1 int value     name: l             value: 0        notes: 
        1 int value     name: r             value: 0        notes: 
        
        while loop (l < r):
            declare: 
                1 int value     name: range     value: r-l 
                1 int value     name: area      value: MIN(height[l], height[r]) * range
            
            if check:
                if area > max_area
                    true ? max_area = area
            
            if check:
                if left height is lower than right (height[l] < height[r])  // MAXIMIZE the MINIMUM height
                    true ? l++
                    
            else:
                r--
        
        return:
            max_area

Visualization:
    height: [1,8,6,2,5,4,8,3,7]
    output: 49  i.e., (7 * 7) (Math.min(height[l], height[r]) * range(r-l))
                              (Math.min(height[1], height[8]) * range(8-1))
                              (Math.min(8, 7) * 7)
    
      
    8 |                        
    7 |     |___________________|_______ 
    6 |     |   |               |       |
    5 |     |   |               |       |
    4 |     |   |       |       |       |
    3 |     |   |       |   |   |       |
    2 |     |   |       |   |   |   |   |
    1 |     |   |   |   |   |   |   |   |
    0 | |   |   |   |   |   |   |   |   |
        _   _   _   _   _   _   _   _   _
        0   1   2   3   4   5   6   7   8
            |---------------------------| <--- the range 
            
    (Loop ends when l ! < r)
    
    max_area=-1
    l=0
    r=8 (height.length-1)
    
        range = 8-0 = 8
        area = MIN(height[0], height[8]) * range = 1 * 8 = 8
        area > max_area
            max_area = area (8)
        height[0] < height[8] -> l++
        
    max_area=8
    l=1
    r=8 (height.length-1)
    
        range = 8-1 = 7
        area = MIN(height[1], height[8]) * range = 1 * 8 = 8
        area > max_area
            max_area = area (49)
        height[1] > height[8] -> r--
        
    max_area=49
    l=1
    r=7
    
        range = 7-1 = 6
        area = MIN(height[1], height[7]) * range = 3 * 6 = 18
        area < max_area
            do nothing
        height[1] > height[7] -> r--
    
    max_area=49
    l=1
    r=6
    
        range = 6-1 = 5
        area = MIN(height[1], height[5]) * range = 4 * 5 = 20
        area < max_area
            do nothing
    
    cont until l == r... (49 is greatest)
*/

class Solution {
    public int maxArea(int[] height) {
        int max_area = -1;
        int l = 0;
        int r = height.length-1;
        
        while(l < r) {
            int range = r - l;
            int area = Math.min(height[l], height[r]) * range;
            if (area > max_area) max_area = area;
            
            if (height[l] < height[r]) l++;
            else r--;
        }
        
        return max_area;
    }
}
