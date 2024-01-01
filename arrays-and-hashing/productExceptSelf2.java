/**
Version: 2 (2 arrays)            M
Programmer: Guillermo
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 2ms (beats 66.87%)
Memory: 53mb (beats 6.93%)

DS: int[]   (given)
Advantage:  O(1) retrieval
            O(1) Insertion

Notes: 
    prefix:     product of numbers from indexes 0 - i to nums.length-1
    postfix:    product of numbers from indexes nums.length-1 - i to 0

Example (using two arrays to store prefix and postfix):
    nums:       [ 1, 2, 3, 4]       nums:       [ 1, 2, 3, 4]
    prefix:     [ 1, 2, 6,24]       postfix:    [24,24,12, 4]
{1=1*1, 2=1*2, 6=1*2*3, 24=1*2*3*4} {4=4*1, 12=4*3, 24=4*3*2, 24=4*3*2*1}

Algorithm:
    approach:
        declare 2 arrays 1 for prefix 1 for postfix products
        calculate result_arr indexes based on prefix[i-1] * postfix[i+1]

    declare 3 int[] arrays: 
        1 for result            size: nums.length   name: result_arr
        1 for prefix products   size: nums.length   name: prefix
        1 for postfix products  size: nums.length   name: postfix
    
    initialize:                                                 s->
        prefix[0] = nums[0]                             // [ 1, 0, 0, 0]
        postfix[nums-length-1] = nums[nums.length-1]    // [ 0, 0, 0, 4]
                                                                 <-s
    for loop
        compute prefix from i=1 - i=nums.length-1   (end)
        note:
            prefix[i] = prefix[i-1] * nums[i]
    
    for loop
        compute postfix from i=nums.length-2 - i=0  (beninging)
        note:
            postfix[i] = postfix[i+1] * nums[i]

    initialize:                                                 
        result_arr[0] = postfix[1]                             // [24, 0, 0, 0]
        result_arr[nums-length-1] = prefix[nums.length-2]      // [ 0, 0, 0, 6]

    for loop
        compute result_arr from i=1 - i=nums.length-2
        note:
            result_arr[i] = prefix[i-1] * postfix[i+1]
    
    return:
        result_arr
    
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result_arr = new int[nums.length];
        int[] prefix     = new int[nums.length];
        int[] postfix    = new int[nums.length];

        prefix[0] = nums[0];
        postfix[nums.length-1] = nums[nums.length-1];
        
        // compute prefix
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i-1] * nums[i];
        }

        // compute postfix
        for (int i = nums.length-2; i >= 0; i--) {
            postfix[i] = postfix[i+1] * nums[i];
        }

        // compute result_arr
        result_arr[0] = postfix[1];
        result_arr[nums.length-1] = prefix[nums.length-2];

        for (int i = 1; i <= nums.length-2; i++) {
            result_arr[i] = prefix[i-1] * postfix[i+1];
        }

        return result_arr;
    }
}
