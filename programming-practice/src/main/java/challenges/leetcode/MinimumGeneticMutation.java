package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 433. Minimum Genetic Mutation
 * 
 * A gene string can be represented by an 8-character long string, with choices from "A","C","G","T". 
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation 
 * is defined as ONE single character changed in the gene string.
 * 
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * 
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make 
 * it a valid gene string. 
 * 
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to 
 * mutate from "start" to "end". If there is no such a mutation, return -1. 
 * 
 * For example,
 * 
 * 		bank	: "AACCGGTA" 
 * 		start	: "AACCGGTT" 
 * 		end		: "AACCGGTA" 
 * 		return	: 1
 * 
 * 		bank	: "AACCGGTA", "AACCGCTA", "AAACGGTA"
 * 		start	: "AACCGGTT"
 * 		end		: "AAACGGTA"
 * 		return	: 2
 * 
 * 		bank	: "AAAACCCC", "AAACCCCC", "AACCCCCC"
 * 		start	: "AAAAACCC"
 * 		end		: "AACCCCCC"
 * 		return	: 3
 * 
 * @author Hxkandwal
 *
 */
public class MinimumGeneticMutation extends AbstractCustomTestRunner {
	
	private static MinimumGeneticMutation _instance = new MinimumGeneticMutation();

    public int _minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String b : bank) set.add (b);
        set.add (start);
        Queue<String> queue = new LinkedList<>();
        queue.offer (start);

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans ++;
            while (size -- > 0) {
                String g = queue.poll();
                for (int idx = 0; idx < g.length(); idx ++) {
                    for (char c : "ACGT".toCharArray()) {
                        if (c == g.charAt(idx)) continue;
                        String next = g.substring (0, idx) + c + g.substring (idx + 1);
                        if (set.contains(next)) {
                            set.remove (next);
                            if (next.equals(end)) return ans;
                            queue.offer (next);
                        }
                    }
                }
            }
        }
        return -1;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("AAAAACCC", "AACCCCCC", new String[] { "AAAACCCC", "AAACCCCC", "AACCCCCC" }, 3);
		_instance.runTest("AACCTTGG", "AATTCCGG", new String[] { "AATTCCGG", "AACCTGGG", "AACCCCGG", "AACCTACC" }, -1);
	}
	
	public void runTest(final String start, final String end, final String[] bank, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { start, end, bank });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
