s1="ABCDEF"
s2="BCXEF"
m=len(s1)
n=len(s2)
memo=[[-1]*(n+1) for _ in range(m+1)]
def lcs(s1,s2,m,n):
    if(m==0 or n==0):
        return 0
    if memo[m][n]!=-1:
        return memo[m][n]
    if s1[m-1]==s2[n-1]:
        memo[m][n]=1+lcs(s1,s2,m-1,n-1)
    else:
        memo[m][n]=max(lcs(s1,s2,m-1,n),lcs(s1,s2,m,n-1))
    return memo[m][n]
print(lcs(s1,s2,m,n))
i=m
j=n
ans=""
while(i>0 and j>0):
    if (s1[i-1]==s2[j-1]):
        ans+=s1[i-1]
        i-=1
        j-=1
    elif memo[i][j-1]>memo[i-1][j]:
        j-=1
    else:
        i-=1
ans=ans[::-1]
print(ans)
