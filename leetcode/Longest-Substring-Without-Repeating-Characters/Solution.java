package longest.string.no.repeat;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Solution {
  public int lengthOfLongestSubstring(String s) {
    Set<Character> achar = new HashSet<Character>();
    if (s == null || s.length() == 0)
      return 0;
    int longest = 1;
    int startIndex = 0;
    int endIndex =  0;
    while (startIndex < s.length()) {
      boolean breaked = false;
      for (; endIndex < s.length(); endIndex++) {
        if (achar.contains(s.charAt(endIndex))) {
          // duplicated
          if (endIndex - startIndex > longest) {
            longest = endIndex - startIndex;
          }
          achar.remove(s.charAt(startIndex));
          startIndex++;
          breaked = true;
          break;
        } else {
          achar.add(s.charAt(endIndex));
        }
      }
      // in case of not break but
      if (!breaked) {
        if (s.length() - startIndex > longest)
          longest = s.length() - startIndex;
        break;
      }
    }


    return longest;
  }

  @Test
  public void test() {
    assertEquals(this.lengthOfLongestSubstring("abcabcbb"), 3);
    assertEquals(this.lengthOfLongestSubstring("bbbbbb"), 1);
    assertEquals(this.lengthOfLongestSubstring("au"), 2);
    assertEquals(this.lengthOfLongestSubstring("dvdf"), 3);
  }
}
