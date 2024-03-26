'''
Programmer: Guillermo           E       *deprecated*        (Array Approach - for awareness)
Language: Python3
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 861ms (beats 5.02%)
Memory: 28.5mb (beats 11.65%)

DS: int[]
Advantage:  O(1) insertion
            O(1) access
            best memory

Approach: 
    after a stock is bought the max of the remaining range is needed
    calculate this max in an array where the ith value is the max from i to length-1
    in a loop check to see if the max from a range is (-) new bought price > max

Algorithm:
    if check:
        len(prices) is 1 ? return 0;
    
    declare & initialize:
        1 int value     name: max_v                                 value: 0
        1 int[] array   name: max_from      size: len(prices)       value: [0 for x in range(len(prices))]
    
    set initial value:
        max_from[length-1] = prices[length-1] // set last index of max_from to last index of prices for loop

    for loop (i in range(len(prices)-2, -1, -1)):
        iterate max_from from index len(prices)-2 to 0 // 
        set max_from[i] to the max of the current ith price value and the current max_from[i+1]
    
    for loop (i in range(len(prices)-1)):
        iterate prices from index 0 to len(prices)-1
        set max_v to the max of max_v and the new current max
        note:
            max_v = max(max_v, max_from[i+1] - prices[i])
    
    return:
        max_v

Visualization of max_from[]:
    prices:     [7,1,5,3,6,4]
    max_from:   [0,0,0,0,0,0] // initial declaration
    max_from:   [0,0,0,0,0,4] // set last val to last val of prices
    max_from:   [0,6,6,6,6,4] // calculate max from ranges from price[i] and max_from[i+1]

Calculating max:
    index:       0 1 2 3 4 5
    prices:     [7,1,5,3,6,4]
    max_from:   [0,6,6,6,6,4]

    max=0       iteration from: i=0 - i=len(prices)-2 (i=4)

                                        sell price        buy price
    when i = 0 -> max = max(max, (max_from[1] (6) - price[0] (7)) (-1)      max=0
    when i = 1 -> max = max(max, (max_from[2] (6) - price[1] (1)) ( 5)      max=5
    when i = 2 -> max = max(max, (max_from[3] (6) - price[2] (5)) ( 1)      max=5
    when i = 3 -> max = max(max, (max_from[4] (6) - price[3] (3)) ( 3)      max=5
    when i = 4 -> max = max(max, (max_from[5] (4) - price[4] (6)) (-2)      max=5
'''

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if len(prices) == 1:
            return 0

        max_v = 0;
        max_from = [0 for x in range(len(prices))]
        max_from[len(prices)-1] = prices[len(prices)-1]
        
        for i in range(len(prices)-2, -1, -1):
            max_from[i] = max(prices[i], max_from[i+1])
            
        for i in range(len(prices)-1):
            max_v = max(max_v, max_from[i+1] - prices[i])


        return max_v
