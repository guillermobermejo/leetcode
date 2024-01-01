/**
Version: 1 (most recent)         E
Programmer: Guillermo
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 2ms (beats 84.68%)
Memory: 45.4mb (beats 6.21%)

DS: HashMap<Integer, Integer>
Advantage:  O(1) Search
            O(1) Insertion

DS: int[]   size: 2
Advantage:  O(1) insert

DS: int[]   (given)
Advantage:  for return

Approach:
   use hashmap

   while traversing the nums[] array
       calculate the remainder (remainder = target - nums[i])
       check if remainder contained in hashmap
           true ? 
               return [key.value, i]
           false ?
               add <nums[i], index> to hashmap

    return result


Algorithm:
    declare int[] and hashmap: 
        1 int[]                           size: 2             name: result
        1 hashmap<integer, integer>       size: nums.length   name: hashmap
        int remainder
        
    initialize:
        remainder = 0

    for loop:
        traverse the nums array from i=0 to i=nums.length-1
        calculate remainder (target - nums[i])
        if the remainder (key) is in hashmap (possible solution)
            check if i (index) == to value of key (key index)
                true ?
                    continue iteration (not valid solution)
            
            store i in result[1]
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
    hashmap.get(-3) = 2

    nums[0]: hashmap.get(-3)    // 2
    nums[1]: i                  // 4
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> hashmap = new HashMap<>(nums.length);
        int remainder = 0;

        // store pairs where key = value at index, value = index
        for (int i = 0; i < nums.length; i++) {
            remainder = target - nums[i];

            if (hashmap.containsKey(remainder)) {   // possible solution
                if (i == hashmap.get(remainder))    // not a solution (same index)
                    continue;

                result[0] = hashmap.get(remainder);
                result[1] = i;

                return result;
            }

            hashmap.put(nums[i], i);
        }
        
        return result;
    }
}
