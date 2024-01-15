/**
Programmer: Guillermo       M       (VARIABLES & NOT MODIFYING NUMS)    ! NOT RECOMMENDED
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 40.4mb (beats 54.54%)

DS: none

Approach: 
    use dynamic programming to store previous work to avoid spending resources on recalculations
    
    generate the first few steps by hand - up to 6 steps
    analyze and come up with a recurrence relation
    use this recurrence relation to store work in an array
    
Analyze:
    nums:   [2,7,9,3,1]
    
    goal:   obtain the max value for robbing non-adjacent houses
    avoid:  robbing a house in the 'middle'
    start:  with the first house worth the most
    
    sub case: '+' means include
        +
    [2, 7, 1] -> [2, 7, 7]
    position:      2 (arr index)
    max profit:    7
        
            the MAX profit from robbing in this neighborhood of 3 houses including all houses is
            house in middle 7
     +     +  
    [2, 7, 6] -> [2, 7, 8]
    position:      2 (arr index)
    max profit:    8
        
            the MAX profit from robbing in this neighborhood of 3 houses including all houses is
            house in middle on left and right excluding middle 8
            
    recurence 
    relation: max = MAX(nums[i] + two_houses_left, one_houses_left)

Algorithm:
    if check:
        if the length of nums is 1 return the value of this house
    
    initialize:   
        nums[1] = MAX(nums[0], nums[1])             // MAX profit of robbing the first two houses
        
    for loop (int i = 2; i < nums.length; i++):     // covers when nums length == 2 (does not enter)
        iterate from the third house (if any) calculating the MAX profit of all houses from 0-ith house inclusive
        note:
            nums[i] = MAX(nums[i] + nums[i-2], nums[i-1])
    
    return:
        nums[nums.length-1]

Visualization of algorithm:
    start:  two_houses_left -> first house: 2   (nums[2])                                           houses: [2]         max: 2
            one_house_left -> second house: 7   (the max value in a neighborhood of TWO houses)     houses: [2, 7]      max: 7
                note: one_house_left -> MAX(nums[0], nums[1])
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    two_houses_down = 2
    one_houses_down = 7
    
            i=2 
            
            max = 9 (nums[2]) + 2 (two_houses_left) || 7 (one_house_left)
                                                 11 || 7
            = 11                    11 max value from houses 0-2 inclusive                  		houses: [2,7,9]     max: 11
            two_houses_down = 7 (one_house_down)
            one_houses_down = 11 (max)
            
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    two_houses_down = 7
    one_houses_down = 11
            
            i=3
            
            max = 3 (nums[3]) + 7 (two_houses_left) || 11 (one_house_left)
                                                 10 || 11
            = 11                    11 max value from houses 0-2 inclusive                  		houses: [2,7,9,3]   max: 11
            two_houses_down = 11 (one_house_down)
            one_houses_down = 11 (max)
            
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    two_houses_down = 11 
    one_houses_down = 11 
            
            i=4
            
            max = 1 (nums[4]) + 11 (two_houses_left) || 11 (one_house_left)
                                                  12 || 11
            = 12                    12 max value from houses 0-2 inclusive                  		houses: [2,7,9,3,1] max: 12
            two_houses_down = 11 (one_house_down)
            one_houses_down = 11 (max)
    
    return max (MAX profit of all houses inclusive)
*/


class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int two_houses_left = nums[0];
        int one_house_left = Math.max(nums[0], nums[1]);
        int max = 0;
        
        for (int i = 2; i < nums.length; i++) {
            max = Math.max(nums[i] + two_houses_left, one_house_left);
            two_houses_left = one_house_left;
            one_house_left = max;
        }
        
        return max;
    }
}
