// Tejaswi playing a game. Game has a display of N numbers in a row, nums[].
// Tejaswi has to pick the numbers one after the other.

// Once Tejaswi picks a number num[i], she will score num[i] points and 
// the number will be disappared on the board, simultaneously all the numbers 
// having the value num[i]+1 or num[i]-1 also disappears. 

// Tejaswi has to select the numbers, such that no more numbers will be left over 
// on the board.

// Tejaswi wants to score maximum number of points.

// Initially Tejaswi has 0 points with her.
// Can you help Tejaswi to score maximum number of points.

// Input Format:
// -------------
// Line-1 -> an integers N, number of numbers on the game board.
// Line-2 -> N space separated integers, numbers on the game board nums[].

// Output Format:
// --------------
// Print an integer as result, maximum score she can get.


// Sample Input-1:
// ---------------
// 3
// 4 5 3

// Sample Output-1:
// ----------------
// 8

// Explanation: 
// ------------
// Pick a number 5 to score 5 points, simultaneously 4 is disappeared from display.
// Then, pick number 3 to score 3 points.
// Totally 8 is the score.


// Sample Input-2:
// ---------------
// 6
// 4 4 5 5 5 6

// Sample Output-2:
// ----------------
// 15

// Explanation: 
// -------------
// Delete 5 to earn 5 points, deleting both 4's and the 6.
// Then, delete 5 again to earn 5 points, and 5 again to earn 5 points.
// Totally 15 is the score.

// Pick a number 5 to score 5 points, simultaneously all 4's and 6 are disappeared 
// from display. Then, again pick the number 5 to score 5 points.
// And again pick the number 5 to score 5 points. Totally 15 is the score.

import java.util.*;
class EarnPoints{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)arr[i]=sc.nextInt();
        TreeMap<Integer,Integer>mp=new TreeMap<>();
        for(int num:arr){
            mp.put(num,mp.getOrDefault(num,0)+1);
        }
        //System.out.println(mp);
        int ans=0;
        int skip=0,take=0,prev=999;
        for(Map.Entry<Integer,Integer>e:mp.entrySet()){
            int key=e.getKey();
            int val=key*e.getValue();
            if(prev+1==key){
                //then i can either skip the cur and take the take of prev or.. skip the prev and add cur to sum..
                int newTake=skip+val;///taking cur + not take prev
                skip=Math.max(skip,take);//if im skipping cur then i have to take max of prev take/skip.
                take=newTake;
            }
            else{
                // then i can take cur and add the max of prev(take,skip) or dont take cur and continue with prev max
                int maxv=Math.max(take,skip);
                take=val+maxv;
                skip=maxv;
            }
            prev=key;
        }
        System.out.println(Math.max(take,skip));
        
    }
    
}