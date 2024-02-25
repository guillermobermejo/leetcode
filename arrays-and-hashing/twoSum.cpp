#include <unordered_map>
#include <iostream>
using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> hashmap;
        vector<int> sol(2);
        int remainder = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            remainder = target - nums[i];
            
            if (hashmap.find(remainder) != hashmap.end()) {
                if (i == hashmap.at(remainder))
                    continue;
                
                sol[0] = hashmap.at(remainder);
                sol[1] = i;
                
                return sol;
            }
            
            hashmap.insert({nums[i], i});
        }
        
        return sol;
    }
};
