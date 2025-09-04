class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        if len(nums) == 1:
            return nums

        hashmap = dict()    # put counts in a dictionary

        for num in nums:
            hashmap[num] = hashmap.get(num, 0) + 1

        buckets = [[] for _ in range(len(nums)+1)]  # treat counts as indices for the buckets and store keys in buckets[count]

        for item in hashmap.items(): 
            buckets[item[1]].append(item[0])

        sol = []
        index = len(buckets) - 1

        while index >= 0:   
            if len(buckets[index]) != 0:
                for val in buckets[index]:
                    sol.append(val)
                    k -= 1

                    if k == 0:
                        break
                if k == 0:
                    break

            index -= 1


        return sol
