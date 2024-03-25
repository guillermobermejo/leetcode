/**
Programmer: Guillermo			M       *cracked*
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 4ms (beats 98.12%)
Memory: 44.86mb (beats 86.30%)

DS: NONE
Advantage: (improved runtime via custom node class)

Approach:
	build 2 stacks from the bottom up
	build 1 reverse linked list using pointers and custom node class for returning top in O(1)
	build 1 reverse linked list using pointers and custom node class for returning min in O(1)

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
        1 ListNode      name: topNode
        1 ListNode      name: minNode

    define ListNode class:
        parameters: int val
                    ListNode prev
        constructor:
                    ListNode(int val, ListNode prev)

    define problem constructor:
        1 ListNode      name: topNode   value: null
        1 ListNode      name: minNode   value: null
    
    define push:
        topNode -> set to new node  parameters: given val
                                                current topNode

        minNode -> if empty -> set to new node          parameters: given val / current minNode
                -> else if new min ? -> set to new node parameters: given val / current minNode
                -> else -> set to new node with old min parameters: minNode.val / current minNode
    
    define pop:
        if check:
            topNode == null ? return : (continue)
        topNode -> topNode.prev
        minNode -> minNode.prev
    
    define top:
        return:
            topNode.val
    
    define getMin:
        return:
            minNode.val
*/

class MinStack {
    public ListNode topNode;
    public ListNode minNode;

    public class ListNode {
        int val;
        ListNode prev;
        ListNode(int val, ListNode prev) { this.val = val; this.prev = prev; }
    }

    public MinStack() {
        this.topNode = null;
        this.minNode = null;
    }
    
    public void push(int val) {
        this.topNode = new ListNode(val, this.topNode);

        if (this.minNode == null)
            this.minNode = new ListNode(val, this.minNode);
        else if (val < minNode.val) 
            this.minNode = new ListNode(val, this.minNode);
        else
            this.minNode = new ListNode(this.minNode.val, this.minNode);
    }
    
    public void pop() {
        if (this.topNode == null) return;

        this.topNode = this.topNode.prev;
        this.minNode = this.minNode.prev;
    }
    
    public int top() {
        return this.topNode.val;
    }
    
    public int getMin() {
        return this.minNode.val;
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