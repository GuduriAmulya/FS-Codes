// Write a program that takes an IP address and subnet mask (in CIDR notation, 
// e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

// Input Format:
// ---------------
// A String, IPAddress
// An integer, CIDR

// Output Format:
// ---------------
// Space separated IP addresses, network IP and broadcast IP.


// Sample Input-1:
// -----------------
// 192.168.1.10
// 24

// Sample Output-1:
// ------------------
// 192.168.1.0 192.168.1.255


// Sample Input-2:
// -----------------
// 192.0.2.1
// 24

// Sample Output-2:
// ------------------
// 192.0.2.0 192.0.2.255

// IPv4 address has 32 bits.
// CIDR /24 → first 24 bits = network
// remaining (32 − CIDR) bits = host bits
//network addr: host bits=0, broadcast addr: host bits=1
import java.util.*;
class PrintBroadCast{
    public static int IpToInt(String ip){
        System.out.println("ip: "+ip);
        String[]parts=ip.split("\\.");
        int res=0;
        for(String p:parts){
            System.out.println("part: "+p);
            res=(res<<8)|Integer.parseInt(p);
            System.out.println("res: "+res);
        }
        return res;
    }
    static String intToIp(int ip){
        return ((ip>>24)&255)+"."+((ip>>16)&255)+"."+((ip>>8)&255)+"."+(ip&255);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String ipAddr=sc.nextLine();
        int cidr=sc.nextInt();
        int ip=IpToInt(ipAddr);
        int subnetMask=cidr==0?0:(-1<<(32-cidr));
        int network=ip&subnetMask;
        int broadcast=network | ~subnetMask;
        System.out.println(intToIp(network)+" "+intToIp(broadcast));
    }
}