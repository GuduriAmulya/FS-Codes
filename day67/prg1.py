'''
Alex and his twin brother Jordan often create secret messages. One day, Jordan 
gives Alex two encrypted messages and challenges him to find the longest common 
palindromic pattern hidden within both messages.

Alex wants your help to decode the longest common palindromic subsequence that 
exists in both strings.

Your task is to determine the length of the longest subsequence that:
- Appears in both messages
- Is a palindrome

Input Format:
-------------
input1: A string representing the first encrypted message.
input2: A string representing the second encrypted message.

Output Fromat:
--------------
Return an integer representing the length of the longest common palindromic 
subsequence shared by both messages.


Sample Input: 
-------------
adfa
aagh

Sample Output:
--------------
2


Sample Input-2:
---------------
abcda
fxaaba

Sample Output:
--------------
3

Explanation:
------------
The longest palindromic subsequence common to both is "aba" with length 3.

'''

from functools import lru_cache

def longestCommonPalindromicSubsequence(s1, s2):
    n, m = len(s1), len(s2)

    @lru_cache(None)
    def dp(i, j, k, l):
        if i > j or k > l:
            return 0

        # All four boundary characters match
        if s1[i] == s1[j] == s2[k] == s2[l]:
            # Single character palindrome
            if i == j or k == l:
                return 1
            return 2 + dp(i + 1, j - 1, k + 1, l - 1)

        return max(
            dp(i + 1, j, k, l),
            dp(i, j - 1, k, l),
            dp(i, j, k + 1, l),
            dp(i, j, k, l - 1)
        )

    return dp(0, n - 1, 0, m - 1)


# Input
s1 = input().strip()
s2 = input().strip()

print(longestCommonPalindromicSubsequence(s1, s2))