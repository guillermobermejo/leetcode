/**
Programmer: Guillermo           E
Language: Java
Time Complexity: O(n) 
Space Complexity: O(1)
Runtime: 2ms (beats 95.65%)
Memory: 43.8mb (beats 94.83%)

DS: none

Approach:
    main function:
        use a helper function isSameTree() that will test if two trees passed are equal
        
        perform 2 if checks, the first will check if the current root is not null
            if true then perform equality check with isSameTree(root, subRoot)
        
        // covers case when root is now null and root.left / root.right will cause null pointer exception
        perform second if check to test if the current root is null, if true return false
            false for the reason that subRoot will never be null therefor null != initialized (minimum 1 node)
        
        // at this point root is not null nor is root and subRoot equal so return FALSE and checks on left and right
        return false || isSameTree(root.left, subRoot) || isSameTree(root.right, subRoot)

    isSameTree() function:
        treat every p and q as a single node instead of a tree for simplicity
    
        traverse both at the same time checking the node of each at every step
        check if both are null, if true RETURN TRUE as null trees are same                      // base case 1
        else if p != null and q == null RETURN FALSE as p is    							    // base case 2
	    else if p == null and q != null RETURN FALSE as p tree ends but q is ongoing	        // base case 3
	    else if p.val != q.val 			RETURN FALSE as the value of p and q are not identical  // base case 4
            // p and q are NOT null at base case 4 so no null pointer exception
    
        return true as both nodes equal as second check for testing false equality not fired and check 
        p and q's left nodes and right nodes respectively for equality i.e.,
            return true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    
Algorithm:  
    main function:
        if check: 
            if root != null
                perform equality test with isSameTree(root, subRoot) function
                    true ? return true  // if function returns true subRoot contained somewhere in root
        
        if check:
            if root == null             // if the root is null we return false as subRoot will never be null
                return false            
                
        return:
            false || isSameTree(root.left, subRoot) || isSameTree(root.right, subRoot)
            // at this point isSameTree returned false for the current tree rooted at root and subRoot
            // check both root's left and right subtree for equality with the subRoot in function isSameTree()

    isSameTree() function:
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {        
        if (root != null) 
            if (isSameTree(root, subRoot)) return true;
        if (root == null)
            return false;
        
        return false || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p != null && q == null) return false;
        else if (p == null && q != null) return false;
        else if (p.val != q.val) return false;
        
        return true && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
