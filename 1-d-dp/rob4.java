/**
Programmer: Guillermo       M       (MODULAR)       ! CAN BE IMPROVED (i.e., O(1) space with variables)
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 40.7mb (beats 46.58%)

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
            // RETURN the MAX of the entire range of nums[] from i=0 to i=nums.length-1 (inclusive) 
            rob_helper(nums, 0, nums.length-1)
    
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
    recurence       (custom structure)
    relation: arr[i] = Math.max(nums[start++] + arr[i-2], arr[i-1]);    // where start is incremented by 2 at the point relation is used (two houses registered)

    nums:   [2, 7, 9, 3, 1]
    start:  0
    end:    4

    start:  arr[0] -> first house: 2   (nums[start++])      i.e., start: 0 then increment to 1      houses: [2]         max: 2
            arr[1] -> second house: 7  (the max value in a neighborhood of TWO houses)              houses: [2, 7]      max: 7
                note: arr[1] -> MAX(arr[0], nums[start++])  i.e., start: 1 then increment to 2
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    arr:    [2, 7, 0, 0, 0]
    
            i=start (2 at this point) 
                               2->3
            arr[2] = 9 (nums[start++]) + 2 (arr[i-2]) || 7 (arr[i-1])
                                                   11 || 7
            = 11                    11 max value from houses 0-2 inclusive                  houses: [2,7,9]     max: 11
    
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    arr:    [2, 7, 11, 0, 0]
            
            i=3
                               3->4
            arr[3] = 3 (nums[start++]) + 7 (arr[i-2]) || 11 (arr[i-1])
                                                   10 || 11
            = 11                    11 max value from houses 0-3 inclusive                  houses: [2,7,9,3]   max: 11
            
    index:   0  1  2  3  4
    nums:   [2, 7, 9, 3, 1]
    arr:    [2, 7,11,11, 0]
            
            i=4
                               4->5
            arr[4] = 1 (nums[start++]) + 11 (arr[i-2]) || 11 (arr[i-1])
                                                    12 || 11
            = 12                    11 max value from houses 0-4 inclusive                  houses: [2,7,9,3,1] max: 12

    arr:    [2, 7,11,11,12]
    
    return arr[arr.length-1] (MAX profit of all houses 0-4 inclusive)
*/


class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return nums[0];
        
        return rob_helper(nums, 0, nums.length-1);
    }
    
    // returns calculation of MAX profit of nums[] from range i=start - i=end (INCLUSIVE)
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
