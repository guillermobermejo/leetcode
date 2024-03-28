'''
Programmer: Guillermo           M
Language: Python3
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 330ms (beats 94.43%)
Memory: 67.3mb (beats 79.93%)

DS: set()
Advantage:  O(1) Insertion
            O(1) Access
            O(n) algorithm

Approach:
    use a python set called 'hashset' to store all elements in nums in hashset for quick O(1) search operations
    traverse the hashset using a python for loop
    valid elements mean they do not have a left neighbor as they are not the first element in the sequence
    for each valid element keep a count at how many immediate right neighbors it has i.e., [1,2,3,4] = 4

Algorithm:
    if check:
        if len(nums) == 0 then return 0
        
    declare & initialize:
        1 set               name: hashset       value: set()
    
    for loop: (i in hashset)
        for all elements in nums add to set
    
    declare & initialize:
        1 int value         name: count         value: 0            notes: the current count variable
        1 int value         name: next_val      value: 0            notes: the next value of a valid value
        1 int value         name: max_count     value: 0            notes: the max count of the longest consecutive
        
    for loop: (i in hashset)
        if check:
            if i does NOT have a left neighbor it is the start of a new sequence
            true? continue loop
            false? goto next iteration
        
        set count = 1
        calculate next_val (i + 1)
        
        while loop: (next_val in hashset)
            increase count by 1 
            nextval += 1            // update next_val
        
        calculate:
            max_count = MAX(count, max_count)   // written as if check
    
    return:
        max_count
        
Visualization of sequences:
    nums:       [100,4,200,1,3,2]
    hashset:    [4,100,200,2,3,1]   // hypothetical
    
    sequences:  [1,2,3,4]
                [100]
                [200]
    
Visualization of algorithm:
    traversing hashset:
    hashset:    [4,100,200,2,3,1]   // hypothetical
        4   verdict: NOT VALID has a left neighbor present in hash (4-1 = 3)
        100 verdict: VALID has no left neighbor present in hash (100-1 = 99)
            START OF SEQUENCE
                count:      1
                101 present ? FALSE
                count stay: 1 (1 == 1 no update max_count)
                max_count:  1
        200 verdict: VALID has no left neighbor present in hash (200-1 = 199)
            START OF SEQUENCE
                count:      1
                201 present ? FALSE
                count stay: 1 (1 == 1 no update max_count)
                max_count:  1 
        2   verdict: NOT VALID has a left neighbor present in hash (2-1 = 1)
        3   verdict: NOT VALID has a left neighbor present in hash (3-1 = 2)
        1   verdict: VALID has no left neighbor present in hash (1-1 = 0)
            START OF SEQUENCE
                count: 1
                2 present   ? TRUE
                count:      2
                3 present   ? TRUE
                count:      3
                4 present   ? TRUE
                count:      4
                5 present   ? FALSE
                count stay: 4 (4 greater update max_count)
                max_count:  4
    return max_count
'''

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        
        hashset = set()

        for i in nums:
            hashset.add(i)

        count = 0
        next_val = 0
        max_count = 0
        
        for i in hashset:   # iterate through hashset Python syntax
            # check if has left neighbor -> not a valid sequence
            if i-1 in hashset:
                continue

            count = 1
            next_val = i + 1
        
            while next_val in hashset:
                count += 1
                next_val += 1

            if count > max_count:
                max_count = count

        return max_count
