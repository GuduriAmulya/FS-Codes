// Imagine you're the chief engineer aboard a futuristic spaceship. 
// The ship is powered by N series of fuel cells arranged in a row, 
// where each fuel cell holds a specific amount of energy measured in 
// megajoules. Your job is to manage these fuel cells by performing 
// two main operations:

// Option 1: Monitor Fuel Reserves: Calculate the total energy available in a 
// consecutive block of fuel cells between indices start and end (inclusive).
// Option 2: Recharge a Fuel Cell: Update the energy level with given 'newEnergy' 
// of a specific 'index' fuel cell when it's refilled.

// Input Format:
// -------------
// Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the 
// number of operations.
// Line-2: N space separated integers.
// next Q lines: Three integers option, start/index and end/newEnergy.

// Output Format:
// --------------
// An integer result, for every totalEnergy.



// Example 1:
// -----------
// Input:
// 8 5
// 1 2 13 4 25 16 17 8
// 1 2 6		//totalEnerge
// 1 0 7		//totalEnerge
// 2 2 18		//recharge
// 2 4 17		//recharge
// 1 2 7		//totalEnerge

// Output:
// 75
// 86
// 80


// Example 2:
// ----------
// Input:
// 16 1
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
// 1 0 15

// Output:
// 136


// Constraints

// - 1 <= N <= 3*10^4  
// - -100 <= fuelCells[i] <= 100  
// - 0 <= index < fuelCells.length  
// - -100 <= newEnergy <= 100  
// - 0 <= start <= end < fuelCells.length  
// - At most 3*10^4 calls will be made to recharge and totalEnergy.


import java.util.*;
class FenwickTreePrg{
    static class FenwickTree{
        int []bit;
        int n;
        FenwickTree(int n){
            this.n=n;
            bit=new int[n+1];//1based ind
        }
        public void update(int i, int val){
            while(i<=n){
                bit[i]+=val;
                i += i&(-i);//rightmostsetbit
            }
        }
        public int prefixSum(int i){
            int sum=0;
            while(i>0){
                sum+=bit[i];
                i -=i&(-i);
            }
            return sum;
        } 
        public int rangeSum(int l ,int r){
            return prefixSum(r)-prefixSum(l-1);
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();//cells;
        int q=sc.nextInt();//operations..
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int ops[][]=new int[q][3];
        for(int i=0;i<q;i++){
            ops[i][0]=sc.nextInt();
            ops[i][1]=sc.nextInt();
            ops[i][2]=sc.nextInt();
        }
        FenwickTree ft=new FenwickTree(n);
        for(int i=0;i<n;i++){
            ft.update(i+1,arr[i]);
        }
        for(int i=0;i<q;i++){
            int option=ops[i][0];
            if(option==1){
                //calc total enegy in the range..
                int ans=ft.rangeSum(ops[i][1]+1,ops[i][2]+1);
                System.out.println(ans);
            }
            else{
                //option 2..
                //update at ind ops[i][0]=2.
                int ind=ops[i][1];
                int newEnergy=ops[i][2];
                int delta=(newEnergy-arr[ind]);
                ft.update(ind+1,delta);
            }
        }
    }
}