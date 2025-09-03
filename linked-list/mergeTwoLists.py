# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        if list1 == None and list2 == None:
            return None
        
        if list1 == None and list2 is not None:
            return list2

        if list2 == None and list1 is not None:
            return list1
        
        dummy = ListNode(-1, None)
        dummy_ptr = dummy

        # build the new list starting at the dummy and move dummy_ptr up every new connection
        while list1 is not None and list2 is not None:

            if list1.val <= list2.val:
                dummy_ptr.next = list1
                list1 = list1.next
                dummy_ptr = dummy_ptr.next
            else:
                dummy_ptr.next = list2
                list2 = list2.next
                dummy_ptr = dummy_ptr.next
            
        if list1 is None:
            dummy_ptr.next = list2
        elif list2 is None:
            dummy_ptr.next = list1
        
        return dummy.next
