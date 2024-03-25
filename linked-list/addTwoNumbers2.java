/**
Programmer: Guillermo           M       ITERATIVE
Language: Java
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 1ms (beats 100%)
Memory: 44.3 (beats 30.97%)

DS: none

Approach: 
    use a while loop to generate the nodes
    use a 'dummy'node to start the list, the actual list begins creation at the next node
    each node created is the currents next node then set current to this next
    
    while both lists are not empty we will generate a new node based on their sum and remainder
    while loop break: if both lists are null we stop and set the final next node based on the remainding carried over value
    if a both lists are not null we take both their values and calculate the value for the newly created node

Algorithm:
    declare & initialize:
        1 int value         name: sum       value: 0            notes: 
        1 int value         name: input     value: 0            notes: 
        1 int value         name: rem       value: 0            notes: 
        1 ListNode value    name: n         value: new Node()   notes: 
        1 ListNode value    name: p         value: n            notes: the 'dummy' node
        
    while loop (true):
        if check:       // terminating case
            if l1 and l2 is null
                true ?
                    remainder != 0: n.next = new ListNode(rem, null)
                    remainder == 0: n.next = null
                    BREAK
                false? 
                    go to else
        else:
            declare & initialize:
                1 int value     name: v1    value: 0 if l1 null / l1.val otherwise      notes: the value of the l1 list if not null
                1 int value     name: v2    value: 0 if l2 null / l2.val otherwise      notes: the value of the l2 list if not null
                
            calculate:
                sum = v1 + v2 + rem
                input = sum % 10        // the right digit of the sum (when sum: 19 then input: 9)
                rem = sumn / 10         // the left digit of the sum (when sum: 19 then input: 1)
                
            create node:
                // create the next node for the current node
                n.next = new ListNode(input)
                // set the current node to point to its next
                n = n.next
            
            move list pointers:
                if either is not null set the lists to its next if null KEEP null
                use ternery i.e.,
                
                l1 = l1 != null ? l1.next : null
                l2 = l2 != null ? l2.next : null
    
    return:
        p.next      // this was set to point to the initial first node, the full list is beyond this dummy node
            
            
Visualization of algorithm:
    l1: [9,9,9,9,9,9,9]
    l2: [9,9,9,9]
    
    1st call (l1, l2, rem):
        l1: [9,9,9,9,9,9,9]
        l2: [9,9,9,9]
        rem: 0
        
        sum = 9 + 9 + 0 = 18
        input = 8
        rem = 1
        
        [D] -> [8] ->                                                          // the list so far
        
        l1: [9,9,9,9,9,9]
        l2: [9,9,9]
        
    2nd call (l1, l2, rem):
        l1: [9,9,9,9,9,9]
        l2: [9,9,9]
        rem: 1
        
        sum = 9 + 9 + 1 = 19
        input = 9
        rem = 1

        [D] -> [8] -> [9] ->                                                   // the list so far
        
        l1: [9,9,9,9,9]
        l2: [9,9]
        
    3rd call (l1, l2, rem):
        l1: [9,9,9,9,9]
        l2: [9,9]
        rem: 1
        
        sum = 9 + 9 + 1 = 19
        input = 9
        rem = 1
        
        [D] -> [8] -> [9] -> [9] ->                                            // the list so far
        
        l1: [9,9,9,9]
        l2: [9]
        
    4th call (l1, l2, rem):
        l1: [9,9,9,9]
        l2: [9]
        rem: 1
        
        sum = 9 + 9 + 1 = 19
        input = 9
        rem = 1
        
        [D] -> [8] -> [9] -> [9] -> [9] ->                                     // the list so far
        
        l1: [9,9,9]
        l2: []
        
    5th call (l1, l2, rem):
        l1: [9,9,9]
        l2: []
        rem: 1
        
        sum = 9 + 0 + 1 = 10
        input = 0
        rem = 1
        
        [D] -> [8] -> [9] -> [9] -> [9] -> [0] ->                              // the list so far
        
        l1: [9,9]
        l2: []
        
    6th call (l1, l2, rem):
        l1: [9,9]
        l2: []
        rem: 1
        
        sum = 9 + 0 + 1 = 10
        input = 0
        rem = 1
        
        [D] -> [8] -> [9] -> [9] -> [9] -> [0] -> [0] ->                       // the list so far
        
        l1: [9]
        l2: []
        
    7th call (l1, l2, rem):
        l1: [9]
        l2: []
        rem: 1
        
        sum = 9 + 0 + 1 = 10
        input = 0
        rem = 1
        
        [D] -> [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] ->                // the list so far
        
        l1: []
        l2: []
        
    LAST call (l1, l2, rem):
        l1: []
        l2: []
        rem: 1
        
        [D] -> [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> null    // the list at the end
        

    RETURN p.next ([D]-> _____)
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum, input, rem;
        sum = input = rem = 0;
        ListNode n = new ListNode();
        ListNode p = n;

        while (true) {
            if (l1 == null && l2 == null) {
                if (rem > 0) n.next = new ListNode(rem, null);
                else n.next = null;
                break;
            }
            else {
                int v1 = l1 == null ? 0 : l1.val;
                int v2 = l2 == null ? 0 : l2.val;
            
                sum = v1 + v2 + rem;
                input = sum % 10;
                rem = sum / 10;
            
                n.next = new ListNode(input);
                n = n.next;
            
                l1 = l1 != null ? l1.next : null;
                l2 = l2 != null ? l2.next : null;
            }   
        }

        return p.next;
    }
}
