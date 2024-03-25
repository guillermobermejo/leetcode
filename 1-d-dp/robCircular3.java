/**
Programmer: Guillermo           M       (MODULAR + VARIABLES - O(1) space)
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 40.7mb (beats 43.09%)

DS: none
            
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
    
    NOTE: arr[] is not used but is for visualizing, two_houses_left and one_house_down
    
    start 1:  at the first index i=0 to i=nums.length-2 (the range excluding the last house)
               0 1 2 3
        nums: [2,1,1,2]
        
               t o m    // where t: two_houses_down,    o: one_house_down   m: max
               0 1 2 
        arr:  [0,0,0]
        
        (AFTER determining the max profit of the first house alone and the first two houses)
                  --->
                  S
              0 1 2       // houses: 0            houses: 0 & 1
        arr: [2,2,0]      // MAX of range 0: 2 | MAX of range 0-1: 2
        
        meaning the range of nums covered is...
        
        nums.length: 3
        range: 0 - nums.length-2 (2)    (AVOID LAST HOUSE)
                |-----|
        index:   0 1 2 3
        nums:   [2,1,1,2]


    start 2:  at the first index i=1 to i=nums.length-1 (the range excluding the first house)
               1 2 3 
        nums: [1,1,2]
        
               t o m    // where t: two_houses_down,    o: one_house_down   m: max
               0 1 2 
        arr:  [0,0,0]
        
        (AFTER determining the max profit of the first house alone and the first two houses)
        
                  --->
                  S
              0 1 2       // houses: 1            houses: 1 & 2
        arr: [1,1,0]      // MAX of range 1: 1 | MAX of range 1-2: 1        NOTE: range of NUMS[]
        
        meaning the range of nums covered is...
        
        nums.length: 3
        range: 1 - nums.length-1 (3)    (AVOID FIRST HOUSE)
                   |-----|
        index:   0 1 2 3
        nums:   [2,1,1,2]          
            
    recurence 
    relation:   max = MAX(nums[start++] + two_houses_down, one_house_down)

Algorithm:
    MAIN FUNCTION:
        if check:
            if the length of nums is 1 return the value of this house (i.e., nums[0])
        
        return:
            // RETURN the MAX of all the range EXCLUDING THE LAST HOUSE and the MAX of all the range EXCLUDING THE FIRST HOUSE
            MAX(rob_helper(nums, 0, nums.length-2), rob_helper(nums, 1, nums.length-1))
    
    rob_helper function(int[] nums, int start, int end):
        if check:
            if the end-start == 0 the range is 1 so return the value of this house (i.e., nums[start]/nums[end])
            
        if check:
            if the end-start == 1 the range is 2 so return the MAX profit of the two houses (i.e., MAX(nums[start], nums[end]))
            
    
        declre & initialize:                                                                                                                                   t   m
            1 int value     name: two_houses_left       value: nums[start++]                    notes: the immediate non-adjacent house to the left     i.e., [2,1,1,2] t is two houses left of m
                                                                                                                                                                 o m
            1 int value     name: one_house_left        value: MAX(arr[0], nums[start++])       notes: the immediate non-adjacent house to the left     i.e., [2,1,1,2] o is one house left of m
            1 int value     name: max                   value: 0                                notes: the MAX profit up to that end range              i.e, in the above, m is the max up to that range
        
        for loop (int i = start; i <= end; i++):
            iterate from the third house (2 houses right of start) to the end house (inclusive)
            note:
                max = Math.max(nums[start++] + two_houses_left, one_house_left)
                two_houses_left = one_house_left
                one_house_left = max
    
        return:
            max     // the MAX profit from the range of houses at i=start to i=end (inclusive)

Visualization of algorithm (rob_helper(nums, 0, nums.length-2)):
    note:   first call to rob_helper is from range dwi=0 to i=2 (nums.length-2)   (excludes last house)
    
    nums:   [2, 1, 1, 2]
    start:  0
    end:    2
                                                        0->1
    start:  two_houses_left -> first house: 2   (nums[start++])                                     houses: [2]         max: 2
            one_house_left -> second house: 2   (the max value in a neighborhood of TWO houses)     houses: [2, 1]      max: 2
                note: one_house_left -> MAX(arr[0], nums[start++])  i.e., start is 1 -> 2
                
    recurence       (custom structure)
    relation: max = Math.max(nums[start++] + two_houses_left, one_house_left);    // where start is incremented by 2 at the point relation is used (two houses registered)
    
    LOOP UNTIL i > end
    
    index:   0  1  2  3 
    nums:   [2, 1, 1, 2]
    two_houses_left = 2
    one_houses_left = 2
    
    
            i=2 (set to value of start)
                            2->3
            max = 1 (nums[start++]) + 2 (two_houses_left) || 2 (one_house_left)
                                                        3 || 2
            = 3                    3 max value from houses 0-2 inclusive                  		    houses: [2,1,1]     max: 3
            two_house_left = 2 (one_house_left)
            one_house_left = 3 (max)
            
            i++ > 3 exit
            
    return max
    
Visualization of algorithm (rob_helper(nums, 1, nums.length-1)):
    note:   second call to rob_helper from range i=1 to i=nums.length-1         (excludes first house)
    
    nums:   [2, 1, 1, 2]
    start:  1
    end:    3
                                                        1->2
    start:  two_houses_left -> first house: 1   (nums[start++])                                     houses: [1]         max: 1
            one_house_left -> second house: 1   (the max value in a neighborhood of TWO houses)     houses: [1, 1]      max: 1
                note: one_house_left -> MAX(arr[0], nums[start++])  i.e., start is 2 -> 3
                
    recurence       (custom structure)
    relation: max = Math.max(nums[start++] + two_houses_left, one_house_left);    // where start is incremented by 2 at the point relation is used (two houses registered)
    
    LOOP UNTIL i > end
    
    index:   0  1  2  3 
    nums:   [2, 1, 1, 2]
    two_houses_left = 1
    one_houses_left = 1
    
    
            i=3 (set to value of start)
                            3->4
            max = 2 (nums[start++]) + 1 (two_houses_left) || 1 (one_house_left)
                                                        3 || 1
            = 3                    3 max value from houses 1-3 inclusive                  		    houses: [1,1,2]     max: 3
            two_house_left = 1 (one_house_left)
            one_house_left = 3 (max)
            
            i++ > 3 exit
            
    return max
    
Visualization of algorithm (rob_helper(nums, 0, nums.length-1)):
    note:   initial call to rob(int[] nums)
    
    return MAX(3, 3)
*/

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        return Math.max(rob_helper(nums, 0, nums.length-2), rob_helper(nums, 1, nums.length-1));
    }
    
    // returns calculation of MAX value of nums[] from i=start - i=end(INCLUSIVE)
    public int rob_helper(int[] nums, int start, int end) {
        if (end-start == 0) return nums[start];
        if (end-start == 1) return Math.max(nums[start], nums[end]);

        int two_houses_left = nums[start++];
        int one_house_left = Math.max(two_houses_left, nums[start++]);
        int max = 0;
        
        for (int i=start; i <= end; i++) {
            max = Math.max(nums[start++] + two_houses_left, one_house_left);
            two_houses_left = one_house_left;
            one_house_left = max;
        }
        
        return max;
    }
}
