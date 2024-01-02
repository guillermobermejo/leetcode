/**
Programmer: CRACKED person *sadge*
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 3ms (beats 83.79%)
Memory: 42.99mb (beats 71.33%)

DS: int[]
Advantage:  O(1) Insertion
            O(1) Access

Approach:
    use ascii value manipulation
    use primitive int[] array of size 26
    use 3 for loops
    first increments values in int[]
    second decrements values in int[]
    last loop checks if all values are 0s meaning same set of ascii values

Notes:
    advanced for loop syntax:
    for (<type> <name> : <primitive array of same type>)
    for (char c : s.toCharArray())

Algorithm:
    if check:
        if lengths are not equal, not valid anagrams

    declare:
        1 int[] array       size: 26    name: ascii_map
    
    for loop:
        for every character value in s
        increment value in ascii_map[]
        note:
            ascii_map[c - 'a']++;
            // when c == 'a', 97 - 97 = 0 -> ascii_map[0]++
    
    for loop:
        for every character value in t
        increment value in ascii_map[]
        note:
            ascii_map[c - 'a']--;
            // when c == 'd', 100 - 97 = 3 -> ascii_map[3]--

    for loop:
        for int value in ascii_map[] array
        if i != 0
            return false
        
    return:
        true
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;

        int ascii_map[] = new int[26];

        for(char c : s.toCharArray())
            ascii_map[c-'a']++;

        for(char c : t.toCharArray())
            ascii_map[c-'a']--;

        for(int i : ascii_map) {
            if (i != 0)
                return false;
        }

        return true;
    }
}
