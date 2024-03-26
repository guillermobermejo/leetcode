'''
Programmer: Guillermo           E       *cracked*       (2-pointer)
Language: Python3
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 719ms (beats 78.38%)
Memory: 27.3mb (beats 90.61%)

DS: none

Approach: 
    taking the prices list as a line graph, we will traverse this graph using sliding window
    y axis prices 
    x axis i positions
    if we dive low (left point -> right point goes down) means prices[i+1] - prices[i] < 0
    if we rise (left point -> right point goes up) means valid profit, prices[i+1] - prices[i] > 0
    traverse position[] in O(n) time sliding two pointers l and r

Algorithm:
    declare & initialize:
        1 int value     name: max_v     value: 0 
        1 int value     name: t         value: 0
        1 int value     name: l         value: 0
        1 int value     name: r         value: 1

    while loop (r < len(prices)):
        if (prices[r] - prices[l]) >= 0 
            valid solution
            t = prices[r] - prices[l]
        else -> increase l
        if (t > max_v) then max_v=t
    
    return:
        max_v

Visualization of prices[] as graph:
    index:       0 1 2 3 4 5
    prices:     [7,1,5,3,6,4]   len(prices): 6
    y-axis: prices  x-axis: indexes (days)

    7|  o                       1st: l=0 r=1 -> prices[1] (1) - prices[0] (7)   (-) 6   effect: l++  max_v=0
    6|                  o       2nd: l=1 r=1 -> prices[1] (1) - prices[1] (1)   ( ) 0   effect: r++  max_v=0
    5|          o     /   \     3rd: l=1 r=2 -> prices[2] (5) - prices[1] (1)   (+) 4   effect: r++  max_v=4
    4|    \       \         o   4th: l=1 r=3 -> prices[3] (3) - prices[1] (1)   (+) 2   effect: r++  max_v=4
    3|        /     o           5th: l=1 r=4 -> prices[4] (6) - prices[1] (1)   (+) 5   effect: r++  max_v=5
    2|                          6th: l=1 r=5 -> prices[5] (4) - prices[1] (1)   (+) 3   effect: r++  max_v=5
    1|      o                   7th: l=3 r=6 -> ! r < len(prices) (6)
    0|                                          loop ends
        _ _ _ _ _ _ _ _ _ _ _   
        0   1   2   3   4   5  
'''

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        max_v = 0
        l = 0
        t = 0
        r = 1
        
        while (r < len(prices)):
            if prices[r] - prices[l] >= 0:
                t = prices[r] - prices[l]
                r += 1
            else:
                l += 1
            if t > max_v:
                max_v = t
            
        return max_v
