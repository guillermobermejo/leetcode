/**
Programmer: Guillermo            E
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 20ms (beats 11.58%)
Memory: 42.9mb (beats 71.10%)

DS: HashMap<Character, Integer>
Advantage:  O(1) Search
            O(1) Insertion

Approach:
    check if both strings contain the same set number of characters each
    use hashmap inisialized with every lowercase with 0 value

    traverse both strings simultaneously
    increment hashmap value by 1 for current key for string s
    decrement hashmap value by 1 for current key for string t

Algorithm:
    if check:
        s.length == t.length 
        true ? 
            return false (must be equal length)

    declare hahsmap:
        hahsmap<character, integer>    size: 26    name: hashmap

    initialize:
        hashmap with every character a - z with 0 value
        use for loop: loop value 'a' loop terminating 'z'

    declare 2 character objects:
        character c1
        character c2

    for loop:
        traverse both strings simultaneously
        for each key at index i of both strings
            increment value of key s.charAt(i) in hashmap
            decrement value of key t.charAt(i) in hashmap
            note:
                hashmap.put(__, hashmap.get(__) +/- 1)

    declare iterator:
        iterator<map.entry<character, integer>>    name: mapIterator
        note:
            = hashmap.entrySet().iterator()

    while loop (mapIterator.hasNext()):
        iterate through every key-value pair of the hashmap
        if value != 0
            return false
        note:
            value = mapIterator.next().getValue()
        
    return:
        true
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
