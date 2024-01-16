/**
Programmer: Guillermo       M       (MODULAR + standard)
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 40.6mb (beats 51.77%)

DS: int[]
Advantage:  O(1) Insertion
            O(1) Access
            storing previous work done from any ranged traversal from i=start - i=end (inclusive)
            
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
    
    start 1:  at the first index i=0 to i=nums.length-2 (the range excluding the last house)
               0 1 2 
        nums: [2,1,1]
        
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


    start 2:  at the first index i=0 to i=nums.length-2 (the range excluding the last house)
               1 2 3 
        nums: [1,1,2]
        
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
    relation:   arr[i] = MAX(nums[start++] + arr[i-2], arr[i-1])

Algorithm:
    MAIN FUNCTION:
        if check:
            if the length of nums is 1 return the value of this house (i.e., nums[0])
        
        return:
            // RETURN the MAX of all the range EXCLUDING THE LAST HOUSE and the MAX of all the range EXCLUDING THE FIRST HOUSE
            MAX(rob_helper(nums, 0, nums.length-2), rob_helper(nums, 1, nums.length-1))
    
    rob_helper function(int[] nums, int start, int end):
        if check:
            end-start == 0  // if the start and end is the same index then only one house, return it
                return nums[end-start]
        
        declare & initialize:
            1 int[] array       name: arr       value: new int[]        size: end-start + 1         notes: the size of the array is only for the amount needed i.e, start:1 & end: 5 -> size: 5
            arr[0]: nums[start++]
            arr[1]: MAX(arr[0], nums[start++])
            
        for loop(int i = 2; i < arr.length; i++):
            traverse the remainder of the arr[] array if larger than size 2 and calculate the max from those ranges
            note:
                arr[i] = MAX(nums[start++] + arr[i-2], arr[i-1])
        
        return:
            arr[arr.length-1]   // the MAX profit from range i=start - i=end (inclusive)

Visualization of algorithm (rob_helper(nums, 0, nums.length-1)):
    note:   first call to rob_helper is from range i=0 to i=2 (nums.length-2)   (excludes last house)

    nums:   [2, 1, 1, 2]
    start:  0
    end:    3
    
    start:  arr[0] -> first house: 2   (nums[start++])      i.e., start: 0 then increment to 1      houses: [2]         max: 2
            arr[1] -> second house: 2  (the max value in a neighborhood of TWO houses)              houses: [2, 1]      max: 2
                note: arr[1] -> MAX(arr[0], nums[start++])  i.e., start: 1 then increment to 2
    
    recurence 
    relation:   arr[i] = MAX(nums[start++] + arr[i-2], arr[i-1])
    
    index:   0  1  2  3
    nums:   [2, 1, 1, 2]
    
                   S
    index:   0  1  2 
    arr:    [2, 2, 0]
    
     i=start (2 at this point) 
                               2->3
            arr[2] = 1 (nums[start++]) + 2 (arr[i-2]) || 1 (arr[i-1])
                                                    3 || 1
            = 3                    3 max value from houses 0-2 inclusive                  houses: [2,1,1]     max: 3
    
    arr:    [2, 2, 3]
    
    return arr[arr.length-1]    arr[2]      i.e., the MAX profit from robbing the range i=0 - i=2
    
Visualization of algorithm (rob_helper(nums, 0, nums.length-1)):
    note:   second call to rob_helper from range i=1 to i=nums.length-1         (excludes first house)

    nums:   [2, 1, 1, 2]
    start:  1
    end:    4
    
    start:  arr[0] -> first house: 1   (nums[start++])      i.e., start: 1 then increment to 2      houses: [1]         max: 2
            arr[1] -> second house: 1  (the max value in a neighborhood of TWO houses)              houses: [1, 1]      max: 2
                note: arr[1] -> MAX(arr[0], nums[start++])  i.e., start: 2 then increment to 3
    
    recurence 
    relation:   arr[i] = MAX(nums[start++] + arr[i-2], arr[i-1])
    
    index:   0  1  2  3
    nums:   [2, 1, 1, 2]
    
                   S
    index:   0  1  2 
    arr:    [1, 1, 0]
    
     i=start (3 at this point) 
                               3->4
            arr[2] = 2 (nums[start++]) + 1 (arr[i-2]) || 1 (arr[i-1])
                                                    3 || 1
            = 3                    3 max value from houses 1-3 inclusive                  houses: [1,1,2]     max: 3
    
    arr:    [1, 1, 3]
    
    return arr[arr.length-1]    arr[2]      i.e., the MAX profit from robbing the range i=1 - i=3
    
Visualization of algorithm (rob_helper(nums, 0, nums.length-1)):
    note:   initial call to rob(int[] nums)
    
    return MAX(3, 3)
*/

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        return Math.max(rob_helper(nums, 0, nums.length-2), rob_helper(nums, 1, nums.length-1));
    }

    // returns calculation of MAX value of nums[] from i=start - i=end(INCLUSIVE)
    public int rob_helper(int[] nums, int start, int end) {
        if (end-start == 0) return nums[start];
        
        int[] arr = new int[end - start+1];
        arr[0] = nums[start++];
        arr[1] = Math.max(arr[0], nums[start++]);
        
        for (int i=2; i < arr.length; i++)
            arr[i] = Math.max(nums[start++] + arr[i-2], arr[i-1]);
        
        return arr[arr.length-1];
    }
}
