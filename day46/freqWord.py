'''Write python code for a method which takes a String (a sentence) as input 
and returns a Map. The Map key is String (word in the String) and 
value is frequency of the word in the given sentence.
(In the words ignore any special characters)

Sample Input:
-------------
Java is fun, and Ja#va@ is powerful 

Sample Output:
--------------
java: 2
is: 2
fun: 1
and: 1
powerful: 1
'''

s=input()
s=s.lower()
freq={}
cleaned=""
for ch in s:
    if ch.isalpha() or ch.isspace():
        cleaned+=ch
print(cleaned)
for word in cleaned.split():
    freq[word]=freq.get(word,0)+1

for key,value in freq.items():
    print(f"{key}: {value}")
    