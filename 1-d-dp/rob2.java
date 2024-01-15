/**
Programmer: Guillermo       M       (IN-PLACE)
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 40.8mb (beats 32.40%)

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
    relation: arr[i] = MAX(nums[i] + arr[i-2], arr[i-1])

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
    start:  nums[0] -> first house: 2   (UNCHANGED)      									houses: [2]         max: 2
            nums[1] -> second house: 7  (the max value in a neighborhood of TWO houses)     houses: [2, 7]      max: 7
                note: nums[1] -> MAX(nums[0], nums[1])
    
    nums @ any ith position is max value of all houses from position 0 up to and including position i
    calculated IN PLACE
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    
            i=2 
            nums[2] = 9 (nums[2]) + 2 (nums[0]) || 7 (nums[1])
                                             11 || 7
            = 11                    11 max value from houses 0-2 inclusive                  houses: [2,7,9]     max: 11
    
    index:   0  1  2  3  4
    nums:   [2, 7,11, 3, 1]
            
            i=3 
            nums[3] = 3 (nums[3]) + 7 (nums[1]) || 11 (nums[2])
                                             10 || 11
            = 11                    11 max value from houses 0-3 inclusive                  houses: [2,7,9,3]   max: 11
            
    index:   0  1  2  3  4
    nums:   [2, 7,11,11, 1]
            
            i=4 
            nums[4] = 1 (nums[4]) + 11 (nums[2]) || 11 (nums[3])
                                              12 || 11
            = 12                    12 max value from houses 0-4 inclusive                  houses: [2,7,9,3,1] max: 12
    
    index:   0  1  2  3  4
    nums:   [2, 7,11,11,12]
    
    return nums.length-1 (MAX profit of all houses inclusive)
*/


class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        nums[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++)
            nums[i] = Math.max(nums[i] + nums[i-2], nums[i-1]);
        
        return nums[nums.length-1];
    }
}
