'''
Write a python code , take inputs also, You have given flatland in the form of m*n grid,
The land conatins some ponds represented as 0,
and the land represented as 1.

Your task is to find the number of square-shaped lands which contains no pond. 


Input Format:
-------------
Line-1: Two integers M and N.
Next M lines: N space separated integers.

Output Format:
--------------
Print an integer, number of square-grids.


Sample Input-1:
---------------
3 4
0 1 1 1
1 1 1 1
0 1 1 1

0 0 0 0 0
0 0 1 1 1
0 1 1 2 2
0 0 1 2 3
Sample Output-1:
----------------
15

Explanation:
------------
There are 10 square-grids of side length 1.
There are 4 square-grids of side length 2.
There is 1 square-grid of side length 3.

Total number of square-grids without a pond in it = 10 + 4 + 1 = 15.


Sample Input-2:
---------------
3 3
1 0 1
1 1 0
1 1 0

Sample Output-2:
----------------
7

Explanation:
------------
There are 6 square-grids of side length 1.
There is 1 square-grid of side length 2.
Total number of square-grids without a pond in it = 6 + 1 = 7.

'''


n,m=map(int,input().split())

arr=[]

for i in range(n):
    row=list(map(int,input().split()))
    arr.append(row)
    
#print(arr)
c=0
for i in range(n):
    for j in range(m):
        if (i!=0 and j!=0):
            if(arr[i][j]==1):
                arr[i][j]+=min(arr[i-1][j],arr[i][j-1],arr[i-1][j-1])
                c+=arr[i][j]
        else:
            c+=arr[i][j]
print(c)