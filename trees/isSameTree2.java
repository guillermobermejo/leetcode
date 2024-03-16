/**
Programmer: Guillermo       E
Language: Java
Time Complexity: O(n) 
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 40.7mb (beats 73.83%)

DS: none

Approach:
    treat every p and q as a single node instead of a tree for simplicity
    
    traverse both at the same time checking the node of each at every step
    check if both are null, if true RETURN TRUE as null trees are same                          // base case 1
    else if p != null and q == null OR p == null and q != null OR p.val != q.val RETURN FALSE   // base case 2
        this check stops when one is condition is true meaning if the third condition is true,
        p and q are NOT null so no null pointer exception
    
    return true as both nodes equal as second check for testing false equality not fired and check 
    p and q's left nodes and right nodes respectively for equality i.e.,
        return true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    
Algorithm:
    if check: 
        if p == null && q == null 
            true ? return true
    else if check: (3 checks)
        else if p != null && q == null || p == null && q != null || p.val != q.val
            true ? return false for any condition that returns true
            // if p contains a node but q is null they cannot be the same as q ended
            // if q contains a node but p is null they cannot be the same as p ended
            // if both checks failed then p and q are NOT null so check if same values
    
    return:
        true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        // recursive call, if this line reached p and q are CURRENTLY valid and not null so check children
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p != null && q == null || p == null && q != null || p.val != q.val) return false;
        
        
        return true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}