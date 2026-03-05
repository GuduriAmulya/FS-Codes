// You are designing a multithreaded output controller that prints values from 1 to N.

// Four independent threads are created:
//     ThreadA → prints "Hi"
//     ThreadB → prints "Hello"
//     ThreadC → prints "HiHello"
//     ThreadD → prints the number itself

// The threads must follow these rules:
//     1. If the number is divisible by 3 only, print "Hi".
//     2. If the number is divisible by 5 only, print "Hello".
//     3. If the number is divisible by both 3 and 5, print "HiHello".
//     4. Otherwise, print the number.
//     5. Output must appear strictly in order from 1 to N.
//     6. All four threads run concurrently.
//     7. Proper synchronization must be implemented to avoid race conditions.

// Input Format:
// -------------
// Line-1: An integer N.

// Output Format:
// --------------
// Print a string array[].

// Constraints:
// • 1 <= n <= 10^4
 
// Sample Input-1:
// ---------------
// 4

// Sample Output-1:
// ----------------
// 1 2 Hi 4 


// Sample Input-2:
// ---------------
// 15

// Sample Output-2:
// ----------------
// 1 2 Hi 4 Hello Hi 7 8 Hi Hello 11 Hi 13 14 HiHello 



import java.util.*;
class Shared{
    int n;
    int cur=1;
    Shared(int n){
        this.n=n;
    }
    public synchronized void printHi() throws InterruptedException{
        while(cur<=n){
            while(cur<=n &&!(cur%3==0 && cur%5!=0)){
                wait();
            }
            if(cur<=n){
                System.out.print("Hi ");
                cur++;
                notifyAll();
            }
        }
        
    }
    public synchronized void printHello() throws InterruptedException{
        while(cur<=n){
            while(cur<=n && !(cur%3!=0 && cur%5==0)){
                wait();
            }
            if(cur<=n){
                System.out.print("Hello ");
                cur++;
                notifyAll();
            }
        }
    }
    public synchronized void printHiHello() throws InterruptedException{
        while(cur<=n){
            while(cur<=n && !(cur%3==0 && cur%5==0)){
                wait();
            }
            if(cur<=n){
                System.out.println("HiHello ");
                cur++;
                notifyAll();
            }
        }
    }
    public synchronized void printNumber() throws InterruptedException{
        while(cur<=n){
            while(cur<=n && (cur%3==0 || cur%5==0)){
                wait();
            }
            if(cur<=n){
                System.out.print(cur+" ");
                cur++;
                notifyAll();
            }
        }
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Shared h=new Shared(n);
        Thread ta=new Thread(()-> {
        try{
            h.printHi();
        }catch(Exception e){
            
        }});
        Thread tb=new Thread(()-> {
        try{
            h.printHello();
        }catch(Exception e){
            
        }});
        Thread tc=new Thread(()-> {
        try{
            h.printHiHello();
        }catch(Exception e){
            
        }});
        Thread td=new Thread(()-> {
        try{
            h.printNumber();
        }catch(Exception e){
            
        }});
        // Thread tb=new Thread(()->h.printHello());
        // Thread tc=new Thread(()->h.printHiHello());
        // Thread td=new Thread(()->h.printNumber());
        ta.start();
        tb.start();
        tc.start();
        td.start();
    }
}