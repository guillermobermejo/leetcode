/**
Programmer: Guillermo           M       RECURSIVE
Language: C++
Time Complexity: O(n)
Space Complexity: O(1)
Runtime: 12ms (beats 93.15%)
Memory: 76.1mb (beats 16.56%)

DS: none

Approach: 
    use recursion to generate the nodes
    use a helper function that will take in both lists and a remainder parameter
    
    while both lists are not empty we will generate a new node based on their sum and remainder
    base case: if both lists are NULL we stop recursion
    other cases: take the 'input' or right value of the sum and create a node with next a recursive call
    
    first call to the helper function is the final return

Algorithm:
    main function:
        return:
            helperfunction(list 1, list 2, rem) where rem == 0
            
    helper function:
        if check:
            if (l1 and l2 == NULL)  // BASE CASE: if BOTH lists are NULL, both numbers represented are finished
            true?
                if check rem != 0:
                    return new ListNode(rem, NULL)
                else:
                    return NULL     // last node was the last node since no remainder
            false?
                continue the call to the helper function
        
        declare and initialize:
            1 int value     name: sum       value: 0    notes: the sum of l1 (if any) and l2 val (if any) AND a remainder
            1 int value     name: input     value: 0    notes: the right value of the sum i.e., if sum == 19, input: 9
        
        if check(l1 != NULL):
            ADD l1.val to sum
            l1 = l1->next       // set the list 1 pointer to the next value     '->' to access value from pointer
        
        if check(l2 != NULL):
            ADD l2.val to sum   
            l2 = l2->next       // set the list 2 pointer to the next value     '->' to access value from pointer
        
        calculate:
            sum == sum + any remainder
            input == sum % 10 (if sum: 19 then input: 9)
            remainder == sum / 10 (if sum: 19 then remainder: 1)
        
        return:
            new ListNode(input, helperfunction(l1, l2, rem))
            
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
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] ->                                                          // humans see
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> NULL    // reality (recursive call collapse FINAL)
        
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
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] ->                                                   // humans see
               [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> null    // reality (recursive call collapse)
        
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
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] ->                                            // humans see
                      [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> NULL    // reality (recursive call collapse)
        
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
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] ->                                     // humans see
                             [9] -> [0] -> [0] -> [0] -> [1] -> NULL    // reality (recursive call collapse)
        
    5th call (l1, l2, rem):
        l1: [9,9,9]
        l2: []
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9,9]
        
        sum = 9 + 1 (rem)
        input = 0
        rem = 1
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] ->                              // humans see
                                    [0] -> [0] -> [0] -> [1] -> NULL    // reality (recursive call collapse)
        
    6th call (l1, l2, rem):
        l1: [9,9]
        l2: []
        rem: 1
        
        sum = 0 + 9 = 9
        l1: [9]
        
        sum = 9 + 1 (rem)
        input = 0
        rem = 1
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] ->                       // humans see
                                           [0] -> [0] -> [1] -> NULL    // reality (recursive call collapse)
        
    7th call (l1, l2, rem):
        l1: [9]
        l2: []
        rem: 1
        
        sum = 0 + 9 = 9
        l1: []
        
        sum = 9 + 1 (rem)
        input = 0
        rem = 1
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] ->                // humans see
                                                  [0] -> [1] -> NULL    // reality (recursive call collapse)
        
    LAST call (l1, l2, rem):
        l1: []
        l2: []
        rem: 1
        
        return ListNode(input, helperfunction(l1, l2, rem))
        [8] -> [9] -> [9] -> [9] -> [0] -> [0] -> [0] -> [1] -> NULL    // humans see
                                                         [1] -> NULL    // reality (recursive call collapse INITIAL)    
*/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        return createListNode(l1, l2, 0);
    }
    
    ListNode* createListNode(ListNode* l1, ListNode* l2, int rem) {
        if (l1 == NULL && l2 == NULL)   // NULL in C++, null in Java, NONE in Python
        {
            if (rem != 0) return new ListNode(rem, NULL);
            else return NULL;
        }

        int sum = 0;
        int input = 0;

        if (l1 != NULL) {
            sum += l1->val;             // accessing values from pointers uses '->'
            l1 = l1->next;
        }

        if (l2 != NULL) {
            sum += l2->val;             // accessing values from pointers uses '->'
            l2 = l2->next;
        }

        sum += rem;
        input = sum % 10;
        rem = sum / 10;

        return new ListNode(input, createListNode(l1, l2, rem));
    }
};
