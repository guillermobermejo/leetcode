/**
Programmer: Guillermo       E
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 7ms (beats 98.46%)
Memory: 60.7mb (beats 19.71%)

DS: HashSet<Integer>
Advantage:  O(1) Search
            O(1) Insertion

Approach:
    use hashset

    advanced for loop where int i is nums[] val
    check if add() function returns false (contained in hashset (O(1)))
    
    true ? return true
    
    outside loop ? return false

Algorithm:
    declare:
        1 hashset<integer>      name: hashset    value: new HashSet<Integer>

    for loop (int i : nums):
        for every i in nums
        TRY to add i in hashset
        returns false ? RETURN TRUE (contained in hashset)
            
    return:
        false
*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashset = new HashSet<>();
        for (int i : nums)
            if (!hashset.add(i)) return true;
            
        return false;
    }
}
