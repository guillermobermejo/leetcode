/**
Programmer: Guillermo           E
Language: C++
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 72ms (beats 97.72%)
Memory: 73.2mb (beats 45.84%)

DS: unordered_set<int>
Advantage:  O(1) Search
            O(1) Insertion

Approach:
    use hashset

    while traversing nums[] array from i=0 to i=nums.size()-1
    check if contained in hashset (O(1))

Algorithm:
    declare:
        1 unordered_set<int>	name: hashset        value:

    for loop:
        traverse nums[] array from i=0 to i=nums.size()-1 
        check if nums[i] is contained in hashset
        true ?
            return true
        false ?
            add to hashset
            
    return:
        false
*/

class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> hashset;

        for (int i = 0; i < nums.size(); i++) {
            // O(1)
            if (hashset.find(nums[i]) != hashset.end())
                return true;
            // O(1)
            hashset.insert(nums[i]);
        }
        return false;
    }
};
