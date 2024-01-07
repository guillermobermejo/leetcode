/**
Programmer: Guillermo           *cracked*
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 1ms (beats 99.92%)
Memory: 61.63 (beats 29.08%)

DS: none

Approach: 
    taking the prices list as a line graph, we will traverse this graph using sliding window
    y axis prices 
    x axis i positions
    if we dive low (left point -> right point goes down) means prices[i+1] - prices[i] < 0
    if we rise (left point -> right point goes up) means valid profit, prices[i+1] - prices[i] > 0
    traverse position[] in O(n) time sliding two pointers l and r

Algorithm:
    if check:
        prices.length is 1 ? return 0;
    
    declare:
        1 int value     name: max
        1 int value     name: t
        1 int value     name: l
    
    initialize:
        max, t, l = 0
        r = 1

    while loop (r < prices.length):
        if (prices[r] - prices[l]) >= 0 
            valid solution
            t = prices[r] - prices[l]
        else -> increase l
        if (t > max) then max=t
    
    return:
        max

Visualization of prices[] as graph:
    index:       0 1 2 3 4 5
    prices:     [7,1,5,3,6,4]   prices.length: 6
    y-axis: prices  x-axis: indexes (days)

    7|  o                       1st: l=0 r=1 -> prices[1] (1) - prices[0] (7)   (-) 6   effect: l++  max=0
    6|                  o       2nd: l=1 r=1 -> prices[1] (1) - prices[1] (1)   ( ) 0   effect: r++  max=0
    5|          o     /   \     3rd: l=1 r=2 -> prices[2] (5) - prices[1] (1)   (+) 4   effect: r++  max=4
    4|    \       \         o   4th: l=1 r=3 -> prices[3] (3) - prices[1] (1)   (+) 2   effect: r++  max=4
    3|        /     o           5th: l=1 r=4 -> prices[4] (6) - prices[1] (1)   (+) 5   effect: r++  max=5
    2|                          6th: l=1 r=5 -> prices[5] (4) - prices[1] (1)   (+) 3   effect: r++  max=5
    1|      o                   7th: l=3 r=6 -> ! r < prices.length (6)
    0|                                          loop ends
        _ _ _ _ _ _ _ _ _ _ _   
        0   1   2   3   4   5  
*/

class Solution {
    public int maxProfit(int[] prices) {
        int max, t, l;
        max = l = t = 0;
        int r = 1;
        while (r < prices.length) {
            if (prices[r] - prices[l] >= 0) t = prices[r++] - prices[l];
            else l++;
            if (t > max) max=t;
        }
        return max;
    }
}
