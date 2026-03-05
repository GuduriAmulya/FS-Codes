// You are given two matrices A and B.
// Your task is to compute the matrix multiplication result C = A × B.

// The implementation must strictly follow this rule:
//     - Each thread must be responsible for computing exactly one cell 
//       of the result matrix.

// This means that:
//     One thread computes one value C[i][j]
//     No thread may compute more than one cll
//     No cell may be computed by more than one thread

// 🧠 Matrix Multiplication Definition
// -----------------------------------
// Matrix A is of size R × K
// Matrix B is of size K × C
// Then the result matrix C is of size R × C, where:
//     C[i][j] = A[i][0]×B[0][j] + A[i][1]×B[1][j] + ... + A[i][K−1]×B[K−1][j]

// Multithreading Requirement
// --------------------------
//     - The total number of threads created must be equal to the number of cells in 
//     the result matrix
//     - Threads must run concurrently
//     - Each thread must write its computed value directly to the shared result matrix
//     - The final output must be correct and deterministic

// Input Format
// ------------
// R K
// Matrix A (R rows and K columns)
// K C
// Matrix B (K rows and C columns)

// Output Format
// -------------
// Result matrix C (R rows and C columns)
// Each row printed on a new line with space-separated integers


// Sample Input
// ------------
// 2 3
// 1 2 3
// 4 5 6
// 3 2
// 7 8
// 9 10
// 11 12

// Sample Output
// -------------
// 58 64
// 139 154

import java.util.*;
import java.util.concurrent.*;
class Task implements Callable<Integer>{
    int arr1[][];
    int arr2[][];
    int row;
    int col;
    Task(int[][]arr1, int[][] arr2 ,int row, int col){
        this.arr1=arr1;
        this.arr2=arr2;
        this.row=row;
        this.col=col;
    }
    public Integer call() throws Exception{
        int ans=0;
        for(int i=0;i<arr1[row].length;i++){
               ans+=(arr1[row][i]*arr2[i][col]);
           
        }
        return ans;
    }
}
public class MatrixMultiThreading{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int r1=sc.nextInt();
        int c1=sc.nextInt();
        
        int arr1[][]=new int[r1][c1];
        for(int i=0;i<r1;i++){
            for(int j=0;j<c1;j++){
                arr1[i][j]=sc.nextInt();
            }
        }
        int r2=sc.nextInt();
        int c2=sc.nextInt();
        int arr2[][]=new int[r2][c2];
        for(int i=0;i<r2;i++){
            for(int j=0;j<c2;j++){
                arr2[i][j]=sc.nextInt();
            }
        }
        int outR=r1;
        int outC=c2;
        ExecutorService executor=Executors.newFixedThreadPool(outR*outC);
        List<Future<Integer>>futures=new ArrayList<>();
        for(int i=0;i<r1;i++){
            for(int j=0;j<c2;j++){
                //System.out.println("hello");
                futures.add(executor.submit(new Task(arr1,arr2,i,j)));
            }
        }
        int res[][]=new int[outR][outC];
        int ind=0;
        for(int i=0;i<outR;i++){
            for(int j=0;j<outC;j++){
                try{
                res[i][j]=futures.get(ind++).get();
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        executor.shutdown();
        for(int r[]:res){
            for(int val:r){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
}
