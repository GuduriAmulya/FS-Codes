/*
You are given a phrase and a paper of size m*n.
So you can type exactly m * n characters on that paper.
i.e,. there are 'm' rows and in each row you can type 'n' characters
You need to type the phrase on the paper with some rules.

The rules are as follows:
    - A word in the phrase cannot be split into two lines.
    - The order of words in the phrase shuld not change.
    - Two consecutive words in a line must be separated by a single space.

Your task is to find out how many times the phrase can be typed on the paper.


Constraint:
-----------
Length of each word is <=10.

Input Format:
-------------
Line-1: Three space separated integers m, n, and s, grid size m*n, number of words.
Line-2: 's' space separated strings, set of words

Output Format:
--------------
Print an integer, no.of times set of words fit into the grid.


Sample Input-1:
---------------
2 8 2
socail distance

Sample Output-1:
----------------
1

Explanation:
------------
social__
distance


Sample Input-2:
---------------
3 6 3
a bc def

Sample Output-2:
----------------
2

Explanation:
------------
a_bc__
def_a_
bc_def

*/


import java.util.*;
class prg2{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();//rows
        int n=sc.nextInt();//cols
        int num=sc.nextInt();//num of words..
        sc.nextLine();
        String words[]=sc.nextLine().split(" ");
        String sentence=String.join(" ",words)+" ";
        // "social distance " adding empty string at end..
        //System.out.println(sentence+"end");
        int len=sentence.length();
        int pos=0;
        for(int i=0;i<m;i++){//we have to fill m rows..
            pos+=n;
            if(sentence.charAt(pos%len)==' '){
                pos++;//move next pos..
            }
            else{
                while(pos>0 && sentence.charAt((pos-1)%len)!=' '){
                    pos--;
                }
            }
        }
        System.out.println(pos/len);


    }
}