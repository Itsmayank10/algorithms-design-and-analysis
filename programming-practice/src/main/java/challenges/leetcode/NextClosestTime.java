package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * 681. Next Closest Time
 *
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 * Example 1:
 *      Input: "19:34"
 *      Output: "19:39"
 *      Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59
 *                   minutes later.
 *
 * Example 2:
 *      Input: "23:59"
 *      Output: "22:22"
 *      Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than
 *                   the input time numerically.
 *
 * @author hxkandwal
 */
public class NextClosestTime extends AbstractCustomTestRunner {

    public String nextClosestTime(String time) {
        time = time.replace (":", "");
        int[] limits = new int [] { 2, 9, 5, 9 };
        int[] sorted = new int [4];
        for (int idx = 0; idx < time.length(); idx ++) sorted [idx] = time.charAt(idx) - '0';
        Arrays.sort(sorted);

        String ans = "";
        int idx = time.length() - 1;
        for (; idx >= 0; idx --) {
            int num = time.charAt(idx) - '0';
            if (num == limits [idx]) continue;

            int bIdx = 0;
            while (bIdx < sorted.length) if (sorted [bIdx] <= num) bIdx ++; else break;

            if (bIdx < sorted.length && sorted [bIdx] <= limits [idx]) {
                ans = time.substring(0, idx) + sorted [bIdx];
                break;
            }
        }

        if (idx >= 1 && Integer.valueOf(ans.substring(0, 2)) > 23) { ans = ans.substring(0, 1); idx = 0; }
        for (idx= idx + 1; idx < time.length(); idx ++) ans += sorted [0];
        return ans.substring(0, 2) + ":" + ans.substring(2);
    }
}
