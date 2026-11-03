import java.util.Arrays;

public class LongestCommonPrefix {

    // 1. BRUTE FORCE APPROACH (Character-by-Character Vertical Scanning)
    // Time Complexity: O(S) - Where S is the total number of characters across all strings.
    // Space Complexity: O(1) - Constant space utilized.
    public static String longestCommonPrefixBrute(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        // Take the first string as a baseline reference
        String firstStr = strs[0];
        
        // Iterate through each character position of the first string
        for (int i = 0; i < firstStr.length(); i++) {
            char currentChar = firstStr.charAt(i);
            
            // Compare this character against the same position in all other strings
            for (int j = 1; j < strs.length; j++) {
                // Terminate if the current string is shorter or if a character mismatch occurs
                if (i == strs[j].length() || strs[j].charAt(i) != currentChar) {
                    return firstStr.substring(0, i);
                }
            }
        }
        
        return firstStr;
    }

    // 2. BETTER APPROACH (Sorting-Based Optimization)
    // Time Complexity: O(N * M log N) - Where N is the number of strings and M is the maximum string length (due to sorting string arrays).
    // Space Complexity: O(1) - O(log N) stack space required by the sorting algorithm.
    public static String longestCommonPrefixBetter(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        // Lexicographically sort the array to place the most disparate strings at the boundaries
        Arrays.sort(strs);

        // We only need to compare the first (lexicographically smallest) and last (largest) strings
        String first = strs[0];
        String last = strs[strs.length - 1];
        int minLength = Math.min(first.length(), last.length());
        
        int i = 0;
        // Move forward as long as characters match
        while (i < minLength && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        return first.substring(0, i);
    }

    // 3. OPTIMAL APPROACH (Horizontal Scanning / Prefix Trimming)
    // Time Complexity: O(S) - In the best case, it trims rapidly. Worst case scans all characters.
    // Space Complexity: O(1) - Modifies the prefix string in place without auxiliary data structures.
    public static String longestCommonPrefixOptimal(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        // Assume the entire first string is the common prefix initially
        String prefix = strs[0];
        
        // Continuously trim the prefix against each subsequent string
        for (int i = 1; i < strs.length; i++) {
            // Shorten the prefix until the current string starts with it
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // Early exit if the common prefix becomes completely empty
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }

    // Driver code to test all three implementations
    public static void main(String[] args) {
        // Test Case 1: Standard matching prefix
        String[] strs1 = {"bat", "bag", "bank", "band"};
        System.out.println("Test Case 1: strs = " + Arrays.toString(strs1));
        System.out.println("Brute Force: \"" + longestCommonPrefixBrute(strs1) + "\"");
        System.out.println("Better (Sorting): \"" + longestCommonPrefixBetter(strs1) + "\"");
        System.out.println("Optimal (Horizontal): \"" + longestCommonPrefixOptimal(strs1) + "\"");
        
        System.out.println("---------------------------------------");

        // Test Case 2: No common prefix present
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Test Case 2: strs = " + Arrays.toString(strs2));
        System.out.println("Brute Force: \"" + longestCommonPrefixBrute(strs2) + "\"");
        System.out.println("Better (Sorting): \"" + longestCommonPrefixBetter(strs2) + "\"");
        System.out.println("Optimal (Horizontal): \"" + longestCommonPrefixOptimal(strs2) + "\"");
    }
}
