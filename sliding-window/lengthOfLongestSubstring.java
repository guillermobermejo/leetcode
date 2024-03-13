/**
Programmer: Guillermo           *cracked*M
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 2ms (beats 99.52%)
Memory: 42.8mb (beats 92.48%)

DS: int[] 

Advantage: O(1) access
           O(1) insertion
           O(1) deletion
           best memory

Approach: 
    use sliding window approach
    use primitive array as a map for ascii values
    
    traverse the array in a worm-like state
    2 pointers start / end will slide from left to right
    end will slide left adding each ascii value into the int[] until we encounter a repeat
        increment a currentmax counter until repeat is found
    then we slide start left until removing from int[] until we reach the ascii value at end
        decrement a currentmax counter until repeat is found
    delete the repeat and comence loop again

    check a max variable to see if currentmax is larger and handle as needed

Note:
    ascii values
    '~' - 126   // ending of what we care about
    ' ' - 32    // beninging of what we care about
    size: '~' - ' ' + 1
          126 -  32 + 1
          95

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
    declare and initialize:
        1 int value     name: start         value: 0    notes: left pointer
        1 int value     name: end           value: 0    notes: right pointer
        1 int value     name: max           value: 0    notes: the total max value
        1 int value     name: currentMax    value: 0    notes: the current max value as we traverse
        1 int[] arr     name asciimap       value: 95   notes: the ascii map that will hold ascii vals from 32-127 

    while loop(end < s.length()):
        while loop(end < s.length()):
            if check:
                if the current ascii value is in the map // asciimap[s.charAt(end)-' '] != 0
                true?
                    break out loop
                false
                    asciimap[s.charAt(end++)-' '] = 1;
                    currentmax++;
        
        if check:
            current max > max
            true? update 

        if check:
            end == s.length() 
            true ? return true
        
        while loop(start <= end):
            remove the ascii value at the key // asciimap[s.charAt(start)-' '] = 0
            currentmax--
            if check:
                if the ascii at start is the same as ascii at end that caused loop end // if s.charAt(start++) == s.charAt(end) 
                true ? 
                    break
                false ?
                    do nothing (continue)
        
    return:
        max

Visualization of Slide:
    index:   0 1 2 3 4 5 6 7
    nums:   [a,b,c,a,b,c,b,b]

    1st while:
        [a,b,c,a,b,c,b,b]
        ->                  currentMax = 1
        [a,b,c,a,b,c,b,b]
        --->                currentMax = 2
        [a,b,c,a,b,c,b,b]
        ----->              currentMax = 3
    exit:
        max = 3
    2nd while:  (until s.charAt(end) is deleted)
        [a,b,c,a,b,c,b,b]
           -->              currentMax = 2


    3rd while:
        [a,b,c,a,b,c,b,b]
           ---->            currentMax = 3
    exit:
        max = 3
    4th while:  (until s.charAt(end) is deleted)
        [a,b,c,a,b,c,b,b]
             -->            currentMax = 2

    5th while:
        [a,b,c,a,b,c,b,b]
             ---->          currentMax = 3
    exit:
        max = 3
    6th while:  (until s.charAt(end) is deleted)
        [a,b,c,a,b,c,b,b]
               -->          currentMax = 2
    7th while:
        [a,b,c,a,b,c,b,b]
               ---->        currentMax = 3
        [a,b,c,a,b,c,b,b]
               ------>      currentMax = 3               
    exit:
        max = 3
    8th while:  (until s.charAt(end) is deleted)
        [a,b,c,a,b,c,b,b]
                 ---->      currentMax = 2
        [a,b,c,a,b,c,b,b]
                   -->      currentMax = 1
    so on...
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start, end, max, currentMax;
        start = end = max = currentMax = 0;

        // 126 - 32 + 1 = 95 (+1 needed to store ascii 94)
        int[] asciimap = new int['~'-' '+1];                    // CAN BE: new int[127]; -> delete: -' '
        
        while(end < s.length()) {
            while(end < s.length()) {
                if (asciimap[s.charAt(end)-' '] != 0) break;    // IF SO: asciimap[s.charAt(end)]
                    asciimap[s.charAt(end++)-' '] = 1;          // IF SO: asciimap[s.charAt(end++)]
                    currentMax++;
            }

            if (currentMax > max) max=currentMax;
            if (end >= s.length()) return max;
            
            while(start <= end) {
                asciimap[s.charAt(start)-' '] = 0;              // IF SO: asciimap[s.charAt(start)]
                currentMax--;
                if (s.charAt(start++) == s.charAt(end)) break;
            }
        }
        
        return max;
    }
}
