'''
Write a python program to convert a decimal number to binary using both
1. Recursive method
2. Iterative method

Implement the methods in Solution class and return the binary number.

Sample Input:
---------------
10

Sample Output:
------------------
Binary (Recursive): 1010
Binary (Iterative): 1010

'''


class Solution:
    def decimal_to_rec(self,n):
        if n==0:
            return "0"
        return self._helper(n)
    def _helper(self,n):
        if n==0:
            return ""
        return self._helper(n//2)+str(n%2)
    def decimal_to_bin_iter(self,n):
        if n==0:
            return "0"
        result=""
        while(n>0):
            result=str(n%2)+result
            n=n//2
        return result
num=int(input())
sol=Solution()

print(f"Binary (Recursive): ",sol.decimal_to_rec(num))
print(f"Binary (Iterative): ",sol.decimal_to_bin_iter(num))
    