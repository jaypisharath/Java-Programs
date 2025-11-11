import java.util.*;

class ThreeSum {

	/**
	 Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
	 Notice that the solution set must not contain duplicate triplets.
	 **/
	static List<List<Integer>> findZeroSumTriplets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 3) return res;

		Arrays.sort(nums);
		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			// skip duplicates for the first element
			if (i > 0 && nums[i] == nums[i - 1]) continue;

			int left = i + 1;
			int right = n - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == 0) {
					res.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// move left and right skipping duplicates
					int leftVal = nums[left];
					int rightVal = nums[right];
					while (left < right && nums[left] == leftVal) left++;
					while (left < right && nums[right] == rightVal) right--;
				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}

		return res;
	}

	// Small driver with example test cases
	public static void main(String[] args) {
		int[][] tests = new int[][]{
			{-1,0,1,2,-1,-4},
			{0, 0, 0},
			{3, -3, 3, -3, 0},
			{1, -1, -1, 0, 1},
			{},
			{1, 2, 3}
		};

		for (int i = 0; i < tests.length; i++) {
			int[] t = tests[i];
			List<List<Integer>> out = findZeroSumTriplets(t);
			System.out.println("Test " + (i + 1) + ": input=" + Arrays.toString(t) + " -> " + out);
		}
	}
}
