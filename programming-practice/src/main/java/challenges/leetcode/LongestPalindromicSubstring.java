package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 5. Longest Palindromic Substring
 * 
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * Example:
 * 		Input: "babad"
 * 		Output: "bab"
 * 
 * 		Note: "aba" is also a valid answer.
 * 
 * Example:
 * 		Input: "cbbd"
 * 		Output: "bb"
 * 	
 * 
 * @author Hxkandwal
 *
 */
public class LongestPalindromicSubstring extends AbstractCustomTestRunner {
	
	private static LongestPalindromicSubstring _instance = new LongestPalindromicSubstring();
			
	// diagonal matching, right to left information storing (not propagating)
	public static String _longestPalindrome(String s) {
		if (s.length() == 0) return "";
        int[][] dp = new int [s.length() + 1][s.length() + 1];
        
        String ans = "";
        for (int row = 0; row < s.length(); row ++)
            for (int col = 0; col <= row; col ++)
                if (s.charAt(row) == s.charAt(col)) {
                    dp [row + 1][col + 1] = (row == col) ? 1 : (dp [row][col + 2] > 0 || (row - col == 1) ? (dp [row][col + 2]  + 2) : 0);
                    
                    if (dp [row + 1][col + 1] > ans.length()) ans = s.substring (col, row + 1);
                }
        return ans;
    }

	// another approach.
	public String longestPalindrome(String s) {
        int [] info = { 0, 1 };
        for (int idx = 0; idx < s.length (); idx ++) {
            extendPalindrome (info, s, idx, idx);
            extendPalindrome (info, s, idx, idx + 1);
        }
        return s.substring (info [0], info [0] + info [1]);
    }
    
    private void extendPalindrome (int [] info, String s, int j, int k) {
        while (j >=0 && k < s.length() && s.charAt (j) == s.charAt (k)) { j --; k ++; }
        if (info [1] < k - j - 1) {
            info [0] = j + 1;
            info [1] = k - j - 1;
        }
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("bbbab", "bbb");
		_instance.runTest("cbbd", "bb");
		_instance.runTest("abccba", "abccba");
		_instance.runTest("abcdcba", "abcdcba");
		_instance.runTest("ab", "a");
		_instance.runTest("abcda", "a");
	}
	
	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
