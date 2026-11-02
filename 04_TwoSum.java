import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // 1. BRUTE FORCE APPROACH
    // Time Complexity: O(N^2) - Checks every possible pair using nested loops
    // Space Complexity: O(1) - No auxiliary space allocated
    public static int[] twoSumBrute(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // Fallback (problem guarantees exactly one solution)
    }

    // 2. BETTER APPROACH (Sorting + Two Pointers)
    // Time Complexity: O(N log N) - Dominated by the custom pair sorting step
    // Space Complexity: O(N) - Allocated to store original indices before sorting
    public static int[] twoSumBetter(int[] nums, int target) {
        int n = nums.length;
        int[][] sortedWithIndices = new int[n][2];
        
        // Store both the value and its original index
        for (int i = 0; i < n; i++) {
            sortedWithIndices[i][0] = nums[i];
            sortedWithIndices[i][1] = i;
        }
        
        // Sort the pairs based on the element values
        Arrays.sort(sortedWithIndices, (a, b) -> Integer.compare(a[0], b[0]));
        
        int left = 0;
        int right = n - 1;
        
        // Use two pointers to converge on the target sum
        while (left < right) {
            int currentSum = sortedWithIndices[left][0] + sortedWithIndices[right][0];
            
            if (currentSum == target) {
                int index1 = sortedWithIndices[left][1];
                int index2 = sortedWithIndices[right][1];
                // Ensure the smaller index is returned first
                return new int[]{Math.min(index1, index2), Math.max(index1, index2)};
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[]{};
    }

    // 3. OPTIMAL APPROACH (HashMap Hashing)
    // Time Complexity: O(N) - Single pass through the array with O(1) average lookups
    // Space Complexity: O(N) - Worst case storing all elements in the hash map
    public static int[] twoSumOptimal(int[] nums, int target) {
        // Map stores: Value -> Original Index
        HashMap<Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Check if the complement value already exists in the map
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            
            // If not found, store the current value and index for future lookups
            numMap.put(nums[i], i);
        }
        
        return new int[]{};
    }

    // Driver code to test all three implementations
    public static void main(String[] args) {
        // Test Case 1: Standard target match
        int[] nums1 = {3, 4, 5, 6};
        int target1 = 7;
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Brute Force: " + Arrays.toString(twoSumBrute(nums1, target1)));
        System.out.println("Better (Sorting): " + Arrays.toString(twoSumBetter(nums1, target1)));
        System.out.println("Optimal (HashMap): " + Arrays.toString(twoSumOptimal(nums1, target1)));
        
        System.out.println("---------------------------------------");

        // Test Case 2: Negative numbers present
        int[] nums2 = {-1, -2, -3, -4, -5};
        int target2 = -8;
        System.out.println("Test Case 2: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Brute Force: " + Arrays.toString(twoSumBrute(nums2, target2)));
        System.out.println("Better (Sorting): " + Arrays.toString(twoSumBetter(nums2, target2)));
        System.out.println("Optimal (HashMap): " + Arrays.toString(twoSumOptimal(nums2, target2)));
    }
}
