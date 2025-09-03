class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if len(s) == 0:
            return 0
        if len(s) == 1:
            return 1

        left = 0
        right = 1

        ascii_map = [0] * 127
        count = 0

        ascii_map[ord(s[0])] = 1
        count = 1
        max_count = 1

        while right < len(s):
            key = ord(s[right])

            if ascii_map[key] == 0:
                ascii_map[key] += 1
                count += 1

                if count > max_count:
                    max_count = count
            else:
                key = ord(s[left])
                
                ascii_map[key] -= 1
                count -= 1
                left += 1
                continue
            
            right += 1
        
        return max_count
