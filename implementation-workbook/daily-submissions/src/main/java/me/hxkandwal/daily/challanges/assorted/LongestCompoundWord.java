package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Longest Compound Word
 * 
 * Given a sorted list of words, find the longest compound word in the list that is constructed by concatenating the words in the list. 
 * 
 * For example, 
 * 		if the input list is: [‘cat’, ‘cats’, ‘catsdogcats’, ‘catxdogcatsrat’, ‘dog’, ‘dogcatsdog’, ‘hippopotamuses’, ‘rat’, ‘ratcat’, ‘ratcatdog’, ‘ratcatdogcat’]. 
 * 
 * Then the longest compound word is ‘ratcatdogcat’ with 12 letters. 
 * 
 * Note that the longest individual words are ‘catxdogcatsrat’ and ‘hippopotamuses’ with 14 letters, but they’re not fully constructed by other words. 
 * Former one has an extra ‘x’ letter, and latter is an individual word by itself not a compound word.
 * 
 * @author Hxkandwal
 *
 */
public class LongestCompoundWord extends AbstractCustomTestRunner {
	
	private static LongestCompoundWord _instance = new LongestCompoundWord();
	
	private LongestCompoundWord() {}
	
	public static class Trie {
		private Character letter;
		private Trie[] children = new Trie[26];
		private boolean isTerminal;
		
		public Trie() {
			this.children = new Trie[26];
		}
		
		public void insert(String word, int index) {
			if (index < word.length()) {
			
			}
		}
	}
	
	public static String _getLongestCompoundWord(String[] words) {
		Trie root = new Trie();
		
		for (String word : words) {
			
		}
		
		return null;
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		_instance.runTest("(AB) C((D E) F)/ SSS", "ABC(DEF)");
	}
	
	public void runTest(final String line, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { line });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}