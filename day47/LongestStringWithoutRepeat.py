'''

Write a Python code to return the length of longest sub-string without repeating 
characters

Sample Input: 
-------------
abcabcbb

Sample Output: 
--------------
3

'''

s=input()
map={}
left=0
max_len=0
for i in range(len(s)):
    if s[i] in map and map[s[i]]>=left :
        left=map[s[i]]+1

    map[s[i]]=i
    max_len=max(max_len,i-left+1)
print(max_len)
