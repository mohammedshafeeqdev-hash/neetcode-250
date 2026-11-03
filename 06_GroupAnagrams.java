import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    // 1. BRUTE FORCE APPROACH (Nested Loop with Frequency Tracking Arrays)
    // Time Complexity: O(N^2 * M) - Where N is the number of strings and M is the maximum string length.
    // Space Complexity: O(N * M) - Required to track which items have been grouped together.
    public static List<List<String>> groupAnagramsBrute(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) return result;

        boolean[] visited = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) continue;

            List<String> currentGroup = new ArrayList<>();
            currentGroup.add(strs[i]);
            visited[i] = true;

            // Compare with all remaining unvisited strings
            for (int j = i + 1; j < strs.length; j++) {
                if (!visited[j] && isAnagram(strs[i], strs[j])) {
                    currentGroup.add(strs[j]);
                    visited[j] = true;
                }
            }
            result.add(currentGroup);
        }
        return result;
    }

    // Helper method to check if two strings are anagrams using frequency arrays
    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }

    // 2. BETTER APPROACH (HashMap with Sorted Strings as Keys)
    // Time Complexity: O(N * M log M) - Where N is the number of strings and M is the max string length. Sorting each string takes O(M log M).
    // Space Complexity: O(N * M) - Auxiliary space needed to store the characters in the map.
    public static List<List<String>> groupAnagramsBetter(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // Sort the string characters to form a canonical signature key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedKey = String.valueOf(chars);

            // Group original strings by their sorted signature
            if (!map.containsKey(sortedKey)) {
                map.put(sortedKey, new ArrayList<>());
            }
            map.get(sortedKey).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // 3. OPTIMAL APPROACH (HashMap with Frequency Array/String Encoding as Keys)
    // Time Complexity: O(N * M) - Avoids sorting entirely. Scans each character of every string exactly once.
    // Space Complexity: O(N * M) - Auxiliary space needed for the map buckets and the encoded frequency keys.
    public static List<List<String>> groupAnagramsOptimal(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];
            // Count frequencies of each character
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // Build a unique structural string key (e.g., "#1#0#2...") based on frequencies
            StringBuilder sb = new StringBuilder();
            for (int freq : count) {
                sb.append('#');
                sb.append(freq);
            }
            String frequencyKey = sb.toString();

            // Group strings matching this explicit frequency profile
            if (!map.containsKey(frequencyKey)) {
                map.put(frequencyKey, new ArrayList<>());
            }
            map.get(frequencyKey).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // Driver code to test all three implementations
    public static void main(String[] args) {
        // Test Case: Standard anagram list
        String[] strs = {"act", "pots", "tops", "cat", "stop", "hat"};
        System.out.println("Test Case: strs = " + Arrays.toString(strs));
        
        System.out.println("Brute Force:       " + groupAnagramsBrute(strs));
        System.out.println("Better (Sorting):  " + groupAnagramsBetter(strs));
        System.out.println("Optimal (Hashing): " + groupAnagramsOptimal(strs));
    }
}
