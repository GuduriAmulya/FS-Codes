'''
Luke is given an integer array M of length N.

For every window size k (where 1 ≤ k ≤ N), perform the following steps:
	1. Consider all contiguous subarrays (windows) of size k.
	2. Find the minimum element in each window.
	3. Among all these minimum values, select the maximum one.
	4. Store this value as the k-th element of a new array P.

Your task is to construct and return the array P.

Input Format:
The first line contains an integer N, the size of the array.
The second line contains N space-separated integers representing the array M.

Output Format:
Print N space-separated integers representing the array P, where:
P[k] = maximum among the minimum values of all contiguous subarrays of size k

Sample Input-1:
3
1 2 3

Sample Output-1:
3 2 1

Explanation:
Windows Analysis
For k = 1:
[1], [2], [3]
Minimums = {1, 2, 3}
Maximum = 3

For k = 2:
[1,2], [2,3]
Minimums = {1, 2}
Maximum = 2

For k = 3:
[1,2,3]
Minimum = 1


Sample INput-2:
4
20 10 30 40

Sample Output-2:
40 30 10 10

Constraints:
1 ≤ N ≤ 10^5
1 ≤ M[i] ≤ 10^9

Expected Time Complexity: O(N)

'''