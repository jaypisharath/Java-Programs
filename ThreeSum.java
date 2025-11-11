import java.util.*;

class ThreeSum {

	/**
	 Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
	 Notice that the solution set must not contain duplicate triplets.
	 **/
	static List<List<Integer>> findZeroSumTriplets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 3) return res;

		// Build frequency map of values (counts)
		Map<Integer, Integer> freq = new HashMap<>();
		for (int v : nums) freq.put(v, freq.getOrDefault(v, 0) + 1);

		int n = nums.length;
		// Use a set to avoid duplicate triplets. We'll normalize each triplet (small->large)
		Set<String> seen = new HashSet<>();

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int a = nums[i];
				int b = nums[j];
				int need = -a - b;
				int totalAvailable = freq.getOrDefault(need, 0);
				if (totalAvailable == 0) continue;

				// subtract usages of a and b if they equal need
				int availableAfterUsingPair = totalAvailable;
				if (need == a) availableAfterUsingPair--;
				if (need == b) availableAfterUsingPair--;
				if (availableAfterUsingPair <= 0) continue; // no third distinct index left for `need`

				// normalize triplet (sort three values locally)
				int x = a, y = b, z = need;
				int tmp;
				if (x > y) { tmp = x; x = y; y = tmp; }
				if (y > z) { tmp = y; y = z; z = tmp; }
				if (x > y) { tmp = x; x = y; y = tmp; }

				String key = x + "#" + y + "#" + z;
				if (!seen.contains(key)) {
					seen.add(key);
					res.add(Arrays.asList(x, y, z));
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
