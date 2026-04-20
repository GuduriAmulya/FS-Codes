'''Write a python program to find the nth prime number. 
The value of n should be input by the user.

Sample Input:
---------------
5

Sample Output:
-----------------
11
'''

n=int(input())
count=0
num=1
def isprime(num):
    if num<2:
        return False
    for i in range(2,int(num**0.5)+1):
        if num % i==0:
            return False
    return True
while count <n:
    num+=1
    if(isprime(num)):
        count+=1
print(num)