class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        if len(nums) == 2:
            return [nums[1], nums[0]]

        sol = [1] * len(nums)
        sol[-1] = nums[-1]

        for i in range(len(nums)-2, -1, -1):
            sol[i] = nums[i] * sol[i+1]

        first = sol[1]
        previous_product = nums[0]

        for i in range(1, len(sol)):
            if i == len(sol)-1:
                sol[i] = previous_product
                break
            else:
                sol[i] = sol[i+1] * previous_product
                previous_product *= nums[i]

        sol[0] = first

        return sol
