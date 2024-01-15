/**
Programmer: Guillermo       M       (MODULAR + VARIABLES - O(1) space)
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 40.8mb (beats 41.85%)

DS: int[]
Advantage:  O(1) retrieval
            O(1) insertion
            storing previous work done

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
            
    recurence       (general structure)
    relation: arr[i] = Math.max(nums[i] + arr[i-2], arr[i-1]);

Algorithm:
    MAIN FUNCTION:
        if check:
            if the length of nums is 1 return the value of this house (i.e., nums[0])
        
        return:
            // RETURN the MAX of all the range from i=0 to i=nums.length-1 (inclusive)
            rob_helper(nums, 0, nums.length-1)
    
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

Visualization of algorithm (rob_helper(nums, 0, nums.length-1)):
    nums:   [2, 7, 9, 3, 1]
    start:  0
    end:    4

    start:  two_houses_left -> first house: 2   (nums[start])                                       houses: [2]         max: 2
            one_house_left -> second house: 7   (the max value in a neighborhood of TWO houses)     houses: [2, 7]      max: 7
                note: one_house_left -> MAX(arr[0], nums[start++])  i.e., start is 1 -> 2
                
    recurence       (custom structure)
    relation: max = Math.max(nums[start++] + two_houses_left, one_house_left);    // where start is incremented by 2 at the point relation is used (two houses registered)
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    two_houses_left = 2
    one_houses_left = 7
    
    
            i=2 (set to value of start)
                            2->3
            max = 9 (nums[start++]) + 2 (two_houses_left) || 7 (one_house_left)
                                                        9 || 7
            = 11                    11 max value from houses 0-2 inclusive                  		houses: [2,7,9]     max: 11
            two_house_left = 7 (one_house_left)
            one_house_left = 11 (max)
    
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    two_houses_left = 7
    one_house_left = 11
            
            i=3
                            3->4
            max = 3 (nums[start++]) + 7 (two_houses_left) || 11 (one_house_left)
                                                       10 || 11
            = 11                    11 max value from houses 0-3 inclusive                  		houses: [2,7,9,3]   max: 11
            two_houses_down = 11 (one_house_down)
            one_houses_down = 11 (max)


    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    two_houses_down = 11 
    one_houses_down = 11 
            
            i=4
                            4->5
            max = 1 (nums[start++]) + 11 (two_houses_left) || 11 (one_house_left)
                                                        12 || 11
            = 12                    12 max value from houses 0-4 inclusive                  		houses: [2,7,9,3,1] max: 12
            two_houses_down = 11 (one_house_down)
            one_houses_down = 12 (max)
    
    return max (MAX profit of all houses 0-4 inclusive)
*/


class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return nums[0];
        
        return rob_helper(nums, 0, nums.length-1);
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
