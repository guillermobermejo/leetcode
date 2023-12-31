/**
Version: 2 (when I was cracked)
Programmer: Guillermo
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 5ms (beats 57.67%)
Memory: 42.8mb (beats 99.86%)

DS: HashMap<Integer, Integer>
Advantage:  O(1) Search
            O(1) Insertion

DS: int[]
Advantage:  for return

Algorithm:
    will amend
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] sol = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        // Populate the hash map with every occurrence in nums[] and corresponding count
        for (int currVal : nums) {
            map.merge(currVal, 1, (oldValue, newValue) -> oldValue + newValue);
        }

        int currNum = 0;
        int leftOver = 0;

        for (int i = 0; i < nums.length; i++) {
            currNum = nums[i];

            if (map.get(currNum) == 1)
                map.remove(currNum);
            else
                map.merge(currNum, 1, (oldValue, newValue) -> oldValue - newValue);

            leftOver = target - currNum;

            if (map.containsKey(leftOver)) {
                sol[0] = i;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == leftOver) {
                        sol[1] = j;
                        return sol;
                    }
                }
            }
        }

        return sol;
    }
}
