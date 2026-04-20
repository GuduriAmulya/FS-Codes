'''

Write a python program, for given a birthdate in yyyy-MM-dd format, calculate 
the person’s current age in years, months, and days.

Input:
------
1990-05-25

Output:
-------
Age: 34 years, 0 months, 10 days

'''
from datetime import datetime
birthday_str=input().strip()
birthday=datetime.strptime(birthday_str,"%Y-%m-%d")
today=datetime.today()
y=today.year-birthday.year
m=today.month-birthday.month
d=today.day-birthday.day
if(d<0):
    m-=1
    prev_month=(today.month-1) if today.month>1 else 12
    prev_year=today.year if today.month>1 else today.year-1
    last_day_prev_month=(datetime(today.year,today.month,1)-datetime(prev_year,prev_month,1)).days
    print((datetime(today.year,today.month,1)-datetime(prev_year,prev_month,1)))
    d+=last_day_prev_month
if m<0:
    y-=1
    m+=12
print(f"Age: {y} years, {m} months, {d} days")