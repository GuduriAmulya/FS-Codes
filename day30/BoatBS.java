// A group of people planned to go an Island for New Year celebrations, 
// They have arrived at a boating point to reach the Island. All of them 
// formed in a line to get into the boat, and you are given their weights 
// as an array Weights[], where i-th person weight is Weights[i].

// Each boat can carry only few people whose total weight is atmost W.
// Each person get into the boat according to the given order only.
// The boat takes T number of trips to transport all the people of the group.

// You will be given the weights of the people in the line, and number of trips T.
// Now, its your turn to find out the minimum weight, the boat can carry, 
// So that the boat can transport all the people to cross the river.

// Input Format:
// -------------
// Line-1: Two space separated integers, N and T, Number of people and T trips.
// Line-2: N space separated integers, weights of the people.

// Output Format:
// --------------
// Print an integer, minimum weight, the boat can carry.


// Sample Input-1:
// ---------------
// 6 3
// 3 2 3 4 1 5

// Sample Output-1:
// ----------------
// 7

// Explanation:
// ------------
// A boat can carry a weight 7 is the minimum to transport all the people in 3 trips:
// trip-1: 3, 2
// trip-2: 3, 4
// trip-3: 1, 5


// Sample Input-2:
// ---------------
// 8 4
// 1 2 3 4 5 6 7 8

// Sample Output-2:
// ----------------
// 11

// Explanation:
// ------------
// A boat can carry a weight 11 is the minimum to transport all the people in 4 trips:
// 1st day: 1, 2, 3, 4
// 2nd day: 5, 6
// 3rd day: 7
// 4th day: 8

// Note that the people must be transported in the order given, so using a
// boat can carry a weight 10 and splitting the packages into parts like 
// (8, 1), (7, 2), (6, 3), (5, 4) is not allowed.


import java.util.*;
class BoatBS{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int arr[]=new int[n];
        int lower=Integer.MIN_VALUE;
        int upper=0;
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
            lower=Math.max(lower,arr[i]);
            upper+=arr[i];
        }
        while(lower<upper){
            int mid=(lower+upper)/2;
            int c=countTrips(arr,mid);
            if(c<=k){
                //trips made are less than desired.. which means.. weight per trip is too much.. decrease the wt..
                upper=mid;
            }
            else{// num of trips too many.. so weight per trip should increase..
                lower=mid+1;
            }
        }
        System.out.println(lower);
    }
    public static int countTrips(int arr[],int mid){
        int c=1;
        int sum=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]+sum>mid){
                sum=arr[i];
                c++;
            }
            else{
                sum+=arr[i];
            }
        }
        //System.out.println(c);
        return c;
    }
}