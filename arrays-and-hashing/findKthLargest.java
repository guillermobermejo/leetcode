class Solution {
    public int findKthLargest(int[] nums, int k) {
        int[] pos_map = new int[10_000+1];
        int[] neg_map = new int[10_000+1];
        
        for (int i : nums) {
            if (i < 0)
                neg_map[i*-1]++;
            else 
                pos_map[i]++;
                
        }
        
        for (int i = pos_map.length-1; i > -1; i--) {
            if (pos_map[i] != 0)
                while (pos_map[i]-- != 0)
                    if (k-- == 1) return i;
        
        }
        
        for (int i = 1; i < neg_map.length-1; i++) {
            if (neg_map[i] != 0) {
                while (neg_map[i]-- != 0)
                    if (k-- == 1)
                        return i*-1;
            }
        }
           
        return -1;
    }
}
