class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        def is_within(right_interval, left_interval):

            if right_interval[0] >= left_interval[0] and right_interval[0] <= left_interval[1]:
                return True
            
            return False

        if len(intervals) == 1:
            return intervals

        # sort the intervals based on beginning time
        intervals = sorted(intervals, key=lambda x: x[0])
        index = 0

        for i in range(1, len(intervals)):
            # left interval will always be the current candidate interval to be merged if need be
            left_interval = intervals[index]

            # right interval will be every ith interval to be checked to the left interval
            right_interval = intervals[i]

            # if the right interval is within the left interval, merge right to left with end max of the two
            if is_within(right_interval, left_interval):
                intervals[index][1] = max(right_interval[1], left_interval[1])
            else:
                # if right interval not in left it is new interval, move pointer and copy this interval
                index += 1
                intervals[index][0] = right_interval[0]
                intervals[index][1] = right_interval[1]

        return intervals[0:index+1]
