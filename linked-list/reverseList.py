# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head == None:
            return head
            
        dummy = ListNode(-1, None)

        while head is not None:
            temp = head
            head = head.next
            temp.next = dummy.next
            dummy.next = temp
            
        return dummy.next

'''
Extra space but more intuitive

        if head == None:
            return head
            
        dummy = ListNode(-1, None)

        while head is not None:
            temp = ListNode(head.val, dummy.next)
            dummy.next = temp

            head = head.next
            
        return dummy.next
'''
