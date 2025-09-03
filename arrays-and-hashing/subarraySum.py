class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        hashmap = {0:1}
        prefix = 0
        count = 0

        for num in nums:
            # calc the sum up until now
            prefix += num

            # does the number that would make sum add to k exist in the hashmap?
            if prefix - k in hashmap:
                # add to the count the number of ways that number can be summed
                count += hashmap[prefix - k]
            
            # increment the number of ways we can get to sum
            hashmap[prefix] = hashmap.get(prefix, 0) + 1

        return count
