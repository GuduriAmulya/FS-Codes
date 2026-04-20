'''
Problem: Write a program to count each word's first index and total occurrences 
in a sentence.

Sample Input: 
apple banana apple orange banana apple

Sample Output:
apple -> first index: 0, count: 3
banana -> first index: 6, count: 2
orange -> first index: 19, count: 1

'''

s=input()
result={}
words=s.split()
index=0
for word in words:
    if word not in result:
        result[word]=[index,1]  #initially [index=0 & count=1]
    else:
        result[word][1]+=1
    index+=(len(word))+1
#print(result)
for w,(ind,c) in result.items():
    print(f"{w} -> first index: {ind}, count: {c}")