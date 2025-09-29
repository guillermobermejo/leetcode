class Solution:
    # Validity guaranteed from the second if statement recursing into appendeing the left bracket first.
    def generateParenthesis(self, n: int) -> List[str]:
        res = []
        def backtrack(cur, open_, close):
            if len(cur) == 2 * n:
                res.append(cur); 
                return
            if open_ < n:
                backtrack(cur + "(", open_ + 1, close)
            if close < open_:
                backtrack(cur + ")", open_, close + 1)
        backtrack("", 0, 0)
        return res
