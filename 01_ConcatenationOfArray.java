import java.util.Arrays;

public class ArrayConcatenation {

    // 1. BRUTE FORCE APPROACH
    // Time Complexity: O(N) - Loops 2N times (two separate passes)
    // Space Complexity: O(1) - Auxiliary space (excluding the output array)
    public static int[] getConcatenationBrute(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
      
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
        }
        
        for (int i = 0; i < n; i++) {
            ans[i + n] = nums[i];
        }
        
        return ans;
    }

    // 2. BETTER APPROACH (Using Native System Methods)
    // Time Complexity: O(N) - Blazing fast due to low-level memory block copying via System.arraycopy
    // Space Complexity: O(1) - Auxiliary space
    public static int[] getConcatenationBetter(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        
        System.arraycopy(nums, 0, ans, 0, n);
        System.arraycopy(nums, 0, ans, n, n);
        
        return ans;
    }

    // 3. OPTIMAL APPROACH (Single Pass Optimization)
    // Time Complexity: O(N) - Exactly N iterations, populating both halves simultaneously
    // Space Complexity: O(1) - Auxiliary space
    public static int[] getConcatenationOptimal(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        
        for (int i = 0; i < n; i++) {
            ans[i] = ans[i + n] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println("Input Array: " + Arrays.toString(nums));

        System.out.println("Brute Force Result: " + Arrays.toString(getConcatenationBrute(nums)));
        System.out.println("Better Approach Result: " + Arrays.toString(getConcatenationBetter(nums)));
        System.out.println("Optimal Approach Result: " + Arrays.toString(getConcatenationOptimal(nums)));
    }
}
