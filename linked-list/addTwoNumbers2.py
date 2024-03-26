'''
Programmer: Guillermo           M       ITERATIVE
Language: Python3
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 47ms (beats 91.07%)
Memory: 16.7mb (beats 48.84%)

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
        1 int value         name: sum_v     value: 0            notes: 
        1 int value         name: input_v   value: 0            notes: 
        1 int value         name: rem       value: 0            notes: 
        1 ListNode value    name: n         value: ListNode()   notes: 
        1 ListNode value    name: p         value: n            notes: the 'dummy' node
        
    while loop (true):
        if check:       // terminating case
            if l1 and l2 is None
                true ?
                    if rem > 0:
                        n.next = ListNode(rem, None)
                    else:
                        n.next = None
                    BREAK
                    
                false? 
                    go to else
        else:
            declare & initialize:
                1 int value     name: v1    value: 0 if l1 None / l1.val otherwise      notes: the value of the l1 list if not None
                1 int value     name: v2    value: 0 if l2 None / l2.val otherwise      notes: the value of the l2 list if not None
                
            calculate:
                sum_v = v1 + v2 + rem
                input_v = sum_v % 10        // the right digit of the sum (when sum: 19 then input: 9)
                rem = int(sum_v / 10)       // the left digit of the sum (when sum: 19 then input: 1)       MANDATORY INT CAST
                
            create node:
                // create the next node for the current node
                n.next = new ListNode(input)
                // set the current node to point to its next
                n = n.next
            
            move list pointers:             // if the pointers are not None move pointer to the next node for processing
                if l1 != None:
                    l1 = l1.next
                    
                if l2 != None:                    
                    l2 = l2.next
    
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
        
        [D] -> [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> None    // the list at the end
        

    RETURN p.next ([D]-> _____)
'''

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        sum_v = 0
        input_v = 0
        rem = 0
        
        n = ListNode()
        p = n

        while (True):
            if (l1 == None and l2 == None):     # None in Python, null in Java, NONE in C++
                if rem > 0:
                    n.next = ListNode(rem, None)
                else:
                    n.next = None
                break
            
            else:
                v1 = 0
                if l1 != None:
                    v1 = l1.val
                    
                v2 = 0
                if l2 != None:
                    v2 = l2.val

                sum_v = v1 + v2 + rem
                input_v = sum_v % 10
                rem = int(sum_v / 10)           # mandatory int() cast
            
                n.next = ListNode(input_v)
                n = n.next
            
                if l1 != None:
                    l1 = l1.next
                    
                if l2 != None:                    
                    l2 = l2.next


        return p.next
