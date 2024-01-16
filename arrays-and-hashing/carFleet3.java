/**
Programmer: Guillermo       M		(NO SORT)
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 14ms (beats 96.83%)
Memory: 58.4mb (beats 30.96%)

DS: int[]
Advantage:  O(1) Insertion
            O(1) Access
            psuedo hashset  // where i == value in position array / [i] == speed
            
DS: int[]
Advantage:  O(1) Insertion
            O(1) Access
            storing car time to arrive at target  // naturally ascending

Approach:
    use a psuedo hashset i.e., a int[] array, speedmap[] where index is a POSITION in nums[] with the value being corresponding speed[i] value
    
    obtain the max position in position[] O(n)
    declare new int[] with size max+1 to store the max position in position[] in the speedmap
    traverse the position[] array storing a position's speed in the speedmap where the index being (position[i]) and the value stored (speed[i])
	
    declare new double[] with size position.length to store the TIME needed to reach the target (avoids calculating while traversing)
        note: time[i] = target - position / speed
	store the time by traversing the speedmap[] array, NATURALLY ASCENDING means we do not need to sort (i.e., do work if value NOT 0)
    
    calculate number of fleets traversing the time[] array in reverse
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
    
    for loop (int i = 0; i < position.length; i++):     // initialize the speedmap where INDEX: position[i] values & values: speed[i] values
        for all indexes in position[]/speed[] (same size)
        speedmap[position[i]] = speed[i]                // naturally ascending by way of indexing nature
    
    declare & initialize: 
        1 double[] array    name: time      value: new double[]     size: position.length   notes: stores the time needed to reach target for EACH car
        1 int value         name: p         value: 0										notes: the value of the next index in time[] for storage
    
    for loop (int i = 0; i < speedmap.length; i++):
        for all indexes in speedmap if there is a value NOT 0 at speedmap[i] then it is a valid position and we this car's time to reach the target at p++
        if check:
            speedmap[i] != 0        i.e.,   speedman[i] == 0 -> continue;
                true ? time[p++] = (target - i) / speedmap[i]       // NOTE: index i is the POSITION of a car i.e., the values in position[]
        
    declare & initialize:
 !      1 int value         name: carfleet      value: 1                notes: set to 1 because there must be at least 1 carfleet (i.e., furthest car the slowest)
                            name: p             value: time.length-1    notes: set to the last cars time value i.e., the rightmost car on a grid
 
    for loop (int i = time.length-2; i >= 0; i--): 		// calculate ALL carfleets based on their final time to reach the target
        traverse the entire time[] array in REVERSE     // this is naturally ascending 
        if check:
            if time[i] <= time[p]
                true ? continue to next iteration of loop
        else:
            carfleet++      // left car time takes LONGER to get to the target so will be a new fleet, increment fleets
            p = i           // the new time to consider will be this current left car at index i i.e., set p to i
    
    return:
        carfleet            // the final number of carfleets that arrive at target
    
Visualization of algorithm:
    position[]:     [10, 8, 0, 5, 3]
    speed[]:        [ 2, 4, 1, 1, 3]
    target:         12
    
    max: 10
    index:                0  1  2  3  4  5  6  7  8  9  10
    speedmap[max+1]:    [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    
    (for all position i in position[] use as index in speedmap with value as corresponding speed -> speed[i])
    
    NOTE: naturally sorts via indexing i.e., resulting from storing positions as indexes
    
        index (positions):    0  1  2  3  4  5  6  7  8  9  10
        speedmap[max+1]:    [ 1, 0, 0, 3, 0, 1, 0, 0, 4, 0, 2]
    
    (traverse speedmap[] & calculate VALID positions (non 0 value) TIME to get to target from)
    time[i] =   target - i / speedmap[i]
        i.e.,   time to reach target = target - position of car / speed of car
    
    NOTE: p is the pointer to the location of the next storage in time[] i.e., the next car in increasing order
    
    position.length: 5
    index:                    0  1  2  3  4
    time[position.length]:  [ 0, 0, 0, 0, 0]
    p: 0
    
        i=0
        speedmap[0] = 1     // VALID
             0->1
        time[p++] = 12-0 / 1 = 12
    
    index:                    0  1  2  3  4
    time[position.length]:  [12, 0, 0, 0, 0]
    p: 1
    
        i=1
        speedmap[1] = 0     // NOT VALID (no car here)
        
        i=2
        speedmap[2] = 0     // NOT VALID (no car here)
        
        i=3
        speedmap[3] = 3     // VALID
             1->2
        time[p++] = 12-3 / 3 = 3
    
    index:                    0  1  2  3  4
    time[position.length]:  [12, 3, 0, 0, 0]
    p: 2
    
        i=4
        speedmap[4] = 0     // NOT VALID (no car here)
        
        i=5
        speedmap[5] = 1     // VALID
             2->3
        time[p++] = 12-5 / 1 = 7
    
    index:                    0  1  2  3  4
    time[position.length]:  [12, 3, 7, 0, 0]
    p: 3
    
        i=6
        speedmap[6] = 0     // NOT VALID (no car here)
        
        i=7
        speedmap[7] = 0     // NOT VALID (no car here)
        
        i=8
        speedmap[8] = 4     // VALID
             3->4
        time[p++] = 12-8 / 4 = 1
    
    index:                    0  1  2  3  4
    time[position.length]:  [12, 3, 7, 1, 0]
    p: 4
    
        i=9
        speedmap[9] = 0     // NOT VALID (no car here)
        
        i=10
        speedmap[10] = 2     // VALID
             4->5
        time[p++] = 12-10 / 2 = 1
        
    index:                    0  1  2  3  4
    time[position.length]:  [12, 3, 7, 1, 1]
        
        
    (traverse time[] in REVERSE with i=3 (time.length-2))
    
    RULES:  if 2 cars arrive to target at the same time, it is 1 car fleet
            if a car arrives faster than another the car on the right it CANNOT PASS IT, time reduced to the right
                       i  p
    index:    0  1  2  3  4
    time[]: [12, 3, 7, 1, 1]
    p: 4 (time.length-1)
    i: 3
    carfleet: 1
    
    time[3] <= time[4]  
        1 <= 1 TRUE     do nothing, left car must reduce time to match right
        
    
                    i     p
    index:    0  1  2  3  4
    time[]: [12, 3, 7, 1, 1]
    p: 4 
    i: 2
    carfleet: 1
        
    time[2] <= time[4]  
        7 <= 1 FALSE    new carfleet, new times must be set to time[i] // no car can pass this car
        
        carfleet++
        p=i
        
                   
                 i  p
    index:    0  1  2  3  4
    time[]: [12, 3, 7, 1, 1]
    p: 2
    i: 1
    carfleet: 2
        
    time[1] <= time[2]  
        3 <= 7 TRUE     do nothing, left car must reduce time to match right
        
        
              i     p
    index:    0  1  2  3  4
    time[]: [12, 3, 7, 1, 1]
    p: 2
    i: 0
    carfleet: 2
        
    time[0] <= time[2]  
        12 <= 7 TRUE    new carfleet, new times must be set to time[i] // no car can pass this car
        
        carfleet++
        
    carfleet: 3
    
    i=-1 exit loop
    
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

        double[] time = new double[position.length];    // store time needed to reach target per car (naturally ascending / bypass sort)
        int p = 0;

        for (int i = 0; i < speedmap.length; i++){      // i = position
            if (speedmap[i] == 0) continue;
                
            time[p++] = ((double)target - i) / (double)speedmap[i];
        }

        int carfleet = 1;
        p = time.length-1;                              // NOT NEEDED (set time of left car to right if left faster)
        
        for (int i = time.length-2; i >= 0; i--) {
            if (time[i] <= time[p]) continue;           // if (time[i] <= time[i+1]) time[i] = time[i+1]    IF 'P' NOT USED
            else {                                      // carfleet++ (left car slower)                     IF 'P' NOT USED
                carfleet++;
                p = i;
            }
        }

        return carfleet;
    }
}
