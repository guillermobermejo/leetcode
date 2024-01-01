/**
Version: 1 (in-place)            M
Programmer: Guillermo
Time Complexity: O(n)
Space Complexity: O(1)      (result_arr does not count)
Runtime: 2ms (beats 66.87%)
Memory: 53mb (beats 51.43%)

DS: int[]   (given)
Advantage:  O(1) retrieval
            O(1) Insertion

Notes: 
    prefix:     product of numbers from indexes 0 - i to nums.length-1
    postfix:    product of numbers from indexes nums.length-1 - i to 0

Example (using two arrays to store prefix and postfix):
    nums:       [ 1, 2, 3, 4]       nums:       [ 1, 2, 3, 4]
    prefix:     [ 1, 2, 6,24]       postfix:    [24,24,12, 4]
    

Algorithm:
    approach: compute postfix and prefix within result_arr[]

    declare result_arr of size nums.length
    declare nums[0] to 1 (necessary for prefix calculation 0 otherwise)

    for loop
        calculate prefix starting from index i = 1 store in result_arr 
        (result will be prefix example shifted 1 left)
    
    declare postfix set to 1

    for loop (reverse)
        calculate postfix in reverse (index nums.length-1 - 0)
            note:   nums[i] = self * postfix
                    postfix = postfix * nums[i]

Algorithm Example:
    index:        0, 1, 2, 3

    nums:       [ 1, 2, 3, 4]
    result_arr: [ 1, 0, 0, 0]

    nums:       [ 1, 2, 3, 4]
    prefix:     [ 1, 1, 2, 6]   (result_arr)

    nums:       [ 1, 2, 3, 4]
    postfix:    [24,12, 8, 6]   (result_arr)    ANSWER


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
