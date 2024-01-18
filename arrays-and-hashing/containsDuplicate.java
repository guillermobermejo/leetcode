/**
Programmer: Guillermo       E
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 10ms (beats 85.11%)
Memory: 57.8mb (beats 21.49%)

DS: HashSet<Integer>
Advantage:  O(1) Search
            O(1) Insertion

Approach:
    use hashset

    while traversing nums[] array from i=0 to i=nums.length-1
    check if contained in hashset (O(1))

Algorithm:
    declare:
        1 hashset<integer>     name: hashset        value: new HashSet<Integer>

    for loop:
        traverse nums[] array from i=0 to i=nums.length-1 
        check if nums[i] is contained in hashset
        true ?
            return true
        false ?
            add to hashset
            
    return:
        false
*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashset = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // O(1)
            if (hashset.contains(nums[i]))
                return true;
            // O(1)
            hashset.add(nums[i]);
        }
        return false;
    }
}
