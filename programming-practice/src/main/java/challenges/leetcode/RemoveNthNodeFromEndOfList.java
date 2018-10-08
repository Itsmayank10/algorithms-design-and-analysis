package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 19. Remove Nth Node From End of List
 * 
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 
 * For example, Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * 
 * Note: Given n will always be valid. Try to do this in one pass.
 * 
 * @author Hxkandwal
 *
 */
public class RemoveNthNodeFromEndOfList extends AbstractCustomTestRunner {

	private static RemoveNthNodeFromEndOfList _instance = new RemoveNthNodeFromEndOfList();
	
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}
	
	// method : let the forward pointer pass 'n' places and the start the node pointer till the forward pointer reaches end.
	public ListNode _removeNthFromEnd(ListNode head, int n) {
		ListNode s = null, f = head;
		while (n -- > 0) f = f.next;
		if (f == null) return head.next;
		while (f != null) {
			f = f.next;
			s = s == null ? head : s.next;
		}
		s.next = s.next.next;
		return head;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("", 1, null);
		_instance.runTest(null, 3, null);
		_instance.runTest("12345", 2, "1235");
		_instance.runTest("12345", 1, "1234");
		_instance.runTest("12345", 4, "1345");
		_instance.runTest("1", 1, null);
		_instance.runTest("12", 1, "1");
	}
	
	public void runTest(final String input, final int n, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, n });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		ListNode head, tail;
		head = tail =  null;
		
		String input = (String) externalVariables[0];
		int n = (Integer) externalVariables [1];
		
		if (input == null || input.isEmpty())
			return null;
		
		for (int idx = 0; idx < input.length(); idx ++) {
			if (head == null) {
				tail = head = new ListNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			} else {
				tail = tail.next = new ListNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			}
		}
		
		try {
			tail = (ListNode) method.invoke(_instance, new Object[] { head, n });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if (tail != null) {
			StringBuilder sb = new StringBuilder();
		
			while (tail != null) {
				sb.append(tail.val);
				tail = tail.next;
			}
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
}
