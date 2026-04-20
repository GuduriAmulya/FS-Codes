'''

Write python code to print system date & time in format like 
2021-10-02 10:30:00 AM

Sample Output: 2025-06-04 11:35:27 AM

'''

from datetime import datetime
now=datetime.now()
format=now.strftime("%Y-%d-%m %I:%M:%S %p")
print(format)#0 marks
print(format,end="")#100
