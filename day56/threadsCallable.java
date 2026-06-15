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

class Solution {
    static class ReadTask implements Callable<Integer> {
        int price;
        ReadTask(int price) { this.price = price; }
        public Integer call() { return price; }
    }
    static class DiscountTask implements Callable<Integer> {
        Future<Integer> prev;
        DiscountTask(Future<Integer> prev) { this.prev = prev; }

        public Integer call() throws Exception {
            int price = prev.get();
            if (price >= 1000) return (int)(price * 0.9);
            else return (int)(price * 0.95);
        }
    }
    static class FinalTask implements Callable<Integer> {
        Future<Integer> prev;
        FinalTask(Future<Integer> prev) { this.prev = prev; }

        public Integer call() throws Exception {
            return prev.get();
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ExecutorService executor = Executors.newFixedThreadPool(n);

        List<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int price = sc.nextInt();

            Future<Integer> f1 = executor.submit(new ReadTask(price));
            Future<Integer> f2 = executor.submit(new DiscountTask(f1));
            Future<Integer> f3 = executor.submit(new FinalTask(f2));

            results.add(f3);
        }

        for (Future<Integer> f : results) {
            System.out.print(f.get() + " ");
        }

        executor.shutdown();
    }
}