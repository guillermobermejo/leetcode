/**
Programmer: Guillermo           *cracked*E
Time Complexity: O(log n)
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 45.68mb (beats 19.53%)

DS: none

Approach: 
    use two pointer approach
    declare two pointers a left and right that will regularly get updated at every iteration of the loop
    calculate a middle value that will be used as the anchor of decisions
    if the value at the middle value index is equal to the target, return the middle
    if not then update left and right based on a comparison of the target and value at middle index
    eliminates 50% of the array at a time

    O(log n)

Algorithm:
    if check:
        nums length == 1 then return if the sole element is the target
    
    declare & initialize:
        1 int value     name: l     value: 0                notes: left pointer
        1 int value     name: r     value: nums.length      notes: right pointer
        1 int value     name: m     value: -1               notes: middle value (the return)
    
    while loop (l < r):
        if check:
            if right and left index are beside eachother 
        
        calculate:
            m = ((l+r) / 2) // this is the middle index which we will test
        
        if check:
            if nums[m] == target we FOUND target, return m

        calculate loop variables:
            l = target > nums[m] ? m : l  // if target is greater than the middle value, l = m
            r = target < nums[m] ? m : l  // if target is less than the middle value, r = m

    return:
        -1  // if outside the loop, target NOT in nums

Visualization of 1st iterations:
    index:    0 1 2 3 4 5
    nums:   [-1,0,3,5,9,12]
    target: 9

    l = 0              
    r = 5 (length-1)

1st:    
index:    0 1 2 3 4 5
range:  |-------------|
nums:   [-1,0,3,5,9,12]
    m = 2 (l+r / 2) -> 0+5/2
    nums[2] = 3
    
    nums[3] != target (9)
    target > r
    l = m
    l= 2

2nd:         
index:    0 1 2 3 4 5
range:       |--------|
nums:   [-1,0,3,5,9,12]
    m = 3 (l+r / 2) -> 2+5/2   
    nums[3] = 5
    
    nums[3] != target (9)
    target > r
    l = m
    l = 3

3rd:         
index:    0 1 2 3 4 5
range:          |-----|
nums:   [-1,0,3,5,9,12]
    m = 4 (l+r / 2) -> 3+5/2
    nums[4] = 9
    
    nums[4] == target (9)

RETURN m
*/

class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) 
            return nums[0] == target ? 0 : -1;

        int l = 0;
        int r = nums.length-1;
        int m = -1;

        while(l < r) {
            if (r-l == 1) {
                if (nums[l] == target) return l;
                else if (nums[r] == target) return r;
                return -1;
            }

            m = ((l+r) / 2);

            if (nums[m] == target)
                return m;
            
            l = target > nums[m] ? m : l;
            r = target < nums[m] ? m : r;
        }

        return -1;
    }
}
