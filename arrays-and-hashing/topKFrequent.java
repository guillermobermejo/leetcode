/**
Programmer: Guillermo           M
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 3ms (beats 99.99%)
Memory: 48.3mb (beats 35.43%)

DS: 2 int[]
Advantage:  O(1) Insertion
            O(1) Access
            bucket sort - implicit sorting via index of arr[i] where i == nums[i] value

DS: 1 ArrayList<Integer>[]  (PRIMITIVE ARRAY of arraylist objects)
Advantage:  O(1) Insertion
            O(1) Access
            bucket sort - implicit sorting via index of arr[i] where i == number of occurrences of a value

Approach:
    use bucket sort where indices are number of occurrences in a list
    
    first we store positives and negative numbers in an array in respective buckets set to the size of the constaint
    second we store these occurrences in an overall buckets array - naturally, most frequent is located rightmost
    third we gather the k frequent values traversing this 'sorted' list
    

Algorithm:
    if check: if nums length == 1, return the nums array
    
    declare and initialize:
        1 int[] array           name: bucketPositives   value: new int[]            size: 10000 + 1 (Constraint)
        1 int[] array           name: bucketNegatives   value: new int[]            size: 10000 + 1 (Constraint)
        
    for loop (all numbers in nums):
        traverse the nums array (all n values)
        for every int value i in nums
        if positive (or 0)
            increment the value of the INDEX of bucketPositives
        if negative
            increment the value of the INDEX (* -1) of bucketNegatives
    
    declare and initialize:
        1 ArrayList<Integer>[]  name: buckets           value: new ArrayList<>()    size: nums.length+1
        
            note: the size is 1 + size of nums to store the WORST CASE when all numbers in num are identical
                    i.e., when nums[]: [1,1,1,1,1,1,1] && size: 7
                          buckets[8] to store worst case buckets[7] = 1
    
    for loop(int i = 0; i < bucketsPositives + 1; i++):
        traverse all indeces of BOTH arrays (bucketPositives & bucketNegatives)
        if the value at ANY index >= 0 in bucketPositives
            if buckets[bucketsPositives[i]] == null     // if no list at index (frequency)
                create new list at this index
            buckets[bucketsPositives[i]].add == i       // retrieve the arraylist at this frequency index and add i (actual val)
            
        if the value at ANY index < 0 in bucketNegatives
            if buckets[bucketsNegatives[i]] == null     // if no list at index (frequency)
                create new list at this index
            buckets[bucketsNegatives[i]].add == i       // retrieve the arraylist at this frequency index and add i (actual val)
        
    declare and initialize:
        1 int value             name: c                 value: 0                    notes: keeps track of how close we are to k elements
        1 int[] array           name: sol               value: new int[]            size: k
        
    for loop(int i = buckets.length-1; i >= 0; i--):    // traverse the bucket sorted array in REVERSE (for most frequent)
        if buckets[i] == null -> continue next i        // if buckets[i] == null, there is no frequency value for a value in buckets
        
        for all int n in buckets[i]: (traverse buckets[i] (the ArrayList))
            sol[c++] = n                                // add this n value to sol and increment c (means n is most frequent)
            
            if c == k -> break                          // if this line return true, then we found ALL k most frequent for sol[]
        
        if c == k -> break
        
    return sol
    
Visualization of algorithm:
    indexes: 0 1 2 3 4 5
    nums:   [1,1,1,2,2,3]
    k:      2

    # lines: 268 - 274
    # store number of frequency of all num values in nums with 2 int[]
    
    // real range: 0-10_000 (i.e., size 10001)
    // default values: 0 for all
    indexes:             0  1  2  3  4  5  ...          // index (positive values in nums[])
    bucketsPositives:   [0, 0, 0, 0, 0, 0, ...]         // frequency of number (index)
    
    indexes:             0  1  2  3  4  5  ...          // index (negative values in nums[] (abs) )
    bucketsNegatives:   [0, 0, 0, 0, 0, 0, ...]         // frequency of number (index)
    
    traversing nums array...
    
    i=0      i
    nums:   [1,1,1,2,2,3]
    nums[0]: 1
    
    nums[0] >= 0    increment index bucketPositives[nums[0]]
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 1, 0, 0, 0, 0, ...]
    
    i=1        i
    nums:   [1,1,1,2,2,3]
    nums[1]: 1
    
    nums[1] >= 0    increment index bucketPositives[nums[1]]
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 2, 0, 0, 0, 0, ...]
    
    i=2          i
    nums:   [1,1,1,2,2,3]
    nums[2]: 1
    
    nums[2] >= 0    increment index bucketPositives[nums[2]]
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 0, 0, 0, 0, ...]
    
    i=3            i
    nums:   [1,1,1,2,2,3]
    nums[3]: 2
    
    nums[3] >= 0    increment index bucketPositives[nums[3]]
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 1, 0, 0, 0, ...]
    
    i=4              i
    nums:   [1,1,1,2,2,3]
    nums[4]: 2
    
    nums[4] >= 0    increment index bucketPositives[nums[4]]
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 0, 0, 0, ...]
    
    i=5                i
    nums:   [1,1,1,2,2,3]
    nums[5]: 3
    
    nums[5] >= 0    increment index bucketPositives[nums[5]]
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 1, 0, 0, ...]
    
    # lines 276 - 290
    // traverse the bucketsPositives/bucketsNegative array (i:0 - i: 10000) && store in buckets where index -> frequency
    // indexes:     the values (nums[] values)
    // [values]:    the frequency of value (index)
    
    nums:       [1,1,1,2,2,3]

    indexes:       0    1     2     3     4     5     6
    buckets:    [null, null, null, null, null, null, null]      size: nums.length + 1   -> (7)  // +1 to store worst case
    
    i=0                  i
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 1, 0, 0, ...]     // DO NOTHING ([0] == 0 -> i has no frequency)
    
    i=0                  i
    indexes:             0  1  2  3  4  5  ...
    bucketsNegatives:   [0, 0, 0, 0, 0, 0, ...]     // DO NOTHING ([0] == 0 -> i has no frequency)



    indexes:       0    1     2     3     4     5     6
    buckets:    [null, null, null, null, null, null, null]
    
    i=1                     i
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 1, 0, 0, ...]     // ADD 1 (i) to buckets[3] (buckets[frequency]) ([1] > 0 -> i has frequency)
    
    i=1                     i
    indexes:             0  1  2  3  4  5  ...
    bucketsNegatives:   [0, 0, 0, 0, 0, 0, ...]     // DO NOTHING ([1] == 0 -> no frequency)
    
    
    indexes:       0    1     2     3     4     5     6
    buckets:    [null, null, null, [1], null, null, null]
    
    i=2                        i
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 1, 0, 0, ...]     // ADD 2 (i) to buckets[2] (buckets[frequency]) ([2] > 0 -> i has frequency)
    
    i=2                        i
    indexes:             0  1  2  3  4  5  ...
    bucketsNegatives:   [0, 0, 0, 0, 0, 0, ...]     // DO NOTHING ([2] == 0 -> i has no frequency)
    
    
    indexes:       0    1     2     3     4     5     6
    buckets:    [null, null, [2],  [1], null, null, null]
    
    i=3                           i
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 1, 0, 0, ...]     // ADD 3 (i) to buckets[1] (buckets[frequency]) ([3] > 0 -> i has frequency)
    
    i=3                           i
    indexes:             0  1  2  3  4  5  ...
    bucketsNegatives:   [0, 0, 0, 0, 0, 0, ...]     // DO NOTHING ([3] == 0 -> i has no frequency)
    
    indexes:       0    1     2     3     4     5     6
    buckets:    [null, [3],  [2],  [1], null, null, null]
    
    i=4                              i
    indexes:             0  1  2  3  4  5  ...
    bucketsPositives:   [0, 3, 2, 1, 0, 0, ...]     // DO NOTHING ([4] == 0 -> i has no frequency)
    
    i=4                              i
    indexes:             0  1  2  3  4  5  ...
    bucketsNegatives:   [0, 0, 0, 0, 0, 0, ...]     // DO NOTHING ([4] == 0 -> i has no frequency)
    
    continues until i > 10000
    
    Final sorted frequency list:
    indexes:       0    1     2     3     4     5     6         // index is frequency
    buckets:    [null, [3],  [2],  [1], null, null, null]       // values are lists of values in nums[] with INDEX frequency
    
    
    # lines 292 - 306
    // traverse the sorted buckets array (i:0 - i: buckets.length (6)) && store sol at index c++ (increment after)
    // indexes:     the values (nums[] values)
    // [values]:    the frequency of value (index)
    c: 0
    sol[k]:     [0,0]
    
    i=6                                               i
    indexes:       0    1     2     3     4     5     6  
    buckets:    [null, [3],  [2],  [1], null, null, null]       // buckets[6] == null -> no frequency continue
    
    c: 0
    sol[k]:     [0,0]
    
    i=5                                         i
    indexes:       0    1     2     3     4     5     6  
    buckets:    [null, [3],  [2],  [1], null, null, null]       // buckets[5] == null -> no frequency continue
    
    
    c: 0
    sol[k]:     [0,0]
    
    i=4                                   i
    indexes:       0    1     2     3     4     5     6  
    buckets:    [null, [3],  [2],  [1], null, null, null]       // buckets[4] == null -> no frequency continue
    
    
    c: 0
    sol[k]:     [0,0]
    
    i=3                             i
    indexes:       0    1     2     3     4     5     6  
    buckets:    [null, [3],  [2],  [1], null, null, null]       // buckets[3] != null -> while c != k store all nums in list (store 1)
    
    
    c: 1
    sol[k]:     [1,0]
    
    i=2                       i
    indexes:       0    1     2     3     4     5     6  
    buckets:    [null, [3],  [2],  [1], null, null, null]       // buckets[2] != null -> while c != k store all nums in list (store 2)
    
    c == k
    c: 2
    sol[k]:     [1,2]
    
    return sol[]
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) return nums;
        
        int[] bucketPositives = new int[10000 + 1];
        int[] bucketNegatives = new int[10000 + 1];
        
        for (int i : nums) {
            if (i >= 0) bucketPositives[i]++;
            if (i < 0) bucketNegatives[-i]++;
        }
        
        ArrayList<Integer>[] buckets = new ArrayList[nums.length+1];
        
        for (int i = 0; i < bucketPositives.length; i++) {
            if (bucketPositives[i] > 0) {
                if (buckets[bucketPositives[i]] == null) buckets[bucketPositives[i]] = new ArrayList<Integer>();
                
                buckets[bucketPositives[i]].add(i);
            }
            
            if (bucketNegatives[i] > 0) {
                if (buckets[bucketNegatives[i]] == null) buckets[bucketNegatives[i]] = new ArrayList<Integer>();
                
                buckets[bucketNegatives[i]].add(-i);
            }
        }
        
        int c = 0;
        int[] sol = new int[k];
        
        for (int i = buckets.length-1; i >=0; i--) {
            if (buckets[i] == null) continue;
            
            for (int n : buckets[i]) {
                sol[c++] = n;
                if (c == k) break;
            }
            
            if (c == k) break;
        }
        
        return sol;
    }
}
