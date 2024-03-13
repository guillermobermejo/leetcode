/**
Programmer: Guillermo			E
Language: C++
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 4ms (beats 94.69%)
Memory: 14.1mb (beats 25.67%)

DS: unordered_map<int, int> 	(hashmap)
Advantage:  O(1) Search
            O(1) Insertion

DS: vector<int>  size: 2
Advantage:  for return

Approach:
   use unordered_map

   while traversing the vector<int> array
       calculate the remainder (remainder = target - nums[i])
       check if remainder contained in hashmap
           true ? 
               return [key.value, i]
           false ?
               add <nums[i], index> to hashmap

    return result


Algorithm:
    declare vector<int> and unordered_map<int,int>: 
        1 int[]                           size: 2             name: result
        1 unordered_map<int, int> 	      					  name: hashmap
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
    hashmap.at(-3) = 2

    nums[0]: hashmap.at(-3)   	// 2
    nums[1]: i                  // 4
*/

#include <unordered_map>
#include <iostream>
using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
	vector<int> sol(2);
        unordered_map<int, int> hashmap;
        int remainder = 0;
		
        // store pairs where key = value at index, value = index
        for (int i = 0; i < nums.size(); i++) {
            remainder = target - nums[i];
            
            if (hashmap.find(remainder) != hashmap.end()) {	// possible solution
                if (i == hashmap.at(remainder))				// not a solution (same index)
                    continue;
                
                sol[0] = hashmap.at(remainder);
                sol[1] = i;
                
                return sol;
            }
            
            hashmap.insert({nums[i], i});
        }
        
        return sol;
    }
};
