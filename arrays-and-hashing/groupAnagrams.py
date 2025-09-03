class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # default entry empty list []
        anagrams = defaultdict(list)

        for string in strs:
            ascii_key = [0] * 26

            for char in string:
                ascii_key[ord(char) - ord('a')] += 1
            
            # hash the tuple and use as key, all anagrams will be stored correctly with key
            anagrams[tuple(ascii_key)].append(string)

        return list(anagrams.values())
