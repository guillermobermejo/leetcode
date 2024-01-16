/**
Programmer: Guillermo       M       (VARIABLES)
Time Complexity: O(n log n) 
Space Complexity: O(n)
Runtime: 25ms (beats 85.78%)
Memory: 57.5mb (beats 48.97%)

DS: int[]
Advantage:  O(1) Insertion
            O(1) Access
            psuedo hashset  // where i == value in position array / [i] == speed

Approach:
    use a psuedo hashset i.e., a int[] array, speedmap[] where index is a POSITION in position[] with the value being corresponding speed[i] value
    
    obtain the max position in position[] O(n)
    declare new int[] with size max+1 to store the max position in position[] in the speedmap
    traverse the position[] array storing a position's speed in the speedmap where the index being (position[i]) and the value stored (speed[i])
    
	sort the position array for calculating time 
    
    traverse the sorted position array in REVERSE
    calculate the # fleets based on the time needed to get to the target from right to left
    since a car cannot pass another this, the number of carfleets only increases if a left car's time is GREATER (takes longer to get to target) than the right
    note:
        left_time = target-position / speed
        left_time = target-position / speed
    
    return the number of carfleets at the end of the traversal
        note: naturally ascending by position in time[] initializations
        
Visualization of cars:
    NOTE:   because a car CANNOT pass another car and MUST slow down we must sort based on POSITION then determine fleets based on time to reach target

    postions:   [10,8,0,5,3]                                    postions:   [0,3,5,8,10]
    target:     12                                              target:     12
    
    
    grid:       y-axis: positions     x-axis: index of car
    
    12|                                                         12|
    11|                                                         11|
    10| o                                                       10|                  o
     9|                                                          9|
     8|     o                                                    8|              o   
     7|                                                          7|                     
     6|                             sorted by position -->       6|                     
     5|              o          (increasing - decreasing)        5|          o            
     4|                                                          4|                     
     3|                  o                                       3|      o               
     2|                                                          2|                     
     1|                                                          1|                     
     0|          o                                               0|  o                   
         _   _   _   _   _                                           _   _   _   _   _
         0   1   2   3   4                                           0   1   2   3   4 
    
Algorithm:
    declare & initialize:
        1 int value         name: max       value: -1           notes: will be set to the LARGEST value in nums[] to be used as the size for speedmap[]
    
    for loop (int i = 0; i < nums.length; i++):     // obtain max
        calculate max: for all int i's in nums keep updating max if i is greater
    
    declare & initialize:
        1 int[] array       name: speedmap  value: new int[]    size: max+1                 notes: size max+1 to be able to store raw value max
    
    for loop (int i = 0; i < position.length; i++):    // initialize the speedmap where INDEX: position[i] values & values: speed[i] values
        for all indexes in position[]/speed[] (same size)
        speedmap[position[i]] = speed[i]               // naturally ascending by way of indexing nature
        
    SORT: O(n log n)
        Arrays.sort(position) in order to calculate the time to reach target by positions closest to the target
            note: the right grid line 39
        
    declare & initialize:
 !      1 int value         name: carfleet      value: 1                    notes: set to 1 because there must be at least 1 carfleet (i.e., furthest car the slowest)
        1 int value         name: p             value: position.length-1    notes: set to the rightmost car position (closest to the target)
 
    for loop (int i = position.length-2; i >= 0; i--):      // calculate ALL carfleets based on their final time to reach the target
        traverse the entire position[] array in REVERSE     // this is naturally ascending 
        declare & initialize:
            1 double value      name: left_time     value: target-position[i] / speedmap[position[i]]       notes: the next car to the left
            1 double value      name: right_time    value: target-position[p] / speedmap[position[p]]       notes: the position of the FIRST car in a carfleet
            
        if check:
            if left_time <= right_time
                true ? continue to next iteration of loop
        else:
            carfleet++      // left car time takes LONGER to get to the target so will be a new fleet, increment fleets
            p = i           // the new time to consider will be this current left car at index i i.e., set p to i
    
    return:
        carfleet            // the final number of carfleets that arrive at target
    
Visualization of algorithm:
    position[]:    [10, 8, 0, 5, 3]
    speed[]:       [ 2, 4, 1, 1, 3]
    target:         12
    
    max: 10
    index:                0  1  2  3  4  5  6  7  8  9  10
    speedmap[max+1]:    [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    
    (for all position i in position[] use as index in speedmap with value as corresponding speed -> speed[i])
    
    NOTE: naturally sorts via indexing i.e., resulting from storing positions as indexes
    
        index (positions):    0  1  2  3  4  5  6  7  8  9  10
        speedmap[max+1]:    [ 1, 0, 0, 3, 0, 1, 0, 0, 4, 0, 2]
        
    Arrays.sort(position)   // right grid on line 39
    
    index:            0  1  2  3  4
    position[]:     [ 0, 3, 5, 8,10]
    target:         12
    
    (traverse position[] & calculate TIME to get to target in reverse)
    left_time =     target - position[i] / speedmap[position[i]]
    right_time =    target - position[p] / speedmap[position[p]]
        i.e.,   time to reach target = target - position of car / speed of car
        
    RULES:  if 2 cars arrive to target at the same time, it is 1 car fleet
            if a car arrives faster than another the car on the right it CANNOT PASS IT, time reduced to the right
        
    (stop loop when i < 0)
    
    position.length: 5
    index:            0  1  2  3  4
    position[]:     [ 0, 3, 5, 8,10]
    carfleet: 1
    
        i=3
        p=4
        left_time = 12-8 / 4 = 1
        right_time = 12-10 / 2 = 1
        
        1 <= 1 TRUE     do nothing, left car must reduce time to match right
        
     
    position.length: 5
    index:            0  1  2  3  4
    position[]:     [ 0, 3, 5, 8,10]
    carfleet: 1
    
        i=2
        p=4
        left_time = 12-5 / 1 = 7
        right_time = 12-10 / 2 = 1
        
        7 <= 1 FALSE    new carfleet, new right car (index p) must be set to i // no car can pass this car
        
        carfleet++
        p = i
        
    
    position.length: 5
    index:            0  1  2  3  4
    position[]:     [ 0, 3, 5, 8,10]
    carfleet: 2
    
        i=1
        p=2
        left_time = 12-3 / 3 = 3
        right_time = 12-5 / 1 = 7
        
        3 <= 7 TRUE     do nothing, left car must reduce time to match right
        
    
    position.length: 5
    index:            0  1  2  3  4
    position[]:     [ 0, 3, 5, 8,10]
    carfleet: 2
    
        i=0
        p=2
        left_time = 12-0 / 1 = 12
        right_time = 12-10 / 2 = 1
        
        12 <= 1 FALSE    new carfleet, new right car (index p) must be set to i // no car can pass this car
        
        carfleet++
        p = i
    
    carfleet: 3
        
    i = -1
        
    return carfleet
*/

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int max = -1;
        
        for (int i : position)
            if (i > max) max = i;

        int[] speedmap = new int[max+1];                // psuedo hashset (position -> speed) where i=position speed[i]=speed at position

        for (int i = 0; i < position.length; i++)
            speedmap[position[i]] = speed[i];

        Arrays.sort(position);                          // sort by positions closest to target (increasing)
        
        int carfleet = 1;
        int p = position.length-1;

        for (int i = position.length-2; i >= 0; i--) {
            double left_time = ((double)target - position[i]) / (double)speedmap[position[i]];
            double right_time = ((double)target - position[p]) / (double)speedmap[position[p]];
            
            if (left_time <= right_time) continue;
            else {
                carfleet++;
                p = i;
            }
        }

        return carfleet;
    }
}
