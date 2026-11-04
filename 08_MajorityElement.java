import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    // 1. BRUTE FORCE APPROACH (Nested Loop with Frequency Counting)
    // Time Complexity: O(N^2) - For every element, count its occurrences by scanning the array.
    // Space Complexity: O(1) - No additional data structures are used.
    public static int majorityElementBrute(int[] nums) {

        if (nums == null || nums.length == 0) return -1;

        for (int i = 0; i < nums.length; i++) {

            int count = 0;

            // Count occurrences of the current element
            for (int j = 0; j < nums.length; j++) {

                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            // Check whether it is the majority element
            if (count > nums.length / 2) {
                return nums[i];
            }
        }

        return -1;
    }


    // 2. BETTER APPROACH (HashMap with Frequency Counting)
    // Time Complexity: O(N) - Each element is visited once.
    // Space Complexity: O(N) - HashMap stores element frequencies.
    public static int majorityElementBetter(int[] nums) {

        if (nums == null || nums.length == 0) return -1;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            // Increase frequency of current element
            map.put(num, map.getOrDefault(num, 0) + 1);

            // Check if current element has become the majority
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }

        return -1;
    }


    // 3. OPTIMAL APPROACH (Sorting)
    // Time Complexity: O(N log N) - Sorting takes O(N log N).
    // Space Complexity: O(1) auxiliary space for in-place sorting (implementation-dependent).
    public static int majorityElementSorting(int[] nums) {

        if (nums == null || nums.length == 0) return -1;

        Arrays.sort(nums);

        // The majority element must occupy the middle position
        return nums[nums.length / 2];
    }


    // 4. MOST OPTIMAL APPROACH (Boyer-Moore Voting Algorithm)
    // Time Complexity: O(N) - One traversal of the array.
    // Space Complexity: O(1) - Only two variables are used.
    public static int majorityElementOptimal(int[] nums) {

        if (nums == null || nums.length == 0) return -1;

        int candidate = 0;
        int count = 0;

        for (int num : nums) {

            // If count becomes zero, select a new candidate
            if (count == 0) {
                candidate = num;
            }

            // Same element increases count; different element decreases it
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }


    // Driver code to test all implementations
    public static void main(String[] args) {

        int[] nums = {5, 5, 1, 1, 1, 5, 5};

        System.out.println("Original Array: " + Arrays.toString(nums));

        System.out.println("Brute Force: " +
                majorityElementBrute(nums.clone()));

        System.out.println("Better (HashMap): " +
                majorityElementBetter(nums.clone()));

        System.out.println("Sorting: " +
                majorityElementSorting(nums.clone()));

        System.out.println("Optimal (Boyer-Moore): " +
                majorityElementOptimal(nums.clone()));
    }
}
