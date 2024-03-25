/**
Programmer: Guillermo           E
Language: Java
Time Complexity: O(n) 
Space Complexity: O(1)
Runtime: 0ms (beats 100%)
Memory: 40.8mb (beats 73.77%)

DS: none

Approach:
    treat every p and q as a single node instead of a tree for simplicity
    
    traverse both at the same time checking the node of each at every step
    check if both are null, if true RETURN TRUE as null trees are same                          // base case 1
    else if p != null and q == null RETURN FALSE as p is    									// base case 2
	else if p == null and q != null RETURN FALSE as p tree ends but q is ongoing			 	// base case 3
	else if p.val != q.val 			RETURN FALSE as the value of p and q are not identical   	// base case 4
        // p and q are NOT null at base case 4 so no null pointer exception
    
    return true as both nodes equal as second check for testing false equality not fired and check 
    p and q's left nodes and right nodes respectively for equality i.e.,
        return true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    
Algorithm:
    if check: 
        if p == null && q == null 
            true ? return true
			
    else if check:
        else if p != null && q == null	// if p contains a node but q is null they cannot be the same as q ended
			true ? return false
		
	else if check:
        else if p == null && q != null	// if q contains a node but p is null they cannot be the same as p ended
			true ? return false

	else if check:
        else if p.val != q.val			// if both checks failed then p and q are NOT null so check if same values
            true ? return false
    
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
        else if (p != null && q == null) return false;
        else if (p == null && q != null) return false;
        else if (p.val != q.val) return false;
        
        return true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
