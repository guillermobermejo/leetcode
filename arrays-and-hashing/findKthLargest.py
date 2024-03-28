'''
Programmer: Guillermo       	M	*Cracked*
Language: Python3
Time Complexity: O(n) 
Space Complexity: O(n)
Runtime: 428ms (beats 96.06%)
Memory: 30mb (beats 35.62%)

DS: int[]   as number map where value at index i is a value in nums[]
Advantage:  O(1) number times encountered search
            O(1) insertion

Approach:
    use 2 int[] maps for the val constraint range of -10^4 <= nums[i] <= 10^4, 1 for positives (& 0) 1 for negatives
    
	traverse all of nums[] array and store every value nums[i] in either neg_map if negative and pos_map if positive
	value nums[i] treated as index in neg_map[] such that neg_map[nums[i]]++ counts specific occurence of negative nums[i]
	value nums[i] treated as index in pos_map[] such that pos_map[nums[i]]++ counts specific occurence of positive nums[i]
	neg_map[10000+1] will hold all negative values (* -1 to work with indexes) all index numbers > 0 && <= 10000
	pos_map[10000+1] will hold all positive values (includes 0) all indexe numbers >= 0 && <= 10000
	
	traverse nums[] array increment nums[i] as index in either neg_map if nums[i] < 0 else pos_map[] as occurrences
	traverse pos_map first from right to left (larger to smallest)
		if a value at pos_map[i] is > 0 there was an occurrence of this index in nums[]
			while pos_map[i] is not 0 (has occurrences of this number not processed)
				if k == 1 return this index i as it is the kth largest if not then decrement both pos_map[i] and k
				
	traverse neg_map first from left to right (larger to smallest concerning negatives)
		if a value at neg_map[i] is > 0 there was an occurrence of this index in nums[]
			while neg_map[i] is not 0 (has occurrences of this number not processed)
				if k == 1 return this index i as it is the kth largest if not then decrement both neg_map[i] and k
	
	return -1		// this line is only reached in the case of a mismatch where k > len(nums)
    
Algorithm:
    declare & initialize:
		1 int[] array		name: pos_map		size: 10000+1		notes: the array that will hold positive values + 0
		1 int[] array		name: neg_map		size: 10000+1		notes: the array that will hold negative values
		
	for loop: (i in range(len(pos_map))) // traverse and increment values at pos_map[i] if >= 0 and neg_map[i*-1] if < 0
		if check:
			if i < 0:
                neg_map[i*-1]+= 1
			else:
                pos_map[i]+= 1
		
	for loop: (i in range(len(pos_map)))  // traverse right to left (largest to smallest for positives)
        declare & initialize:
            1 int value     name: j     value: len(pos_map)-i-1     notes: the corrected index in reverse order
		if check:
			if pos_map[j] != 0		// if value at pos_map[i] != 0, its a nums[] val, check for kth largest & decrement 
				while loop: (pos_map[j] != 0)			// check if still an occurrence (not 0) then decrement after 
					if check:
						if k == 1 return j 	// k == 1 means we are at the largest, k decremented after each if check
                    
                    update values:
                        k -= 1
                        pos_map[j]-= 1
						
	for loop: (i in range(len(neg_map)))   // traverse left to right (largest to smallest for negatives)
		if check:
			if neg_map[i] != 0		// if value at neg_map[i] != 0, its a nums[] val, check for kth largest & decrement 
				while loop: (neg_map[i] != 0)			// check if still an occurrence (not 0) then decrement after 
					if check:
						if k == 1 return i 	// k == 1 means we are at the largest, k decremented after each if check
                        
                update values:
                    k -= 1
                    neg_map[i] -= 1
						
	return:
		return -1	// this line is only reached in the case of a mismatch where k > len(nums)
		
Visualization of Algorithm:
	nums:	[-3,-2,-2, 5, 4, 4]
	k: 		5	// need 5th largest meaning when k == 1 while decrememting value of index is immediately returned 
	
	traversing nums[] and incrementing maps[nums[i]]++ for every nums[i] occurrence
	
	traverse:	 <-----------------------------
	index:		  0  1  2  3  4  5  6  7  10000
	pos_map:	[ 0, 0, 0, 0, 2, 1, 0, 0 ,....]
	
	traverse:	 ------------------------------>
	index:		  0  1  2  3  4  5  6  7  10000
	neg_map:	[ 0, 0, 2, 1, 0, 0, 0, 0 ,....]
	
	traverse pos_map from right to left until pos_map[i] != 0
	
	(pos_map[10000] -> pos_map[6] == 0)
	
	pos_map[5] != 0 	// value = 5		occurrence = 1		k = 5
		5 is an occurrence in nums[]
			loop & decrement pos_map[5] until 0 and test for k == 1 
			1st iteration:
				pos_map[5] == 0 ? -> false pos_map[5] == 1
					if k == 1 ? -> false k == 5
                    
                pos_map[5] -= 1         // was 1 but now 0
                k -= 1                  // was 5 but now 4
			
			2nd iteration:
				pos_map[5] == 0 ? -> true pos_map[5] == 0 exit
	
	pos_map[4] != 0 	// value = 4		occurrence = 2		k = 4
		4 is an occurrence in nums[]
			loop decrement pos_map[4] until 0 and test for k-- == 1 
			1st iteration:
				pos_map[4] == 0 ? -> false pos_map[4] == 2 
					if k == 1 ? -> false k == 4
                    
                pos_map[4] -= 1         // was 2 but now 1
                k -= 1                  // was 4 but now 3
			
			2nd iteration:
				pos_map[4] == 0 ? -> false pos_map[4] == 1
					if k == 1 ? -> false k == 3
                    
                pos_map[4] -= 1         // was 1 but now 0
                k -= 1                  // was 3 but now 2
					
			3rd iteration:
				pos_map[4] == 0 ? -> true pos_map[4] == 0 exit
				
	(pos_map[3] -> pos_map[0] == 0)
	
	all positive value checks exhausted at this point in pos_map go to neg_map
		
	traverse neg_map from left to right until neg_map[i] != 0
	
	(neg_map[1] == 0)
	
	neg_map[2] != 0 	// value = -2		occurrence = 2		k = 2
		-2 is an occurrence in nums[]
			loop decrement neg_map[2] until 0 and test for k == 1 
			1st iteration:
				neg_map[2] == 0 ? -> false neg_map[2] == 2
					if k == 1 ? -> false k == 2
                    
                neg_map[2] -= 1         // was 2 but now 1
                k -= 1                  // was 2 but now 1
			
			LAST iteration:
				neg_map[2] == 0 ? -> false neg_map[2] == 1
					if k == 1 ? -> true k == 1
						RETURN i*-1		// index i == 2
	
	returns: 5th largest value in nums: [-3,-2,-2, 5, 4, 4]
	returns: -2	
'''

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        pos_map = [0] * (10000+1)       # declaring array of size n syntax
        neg_map = [0] * (10000+1)
        
        for i in nums:
            if i < 0:
                neg_map[i*-1]+= 1
            else:
                pos_map[i]+= 1
        
        for i in range(len(pos_map)):
            j = len(pos_map)-i-1        # adjust value j to reverse index (1 -> size-2)
            if pos_map[j] != 0:
                while pos_map[j] != 0:
                    if k == 1:
                        return j
                
                    k -= 1
                    pos_map[j]-= 1
            
        
        for i in range(len(neg_map)):
            if neg_map[i] != 0:
                while neg_map[i] != 0:
                    if k == 1:
                        return i*-1
                    
                    k -= 1
                    neg_map[i] -= 1

        return -1
