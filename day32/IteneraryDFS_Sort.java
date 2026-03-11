/*
Gopal would like to go on Tour, and planned a schedule.
Airport authority has created a new way of issuing tickets.
Gopal purchased a set of airline tickets, 
each ticket contains the 'departure from' and 'arrival to'.

Redesign the Gopal's tour schedule in an order.
Gopal intially must begin his tour from BZA.
Hence the tour schedule's start point should begin with BZA. 

You are given a list of flight tickets which Gopal has purchased.
Your task is to find out and return the tour schedule that has the least 
lexical order. Gopal must use all the tickets once and only once.

Note:
------
If there are multiple valid schedules, you should return the schedule 
that has the smallest lexical order when read as a single string. 
For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

All airports are represented by three capital letters.
You may assume all tickets form at least one valid schedule.

Input Format:
-------------
Single Line of tickets separated by comma, and each ticket separated by space, 
Gopal's flight tickets.

Output Format:
--------------
Print the schedule, which is smallest lexical order of tour schedule.


Sample Input-1:
----------------
DEL HYD,BZA DEL,BLR MAA,HYD BLR
    1      0        3    2
Sample Output-1:
--------------------
[BZA, DEL, HYD, BLR, MAA]


Sample Input-2:
------------------
BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR
 0          3       1       2    4
Sample Output-2:
------------------
[BZA, BLR, CCU, BZA, CCU, BLR]

*/

import java.util.*;
class IteneraryDFS_Sort{
    public static int total;
    public static Map<String, List<String>>graph;
    public static Map<String,boolean[]>used;
    public static List<String>path;
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        String arr[]=sc.nextLine().split(",");
        graph=new HashMap<>();
        total=arr.length;
        for(String a:arr){
            String p[]=a.split(" ");
            String from=p[0];
            String to=p[1];
            graph.putIfAbsent(from,new ArrayList<>());
            graph.get(from).add(to);
        }
        used=new HashMap<>();
        for(String key:graph.keySet()){
            Collections.sort(graph.get(key));//sort the desti..in alphabetical 
            used.put(key, new boolean[graph.get(key).size()]);
        }
        //System.out.println(graph);
        path=new ArrayList<>();
        path.add("BZA");
        dfs("BZA");
        System.out.println(path);
    }
    public static boolean dfs(String src){
        if(path.size()==total+1)return true;
        List<String>dest=graph.get(src);
        if(dest==null)return false;
        boolean[]visit=used.get(src);
        for(int i=0;i<dest.size();i++){
            if(visit[i])continue;
            visit[i]=true;
            path.add(dest.get(i));
            if(dfs(dest.get(i))){
                return true;
            }
            path.remove(path.size()-1);
            visit[i]=false;
        }
        return false;
    }
}