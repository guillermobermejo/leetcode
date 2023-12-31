class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashmap = new HashSet<>();
		
		// while storing all numbers in a hashset, check if it is already contained
        for (int i = 0; i < nums.length; i++) {
			
			// O(1)
            if (hashmap.contains(nums[i]))
                return true;

			// O(1)
            hashmap.add(nums[i]);
        }

        return false;
    }
}