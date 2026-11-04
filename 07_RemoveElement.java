import java.util.Arrays;

public class RemoveElement {

    // 1. BRUTE FORCE APPROACH (Element Shifting)
    // Time Complexity: O(N^2) - In the worst case, we repeatedly shift remaining elements.
    // Space Complexity: O(1) - No extra array is used.
    public static int removeElementBrute(int[] nums, int val) {

        if (nums == null || nums.length == 0) return 0;

        int length = nums.length;
        int i = 0;

        while (i < length) {

            if (nums[i] == val) {

                // Shift all remaining elements one position left
                for (int j = i; j < length - 1; j++) {
                    nums[j] = nums[j + 1];
                }

                // Reduce logical array length
                length--;

                // Check the same position again
            } else {
                i++;
            }
        }

        return length;
    }


    // 2. BETTER APPROACH (Temporary Array)
    // Time Complexity: O(N) - Visit each element once and copy valid elements.
    // Space Complexity: O(N) - Requires an additional array.
    public static int removeElementBetter(int[] nums, int val) {

        if (nums == null || nums.length == 0) return 0;

        int[] temp = new int[nums.length];
        int k = 0;

        // Store only elements that are not equal to val
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {
                temp[k] = nums[i];
                k++;
            }
        }

        // Copy valid elements back to original array
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

        return k;
    }


    // 3. OPTIMAL APPROACH (Two Pointers - Read and Write)
    // Time Complexity: O(N) - Each element is visited exactly once.
    // Space Complexity: O(1) - Modifies the original array without extra storage.
    public static int removeElementOptimal(int[] nums, int val) {

        if (nums == null || nums.length == 0) return 0;

        int w = 0;

        // Read pointer scans every element
        for (int r = 0; r < nums.length; r++) {

            // Keep only elements that are not equal to val
            if (nums[r] != val) {

                nums[w] = nums[r];
                w++;
            }
        }

        return w;
    }


    // 4. OPTIMAL APPROACH (Two Pointers - Swapping)
    // Time Complexity: O(N) - Each element is checked at most once.
    // Space Complexity: O(1) - No additional memory is used.
    // Note: Relative order is not maintained.
    public static int removeElementOptimalSwap(int[] nums, int val) {

        if (nums == null || nums.length == 0) return 0;

        int left = 0;
        int right = nums.length;

        while (left < right) {

            if (nums[left] == val) {

                // Replace unwanted element with the last unchecked element
                nums[left] = nums[right - 1];

                right--;

            } else {

                left++;
            }
        }

        return right;
    }


    // Driver code to test all implementations
    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

        System.out.println("Original Array: " + Arrays.toString(nums));
        System.out.println("Value to Remove: " + val);

        // Use separate copies because each approach modifies the array
        int[] brute = nums.clone();
        int[] better = nums.clone();
        int[] optimal = nums.clone();
        int[] swap = nums.clone();

        int k1 = removeElementBrute(brute, val);
        int k2 = removeElementBetter(better, val);
        int k3 = removeElementOptimal(optimal, val);
        int k4 = removeElementOptimalSwap(swap, val);

        System.out.println("\nBrute Force: " +
                Arrays.toString(Arrays.copyOf(brute, k1)));

        System.out.println("Better: " +
                Arrays.toString(Arrays.copyOf(better, k2)));

        System.out.println("Optimal: " +
                Arrays.toString(Arrays.copyOf(optimal, k3)));

        System.out.println("Optimal Swap: " +
                Arrays.toString(Arrays.copyOf(swap, k4)));
    }
}
