/**
Programmer: Guillermo       M
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 26ms (beats 72.15%)
Memory: 67.3mb (beats 10.54%)

DS: HashSet<Integer> 
Advantage:  O(1) Insertion
            O(1) Access
            O(n) algorithm

Approach:
    use a java hashset to store all elements in nums in hashset for quick O(1) search operations
    traverse the hashset using an iterator
    valid elements mean they do not have a left neighbor as they are not the first element in the sequence
    for each valid element keep a count at how many immediate right neighbors it has i.e., [1,2,3,4] = 4

Algorithm:
    if check:
        if nums length == 0 then return 0
        
    declare & initialize:
        1 HashSet<Integer>      name: set       value: new HashSet<>()
    
    for loop:
        for all elements in nums add to set
    
    declare & initialize:
        1 int value         name: count                             notes: the current count variable
        1 int value         name: current_val                       notes: the current value tested
        1 int value         name: next_val                          notes: the next value of a valid value
        1 int value         name: max_count     value: 0            notes: the max count of the longest consecutive
        1 Iterator<Integer> name: setIterator   value: set.iterator notes: the iterator for the declared set
        
    while(setIterator.hasNext):
        current_val = setIterator.next()
        if check:
            if current_val does NOT have a left neighbor it is the start of a new sequence
            true? continue loop
            false? goto next iteration
        
        set count = 1
        calculate next_val (current_val + 1)
        
        while loop(set.contains(next_val++)):   // we increment next_val inside
            increase count by 1
        
        calculate:
            max_count = MAX(count, max_count)
    
    return:
        max_count
        
Visualization of sequences:
    nums:       [100,4,200,1,3,2]
    hashset:    [4,100,200,2,3,1]   // hypothetical
    
    sequences:  [1,2,3,4]
                [100]
                [200]
    
Visualization of algorithm:
    traversing hashset:
    hashset:    [4,100,200,2,3,1]   // hypothetical
        4   verdict: NOT VALID has a left neighbor present in hash (4-1 = 3)
        100 verdict: VALID has no left neighbor present in hash (100-1 = 99)
            START OF SEQUENCE
                count:      1
                101 present ? FALSE
                count stay: 1 (1 == 1 no update max_count)
                max_count:  1
        200 verdict: VALID has no left neighbor present in hash (200-1 = 199)
            START OF SEQUENCE
                count:      1
                201 present ? FALSE
                count stay: 1 (1 == 1 no update max_count)
                max_count:  1 
        2   verdict: NOT VALID has a left neighbor present in hash (2-1 = 1)
        3   verdict: NOT VALID has a left neighbor present in hash (3-1 = 2)
        1   verdict: VALID has no left neighbor present in hash (1-1 = 0)
            START OF SEQUENCE
                count: 1
                2 present   ? TRUE
                count:      2
                3 present   ? TRUE
                count:      3
                4 present   ? TRUE
                count:      4
                5 present   ? FALSE
                count stay: 4 (4 greater update max_count)
                max_count:  4
    return max_count
            
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i : nums)
            set.add(i);

        int count, current_val, next_val;
        int max_count = 0;

        Iterator<Integer> setIterator = set.iterator();
        
        while (setIterator.hasNext()) {
            // check if has left neighbor -> not a valid sequence
            current_val = setIterator.next();
            if (set.contains(current_val-1)) continue;

            count = 1;
            next_val = current_val + 1;
        
            while (set.contains(next_val++))
                count++;

            max_count = count > max_count ? count : max_count;
        }

        return max_count;
    }
}
