/*
There is a row of buildings constructed by Raj Group Ltd.

The civil engineer planned to construct the buildings in ascending order 
of their heights, but there is a group of contiguous buildings not constructed 
according to the plan, remove such group of buildings from that row of buildings 
(can be empty), so the buildings in the row are in sorted order of their heights.

Your task is to find and return the number of buildings in such group.

Input Format:
-------------
Line-1: An integer N, length of array.
Line-2: N space separated integers, heights of the buildings.

Output Format:
--------------
Print an integer, the minimum of group of buildings.


Sample Input-1:
---------------
8
2 3 5 12 2 4 5 7


Sample Output-1:
----------------
3

Explanation:
------------
The minimum group of builings in a row, you can remove is [5, 12, 2] or 
[12, 2, 4]  of length 3. 
 - the remaining buildings with the heights after removal will be [2, 3, 4, 5, 7],
 OR [2, 3, 5, 5, 7], which are in ascending order.


Sample Input-2:
---------------
6
9 7 5 4 2 1

-1 -1 -1 -1 -1 -1
len-1 ans
Sample Output-2:
----------------
5

Explanation:
------------
The minimum group of builings in a row, you can remove is [7, 5, 4, 2, 1] or  
[9, 7, 5, 4, 2]  of length 5. 


SOLVE USING prefix and suffix increasing subarray. then 2pointers to  find the min len..
*/

import java.util.*;
class trickyIncrArray{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int l=0;
        boolean found=false;
        while(l<n-1 && arr[l]<=arr[l+1]){
            l++;
        }
        if(l==n-1){
            found =true;
        }
        if(found){
            System.out.println(0);
            return;
        }
        int k=n-1;
        while(k>0 &&arr[k-1]<=arr[k]){
            k--;
        }
        int ans=Integer.MAX_VALUE;
        ans=Math.min(n-l-1,k);
        //System.out.println(n-l-1+" "+k);
        int i=0,j=k;
        while(i<=l && j<n){
         if(arr[i]<=arr[j]){
             //System.out.println(j-i-1);
             ans=Math.min(ans,j-i-1);
             i++;
         }
         else{
             j++;
         }
        }
        System.out.println(ans);
        
    }
}

