/**
Programmer: Guillermo           E       *CRACKED*
Language: Java
Time Complexity: O(log n)
Space Complexity: O(1)
Runtime: 5ms (beats 99.27%)
Memory: 45.68 (beats 48.22%)

DS: int[]

Advantage: O(1) Insertion
           O(1) Access
           O(1) Deletion
           best memory

Approach: 
    use sliding window approach
    use primitive int[] to keep track of letter frequency

    use 2 pointers start/end 
    move end gradually keeping track of all letter frequency on the map (map[s.charAt(end)-'A'])
    after each end increment, check if the current string from start(inclusive) to end(exclusive) is valid
    validity means length - mostfrequent <= k 
        this means that the string needs at least left value substitutes to be as long as length
    if not valid
        adjust the start index rightward, decrementing each from map
    
Algorithm:
    declare & initialize:
        1 in value      name: start         value: 0        notes: left index of sliding window
        1 in value      name: end           value: 0        notes: right index of sliding window
        1 in value      name: max           value: 0        notes: the max repeating occurrences
        1 in value      name: length        value: 0        notes: length the length of the substring between start - end
        1 in value      name: maxFrequency  value: 0        notes: the number of the largest frequency for any given char
        1 int[] array   name: map           value:'Z'-'A'+1 notes: the ascii map for the frequency of a given char

    while loop(end < s.length):
        increment frequency of character at index end to map
        increment end
        calculate maxFrequency based on the new value at map[s.charAt(end)-'A']
        calculate length (end+1 - start+1)

        if check:
            if valid
                update max based on length
            not valid
                while loop:
                    remove frequency of character at start index
                    increase start index
                    calculate new maxFrequency
                    for loop:
                        traverse map to obtain new maxFrequency
                    
                    calculate new length (end+1 - start+1)
                    if check:
                        if valid    
                            calculate max based on length 
                            break   
    return max

Visualization of Algorithm (after traversing ):
    valid check = length - frequency <= k
    k=1
    nums: [A,A,B,A,B,B,A]     

    [A,A,B,A,B,B,A]     
    -->
    length(1) - freq(1) 0 <= 1      // freq: A
    VALID  max = 1

    [A,A,B,A,B,B,A]     
    ---->
    length(2) - freq(2) 0 <= 1      // freq: A
    VALID  max = 2

    [A,A,B,A,B,B,A]     
    ----->
    length(3) - freq(2) 1 <= 1      // freq: A
    VALID  max = 2

    [A,A,B,A,B,B,A]     
    ------->
    length(4) - freq(3) 1 <= 1      // freq: A
    VALID  max = 4

    [A,A,B,A,B,B,A]     k=1
    --------->
    length(5) - freq(3) 2 <= 1      // freq: A
    NOT VALID max = 4

    [A,A,B,A,B,B,A]     k=1
       ------>
    length(4) - freq(2) 2 <= 1      // freq: A || B
    NOT VALID max = 4

    [A,A,B,A,B,B,A]     k=1
         ---->
    length(3) - freq(2) 1 <= 1      // freq: B
    VALID max = 4 

    [A,A,B,A,B,B,A]     k=1
         ------>
    length(4) - freq(3) 1 <= 1      // freq: B
    VALID max = 4 

    [A,A,B,A,B,B,A]     k=1
         -------->
    length(5) - freq(3) 2 <= 1      // freq: B
    NOT VALID max = 4 

    [A,A,B,A,B,B,A]     k=1
           ------->
    length(4) - freq(2) 2 <= 1      // freq: A || B
    NOT VALID max = 4 

    [A,A,B,A,B,B,A]     k=1
             ----->
    length(3) - freq(2) 1 <= 1      // freq: B
    NOT VALID max = 4 

    RETURN max
*/

class Solution {
    public int characterReplacement(String s, int k) {
        int start, end, max, length, maxFrequency = k;
        start = end = max = length = maxFrequency = 0;

        int[] map = new int['Z'-'A'+1];

        while (end < s.length()) {
            map[s.charAt(end)-'A'] += 1;
            maxFrequency = map[s.charAt(end)-'A'] > maxFrequency ? map[s.charAt(end)-'A'] : maxFrequency;
            end++;
            length = (end+1) - (start+1);

            if (length - maxFrequency <= k)
                max = length > max ? length : max;
            else {
                while(true) {
                    map[s.charAt(start++)-'A'] -= 1;
                    maxFrequency = 0;
                    for (int i : map)
                        maxFrequency = i > maxFrequency ? i : maxFrequency;

                    length = (end+1) - (start+1);

                    if (length - maxFrequency <= k) {
                        max = length > max ? length : max;
                        break;
                    }
                }
            }
        }

        return max;
    }
}
