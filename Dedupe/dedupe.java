class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) return 0;
        int pos = 0;
        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[pos]) {
                pos++;
                nums[pos] = nums[readIndex];
                System.out.println("pos: " + pos + ", readIndex: " + readIndex + ", nums[readIndex]: " + nums[readIndex] +", nums[pos]: " + nums[pos]);
            }
        }
        return pos+1;        
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = solution.removeDuplicates(nums);
        System.out.println("Length after removing duplicates: " + k);
        System.out.print("Deduped Elements: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");    
    }
}
}