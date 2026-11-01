import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {

    // 1. BRUTE FORCE APPROACH
    // Time Complexity: O(N^2) - Compares every pair using nested loops
    // Space Complexity: O(1) - No extra memory allocated
    public static boolean containsDuplicateBrute(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 2. BETTER APPROACH (Sorting)
    // Time Complexity: O(N log N) - Dominated by the sorting step
    // Space Complexity: O(1) or O(N) - Depends on the built-in sorting algorithm's implementation
    public static boolean containsDuplicateBetter(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    // 3. OPTIMAL APPROACH (HashSet Hashing)
    // Time Complexity: O(N) - Single pass through the array with O(1) average lookups
    // Space Complexity: O(N) - Worst case storing all unique elements in the set
    public static boolean containsDuplicateOptimal(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    // Driver code to test all three implementations
    public static void main(String[] args) {
        // Test Case 1: Contains duplicates
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Brute Force: " + containsDuplicateBrute(nums1));
        System.out.println("Better (Sorting): " + containsDuplicateBetter(nums1.clone())); // clone to preserve original array
        System.out.println("Optimal (HashSet): " + containsDuplicateOptimal(nums1));
        
        System.out.println("---------------------------------------");

        // Test Case 2: All unique elements
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        System.out.println("Brute Force: " + containsDuplicateBrute(nums2));
        System.out.println("Better (Sorting): " + containsDuplicateBetter(nums2.clone()));
        System.out.println("Optimal (HashSet): " + containsDuplicateOptimal(nums2));
    }
}
