/**
Programmer: Guillermo			E
Language: Java
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 0ms (beats 100%)
Memory: 43.8mb (beats 95.80%)

DS: none

Approach:
   use turtle and hair apprach where one pointer moves by one (slow) and the other by two (fast)
   
   the pointers will wither meet at some point if there is a cycle or list will end not meeting
   
   in while loop, test to see if pointers meet and if not increase pointers if they do return true
   at the end return false


Algorithm:
    if check:
        check if the list is null
            true ? return false as no cycle in an empty list
    
    declare & initialize:
        1 ListNode pointer      name: slow      value: head         notes: point to head      | moves 1
        1 ListNode pointer      name: fast      value: head.next    notes: point to head.next | moves 2
        
    while loop: (fast != null && fast.next != null)
        if check:
            if slow & fast are the same node there is a cycle
                true ? return true
        
        increment pointers:
            move slow by 1: slow = slow.next
            move fast by 2: fast = fast.next.next
    
    return:
        false       // at this point list traversal ended therefore no cycle present
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            if (slow == fast.next) return true;
            
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return false;
    }
}
