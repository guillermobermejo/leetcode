'''
Programmer: Guillermo       M   *CRACKED*
Language: Python3
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 41ms (beats 100%)
Memory: 16.9mb (beats 86.19%)

DS: none

Approach: 
    traverse the nums[] array from left to right calculating the product of each sub array 
        '0' means end of a sub array / suffix set to 1
    
    traverse the nums[] array from right to left calculating the product of each sub array
        '0' means end of a sub array / suffix set to 1
        
    calculate the max product as each non '0' is encountered
    
    return maxProduct

Algorithm:
    if check:
        if nums[] length == 1
            true ? return nums[0]
            
    declare & initialize:
        1 int value     name: maxProduct    value: 0    notes: the max product of a sub array
        1 int value     name: product       value: 1    notes: the 'suffix' of a sub array for multiplying
                                                    ------->
    for loop: (i in range(len(nums)))               [., ., ., .,]
        if check:
            if the current number is a '0' (end of array)
                true ? 
                    product = 1
                    continue loop iteration
            
        procuct *= nums[i]  // multiply current number to the product
        
        if check:
            if product > maxProduct
                true ? 
                    update maxProduct to product
                    
                                                        <--------
    for loop: (i in range(len(nums)))               [., ., ., .,]
        if check:                                   nums[len(nums)-i-1]**************
            if the current number is a '0' (end of array)
                true ? 
                    product = 1
                    continue loop iteration
            
        procuct *= nums[i]  // multiply current number to the product
        
        if check:
            if product > maxProduct
                true ? 
                    update maxProduct to product
        
        return:
            maxProduct

Visualization of algorithm:
    nums[]: [-3,-7,5,-10,10,9]
    
    first for loop (left to right):
    
    ------->
    [-3,-7,5,-10,10,9]
    
        
        [-3,-7,5,-10,10,9]
        -->
        
        maxProduct: 0
        product: 1
        
        product: 1 * -3 = -3
        maxProduct: -3 > 0 ? = 0
        
        
        [-3,-7,5,-10,10,9]
        ----->
        
        maxProduct: 0
        product: -3
        
        product: -3 * -7 = 21
        maxProduct: 21 > 0 ? = 21
        
        
        
        [-3,-7,5,-10,10,9]
        ------->
        
        maxProduct: 0
        product: 21
        
        product: 21 * 5 = 105
        maxProduct: 105 > 21 ? = 105
        
        
        
        [-3,-7,5,-10,10,9]
        ----------->
        
        maxProduct: 0
        product: 105
        
        product: 105 * -10 = -1050
        maxProduct: -1050 > 105 ? = 105
        
        
        
        [-3,-7,5,-10,10,9]
        -------------->
        
        maxProduct: 0
        product: -1050
        
        product: -1050 * 10 = -10500
        maxProduct: -10500 > 105 ? = 105
        
        
        
        [-3,-7,5,-10,10,9]
        ---------------->
        
        maxProduct: 0
        product: -10500
        
        product: -10500 * 9 = -94500
        maxProduct: -10500 > 105 ? = 105
        
        
    second for loop (left to right):
    
              <-------
    [-3,-7,5,-10,10,9]
    
        
        [-3,-7,5,-10,10,9]
                        <-
        
        maxProduct: 105
        product: 1
        
        product: 1 * 9 = 9
        maxProduct: 9 > 105 ? = 105
        
        
        [-3,-7,5,-10,10,9]
                     <----
        
        maxProduct: 105
        product: 9
        
        product: 9 * 10 = 90
        maxProduct: 90 > 105 ? = 105
        
        
        
        [-3,-7,5,-10,10,9]
                 <--------
        
        maxProduct: 105
        product: 90
        
        product: 90 * -10 = -900
        maxProduct: -900 > 105 ? = 105
        
        
        
        [-3,-7,5,-10,10,9]
              <-----------
        
        maxProduct: 0
        product: -900
        
        product: -900 * 5 = -4500
        maxProduct: -4500 > 105 ? = 105
        
        
        
        [-3,-7,5,-10,10,9]
            <-------------
        
        maxProduct: 0
        product: -1050
        
        product: -4500 * -7 = 31500
        maxProduct: 31500 > 105 ? = 31500
        
        
        
        [-3,-7,5,-10,10,9]
        <-----------------
        
        maxProduct: 31500
        product: 31500
        
        product: 31500 * -3 = -94500
        maxProduct: -94500 > 31500 ? = 31500
        
        
    return  31500 (maxProduct)
'''

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return nums[0]
        
        maxProduct = 0
        product = 1
        
        for i in range(len(nums)):
            if nums[i] == 0:
                product = 1
                continue
                
            product *= nums[i]
            if product > maxProduct:
                maxProduct = product
            
        product = 1
        
        for i in range(len(nums)):
            j = len(nums)-i-1        # adjust value j to reverse index (1 -> size-2)
            if nums[j] == 0:
                product = 1
                continue
            
            product *= nums[j]
            if product > maxProduct:
                maxProduct = product
        
        return maxProduct
 
