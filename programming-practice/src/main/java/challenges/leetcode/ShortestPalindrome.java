package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 214. Shortest Palindrome
 * 
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest 
 * palindrome you can find by performing this transformation.
 * 
 * For example:
 * 
 * 		Given "aacecaaa", return "aaacecaaa".
 * 		
 * 		Given "abcd", return "dcbabcd".
 * 
 * @author Hxkandwal
 */
public class ShortestPalindrome extends AbstractCustomTestRunner {
	
	private static ShortestPalindrome _instance = new ShortestPalindrome();

    public String _shortestPalindromeCleaner(String s) {
        for (int idx = s.length()/2; idx >= 0; idx --) {
            if (find (s, idx, idx + 1)) return new StringBuilder(s.substring(idx + idx + 2)).reverse() + s;
            if (find (s, idx, idx)) return new StringBuilder(s.substring(idx + idx + 1)).reverse() + s;
        }
        return s;
    }

    private boolean find (String s, int c1, int c2) {
        int j = c1, k = c2;
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) { j --; k ++; }
        return j < 0;
    }

	// https://discuss.leetcode.com/topic/27261/clean-kmp-solution-with-super-detailed-explanation
	public String _shortestPalindromeKMP(String s) {
		if (s.length() <= 1) return s;
        String str = s + "#" + new StringBuilder (s).reverse ().toString ();
        int [] pi = new int [str.length ()];
        for (int i = 0, j = 1; j < str.length (); j ++) {
            while (i > 0 && str.charAt (i) != str.charAt (j)) i = pi [i - 1];
            if (str.charAt (i) == str.charAt (j)) pi [j] = ++ i;
        }
        return new StringBuilder (s.substring (pi [pi.length - 1])).reverse().toString() + s;
	}
	
	public String _shortestPalindrome(String s) {
        if (s.length() == 0) return s;
        String ans = s;
        for (int idx = s.length()/2; idx >= 0; idx --) {
            int j = idx, k = idx + 1;
            while (j >= 0 && k < s.length () && s.charAt (j) == s.charAt (k)) { j --; k ++; }
            if (j < 0 && s.length() - k < ans.length()) ans = s.substring (k);
            j = idx - 1; k = idx + 1;
            while (j >= 0 && k < s.length () && s.charAt (j) == s.charAt (k)) { j --; k ++; }
            if (j < 0 && s.length() - k < ans.length()) ans = s.substring (k);
        }
        StringBuilder sb = new StringBuilder (ans);
        return sb.reverse().toString() + s;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abcd", "dcbabcd");
		_instance.runTest("aacecaaa", "aaacecaaa");
	}
	
	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
