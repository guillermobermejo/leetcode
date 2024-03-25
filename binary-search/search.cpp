/**
Programmer: Guillermo           E
Language: C++
Time Complexity: O(log n)
Space Complexity: O(1)
Runtime: 14ms (beats 98.93%)
Memory: 30mb (beats 22.84%)

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
    declare & initialize:
        1 int value     name: l     value: 0                notes: left pointer
        1 int value     name: r     value: nums.size()-1    notes: right pointer
        
    while loop (l <= r):
        declare and initialize:
            1 int value     name: m     value: (l+r)/2      notes: middle value (the return)
        
        if check:
            if nums[m] == target 
                true ? we FOUND target, return m
                false ? continue this iteration of the loop (calculate pointers)

        calculate pointers:
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

    1st:      l   m     r
    index:    0 1 2 3 4 5
    range:  |-------------|
    nums:   [-1,0,3,5,9,12]
        l=0
        r=5
        m = 2 (l+r / 2) -> 0+5/2
        
        nums[2] = 3
        3 != target (9)
        
        target > r
        l = m+1
        l= 3
    
    2nd:            l m r
    index:    0 1 2 3 4 5
    range:         |------|
    nums:   [-1,0,3,5,9,12]
        l=3
        r=5
        m = 4 (l+r / 2) -> 3+5/2   
        
        nums[4] = 9
        9 == target (9)

    RETURN m
*/

class Solution {
public:
    int search(vector<int>& nums, int target) {
        int l = 0;
        int r = nums.size()-1;

        while(l <= r) {
            int m = (l+r)/2;

            if (nums[m] == target)
                return m;
            
            l = target > nums[m] ? m+1 : l;
            r = target < nums[m] ? m-1 : r;
        }

        return -1;
    }
};
