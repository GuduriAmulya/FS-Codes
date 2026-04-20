'''

Write python program to find and return minimum number of denominations – given 
an infinite supply of each denomination of {1, 2, 5, 10, 20, 50, 100, 200,500, 
2000} and a target value N

Sample Input: 
-------------
187

Sample Output: 
--------------
[100, 50, 20, 10, 5, 2]

'''
n=int(input())
arr=[2000,500,200,100,50,20,10,5,2,1]
i=0
ans=[]
while(n>0):
    if(arr[i]<=n):
        count=n//arr[i]
        for _ in range(count):
            ans.append(arr[i])
        n=n%arr[i]
    i+=1
print(ans)

