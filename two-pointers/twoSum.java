/**
Programmer: Guillermo       M
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 2ms (beats 89.86%)
Memory: 47mb (beats 37.88%)

DS: none

Approach:
    use 2 pointer approach to test whether two integers add to the target
    
    keep a left and right pointer l & r to keep track of the numbers to be tested
    add the numbers at both pointers
    if it is greater than target, decrement r (NEED SMALLER NUMBER)
    if it is less than the target, increment l (NEED BIGGER NUMBER)    
    
Algorithm:
    declare & initialize:
        1 int value         name: l     value: 0                           notes: the left index
        1 int value         name: r     value: numbers.length-1            notes: the right index
        1 int[] array       name: sol   value: new int[]    size: 2        notes: the int[] returned
        
    while loop(l < r):
        declare & initialize:
            1 int value     name: sum   value: numbers[l] + numbers[r]     notes: the sum of the pointers
        
        if check:
            if the sum is == target
                true ?
                    add to sol and RETURN
        
        if check:
            if sum > target 
                true ? r--      // NEED SMALLER NUMBER
        else:
            l++                 // NEED BIGGER NUMBER
        
    return:
        sol

Visualization:
    numbers: [2,7,11,15]
    target:   9
    
    l = 0
    r = 3 (numbers.length-1)
    
    1st iteration:
        l = 0
        r = 3
                   l        r
        index:     0  1  2  3
        numbers: [ 2, 7,11,15]
        sum = 2 + 15 = 17 (numbers[0] + numbers[3])
        
        17 (sum) > 9 (target)
            r--
    
    2nd iteration:
        l = 0
        r = 2
                   l     r
        index:     0  1  2  3
        numbers: [ 2, 7,11,15]
        sum = 2 + 11 = 13 (numbers[0] + numbers[2])
        
        13 (sum) > 9 (target)
            r--
            
    Final iteration:
        l = 0
        r = 1
                   l  r
        index:     0  1  2  3
        numbers: [ 2, 7,11,15]
        sum = 2 + 7 = 9 (numbers[0] + numbers[1])
        
        9 (sum) == 9 (target)
            return sol[l+1, r+1] // [1,2]
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length-1;
        int[] sol = new int[2];
        
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            
            if (sum == target) {
                sol[0] = l+1;
                sol[1] = r+1;
                return sol;
            }
            
            if (sum > target) r--;
            else l++;
        }
        
        return sol;
    }
}
