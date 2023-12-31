/**
Programmer: Guillermo
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 20ms (beats 11.58%)
Memory: 42.9mb (beats 71.10%)

DS: HashMap<Character, Integer>
Advantage:  O(1) Search
            O(1) Insertion

Algorithm:
    check if lengths are equal
        true ? 
            return false

    build hashmap of size 26 and initialize with lowercase values as keys each with 0 value

    traverse both strings simultaneously
        for each key at index i of both strings
            increment value of key of s
            decrement value of key of t

    build iterator
    iterate through every key-value pair of the hashmap
        if value != 0
            return false
        
    return true
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> hashmap = new HashMap<>(26);
        for (char ch = 'a'; ch <= 'z'; ch++) {
            hashmap.put(ch, 0);
        }
        
        Character c1;
        Character c2;

        for (int i = 0; i < s.length(); i++) {
            // O(1)
            c1 = s.charAt(i);
            // O(1)
            c2 = t.charAt(i);

            // O(1) + O(1)
            hashmap.put(c1, hashmap.get(c1) + 1);
            // O(1) + O(1)
            hashmap.put(c2, hashmap.get(c2) - 1);
        }

        Iterator<Map.Entry<Character, Integer>> mapIterator = hashmap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (mapIterator.next().getValue() != 0)
                return false;
        }

        return true;
    }
}
