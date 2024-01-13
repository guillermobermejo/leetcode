/**
Programmer: Guillermo       M
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 40.8mb (beats 28.98%)

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
            
    recurence 
    relation: arr[i] = MAX(nums[i] + arr[i-2], arr[i-1])

Algorithm:
    if check:
        if the length of nums is 1 return the value of this house
    
    declare & initialize:
        1 int[] array       name: arr       value: new int[]        size: nums.length       notes: arr at every ith position will contain the MAX profit from robbing houses 0-i inclusive
        arr[0] = nums[0]                    // MAX profit of robbing the first house
        arr[1] = MAX(nums[0], nums[1])      // MAX profit of robbing the first two houses
        
    for loop (int i = 2; i < nums.length; i++):     // covers when nums length == 2 (does not enter)
        iterate from the third house (if any) calculating the MAX profit of all houses from 0-ith house inclusive
        note:
            arr[i] = MAX(nums[i] + arr[i-2], arr[i-1])
    
    return:
        arr[nums.length-1]

Visualization of algorithm:
    start:  arr[0] -> first house: 2    (the max value in a neighborhood of ONE house)      houses: [2]         max: 2
            arr[1] -> second house: 7   (the max value in a neighborhood of TWO houses)     houses: [2, 7]      max: 7
                note: arr[1] -> MAX(arr[0], arr[1])
    
    arr @ any ith position is max value of all houses from position 0 up to and including position i
    
    nums:   [2, 7, 9, 3, 1]
    index:   0  1  2  3  4
    arr:    [2, 7, 0, 0, 0]
            i=2 
            arr[2] = 9 + 2 || 7
            = 11                    11 max value from houses 0-2 inclusive                  houses: [2,7,9]     max: 11
    
    index:   0  1  2  3  4
    arr:    [2, 7,11, 0, 0]
            i=3 
            arr[3] = 3 + 7 || 11
            = 11                    11 max value from houses 0-3 inclusive                  houses: [2,7,9,3]   max: 11
            
    index:   0  1  2  3  4
    arr:    [2, 7,11,11, 0]
            i=4 
            arr[4] = 1 + 11 || 11
            = 12                    12 max value from houses 0-4 inclusive                  houses: [2,7,9,3,1] max: 12
    
    index:   0  1  2  3  4
    arr:    [2, 7,11,11,12]
*/


class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        arr[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++)
            arr[i] = Math.max(nums[i] + arr[i-2], arr[i-1]);
        
        return arr[nums.length-1];
    }
}
