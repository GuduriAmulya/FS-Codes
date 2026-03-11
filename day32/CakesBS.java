/*Swiss Castle bakers has recivied P orders of cakes, and each cake has 
to filled with Q types of creams. To prepare varieties of creams, each 
variety takes different amounts of time in minutes, where i-th variety 
takes time[i] minutes.

You need to prepare P cakes. To prepare a cake, you need to use Q adjacent 
creams from the cook. The cook can prepare N varieties of creams, the i-th 
variety will be ready in time[i] minutes, and then you can use it to fill 
the cake.  [ Note: you can use each variety only once ].
 
Your task is to find and return the minimum time (in minutes) required, 
to prepare P cakes filled with Q types of creams. If it is impossible to 
prepare P cakes, print -1.


Input Format:
-------------
Line-1: Three space separated integers, N, P and Q.
Line-2: N space separated integers, time[].

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
6 2 2
3 7 2 2 4 8

Sample Output-1:
----------------
7

Explanation:
-------------
* means not ready, R means ready.
Minute-1: * * * * * *
Minute-2: * * R R * *
Minute-3: R * R R * *
Minute-4: R * R R R *
Minute-5: R * R R R *
Minute-6: R * R R R *
Minute-7: R R R R R *
Now you can create two cakes with two varieties of fillings
Cake-1 can use variety-2 and variety-3
Cake-2 can use variety-0 and variety-1.

Sample Input-2:
---------------
5 3 2
2 3 5 3 4

Sample Output-2:
----------------
-1

Explanation:
------------
We need 6 varieties of creams to prepare 3 cakes.
We have only 5 varieties, so not possible. Print -1.
*/


import java.util.*;
class CakesBS{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int p=sc.nextInt();//cakes..
        int q=sc.nextInt();//fillings per cake..
        int arr[]=new int[n];
        int l=Integer.MAX_VALUE;
        int r=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
            l=Math.min(l,arr[i]);
            r=Math.max(r,arr[i]);
        }
        int ans=-1;
        while(l<=r){
            int mid=(l+r)/2;
            if(isPossible(arr,mid,p,q)){//try lower value
                ans=mid;
                r=mid-1;
            }
            else{
                l=mid+1;
            }
        }
        System.out.println(ans);
        
    }
    public static boolean isPossible(int[]arr, int mid,int p, int q){
        //can i make p cakes with q consecutive frostings per cake..
        int c=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<=mid){
                c++;
                if(c==q){
                    p--;
                    c=0;
                }
                if(p==0)return true;
            }
            else{
                c=0;
            }
        }
        return false;
    }
}