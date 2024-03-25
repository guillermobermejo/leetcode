/**
Programmer: Guillermo        	M
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 5ms (beats 63.19%)
Memory: 45.6mb (beats 75.27%)

DS: PriorityQueue<Integer> (minheap)
Advantage: O(1) minimum access

Approach:
    build 1 reverse linked list using pointers and custom node class for returning top in O(1)
    build 1 minheap for storing all values and returning min in O(1)
    
    create custom node class with two fields
        type: int       name: val
        type: ListNode  name: prev

    build a stack using pointers and custom node class for returning top in O(1)
    *NOTE:
        minheap will keep track of the current minimum in the stack
        peek -> O(1)
        pop -> O(1)
            resize after -> O(n log n)
        remove-> O(n)
            resize after -> O(n log n)

Algorithm:
    declare:
        1 ListNode                  name: topNode
        1 PriorityQueue<Integer>    name: minHeap

    define ListNode class:
        parameters: int val
                    ListNode prev
        constructor:
                    ListNode(int val, ListNode prev)

    define problem constructor:
        1 ListNode                  name: topNode   value: null
        1 PriorityQueue<Integer>    name: minHeap   value: new PriorityQueue<Integer>
    
    define push:
        topNode -> set to new node  parameters: given val
                                                current topNode

        minHeap -> add new value to heap
    
    define pop:
        if check:
            topNode == null ? return : (continue)
        minHeap -> minHeap.remove(topNode.val)
        topNode -> topNode.prev
    
    define top:
        return:
            topNode.val
    
    define getMin:
        return:
            minHeap.peek()
*/

class MinStack {
    public ListNode topNode;
    PriorityQueue<Integer> minHeap;

    public class ListNode {
        int val;
        ListNode prev;
        ListNode(int val, ListNode prev) { this.val = val; this.prev = prev; }
    }

    public MinStack() {
        this.topNode = null;
        this.minHeap = new PriorityQueue<Integer>();
        
    }
    
    public void push(int val) {
        this.topNode = new ListNode(val, this.topNode);
        this.minHeap.add(val);
    }
    
    public void pop() {
        if (this.topNode == null) return;

        this.minHeap.remove(this.topNode.val);
        this.topNode = this.topNode.prev;
    }
    
    public int top() {
        return this.topNode.val;
    }
    
    public int getMin() {
        return this.minHeap.peek();
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

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */