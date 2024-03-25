/**
Programmer: Guillermo           E       *cracked*       JAVA ADVANTAGE
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 2ms (beats 99.31%)
Memory: 42.6mb (beats 70.2%)

DS: none

Approach: 
    use two pointer approach
    use java built-in functions
    
    java functions: Character.isLetterOrDigit()     return: boolean     true: if alphanumeric   false: otherwise 
                    Character.toLowerCase()         return: boolean     true: if successful     false: otherwise
    
    use two pointers l/r where l will be incremented and r decremented until meeting
    if at any point they are not equal (alphanumerics) return false

Algorithm:
    declare & initialize: 
        1 int value     name: l     value: 0                notes: the start index of the array
        1 int value     name: r     value: s.length()-1     notes: the end index of the array
        
    while loop(l < r):
        declare & initialize:
            1 char value    name: c1    value: s.charAt(l)  notes: the current char value at left index l
            1 char value    name: c2    value: s.charAt(r)  notes: the current char value at right index r
            
        if check:
            if c1 is not alphanumeric 
            true ? ignore and increment
            false ? continue to else if
        else if check:
            if c2 is not alphanumeric
            true ? ignore and decrement 
            false ? continue to else
        else:
            // they are both alphanumeric and we DO NOT CARE about uppercase so use toLowerCase on everything
            if c1 after lowercase and c2 after lowercase are not equal
                return false
            increment l
            decrement r
        
    return: // at this point it is valid since l and r have met and all characters are the same
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
        int l = 0;
        int r = s.length()-1;

        while (l < r) {
            char c1 = s.charAt(l);
            char c2 = s.charAt(r);

            if (!Character.isLetterOrDigit(c1)) l++;
            else if (!Character.isLetterOrDigit(c2)) r--;
            else {
                if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) return false;
                l++;
                r--;
            }
        }

        return true;
    }
}
