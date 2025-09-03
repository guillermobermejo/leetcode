class Solution:
    def searchRotated(self, nums: List[int], target: int) -> int:
        if len(nums) == 1:
            return 0 if nums[0] == target else -1
        
        left = 0
        right = len(nums)-1

        while left <= right:
            middle = int((right + left) / 2)

            if nums[middle] == target:
                return middle

            if nums[left] <= nums[middle]: # if the left side is ascending / not rotated
                if target >= nums[left] and target <= nums[middle]: # if the target is within the left range i.e, if less than middle
                    right = middle - 1
                else:
                    left = middle + 1
            else:
                if target >= nums[middle] and target <= nums[right]: # if the target is within the right range i.e., greater than middle
                    left = middle + 1
                else:
                    right = middle - 1

            
        return -1
