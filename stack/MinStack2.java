/**
Programmer: Guillermo           M
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 5ms (beats 63.19%)
Memory: 44.7mb (beats 89.64%)

DS: Stack<Integer> 
Advantage: O(1) minimum access

Approach:
	build 1 reverse linked list using pointers and custom node class for returning top in O(1)
	build 1 stack for keeping track of minimum state and returning min in O(1)

    create custom node class with two fields
        type: int       name: val
        type: ListNode  name: prev

    *NOTE:
        value state behavior -
            for ALL items in stack, there is a corresponding minimum value in the stack
                    ------------>
            example: push: 3,4,5,2,8

                        |8|             |2|
                        |2|             |2|
                        |5|             |3|
                        |4|             |3|
            valstack:   |3| minstack:   |3|


Algorithm:
    declare:
        1 ListNode          name: topNode
        1 Stack<Integer>    name: minStack

    define ListNode class:
        parameters: int val
                    ListNode prev
        constructor:
                    ListNode(int val, ListNode prev)

    define problem constructor:
        1 ListNode          name: topNode   value: null
        1 Stack<Integer>    name: minNode   value: null
    
    define push:
        topNode -> set to new node  parameters: given val
                                                current topNode

        minStack -> if empty -> push val                    parameters: given val
                 -> else if new min ? -> push new val       parameters: given val
                 -> else -> push old value                  parameters: minStack.peek()
    
    define pop:
        if check:
            topNode == null ? return : (continue)
        topNode -> topNode.pop()
        minNode -> minStack.pop()
    
    define top:
        return:
            topNode.val
    
    define getMin:
        return:
            minStack.pop()
*/

class MinStack {
    public ListNode topNode;
    Stack<Integer> minStack;

    public class ListNode {
        int val;
        ListNode prev;
        ListNode(int val, ListNode prev) { this.val = val; this.prev = prev; }
    }

    public MinStack() {
        this.topNode = null;
        this.minStack = new Stack<Integer>();
        
    }
    
    public void push(int val) {
        this.topNode = new ListNode(val, this.topNode);

        if (this.minStack.isEmpty())
            minStack.push(val);
        else if (val < minStack.peek()) 
            minStack.push(val);
        else
            minStack.push(minStack.peek());
    }
    
    public void pop() {
        if (this.topNode == null) return;

        this.topNode = this.topNode.prev;
        this.minStack.pop();
    }
    
    public int top() {
        return this.topNode.val;
    }
    
    public int getMin() {
        return this.minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
