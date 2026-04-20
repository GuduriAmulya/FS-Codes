/*
In an e-commerce system, multiple orders are processed concurrently.

Each order goes through 3 logical steps:
	- Thread A → Reads base price
	- Thread B → Applies discount:
		If price ≥ 1000 → 10% discount
		Else → 5% discount
	- Thread C → Returns final payable amount

Each order runs independently using Callable.

Input Format:
-------------
Line-1: Integer N
Line-2: N space-separated integers (prices)

Output Format:
--------------
Print final payable amounts


Sample Input:
-------------
5
1000 500 2000 300 1500

Sample Output:
--------------
900 475 1800 285 1350

*/

//incomplete!!!!

import java.util.*;
import java.util.concurrent.*;
class Solution{
    static class Read implements Callable<Integer>{
        
    }
    static class orderTask implements Callable<Integer>{
        int price;
        public orderTask(int price){
            this.price=price;
        }
        public Integer call(){
            int finalPrice=0;
            if(price>=1000){
                finalPrice=(int)(price*0.90);
            }
            else{
                finalPrice=(int)(price*0.95);
            }
            return finalPric;
        }
    }
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        ExecutorService executor=Executors.newFixedThreadPool(n);
        List<Future<Integer>>res=new ArrayList<>();
        for(int i=0;i<n;i++){
            int k=sc.nextInt();
            res.add(executor.submit(new orderTask(k)));
        }
        for(Future<Integer>f:res){
            System.out.println(f.get());
        }
        
    }
}