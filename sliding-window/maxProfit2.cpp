/**
Programmer: Guillermo           E       *cracked*       (Array Approach)
Language: Java
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 75ms (beats 92.04%)
Memory: 96.4mb (beats 5.90%)

DS: int[]
Advantage:  O(1) insertion
            O(1) access
            best memory

Approach: 
    after a stock is bought the max of the remaining range is needed
    calculate this max in an array where the ith value is the max from i to size()-1
    in a loop check to see if the max from a range is (-) new bought price > max

Algorithm:
    if check:
        prices.size() is 1 ? return 0;
    
    declare:
        1 int value     name: max_v                                 value: 0
        1 int[] array   name: max_from      size: prices.size()     value: defaults to 0
    
    set initial value:
        max_from[size()-1] = prices[size()-1] // set last index of max_from to last index of prices for loop

    for loop:
        iterate max_from from index prices.size()-2 to 1 // 1 for reason of being the first max_from after 1st bought
        set max_from[i] to the max of the current ith prices value and the current max_from[i+1]
    
    for loop:
        iterate prices from index 0 to prices.size()-1
        set max to the max of max and the new current max
        note:
            max_v = max(max_v, max_from[i+1] - prices[i]);

Visualization of max_from[]:
    prices:     [7,1,5,3,6,4]
    max_from:   [0,0,0,0,0,0] // initial declaration
    max_from:   [0,0,0,0,0,4] // set last val to last val of prices
    max_from:   [0,6,6,6,6,4] // calculate max from ranges from prices[i] and max_from[i+1]

Calculating max:
    index:       0 1 2 3 4 5
    prices:     [7,1,5,3,6,4]
    max_from:   [0,6,6,6,6,4]

    max=0       iteration from: i=0 - i=prices.size()-2 (i=4)

                                        sell price        buy price
    when i = 0 -> max = max(max, (max_from[1] (6) - prices[0] (7)) (-1)             max=0
    when i = 1 -> max = max(max, (max_from[2] (6) - prices[1] (1)) ( 5)             max=5
    when i = 2 -> max = max(max, (max_from[3] (6) - prices[2] (5)) ( 1)             max=5
    when i = 3 -> max = max(max, (max_from[4] (6) - prices[3] (3)) ( 3)             max=5
    when i = 4 -> max = max(max, (max_from[5] (4) - prices[4] (6)) (-2)             max=5
*/

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (prices.size() == 1) return 0;

        int max_v = 0;
        int max_from[prices.size()];
        max_from[prices.size()-1] = prices[prices.size()-1];

        for (int i = prices.size()-2; i > 0; i--)
            max_from[i] = max(prices[i], max_from[i+1]);

        for (int i = 0; i < prices.size()-1; i++) 
            max_v = max(max_v, max_from[i+1] - prices[i]);

        return max_v;
    }
};
