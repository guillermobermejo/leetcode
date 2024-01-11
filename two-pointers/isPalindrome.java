/**
Programmer: Guillermo           *cracked*E
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 1ms (beats 100%)
Memory: 43.3mb (beats 54.5%)

DS: none

Approach: 
    traverse the given string s from left to right simultaneously using two pointers for indexing
    if a character at either of the indexes is not alphanumeric, we ignore
    if both are valid alphanumeric, ignore uppercase and check if equal
    if not NOT palindrome if yes continue until end
    end reached means true if not returned false within loop

    NOTE: use ascii values
    0 - 48
    A - 65
    a - 97

Algorithm:
    if check:
        if length is 1, return true bc if not alphanumeric -> would be removed and be empty

    declare & initialize:
        1 int   name: l     value: 0 (beginning)    note: the left pointer
        1 int   name: r     value: length-1(end)    note: the right pointer

    while loop(l < r):
        declare & initialize:
            1 int   name a1:    value: current character - 'a' 
            1 int   name a2:    value: current character - 'a'

            if check:
                check if the left character is NOT a lower case
                true ? 
                    if check:
                        check if the left character ascii value +32 is NOT a lowercase
                        true ? 
                            if check:
                                check if left character is NOT a numeric
                                true ? 
                                    move pointer to right (l++)
                                false ? 
                                    CONTINUE TO NEXT LOOP
                        false ? 
                            continue this loop (l axcii value is in the range 0-25 here)
                false ? 
                    continue this loop
            
            if check:
                check if the right character is NOT a lower case
                true ? 
                    if check:
                        check if the right character ascii value +32 is NOT a lowercase
                        true ? 
                            if check:
                                check if right character is NOT a numeric
                                true ? 
                                    move pointer to left (r--)
                                false ? 
                                    CONTINUE TO NEXT LOOP
                        false ? 
                            continue this loop (r axcii value is in the range 0-25 here)
                false ? 
                    continue this loop
                
            if check:
                check if a1 == a2
                true ? continue this loop
                false ? return false
            
        shift left pointer left     (l++)
        shift right pointer right   (r--)

        return: 
            true
			
Visualization of traversal:
	s = "rac'eca=-r"
	
	pointers l					 r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
	pointers   l				 r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
	pointers   l				 r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
			==
	
	pointers     l			   r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
	pointers     l			 r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
	pointers     l		   r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
			==
			
	pointers       l	 r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
		
			==
			
	pointers         l r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
			
	
	pointers          l/r
	index:	 0 1 2 3 4 5 6 7 8 9 10 
			[ ,r,a,c,',e,c,a,=,-,r]
	
	RETURN TRUE
	
*/

class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 1) 
            return true;

        int l = 0;
        int r = s.length()-1;

        while (l <= r) {
            int a1 = s.charAt(l)-'a';
            int a2 = s.charAt(r)-'a';

            if (!(a1 >= 0 && a1 <=25)) {
                a1 += 32;
                if (!(a1 >= 0 && a1 <= 25)) {
                    a1 = s.charAt(l);
                    if (!(a1 >= 48 && a1 <= 57)) {
                        l++;
                        continue;
                    }
                }
            }

            if (!(a2 >= 0 && a2 <=25)) {
                a2 += 32;
                if (!(a2 >= 0 && a2 <= 25)) {
                    a2 = s.charAt(r);
                    if (!(a2 >= 48 && a2 <= 57)) {
                        r--;
                        continue;
                    }
                }
            }

            if (a1 != a2)
                return false;

            l++;
            r--;
        }

        return true;
    }
}
