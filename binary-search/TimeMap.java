/**
Programmer: Guillermo       M		
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 131ms (beats 91.78%)
Memory: 119.9mb (beats 46.87%)

DS: HashMap<String, ArrayList<Pair<Integer, String>>>
Advantage:  O(1) Key search
            O(1) Amortized insertion

Approach:
    use a hashmap where keys are strings and values an arraylist of pairs of an integer representing the timestamp and a string
    
    constructor
        declare & initialize hashmap
    
    set
        if key non existant declare & initialize new arraylist then add key and timestamp as pair
        if key exists add to end of value of key (arraylist)
        
    get
        if arraylist non existant for the key, return ""
        if arraylist exists for the key
            perform binary search where if value not found find next smallest timestamp amd return value
            if timestamp exists, return value of pair assiciated with the key == timestamp
        
Algorithm:
    timemap class:
        declare class member:
            name: map       type: HashMap<String, Pair<Integer, String>>        notes: the hashmap containing the key value and timestamp
        
        TimeMap():
            initialize map:
                value: new HashMap<>()
        
        set(String key, String value, int timestamp):
            if check:
                if the key DOES NOT currently has a arraylist allocated (map.get(key) == null) i.e., key not registered
                    declare & initialize new arraylist and add a new pair of timestamp, value
                    put key and initialized array as value of key in map
                else the key DOES exist
                    add new pair of timestamp, value to the END of the arraylist (map.get(key).add(new Pair<>(timestamp, value)))
        
        get(String key, int timestamp):
            declare arraylist pointer:
                1 ArrayList<Pair<Integer, String>> variable     name: lst       value: map.get(key)         notes: pointer to key's value
            
            declare & initialize:   
                1 int value     name: l     value: 0                notes: left pointer to list index for binary search
                1 int value     name: r     value: lst.size()-1     notes: right pointer to list index for binary search
                1 int value     name: m     value: 0                notes: middle pointer to list to be checked index in binary search
            
            while loop (l >= r):
                if check:
                    if lst.get(m).getKey() == timestamp
                        true ? we FOUND value, return lst.get(m).getValue()
                        false ? continue this iteration of the loop (calculate pointers)

                    calculate pointers:
                        l = timestamp > lst.get(m).getKey() ? m+1 : l  // if timestamp is greater than the middle value, l = m
                        r = timestamp < lst.get(m).getKey() ? m-1 : r  // if timestamp is less than the middle value, r = m

            return:
                timestamp < lst.get(0).getKey() ? "" : 									// covers 2 elements in lst (timestamp less)
                    timestamp < lst.get(m).getKey() ? lst.get(m-1).getValue() : 		// covers immediate middle > timestamp (left)
                    lst.get(m).getValue(); // if outside the loop, target NOT in nums	// covers everything else
                
            

*/

class TimeMap {
    HashMap<String, ArrayList<Pair<Integer, String>>> map;
    

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (map.get(key) == null) {
            ArrayList<Pair<Integer, String>> lst = new ArrayList<>();
            lst.add(new Pair<>(timestamp, value));
            map.put(key, lst);
        }
        else 
            map.get(key).add(new Pair<>(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        ArrayList<Pair<Integer, String>> lst = map.get(key);
        
        if (lst == null)
            return "";
        
        int l = 0;
        int r = lst.size()-1;
        int m = -1;
        
        while (l <= r) {
            m = (l+r) / 2;
            
            if (lst.get(m).getKey() == timestamp)
                return lst.get(m).getValue();
            
            l = timestamp > lst.get(m).getKey() ? m+1 : l;
            r = timestamp < lst.get(m).getKey() ? m-1 : r;
        }
            
        return timestamp < lst.get(0).getKey() ? "" : timestamp < lst.get(m).getKey() ? lst.get(m-1).getValue() : lst.get(m).getValue();
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */