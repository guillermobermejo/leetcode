/**
Programmer: Guillermo       E
Time Complexity: O(n)
Space Complexity: O(n)
Runtime: 27ms (beats 36.15%)
Memory: 44.9mb (beats 50.27%)

DS: none

Approach:
    use binary search appoach (on range of MAX bananas in a pile i.e., 1-MAX)
    
    r - the MAX bananas, in a pile in piles[] array
    l - 1, the beginning of the range of bananas to be tested
    
    from 1 - MAX find the middle value to serve as 'rate of bananas eaten per hour' this is 'm'
    determine if this rate 'm' is capable of eating ALL bananas within h hours SET val to 't'
        true ?  if m lower than CURRENT min update
                SET r TO m-1 (desire smaller min)
        false ?
                SET l TO m+1 (need bigger rate)
    
    final result is the MIN rate of bananas to eat slowly within 1000 hours
    
Algorithm:
    declare & initialize:
        1 int value     name: k     value: 0        notes: the final MINIMUM number of bananas to finish within h hours
        1 int value     name: t     value: 0        notes: total hours needed to eat piles[i] bananas at rate of 'm' bananas per hour
        1 int value     name: m     value: 0        notes: the rate 'm' chosen to eat per hour i.e., the 'middle' value of the range
        1 int value     name: l     value: 1        notes: the beginning of the range of bananas to be tested as the 'm' (rate eaten per hour)
        1 int value     name: r     value: -1       notes: the end of the range of bananas to be tested as the 'm' (rate eaten per hour)
        
    for loop (int i : piles):
        for every int i in piles
        if (i > r) r = i            // get MAX value of bananas in a pile (the right value)
    
    // BINARY SEARCH: test every value in range as the RATE of bananas eaten per hour ('m')
    
    while (l <= r):
        calculate:
            m = (l + r) / 2;
            
            for loop (int i : piles):
                calculate:
                    total hours needed to eat all piles with a rate of 'm' per hour
                    t += Math.ceil((double) i / m)
            
            if check:
                if t < h
                    true ?  update k->m     // NATURALLY SMALLER (always checks for smaller k after finding one that works)
                            update r->r=m-1     // find SMALLER k
            else:
                l = m+1     // t takes longer than h, find larger rate to eat at     // shift l to m+1
                
        reset:
            t=0             // reset t for calculating next rate
            
    return:
        k       // the MIN rate at which to eat
                    
Visualization of algorithm:
    piles:  [3,6,7,11]
    h:      8
    
    r: 11 (max pile)
    l: 1
             l              m               r
    range:  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11]
    
    Iteration 1:
                    l           m               r
        range:  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11]
        
        m: 11+1 / 2 = 6
        
        for loop (int i : piles):
            t += Math.ceil((double) i / m)
            
        t: 6
        
        if t < 9 TRUE
            k = 6 (m)
            r = 5 (m-1)     // VALID NOW LOOK FOR SMALLER RATE 'm' TO EAT
            
    
    Iteration 2:
                 l     m     r
        range:  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11]
        
        m: 5+1 / 2 = 3
        
        for loop (int i : piles):
            t += Math.ceil((double) i / m)
            
        t: 10
        
        if t < 9 FALSE
            l = 3 (m+1)     // NOT VALID LOOK FOR GREATER RATE 'm' TO EAT
            
    
    Iteration 3:
                       l  m  r
        range:  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11]
        
        m: 5+3 / 2 = 4
        
        for loop (int i : piles):
            t += Math.ceil((double) i / m)
            
        t: 8
        
        if t < 9 TRUE
            k = 3 (m)
            l = 4 (m+1)     // NOT VALID LOOK FOR GREATER RATE 'm' TO EAT
            
    l == r EXIT
    
    return k (3)       
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int k, t, m;
        k = t = m = 0;
        int l = 1;
        int r = -1;
        
        for (int i : piles) 
            if (i > r) r = i;

        while (l <= r) {
            m = (l + r) / 2;

            for (int i : piles)
                t += Math.ceil((double) i / m);
            
            System.out.println("m: " + m);
            System.out.println("t: " + t);
               
            if (t <= h) {
                k = m;
                r = m-1;
            }
            else 
                l = m+1;
            
            t = 0;
        }

        return k;
    }
}