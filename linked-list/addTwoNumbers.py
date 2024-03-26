'''
Programmer: Guillermo           M       RECURSIVE
Language: Python3
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 48ms (beats 88.55%)
Memory: 16.6mb (beats 90.94%)

DS: none

Approach: 
    use recursion to generate the nodes
    use a helper function that will take in both lists and a remainder parameter
    
    while both lists are not empty we will generate a new node based on their sum and remainder
    base case: if both lists are None we stop recursion
    other cases: take the 'input' or right value of the sum and create a node with next a recursive call
    
    first call to the helper function is the final return

Algorithm:
    main function:
        return:
            self.helperfunction(list 1, list 2, rem) where rem == 0
            
    helper function:
        if check:
            if (l1 and l2 == None)  // BASE CASE: if BOTH lists are None, both numbers represented are finished
            true?
                if check rem != 0:
                    return ListNode(rem, None)
                else:
                    return None     // last node was the last node since no remainder
            false?
                continue the call to the helper function
        
        declare and initialize:
            1 int value     name: sum       value: 0    notes: the sum of l1 (if any) and l2 val (if any) AND a remainder
            1 int value     name: input     value: 0    notes: the right value of the sum i.e., if sum == 19, input: 9
        
        if check(l1 != None):
            ADD l1.val to sum
            l1 = l1.next        // set the list 1 pointer to the next value
        
        if check(l2 != None):
            ADD l2.val to sum   
            l2 = l2.next        // set the list 2 pointer to the next value
        
        calculate:
            sum == sum + any remainder
            input == sum % 10 (if sum: 19 then input: 9)
            remainder == int(sum / 10) (if sum: 19 then remainder: 1)   // REQUIRED TYPE CAST TO INT
        
        return:
            ListNode(input, self.helperfunction(l1, l2, rem))
            
Visualization of algorithm:
    l1: [9,9,9,9,9,9,9]
    l2: [9,9,9,9]
    
    1st call (l1, l2, rem):
        l1: [9,9,9,9,9,9,9]
        l2: [9,9,9,9]
        rem: 0
        
        sum = 0 + 9 = 9
        l1: [9,9,9,9,9,9]
        
        sum = 9 + 9 = 18
        l2: [9,9,9]
        
        sum = 18 + 0 (rem)
        input = 8
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] ->                                                          // humans see
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> None    // reality (recursive call collapse FINAL)
        
    2nd call (l1, l2, rem):
        l1: [9,9,9,9,9,9]
        l2: [9,9,9]
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9,9,9,9,9]
        
        sum = 9 + 9 = 18
        l2: [9,9]
        
        sum = 18 + 1 (rem)
        input = 9
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] ->                                                   // humans see
               [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> None    // reality (recursive call collapse)
        
    3rd call (l1, l2, rem):
        l1: [9,9,9,9,9]
        l2: [9,9]
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9,9,9,9]
        
        sum = 9 + 9 = 18
        l2: [9]
        
        sum = 18 + 1 (rem)
        input = 9
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] ->                                            // humans see
                      [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> None    // reality (recursive call collapse)
        
    4th call (l1, l2, rem):
        l1: [9,9,9,9]
        l2: [9]
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9,9,9]
        
        sum = 9 + 9 = 18
        l2: []
        
        sum = 18 + 1 (rem)
        input = 9
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] ->                                     // humans see
                             [9] -> [0] -> [0] -> [0] -> [1] -> None    // reality (recursive call collapse)
        
    5th call (l1, l2, rem):
        l1: [9,9,9]
        l2: []
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9,9]
        
        sum = 9 + 1 (rem)
        input = 0
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] ->                              // humans see
                                    [0] -> [0] -> [0] -> [1] -> None    // reality (recursive call collapse)
        
    6th call (l1, l2, rem):
        l1: [9,9]
        l2: []
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9]
        
        sum = 9 + 1 (rem)
        input = 0
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] ->                       // humans see
                                           [0] -> [0] -> [1] -> None    // reality (recursive call collapse)
        
    7th call (l1, l2, rem):
        l1: [9]
        l2: []
        rem: 1
        
        sum = 0 + 9 = 9
        l1: []
        
        sum = 9 + 1 (rem)
        input = 0
        rem = 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] ->                // humans see
                                                  [0] -> [1] -> None    // reality (recursive call collapse)
        
    LAST call (l1, l2, rem):
        l1: []
        l2: []
        rem: 1
        
        return ListNode(input, self.helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> None    // humans see
                                                         [1] -> None    // reality (recursive call collapse INITIAL)    
'''

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        return self.createListNode(l1, l2, 0)
        
    def createListNode(self, l1: Optional[ListNode], l2: Optional[ListNode], rem: int) -> Optional[ListNode]:
        if l1 == None and l2 == None:   # None in Python, null in Java, NULL in C++
            if rem != 0: 
                return ListNode(rem, None)
            else:
                return None
        
        sum = 0
        input = 0

        if l1 != None:
            sum += l1.val
            l1 = l1.next

        if l2 != None:
            sum += l2.val
            l2 = l2.next

        sum += rem
        input = sum % 10
        rem = int(sum / 10)             # ! required int() type case '/' operator returns double

        return ListNode(input, self.createListNode(l1, l2, rem))    # recursive call requires 'self.' to invoke
