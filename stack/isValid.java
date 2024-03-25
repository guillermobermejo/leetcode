/**
Programmer: Guillermo           E
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 2ms (beats 79.20%)
Memory: 41.28mb (beats 20.54%)

DS: Stack<Character>
Advantage: (required)

Approach:
    use stack

    traverse string as char array
    for every char c 
    check if left or right parenthesis
    left ? push to stack right ? validate 

Algorithm:
    declare:
        stack<integer>   name: stack

    for loop:
        for every char c in s as char[] array
        check if left parenthesis
            true ?
                push c to stack
        false ?
            check if right parenthesis
                true ? 
                    check if stack is empty if not check if pop == c
                        true ?
                            continue
                        false ?
                            return false
            
    return:
        stack == emoty
*/
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(c);
            else if (c == '[')
                stack.push(c);
            else if (c == '{')
                stack.push(c);
            else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(')
                    return false;
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[')
                    return false;
            }
            else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
