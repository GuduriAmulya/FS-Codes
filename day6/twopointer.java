// Trivikram and Vamshi are friends. They need to write N screen-plays, numbered 
// from 1 to N. Each screen-play requires specific number of papers to write.

// Initially, Trivikram has T number of papers in his box and Vamshi has V number of 
// papers in his box. They have started the screenplay writing in the following way:
//     - Trivikram starts from screen-play number 1 to N and Vamshi starts from N to 1.
//     - They begin writing the screen-plays at same time.
//     - Each screen-play takes same amount of time, irrespective of its size.
//     - Trivikram or Vamshi must write the screen-play, if they have enough number 
//       of papers in their respective boxes. Otherwise, they first have to refill 
//       their boxes (instantly) and then write the screen-play.
//     - In case, Trivikram and Vamshi reach the same screen-play, the one with more
//       number of papers currently in their box should write the screen-play. If 
//       they have the same number of papers, then Trivikram should write the 
//       screen-play.

// You are given 1-indexed integer array screen-play[] of size N, where screen-play[i]
// is the number of papers required for ith screen-play, and two integers T and V.
// Your task is to return the number of times they have to refill to write all the 
// screen-plays.

// Constraint:
// -----------
// max(screen-play) < T,V < 10^9


// Input Format:
// -------------
// Line-1: 3 space separated integers, indicates N, T and V.
// Line-2: N space separated integers, screen-plays[].

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 6 10 10
// 5 3 4 4 5 4

// //5 6
// //2 1
// //0 
// Sample Output-1:
// ----------------
// 2

// Explanation: 
// ------------
// - Initially, Trivikram and Vamshi have 10 papers each in their boxes.
// - Trivikram writes Screen-play-1, Vamshi writes Screen-play-6.
// - Trivikram and Vamshi now have 5 and 6 papers in their boxes respectively.
// - Trivikram writes Screen-play-2, Vamshi writes Screen-play-5.
// - Trivikram and Vamshi now have 2 and 1 papers in their boxes respectively.
// - Trivikram and Vamshi do not have enough number of papers to write screen-play-3 
// and screen-play-4 , so they refills their boxes and finished the screenplays.
// So, the total number of times they have to refill is 0 + 0 + 1 + 1 + 0 + 0 = 2.


// Sample Input-2:
// ---------------
// 3 9 7
// 4 5 2

// Sample Output-2:
// ----------------
// 0

// Explanation: 
// ------------
// - Initially, Trivikram and Vamshi have 9 papers and 7 papers each in their boxes.
// - Trivikram writes Screen-play-1, Vamshi writes Screen-play-3.
// - Trivikram and Vamshi now have 5 papers each in their boxes respectively.
// - Now, Trivikram writes Screen-play-2. Finished the screenplays.
// So, the total number of times they have to refill is 0 + 0 + 0 = 0.

import java.util.*;
class twopointer{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int t=sc.nextInt();
        int temp_t=t;
        int v=sc.nextInt();
        int temp_v=v;
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int i=0;
        int j=n-1;
        int refill=0;
        while(i<j){
            if(arr[i]>temp_t){
                temp_t=t;
                refill++;
                //System.out.println("rem t: "+t);
            }
            temp_t-=arr[i];
            if(arr[j]>temp_v){
                temp_v=v;
                refill++;
            }
            temp_v-=arr[j];
            i++;
            j--;
        }
        if(i==j){
            if(temp_t>=temp_v){
                if(arr[i]>temp_t){
                    refill++;
                }
            }else{
                if(arr[i]>temp_v){
                    refill++;
                }
            }
        }
        System.out.println(refill);
        
    }
}