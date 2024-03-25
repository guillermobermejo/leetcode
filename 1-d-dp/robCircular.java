/**
Programmer: Guillermo           M
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 40.8mb (beats 41.89%)

DS: int[]
Advantage:  O(1) Insertion
            O(1) Access
            storing previous work done from right traversal
            
DS: int[]
Advantage:  O(1) Insertion
            O(1) Access
            storing previous work done from left traversal

Approach: 
    use dynamic programming to store previous work to avoid spending resources on recalculations
    
    calculate a few examples by hand to see pattern
    analyze and come up with a recurrence relation
    use this recurrence relation to store work in an array
    
Analyze:
    nums:   [2,1,1,2]
    
    goal:   obtain the max value for robbing non-adjacent houses from a CIRCULAR neighborhood 
            means we need two arrays for 
                when the first house is robbed, the last CANNOT be robbed
                when the last house is robbed, the first CANNOT be robbed
                left arr: (ith position == MAX value from house i=0 - i=nums.length-2 inclusive (nums.length-1 would be adjacent to 0) )
                right arr: (ith position == MAX value from house i=nums.length-1 - i=1 inclusive (0 would be adjacent to nums.length-1) )

    avoid:  robbing a house that is adjacent to another (immediately left or immediately right (cannot rob the last house if first is robbed and vice versa))
    
                                                   !!!!!
    start 1:  at the third house to the right i=2 
              (AFTER determining the max profit of the first house alone and the first two houses alone while avoid last house)
                    --->
                    S
                0 1 2       // houses: 0            houses: 0 & 1
        l_arr: [2,2,1]      // MAX of range 0: 2 | MAX of range 0-1: 2
        
        meaning the range of nums covered is...
        
        nums.length: 3
        range: 0 - nums.length-2 (2)    (AVOID LAST HOUSE)
                |-----|
        index:   0 1 2 3
        nums:   [2,1,1,2]
                                                              !!!!!
    start 2:  at the third house to the left i=nums.length-3 
              (AFTER determining the max profit of the first house alone and the first two houses alone while avoid first house)
             <---
                S
                0 1 2       // houses: 2            houses: 2 & 1
        r_arr: [1,2,2]      // MAX of range 2: 2 | MAX of range 2-1: 2
        
        meaning the range of nums covered is...
                      
        nums.length: 3
        range: nums.length-1 (3) - 1    (AVOID FIRST HOUSE)
                   |-----|
        index:   0 1 2 3
        nums:   [2,1,1,2]
            
    recurence 
    relation:   l_arr[i] = MAX(nums[i] + arr[i-2], arr[i-1])
                r_arr[i] = MAX(nums[i+1] + arr[i+2], arr[i+1])

Algorithm:
    if check:
        if the length of nums is 1 return the value of this house
        
    if check:
        if the length of nums is 2 return the max value of robbing 1 house (the greater of the two)
        
    declare & initialize:
        1 int arr[]     name: rob_rightward     value: new int[]    size: nums.length-1     notes: the array containing the MAX values for all i's except the last house
        1 int arr[]     name: rob_leftward      value: new int[]    size: nums.length-1     notes: the array containing the MAX values for all i's except the first house
        
        rob_rightward[0] = nums[0]                                                              // the FIRST house going leftward from nums
        rob_rightward[1] = MAX(nums[0], nums[1])                                                // the MAX of the first TWO houses going leftward from nums
        
        rob_leftward[rob_leftward.length-1]  = nums[nums.length-1]                              // the FIRST house going rightward from nums (the last house)
        rob_leftward[rob_leftward.length-2]  = MAX(nums[nums.length-1], nums[nums.length-2])    // the MAX of the first TWO houses going rightward from nums (last two)
    
    for loop(int i = 0; i < rob_rightward.length; i++): 
        l_arr[i] = MAX(nums[i] + arr[i-2], arr[i-1])
    
    for loop(int i = 0; i < rob_rightward.length; i++):     // note: nums[i+1] because rob_leftward[] off by 1 as the 'second' house is at index 0 (ommited first house in size)
        r_arr[i] = MAX(nums[i+1] + arr[i+2], arr[i+1])
    
    return:
        MAX of the LAST index of rob_rightward and the FIRST index of rob_leftward
            i.e., the max of excluding robbing last house and excluding robbing the first house

Visualization of algorithm:
    start:  l_arr[0] -> first house: 2    (the max value in a neighborhood of ONE house)      houses: [2]         max: 2
            l_arr[1] -> second house: 2   (the max value in a neighborhood of TWO houses)     houses: [2, 1]      max: 2
            
            r_arr[r_arr.length-1] -> first house: 2    (the max value in a neighborhood of ONE house)      houses: [2]         max: 2
            r_arr[r_arr.length-2] -> second house: 2   (the max value in a neighborhood of TWO houses)     houses: [1, 2]      max: 2
                
                
    recurence 
    relation:   l_arr[i] = MAX(nums[i] + arr[i-2], arr[i-1])
                r_arr[i] = MAX(nums[i+1] + arr[i+2], arr[i+1])
    
    index:   0  1  2  3
    nums:   [2, 1, 1, 2]
    index:   0  1  2
    r_arr:  [2, 2, 0]
    
    index:   0  1  2  3
    nums:   [2, 1, 1, 2]
    index:      0  1  2
    l_arr:     [0, 2, 2]
    
    
    sub case: '+' means include
    
    nums:   [2, 1, 1, 2]
    index:   0  1  2  3
    l_arr:  [2, 2, 0]
    
             +     + <- this is nums[2]
    l_arr:  [2, 2, 1] -> [2, 2, 3]
        position:      2 (arr index)
        max profit:    3
        
            i=2
            l_arr[2] = 1 (nums[2]) + 2 (l_arr[0]) || 2 (l_arr[1])
                                                3 || 2                                             0 1 2
            = 3                    3 max value from houses 0-2 inclusive                  houses: [2,1,1]     max: 3
    
    nums:   [2, 1, 1, 2]
    index:   0  1  2  3
    r_arr:  [0, 2, 2]
    
  nums[1] -> +     + 
    r_arr:  [1, 2, 2] -> [3, 2, 2]
        position:      0 (arr index)
        max profit:    3
        
            i=0
            r_arr[0] = 1 (nums[0+1]) + 2 (r_arr[2]) || 2 (r_arr[1])
                                                3 || 2                                             1 2 3
            = 3                    3 max value from houses 1-3 inclusive                  houses: [1,1,2]     max: 3
    
    return  the MAX of robbing from the left or robbing from the right i.e.,
            the MAX from including the first house and excluding the last house OR excluding the first house and including the last
*/

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int[] rob_rightward = new int[nums.length-1];
        int[] rob_leftward  = new int[nums.length-1];
        
        rob_rightward[0] = nums[0];
        rob_rightward[1] = Math.max(nums[0], nums[1]);
        
        rob_leftward[rob_leftward.length-1]  = nums[nums.length-1];
        rob_leftward[rob_leftward.length-2]  = Math.max(nums[nums.length-1], nums[nums.length-2]);
        
        for (int i = 2; i < rob_rightward.length; i++)
            rob_rightward[i] = Math.max(nums[i] + rob_rightward[i-2], rob_rightward[i-1]);
        
        for (int i = rob_leftward.length-3; i >= 0; i--)
            rob_leftward[i] = Math.max(nums[i+1] + rob_leftward[i+2], rob_leftward[i+1]);   // nums[i+1] bc indexes mismatched
        
        return Math.max(rob_rightward[rob_rightward.length-1], rob_leftward[0]);
    }
}
