// A smart factory has a central alarm system that coordinates three independent 
// subsystems:
//     - Thread A – Alarm Controller -> Activates a warning beep (prints 0).
//     - Thread B – Even Sensor Unit -> Reports only even-numbered machine IDs.
//     - Thread C – Odd Sensor Unit -> Reports only odd-numbered machine IDs.

// The system must produce an alternating sequence:
// 0 1 0 2 0 3 0 4 0 5 ...


// The total length of the sequence must be 2n, where: n is the total number of 
// machine IDs (from 1 to n). Each machine report must always be preceded by a 
// warning beep (0).

// Implement a class FactoryAlarmSystem that ensures proper synchronization between 
// three threads so that: The alarm thread always prints 0, Then either the odd or 
// even thread prints the next correct number. Continue this pattern until all 
// numbers from 1 to n are printed

// only

// 🧾 Input Format
// ---------------
// A single integer: N

// 🖨 Output Format
// ----------------
// A continuous string representing the alarm and machine sequence.

// Sample Input-1:
// ---------------
// 2

// Sample Output-1:
// ----------------
// 0102

// Sample Input-2:
// ---------------
// 5

// Sample Output-2:
// ----------------
// 0102030405


import java.util.*;
class Shared{
    int n;
    int cur=1;
    boolean flag=true;//zero print
    boolean odd=true;
    Shared(int n){
        this.n=n;
    }
    public synchronized void alarm() throws InterruptedException{
        while(cur<=n){
            while(cur<=n && !flag){
                wait();
            }
            if(cur>n)break;
            System.out.print(0);
            flag=false;
            notifyAll();
            
        }
    }
    public synchronized void printEven() throws InterruptedException{
        while(cur<=n){
            while(cur<=n &&(flag || odd)){
                wait();
            }
            if(cur>n)break;
            System.out.print(cur);
            cur++;
            flag=true;
            odd=true;
            notifyAll();
        }
        
    }
    public synchronized void printOdd() throws InterruptedException{
        while(cur<=n){
            while(cur<=n && (flag || !odd)){
                wait();
            }
            if(cur>n)break;
            System.out.print(cur);
            cur++;
            flag=true;
            odd=false;
            notifyAll();
        }
        
    }
}
class ThreadPrintevenOdd{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Shared s=new Shared(n);
        Thread a1=new Thread(()->{
            try{
                s.alarm();
            }
            catch(Exception e){}
        });
        Thread a2=new Thread(()->{
            try{
                s.printOdd();
            }
            catch(Exception e){}
        });
        Thread a3=new Thread(()->{
            try{
                s.printEven();
            }
            catch(Exception e){}
        });
        a1.start();
        a2.start();
        a3.start();
    }
}