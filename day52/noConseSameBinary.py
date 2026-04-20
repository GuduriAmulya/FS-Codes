'''
Vihan is given a number N and He wants to check whether N is a converse number
or not. The binary form of number N is said to be converse number, if it obeys 
the following property: "every pair of adjacent digits are different". 

Your task is to help Vihan to find N is a converse number or not.
If yes, print 'true', otherwise print 'false'.

Input Format:
-------------
An integer N, the positive number.

Output Format:
--------------
Print a boolean result.

Sample Input-1:
---------------
85

Sample Output-1:
----------------
true

Explanation:
------------
Binary Rep of 85 is 1010101 


Sample Input-2:
---------------
87

Sample Output-2:
----------------
false

Explanation:
------------
Binary Rep of 87 is 1010111

'''
n=int(input())
y=bin(n)
found=True
for i in range(len(y)-1):
    if(y[i]==y[i+1]):
        found=False
        break
if(found):
    print("true")
else:
    print("false")