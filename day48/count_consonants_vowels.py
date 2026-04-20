'''

Write a program that reads a sentence, count and display the total number of 
vowels and consonants.

Input: 
------
Hello World

Output:
-------
Vowels: 3, Consonants: 7

'''

s=input().lower()
vowels=['a','e','i','o','u']
v=0
c=0
for ch in s:
    if ch in vowels:
        v+=1
    elif(ch !=' ' and ch.isalpha()):
        c+=1
print(f"Vowels: {v}, Consonants: {c}")