/**
Programmer: Guillermo           E
Language: C++
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 7.1mb (beats 85.32%)

DS: int[]
Advantage:  O(1) retrieval
            O(1) insertion
            storing previous work done

Approach: 
    use dynamic programming to store previous work to avoid spending resources on recalculations
    
    generate the first few steps by hand - up to 6 steps
    analyze and come up with a recurrence relation
    use this recurrence relation to store work in an array
    
Analyze:
    1 step: size:  1    steps: [1]
    2 step: size:  2    steps: [1+1, 2]
    3 step: size:  3    steps: [1+1+1, 2+1, 1+2]
    4 step: size:  5    steps: [1+1+1+1, 2+1+1, 1+2+1, 1+1+2, 2+2]
    5 step: size:  8    steps: [1+1+1+1+1, 2+1+1+1, 1+2+1+1, 1+1+2+1, 1+1+1+2, 2+2+1, 1+2+1, 1+1+2]
    6 step: size: 13    steps: [1+1+1+1+1+1, 2+1+1+1+1, 1+2+1+1+1, 1+1+2+1+1, 1+1+1+2+1, 1+1+1+1+2, 2+2+1+1, 2+1+2+1, 2+1+1+2, 1+2+2+1, 1+2+1+2, 1+1+2+2, 2+2+2]
    
    recurrence relation: arr[i] = arr[i-1] + arr[1-2]
    
    n: 6
    indexes:         0  1
    initial values: [1, 2]
    stored array:   [1, 2, 3, 5, 8, 13]

Algorithm:
    if check: 
        if n less than 3, then return n // covers the case when the stairs are only 1 or 2 case
    
    delcare & initialize:
        1 int[] array       name: arr   value: default    size: n
        arr[0] = 1  // the number of steps possible at 1 step
        arr[1] = 2  // the number of steps possible at 2 step
        
    for loop(int i = 2; i < n; i++):
        traverse the arr from index 2 to n-1 (end)
        calculate the number of steps at the ith position from the last two indexes
        note:
            arr[i] = arr[i-1] + arr[i-2]
        
    return:
        arr[n-1] // the specified step id the last number calculated
        
*/

class Solution {
public:
    int climbStairs(int n) {
        if (n < 3) return n;
        
        int arr[n];
        arr[0] = 1;
        arr[1] = 2;
        
        for(int i = 2; i < n; i++)
            arr[i] = arr[i-1] + arr[i-2];
    
        return arr[n-1];
    }
};
