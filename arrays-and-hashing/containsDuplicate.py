'''
Programmer: Guillermo           E
Language: Python3
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 411ms (beats 80.69%)
Memory: 32.1mb (beats 32.15%)

DS: set
Advantage:  O(1) Search
            O(1) Insertion

Approach:
    use hashset

    while traversing nums[] array from i=0 to len(nums)-1
    check if contained in hashset (O(1))

Algorithm:
    declare:
        1 set               name: hashset        value: set()

    for loop:
        traverse nums[] array from i=0 to i=len(nums)-1 
        check if nums[i] is contained in hashset
        true ?
            return true
        false ?
            add to hashset
            
    return:
        false
'''

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        hashset = set()
        
        for i in range(len(nums)):
            # O(1)
            if nums[i] in hashset:
                return True
            # O(1)    
            hashset.add(nums[i])
        
        return False
