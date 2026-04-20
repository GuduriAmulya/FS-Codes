'''
Problem: 
--------
Write a Python code to identify the nth largest number without 
sorting the array


Sample Input:
-------------
4 2
3 1 5 2

Sample Output:
--------------
3

'''

#Bubble sort till k..
#each time from the end.. max values are placed//
# k=0 [1 3 2 5]
#k=1: [1 2 3 5]

n,k=map(int,input().split())
arr=list(map(int, input().split()))
for i in range(k):
    for j in range(0,n-i-1):
        if (arr[j]>arr[j+1]):
            arr[j],arr[j+1]=arr[j+1],arr[j]
print(arr[n-k])


    
# using heap

# import heapq

# n, k = map(int, input().split())
# arr = list(map(int, input().split()))

# min_heap = arr[:k]
# heapq.heapify(min_heap)

# for num in arr[k:]:
#     if num > min_heap[0]:
#         heapq.heappop(min_heap)
#         heapq.heappush(min_heap, num)

# print(min_heap[0])