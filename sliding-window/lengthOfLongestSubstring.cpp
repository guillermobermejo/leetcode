/**
Programmer: Guillermo       M   *Cracked c++ lets goooo*	
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 9.2mb (beats 90.17%)

DS: int[]   as an ascii_map
Advantage:  O(1) ascii encountered search
            O(1) insertion

Approach:
    use an int[] array as an ascii_map to keep track of ascii characters encountered
        range should be '~' (127) - ' ' (32) for all lowercase & uppercase space and special characters
    
    use two pointer approach to solve longest substring without repeating characters
    pointers will be 'l' and 'r' where left pointer will only be moved when char at right pointer is a duplicate
        'l' will be moved up removing every character occurance (s['l']) from the ascii map (ascii_map[s['l']-' ']--)
        this happens until we encounter the duplicate character at s['r'] (we remove this last occurence to add our 'r')
        
    encountering duplicates
        once a duplicate is encountered (represented as a 1 in ascii_map[ascii of character - ' '])
        test for currentMax (r-l) which represents current length, if it is larger than our max and set if true
    
    once finished
        cover the case when there are no duplicates with a simple currentmax check at the end of the loop

Note:
    ascii values
    'del' -> 127 // ending of what we care about but never triggered (126 ascii is char '~' but del is never triggered)
    ' '   -> 32  // beninging of what we care about
	size: 95 (0-94 indexed for holding ' ' - '~')
    
Visualization ascii_map:
    index        0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
    ascii_map: [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    ascii for: ' '  !  "  #  $  %  &  '  (  )  *  +  ,  -  .  /  0  1  2  3  4  5  6  7  8  9  :  ;  <  =  >  ? 
    
    index:      32 33 34 45 46 47 48 49 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63
    ascii_map:   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    ascii for:   @  A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z  [  \  ]  ^  _ 
    
    index:      64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 
    ascii_map:   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
    ascii for:   `  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z  {  |  }  ~
    
    register ascii structure:
        ascii_map[char in s - ' ']++
                  <ascii>   - 32
                  
    registering 'a':
        ascii_map[ a - ' ']++
                  97 - 32
        ==
        ascii_map[65]++
        
    registering ' ': (space)
        ascii_map[' ' - ' ']++
                  32  - 32
        ==
        ascii_map[0]++
    
Algorithm:
    if check:
        if length of s is 0
            return 0 for no length
    
    declare & initialize:
        1 int[] array       name: ascii_map     value: {0}  size: 95    notes: the ascii_map for tracking
        1 int value         name: l             value: 0                notes: left pointer
        1 int value         name: r             value: 1                notes: right pointer
        1 int value         name: max           value: 1                notes: overall max length of sub
        1 int value         name: currentMax    value: 1                notes: current max length of sub
        
    set value:
        set first character in s in ascii_map
        ascii_map[s[l]-' ']++;
        
    for loop: (r; r < s.length(); r++)
        if check:
            if the current ascii tested at index r of string s is already in substring
                max check:
                    calculate current length of substring (currentMax) which is r-l
                    if currentMax is greater than max adjust max
                    
                while loop: (ascii_map[s[r-' ']] != 0)  // while the reapeating is not deleted
                    ascii_map[s[l++]-' ']--             // delete ascii at l pointer and increment
                    
        increment ascii at r:
            register the current ascii at index r of string s in ascii_map 
            ascii_map[s[r]-' ']++
    
    if check:
        if the current ascii tested at index r of string s is already in substring
            max check:
                calculate current length of substring (currentMax) which is r-l
    
    return:
        // covers the case where there are no duplicates and the if check in the for loop not fired
        return the greater of the current length of the substring and registered max length
        return currentMax > max ? currentMax : max;
*/

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if (s.length() == 0) return 0;
        
        int ascii_map[127-32] = {0};            // set default value of c++ int[] to 0
        int l = 0;
        int r, max, currentMax;
        r = max = currentMax = 1;
        
        ascii_map[s[l]-' ']++;
        
        for (r; r < s.length(); r++) {          // access string in c++ using str[?]
            if (ascii_map[s[r]-' '] != 0) {
                currentMax = r-l;
                if (max < currentMax) max = currentMax;
                
                while (ascii_map[s[r]-' '] != 0)
                    ascii_map[s[l++]-' ']--;
            }
            ascii_map[s[r]-' ']++;
        }
        
        currentMax = r-l;
        return currentMax > max ? currentMax : max;
    }
};
