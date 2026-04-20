/*
You are given a set of releases of a software and you are asked to find the most
recent release. There may be multiple checkins of the software in a given branch. 
Each branch may also have sub branches. For example release 3-5-4 refers to the 
fourth checkin in the fifth sub branch of the third main branch. 
This hierarchy can go upto any number of levels. 

If a level is missing it is considered as level 0 in that hierarchy. 
For example 3-5-7 is  same as 3-5-7-0 or even same as 3-5-7-0-0. 
The higher numbers denote more recent releases. 

For example 3-5-7-1 is more recent than 3-5-7 but less recent than 3-6.

Input Format:
-------------
A single line space separated strings, list of releases 

Output Format:
--------------
Print the latest release of the software.


Sample Input-1:
---------------
1-2 1-2-3-0-0 1-2-3

Sample Output-1:
----------------
1-2-3

Sample Input-2:
---------------
3-5-4 3-5-7 3-5-7-1 3-5-7-0-0 3-6

3-5-4-0-0   3-5-7-1-0   3-5-7-0-0   3-6-0-0-0
//sort the list in asending order...
Sample Output-2:
----------------
3-6

 */


//75/100
// 2 testcases failing
import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String arr[]=sc.nextLine().split(" ");
        //Arrays.sort(arr);
        List<List<Integer>>l=new ArrayList<>();
        int ind=0;
        
        for(String s:arr){
            String a[]=s.split("-");
            l.add(new ArrayList<>());
            for(int i=0;i<a.length;i++){
                l.get(ind).add(Integer.parseInt(a[i]));
            }
            ind++;
        }
        //System.out.println(l);
        l.sort((l1,l2)->{
            int maxSize=Math.max(l1.size(),l2.size());
            for(int i=0;i<maxSize;i++){
                int v1=i<l1.size()?l1.get(i):0;
                int v2=i<l2.size()?l2.get(i):0;
                int cmp=Integer.compare(v1,v2);
                if(cmp!=0)return cmp;
            }
            return 0;
        });
        //System.out.println(l);
        //int i=l.size()-1;
        List<Integer>ans=l.get(l.size()-1);
        int last=ans.size()-1;
        while(last>=0 && ans.get(last)==0){
            last--;
        }
        for(int j=0;j<=last;j++){
            System.out.print(ans.get(j));
            if(j!=ans.size()-1){
                System.out.print("-");
            }
        }
        //System.out.println(l.get(i));
    }
}