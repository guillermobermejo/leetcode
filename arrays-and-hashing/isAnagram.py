class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        ascii_map = [0] * 26

        for char in s:
            ascii_map[ord(char) - ord('a')] += 1

        for char in t:
            ascii_map[ord(char) - ord('a')] -= 1

        for i in range(26):
            if ascii_map[i] != 0:
                return False

        return True
