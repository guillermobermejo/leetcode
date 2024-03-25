'''
Programmer: Guillermo           M
Language: Python3
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 502ms (beats 92.93%)
Memory: 30.1mb (beats 42.56%)

DS: none

Approach:
    use 2 pointer approach
    
    two pointers l and r (left and right pointers) will traverse height[] in O(n)
    pointers will start off max range i=0 to i=len(height)-1
    calculate area of current endpoints, if greater than max area adjust max area
    
    MAXIMIZE THE MINIMUM END POINT
    MOVE which ever endpoint is the lesser of the two, if EQUAL move right left

Algorithm:
    declare:
        1 int value     name: max_area      value: -1               notes: max area overall
        1 int value     name: l             value: 0                notes: left pointer
        1 int value     name: r             value: len(height)-1    notes: right pointer
        
        while loop (l < r):
            declare: 
                1 int value     name: range_w   value: r-l 
                1 int value     name: area      value: MIN(height[l], height[r]) * range_w
            
            if check:
                if area > max_area
                    true ? max_area = area
            
            if check:
                if left height is lower than right (height[l] < height[r])  // MAXIMIZE the MINIMUM height
                    true ? l++                                              // l += 1
                    
            else:
                r--                                                         // r -= 1
        
        return:
            max_area

Visualization:
    height: [1,8,6,2,5,4,8,3,7]
    output: 49  i.e., (7 * 7) (min(height[l], height[r]) * range(r-l))
                              (min(height[1], height[8]) * range(8-1))
                              (min(8, 7) * 7)
    
      
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
        height[0] < height[8] -> l += 1
        
    max_area=8
    l=1
    r=8 (height.length-1)
    
        range = 8-1 = 7
        area = MIN(height[1], height[8]) * range = 1 * 8 = 8
        area > max_area
            max_area = area (49)
        height[1] > height[8] -> r -= 1
        
    max_area=49
    l=1
    r=7
    
        range = 7-1 = 6
        area = MIN(height[1], height[7]) * range = 3 * 6 = 18
        area < max_area
            do nothing
        height[1] > height[7] -> r -= 1
    
    max_area=49
    l=1
    r=6
    
        range = 6-1 = 5
        area = MIN(height[1], height[5]) * range = 4 * 5 = 20
        area < max_area
            do nothing
    
    cont until l == r... (49 is greatest)
'''

class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_area = -1
        l = 0
        r = len(height)-1
        
        while(l < r):
            range_w = r - l
            area = min(height[l], height[r]) * range_w
            
            if area > max_area:
                max_area = area
            
            if height[l] < height[r]: 
                l += 1                      # no ++ in Python
            else:
                r -= 1                      # no -- in Python
        
        return max_area
