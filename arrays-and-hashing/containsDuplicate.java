/**
Programmer: Guillermo            E
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 10ms (beats 85.11%)
Memory: 57.8mb (beats 21.49%)

DS: HashSet<Integer>
Advantage:  O(1) Search
            O(1) Insertion

Algorithm:
    While traversing nums[]
        at each index check if value is in hashset
        true ?
            return true
        false ?
            add to hashset
    return false
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
