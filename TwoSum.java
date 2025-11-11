import java.util.*;

class TwoSum {

	/**
	 * Return all unique doublets [a, b] such that a + b == 0 and a, b come from nums (i != j).
	 * Each doublet is returned with the smaller element first (so negatives come before positives).
	 * Duplicate doublets are avoided: e.g., only one of [-1, 1] is returned even if multiple occurrences exist.
	 */
	static List<List<Integer>> findZeroSumDoublets(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);

		List<List<Integer>> res = new ArrayList<>();

		// Handle 0 specially: need at least two zeros to form [0,0]
		if (freq.getOrDefault(0, 0) >= 2) {
			res.add(Arrays.asList(0, 0));
		}

		// For each positive number, check for its negative counterpart to avoid duplicates
		for (int key : new HashSet<>(freq.keySet())) {
			if (key == 0) continue;
			if (key < 0) {
				int neg = key;
				int pos = -neg;
				if (freq.containsKey(pos)) {
					res.add(Arrays.asList(neg, pos));
				}
			}
		}

		return res;
	}

	// Small driver with example test cases
	public static void main(String[] args) {
		int[][] tests = new int[][]{
			{1, -1, 2, -2, 1},
			{0, 0, 0},
			{3, -3, 3},
			{},
			{1, 2, 3}
		};

		for (int i = 0; i < tests.length; i++) {
			int[] t = tests[i];
			List<List<Integer>> out = findZeroSumDoublets(t);
			System.out.println("Test " + (i + 1) + ": input=" + Arrays.toString(t) + " -> " + out);
		}
	}
}
