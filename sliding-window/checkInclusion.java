 /**
Programmer: Guillermo       M   *CRACKED*
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 5ms (beats 96.67%)
Memory: 42.1mb (beats 95.22%)

DS: int[]

Advantage:
    O(1) Access
    O(1) Insertion
    - 'ascii map' of s1 (scanned ascii: all s1.length() ascii)
    
DS: int[]

Advantage:
    O(1) Access
    O(1) Insertion
    - 'ascii map' of s2 (scanned ascii: first s1.length() ascii)

Approach: 
    use sliding window approach

    store the ascii counts of s1 in an int[] array size 26
    store the ascii counts of s2 UP TO s1.length()* in an int[] array size 26
    calculate 'matches' variable on the number of same occurences of the 26 ascii
    
    set l = 0 (beginning of sliding window)
    set r = s1.length() (end of sliding window)
    
*    NOTE: window size will always be 1 + s1.length() because we need to add a new ascii (the additional index)
            but true window tested is s1.length() itself
            i.e, ab         s1.length(): 2
                 eidbaoo    window size: 3
                 ---        del: e  add: d
                            actual substring: id
    
    for loop - start from position s1.length() (1 off the end) to traverse s2 (up to s2.length() - 1)
    i.e., s1: ab
          s2: eidbaooo
                ->
          
        if at any time (beginning of loop) matches is 26, the current substring in the current window is 26 VALID
        if not move window foward one (i.e. eidbaoo) 
                                             --
        add the right most to s2_map (increment ascii)
        delete the ascii not covered anymore in s2_map (decrement ascii)
        
        increment l and r
    
    if not then return false
        
Algorithm:
    if check:
        if s1.length() > s2.length()
            true ? RETURN FALSE
    
    declare & initialize:
        1 int[] array   name: s1_map    size: 26    notes: the ascii map of s1 (scanned ascii of all s1.length() of s1)
        1 int[] array   name: s2_map    size: 26    notes: the ascii map of s2 (scanned ascii of first s1.length() of s2)
        
        1 int value     name: matches   value: 0    notes: the number of matches between s1 and s1.length() window of s2
        
    for loop (int c : s1.toCharArray()):            // the entire length of s1 of s1 is scanned
        increment index of ascii value-'a' in s1_map
        i.e., s1_map[c-'a']++
    
    for loop (int i = 0; i < s1_map.length; i++):   // the first length of s1 of s2 is scanned
        increment index of ascii value-'a' in s2_map
        i.e., s2_map[s2.charAt(i)-'a']++
        
    for loop (int i = 0; i < s1.length; i++):
        check if the ascii value the index represents matches for both s1_map and s2_map
        i.e., s1_map[i] == s2_map[i]
    
    declare & initialize:
        1 int value     name: l         value: 0    notes: the left index of the sliding window (delete part)
        1 int value     name: r         value: 0    notes: the right index of the sliding window (add part)
        
    while loop (r < s2.length()):
        if check: 
            if matches == 26
                true ? RETURN TRUE  // the current window (l to r-1) has exact ascii values IS valid permutation
        
        declare & initialize: 
            1 int value     name: del_char      value: s2.charAt(l)-'a'     notes: the index of the ascii value deleted
            1 int value     name: add_char      value: s2.charAt(r)-'a'     notes: the index of the ascii value added
        
        if check 1 (when deleting):
            if after deleting the ascii from the map makes us match the ascii count of s1 for this ascii
            i.e., s1_map[del_char] == --s2_map[del_char]
                true ? INCREMENT MATCHES
        
        else if check 1 (when deleting):    // if the case before fails check if we were JUST valid and deleting make us not
            if we were just valid as in s1_map and s2_map HAD the same occurrences
            i.e., s1_map[del_char]-1 == s2_map[del_char]
                true ? DECREMENT MATCHES    // decrement because we matched before
        
*        // if none then matches does not change i.e., handles duplicates


        if check 2 (when adding):
            if after adding the ascii to the map makes us match the ascii count of s1 for this ascii
            i.e., s1_map[add_char] == ++s2_map[add_char]
                true ? INCREMENT MATCHES
        
        else if check 2 (when adding):      // if the case before fails check if we were JUST valid and adding make us not
            if we were just valid as in s1_map and s2_map HAD the same occurrences
            i.e., s1_map[add_char]+1 == s2_map[add_char]
                true ? DECREMENT MATCHES    // decrement because we matched before
        
*        // if none then matches does not change i.e., handles duplicates
        
            
        move window by 1:
            l++
            r++
    
    return:
        matches == 26

Visualization of algorithm:
    s1: ab
    s2: eidbaooo
    
    *SCAN FIRST s1.length() characters of BOTH s1 and s2 in s1_map and s2_map*
    
    represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
    index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
    s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    
    represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
    index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
    s2_map[]:   [0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    
    matches: 22
    
    first loop:
        eidbaooo
        ---
    
        matches != 26
        del_char: 4 (e - 'a')
        add_char: 3 (d - 'a')
        
        deleting 4 from s2_map...
                                     *
            represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
            s2_map[]:   [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            
            if case 1: now matching
            matches: 23 (+1)
            
        adding 3 to s2_map...
                                  *   
            represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
            s2_map[]:   [0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            
            else if case 2: now NOT matching
            matches: 22 (-1)
            
    second loop:
        eidbaooo
         ---
    
        matches != 26
        del_char: 8 (i - 'a')
        add_char: 1 (b - 'a')
        
        deleting 8 from s2_map...
                                                 *
            represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
            s2_map[]:   [0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            
            if case 1: now matching
            matches: 23 (+1)
            
        adding 1 to s2_map...
                            *   
            represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
            s2_map[]:   [0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            
            if case 2: now matching
            matches: 24 (+1)
        
    third loop:
        eidbaooo
          ---
    
        matches != 26
        del_char: 3 (d - 'a')
        add_char: 0 (a - 'a')
        
        deleting 3 from s2_map...
                                  *
            represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
            s2_map[]:   [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            
            if case 1: now matching
            matches: 25 (+1)
            
        adding 0 to s2_map...
                         *   
            represents:  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
            index:       0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
            s2_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            s1_map[]:   [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            
            if case 2: now matching
            matches: 26 (+1)
            
     last loop:
        eidbaooo
           ---
    
        matches == 26
        
        return TRUE     // valid permutation in s2 of s1: ba
*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        int[] s1_map = new int[26];
        int[] s2_map = new int[26];
        int matches = 0;
        
        for (char c : s1.toCharArray())
            s1_map[c-'a']++;
        
        for (int i = 0; i < s1.length(); i++)
            s2_map[s2.charAt(i)-'a']++;
        
        for (int i = 0; i < s1_map.length; i++)
            if (s1_map[i] == s2_map[i]) matches++;
        
        int l = 0;
        int r = s1.length();       
        
        while (r < s2.length()) {
            if (matches == 26) return true;
            
            int del_char = s2.charAt(l)-'a';
            int add_char = s2.charAt(r)-'a';
            
            if (s1_map[del_char] == --s2_map[del_char]) matches += 1;
            else if (s2_map[del_char]+1 == s1_map[del_char]) matches -= 1;
            
            if (s1_map[add_char] == ++s2_map[add_char]) matches += 1;
            else if (s2_map[add_char]-1 == s1_map[add_char]) matches -= 1;
            
            l++;
            r++;
        }
         
        return matches == 26;
    }
}
