'''

Problem: 
Write Python code to identify if given two strings are Anagrams 
(strings that contain same set of alphabets)

Sample Input: 
-------------
listen silent

Sample Output: 
--------------
true

'''
def findIfAna(word1,word2):
    if(len(word1)!=len(word2)):
        return False
    freq={}
    for i in range(len(word1)):
        freq[word1[i]]=freq.get(word1[i],0)+1
    for i in range(len(word2)):
        if(word2[i] not in freq):
            return False
        freq[word2[i]]-=1
    return True

word1,word2=input().split()
print(findIfAna(word1,word2))