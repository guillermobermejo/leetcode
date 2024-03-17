'''
Programmer: Guillermo			E
Language: Python3
Time Complexity: O(n)
Space Complexity: O(n)
Runtime:47ms (beats 97.86%)
Memory: 17.8mb (beats 19.69%)

DS: dict() 	(hashmap)
Advantage:  O(1) Search
            O(1) Insertion

DS: int[]   size: 2
Advantage:  for return

Approach:
   use dictionary

   while traversing the nums array
       calculate the remainder (remainder = target - nums[i])
       check if remainder contained in hashmap
           true ? 
               return [key.value, i]
           false ?
               add <nums[i], index> to hashmap

    return result

Algorithm:
    declare int[] and dict(): 
        1 int[]                           size: 2             name: result
        1 dict()                	      					  name: hashmap
        int remainder
        
    initialize:
        remainder = 0

    for loop:
        traverse the nums array from range i in len(nums)
        calculate remainder (target - nums[i])
        if check:
            the remainder (key) is in hashmap (possible solution)
            check if i (index) == to value of key (key index)
                true ?
                    continue iteration (not valid solution)
            
            store i in result[i]
            store key value (index) in result[0]
            return result

    return:
        result

Possible Scenario:
    index:  0  1  2  3  4
    nums: [-1,-2,-3,-4,-5]
    target: -8 (negative)

    solution (when i = 4):
    i: 4
    nums[i]: -5
    remainder: -8 - (-5) = -3
    hashmap (so far): {-1=0,-2=1,-3=2,-4=3}
    hashmap.at(-3) = 2

    nums[0]: hashmap.at(-3)   	// 2
    nums[1]: i                  // 4
'''

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        result = [0] * 2
        hashmap = dict()
        remainder = 0
        
        # store pairs where key = value at index, value = index
        for i in range(len(nums)):
            remainder = target - nums[i]
            
            if remainder in hashmap:    
                if i == hashmap[remainder]:
                    continue
                
                result[0] = hashmap[remainder]
                result[1] = i
                
                return result
            
            hashmap[nums[i]] = i
        
        return result
