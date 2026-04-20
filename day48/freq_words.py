'''

Write a python program that reads a sentence and counts how many times each word 
appears. Display only the words that occur more than once.

Input: 
------
this is a test this test is easy

Output:
-------
this -> 2
is -> 2
test -> 2

'''

words=input().split()
freq={}
for w in words:
    freq[w]=freq.get(w,0)+1
#print(freq)
for x,y in freq.items():
    if(y>1):
        print(f"{x} -> {y}")