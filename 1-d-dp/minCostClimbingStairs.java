/**
Programmer: Guillermo           E
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 43mb (beats 64.67%)

DS: int[]
Advantage:  O(1) retrieval
            O(1) insertion
            storing previous work done

Approach: 
    use dynamic programming to store previous work to avoid spending resources on recalculations
    
    analyze and come up with a recurrence relation
    use this recurrence relation to store work in an array
    
Analyze:
    indexes:     0   1   2   3   4   5   6   7   8   9
    test case: [  1,100,  1,  1,  1,100,  1,  1,100,  1]
    output:6
    
    goal: 
        store the MINIMUM cost to reach each step after the initial first two
        useful testcase to visualize recurrence relation: 
                            [10,15,20]
            explanation:    here the output is 15 but it is also the MIN of either 
                                the second to last step 15 
                                OR
                                the third to last step plus the last step (10+20=30)
                                MIN(15, 30) is 15
    
    recurrence relation: 
        arr[i] = cost[i] + MIN(arr[i-1], arr[i-2])
    
    indexes:     0   1   2   3   4   5   6   7   8   9
    cost:      [  1,100,  1,  1,  1,100,  1,  1,100,  1]
    
    indexes:     0   1   2   3   4   5   6   7   8   9
    arr:       [  1,100,  2,  3,  3,103,  4,  5,104,  6]    // min cost of each step array
    
    return:
        the MIN of the last two elements in the new arr min cost 
        that is either we pay either the second to last step to skip the last and step to FINAL
        or we are at the last step and pay that price to get to the final step

Algorithm:
    declare & initialize:
        1 int[] array       name: arr       value: new int[]    size: nums.length
        arr[0] = cost[0]
        arr[1] = cost[1]
        
    for loop(int i=2; i<arr.length-1; i++):
        iterate the cost array from index 2 to arr.length-1 (end)       // covers the case when the array is size two 
        set the element at the ith position to the min cost to get to this step + its own cost (the recurrence relation)
        note:
            arr[i] = cost[i] + Math.min(arr[i-1], arr[i-2])
            
    return:
        the MIN of the last two steps (the min steps needed to get to final step)
        
        
*/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] arr = new int[cost.length];
        arr[0] = cost[0];
        arr[1] = cost[1];
        
        for (int i = 2; i < cost.length; i++)
            arr[i] = cost[i] + Math.min(arr[i-1], arr[i-2]);
           
        return Math.min(arr[cost.length-1], arr[cost.length-2]);
    }
}
