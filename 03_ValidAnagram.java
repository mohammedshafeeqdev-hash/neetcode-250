import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    // 1. BRUTE FORCE APPROACH
    // Time Complexity: O(N^2) - Iterates through string 's' and manually finds/removes characters in 't'
    // Space Complexity: O(N) - Converts string 't' to a StringBuilder to allow character deletion
    public static boolean isAnagramBrute(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        StringBuilder sb = new StringBuilder(t);
        
        // Loop through each character of the first string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = sb.indexOf(String.valueOf(ch));
            
            // If the character is found, remove it from the StringBuilder
            if (index != -1) {
                sb.deleteCharAt(index);
            } else {
                return false;
            }
        }
        
        // If all characters match, the StringBuilder will be empty
        return sb.length() == 0;
    }

    // 2. BETTER APPROACH (Sorting)
    // Time Complexity: O(N log N) - Dominated by the character array sorting step
    // Space Complexity: O(N) - Allocated memory for converting strings to character arrays
    public static boolean isAnagramBetter(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Convert strings to character arrays
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        // Sort both character arrays alphabeticaly
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        // Compare the sorted arrays
        return Arrays.equals(sChars, tChars);
    }

    // 3. OPTIMAL APPROACH (Frequency Counter Array / HashMap Hashing)
    // Time Complexity: O(N) - Single pass through both strings to count frequencies
    // Space Complexity: O(1) - Fixed-size integer array of size 26 (constant memory)
    public static boolean isAnagramOptimal(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Frequency bucket array for 26 lowercase English letters
        int[] charCounts = new int[26];

        // Increment count for string s, decrement count for string t
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        // If it's a valid anagram, every bucket balance must be exactly zero
        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    // Driver code to test all three implementations
    public static void main(String[] args) {
        // Test Case 1: Valid Anagram
        String s1 = "racecar";
        String t1 = "carrace";
        System.out.println("Test Case 1: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Brute Force: " + isAnagramBrute(s1, t1));
        System.out.println("Better (Sorting): " + isAnagramBetter(s1, t1));
        System.out.println("Optimal (Frequency Counter): " + isAnagramOptimal(s1, t1));
        
        System.out.println("---------------------------------------");

        // Test Case 2: Invalid Anagram
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Test Case 2: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Brute Force: " + isAnagramBrute(s2, t2));
        System.out.println("Better (Sorting): " + isAnagramBetter(s2, t2));
        System.out.println("Optimal (Frequency Counter): " + isAnagramOptimal(s2, t2));
    }
}
