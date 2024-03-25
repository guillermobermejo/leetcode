/**
Programmer: Guillermo           M   (in-place)
Language: Java
Time Complexity: O(n)
Space Complexity: O(1)      (result_arr does not count)
Runtime: 2ms (beats 66.87%)
Memory: 53mb (beats 51.43%)

DS: int[]   (given)
Advantage:  O(1) retrieval
            O(1) Insertion

Notes: 
    prefix[]:   for all index: product of numbers from indexes i=0 to i=nums.length-1
    postfix[]:  for all index: product of numbers from indexes i=nums.length-1 to i=0

Example (using two arrays to store prefix and postfix):
    nums:       [ 1, 2, 3, 4]       nums:       [ 1, 2, 3, 4]
    prefix:     [ 1, 2, 6,24]       postfix:    [24,24,12, 4]

Approach:
    compute prefix[] and postfix[] within result_arr[]
    1st loop calculate prefix values in result_arr[] from i=1 tp i=nums.length-2
    2nd loop calculate postfix values in result_arr[] from i=nums.length-2 to i=1
    
Algorithm:
    declare int[]: 
        1 for result            size: nums.length   name: result_arr
    
    initialize:
        nums[0] to 1 (necessary for prefix calculation 0 otherwise)

    for loop:
        calculate prefix in result_arr[] from i=1 to i=nums.length-1 (end)
        (result will be prefix example shifted 1 left)
        note:
            result_arr[i] = result_arr[i-1] * nums[i-1];
            
    declare & initialize:
        int postfix and set to 1

    for loop: (reverse)
        calculate postfix in result_arr[] in reverse (i=nums.length-1 to i=0)
            note:   
                nums[i] = self * postfix
                postfix = postfix * nums[i]

Algorithm Example:
    index:        0, 1, 2, 3

    nums:       [ 1, 2, 3, 4]
    result_arr: [ 1, 0, 0, 0]

    // calculate prefix in result_arr
    nums:       [ 1, 2, 3, 4]
    prefix:     [ 1, 1, 2, 6]   (result_arr)

    // calculate postfix in result_arr (step by step)
    for loop in reverse from result_arr[nums.length-1] - result_arr[0]
    (                                                                           postfix = 1
            pass 1: result_arr[3] = result_arr[3] * postfix -> 6 * 1 = (6)      postfix = postfix * nums[3] -> 1 * 4 = (4)
                index:        0, 1, 2, 3
                result_arr: [ 1, 1, 2, 6] 
                
            pass 2: result_arr[2] = result_arr[2] * postfix -> 2 * 4 = (8)      postfix = postfix * nums[2] -> 4 * 3 = (12)
                index:        0, 1, 2, 3
                result_arr: [ 1, 1, 8, 6]

            pass 3: result_arr[1] = result_arr[1] * postfix -> 1 * 12 = (12)    postfix = postfix * nums[1] -> 12 * 2 = (24)
                index:        0, 1, 2, 3
                result_arr: [ 1,12, 8, 6]

            pass 4: result_arr[0] = result_arr[0] * postfix -> 1 * 24 = (24)    postfix = postfix * nums[0] -> 24 * 1 = (24)
                index:        0, 1, 2, 3
                result_arr: [24,12, 8, 6]       ANSWER
    )

    nums:       [ 1, 2, 3, 4]
    postfix:    [24,12, 8, 6]   (result_arr)    ANSWER
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result_arr = new int[nums.length];
        result_arr[0] = 1;
        
        // compute prefix
        for (int i = 1; i < nums.length; i++) {
            result_arr[i] = result_arr[i-1] * nums[i-1];
        }

        // compute postfix
        int postfix = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            result_arr[i] = result_arr[i] * postfix;
            postfix *= nums[i];
        }

        return result_arr;
    }
}
