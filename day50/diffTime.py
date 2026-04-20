'''

Write a python program that reads two timestamps (yyyy-MM-dd HH:mm:ss format) and
display the time elapsed between them in minutes and seconds.

Input: 
------
2025-06-04 10:30:00
2025-06-04 11:15:40

ans=45min, 40 sec
10*60+30=>630
11*60+15=>675
2025-06-04 10:05:00
2025-06-04 11:15:40


60+(10)
70
Output: 
-------
Elapsed: 45 minutes 40 seconds

'''
#one y=>365*24*60=>525600
#month=>m=>31*24*60=>44640
#day=>24


from datetime import datetime

# input
t1 = input().strip()
t2 = input().strip()

# convert to datetime
format_str = "%Y-%m-%d %H:%M:%S"
dt1 = datetime.strptime(t1, format_str)
dt2 = datetime.strptime(t2, format_str)

# difference
diff = abs(dt2 - dt1)

# total seconds
total_seconds = int(diff.total_seconds())

# convert
minutes = total_seconds // 60
seconds = total_seconds % 60

print(f"Elapsed: {minutes} minutes {seconds} seconds")

# t1=input().split(' ')
# t2=input().split(' ')
# d=list(map(int,t1[1].split(':')))
# d2=list(map(int,t2[1].split(':')))
# m1=d[0]*60+d[1]
# m2=d2[0]*60+d2[1]
# h_diff=abs(d[0]-d2[0])
# m_diff=abs(d[1]-d2[1])
# s_diff=abs(d[2]-d2[2])
# minutes=(m2-m1)
# #print(minutes)
# y1=list(map(int,t1[0].split('-')))
# y2=list(map(int,t2[0].split('-')))
# y_diff=abs(y1[0]-y2[0])*365*24*60
# mon_diff=abs(y1[1]-y2[1])*30*24*60
# day_diff=abs(y1[2]-y2[2])*24*60
# minutes+=y_diff+mon_diff+day_diff
# sec=abs(d[2]-d2[2])
# print(f"Elapsed: {minutes} minutes {sec} seconds")

# #print(d)