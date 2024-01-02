/**
Programmer: CRACKED person *sadge*
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 1ms (beats 98.54%)
Memory: 41.5mb (beats 10.3%)

DS: Stack<Character>
Advantage: (required)

Approach:
    use stack

    traverse string as char array
    for every char c 
    check if left or right parenthesis
    if left push pair to stack
    if right check the pop


Algorithm:
    declare:
        stack<integer>   name: stack

    for loop:
        for every char c in s as char[] array
        check if left parenthesis
            true ?
                push identical pair to stack
        false ?
            check if stack is empty || current c is identical to the pop
            
    return:
        stack == empty
*/
class Solution {
    public boolean isValid(String s) {
	    Stack<Character> stack = new Stack<Character>();
        
	    for (char c : s.toCharArray()) {
    		if (c == '(')
    		    stack.push(')');
		    else if (c == '{')
        		stack.push('}');
		    else if (c == '[')
        		stack.push(']');
		    else if (stack.isEmpty() || stack.pop() != c)
    			return false;
    	}

    	return stack.isEmpty();
    }
}