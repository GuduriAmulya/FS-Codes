'''
Write a Python Program to read a sentence and print each word reversed, but 
maintain the original word order.

Input: 
------
Java is fun

Output:
-------
avaJ si nuf

'''
words=input().split()
reverse_words=[]
for w in words:
    reverse_words.append(w[::-1])
#print(reverse_words)
newSen=" ".join(reverse_words)
print(newSen)
    
