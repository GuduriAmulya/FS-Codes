// Write a program that takes an IP address and subnet mask (in CIDR notation, 
// e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
// format.

// Input Format:
// -------------
// An integer, CIDR

// Output Format:
// --------------
// String, Subnet's IP Address


// Sample Input-1:
// ---------------
// 25

// Sample Output-1:
// ----------------
// 255.255.255.128


// Sample Input-2:
// ---------------
// 22

// Sample Output-2:
// ----------------
// 255.255.252.0

import java.util.*;
class IPCIDR{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int subnet[]=new int[4];
        for(int i=0;i<4;i++){
            if(n>=8){
                subnet[i]=255;
                n-=8;
            }
            else if(n>0){
                subnet[i]=256-(int)Math.pow(2,8-n);
                n=0;
            }
            else{
                subnet[i]=0;
            }
        }
        System.out.println(subnet[0]+"."+subnet[1]+"."+subnet[2]+"."+subnet[3]);

    }
}