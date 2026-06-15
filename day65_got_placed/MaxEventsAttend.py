'''
In Hyderabad after a long pandemic gap, the Telangana Youth festival Is 
Organized at HITEX. In HITEX, there are a lot of programs planned. During 
the festival in order to maintain the rules of Pandemic, they put a 
constraint that one person can only attend any one of the programs in 
one day according to planned days.

Now it’s your aim to implement the "Solution" class in such a way that 
you need to return the maximum number of programs you can attend according 
to given constraints.

Explanation:
You have a list of programs ‘p’ and days ’d’, where you can attend only 
one program on one day. Programs [p] = [first day, last day], 
p is the program's first day and the last day.


Input Format:
-------------
Line-1: An integer N, number of programs.
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

Output Format:
--------------
An integer, the maximum number of programs you can attend.


Sample Input-1:
---------------
4
1 2,2 4,2 3,2 2

Sample Output-1:
----------------
4

Sample Input-2:
---------------
6
1 5,2 3,2 4,2 2,3 4,3 5

Sample Output-2:
----------------
5

'''
import heapq
n=int(input())
events=[]
for item in input().split(","):
    s,e=map(int,item.strip().split())
    events.append([s,e])
events.sort()#sort by start day.
heap=[]
i=0
count=0
last_day=max(e for s,e in events)
for day in range(1,last_day+1):
    while (i<n and events[i][0]==day): #push all the events(end day) that start on day. to min heap..
        heapq.heappush(heap,events[i][1])
        i+=1
    
    #remove the events that r expired.
    while(i<n and heap[0]<day):
        heapq.heappop(heap)
    if heap:
        count+=1
        heapq.heappop(heap)
print(count)
        
    