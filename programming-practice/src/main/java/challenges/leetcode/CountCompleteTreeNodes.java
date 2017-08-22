package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 222. Count Complete Tree Nodes
 * 
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia:
 * 
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far 
 * left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * 
 * @author Hxkandwal
 */
public class CountCompleteTreeNodes extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// takes log(n) time
    public int height (TreeNode node) {
        return (node == null ? -1 : 1 + height (node.left));    
    }
    
    // log (n) work as we are splitting tree in every go.
    public int countNodes(TreeNode root) {
        int h = height (root);
        return h < 0 ? 0 : (height (root.right) == h - 1 ? (1 << h) + countNodes (root.right)
                                                         : (1 << h - 1) + countNodes (root.left));
    }

    // another approach
    public int _countNodes(TreeNode root) {
        if (root == null) return 0;
        int ans = 1, l = h (root.left), r = h (root.right);
        if (l == r) ans += ((1 << l) - 1) + countNodes (root.right);
        else ans += ((1 << r) - 1) + countNodes (root.left);
        return ans;
    }

    private int h (TreeNode n) {
        return n == null ? 0 : 1 + h (n.left);
    }

}
