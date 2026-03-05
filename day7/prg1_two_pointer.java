// "Emphatic Pronunciation" of a given word is where we take the word and
// replicate some of the letter to emphasize their impact.

// Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
// We define emphatic pronunciation of a word, which is derived by replicating 
// a group (or single) of letters in the original word. 

// So that the replicated group is atleast 3 characters or more and 
// greater than or equal to size of original group. 
// For example Good -> Goood is an emphatic pronunciation,
// but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
// In the question you are given the "Emphatic pronunciation" word, 
// you have to findout how many words can legal result in the 
// "emphatic pronunciation" word.

// Input Format:
// -------------
// Line-1 -> A String contains a single word, Emphatic Pronunciation word
// Line-2 -> Space seperated word/s

// Output Format:
// --------------
// Print an integer as your result

// ss
// s
// Sample Input-1:
// ---------------
// goood
// good goodd

// Sample Output-1:
// ----------------
// 1


// Sample Input-2:
// ---------------
// heeelllooo
// hello hi helo

// Sample Output-2:
// ----------------
// 2
import java.util.*;
class prg1_two_pointer{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String emp=sc.nextLine();
        Map<Character,Integer>mp=new LinkedHashMap<>();
        for(char ch:emp.toCharArray()){
            mp.put(ch,mp.getOrDefault(ch,0)+1);
        }
        String[]arr=sc.nextLine().split(" ");
        int count=0;
        for(String st:arr){
            //System.out.println("for : "+st);
            //if(check(emp,st,mp)){
            if(check(emp,st)){
                count++;
            }
        }
        System.out.println(count);
    }
    public static boolean check(String emp, String s){
        int i=0;
        int j=0;
        while(i<emp.length() && j<s.length()){
            //System.out.println("for: i: "+i+" j: "+j);
            if(emp.charAt(i)!=s.charAt(j))return false;
            char ch=emp.charAt(i);
            int len1=0;
            int len2=0;
            while(i<emp.length() && ch==emp.charAt(i)){
                i++;
                len1++;
            }
            while(j<s.length() && ch==s.charAt(j)){
                j++;
                len2++;
            }
            if(len1<len2)return false;
            if(len1>len2 && len1<3)return false;
            
        }
        return i==emp.length() && j==s.length();
    }
    // public static boolean check(String emp, String s,Map<Character,Integer>mp_emp){
    //     Map<Character,Integer>mp_s=new LinkedHashMap<>();
    //     for(char c:s.toCharArray()){
    //         mp_s.put(c,mp_s.getOrDefault(c,0)+1);
    //     }
    //     for(Map.Entry<Character,Integer>entry:mp_emp.entrySet()){
    //         char ch=entry.getKey();
    //         int val_emp=entry.getValue();
    //         if(!mp_s.containsKey(ch)){
    //             return false;
    //         }
    //         int val_s=mp_s.get(ch);
    //         //System.out.println("for c: "+ch+" em: "+val_emp+" s_val: "+val_s);
    //         if(val_emp==val_s)continue;
    //         if(val_emp<val_s)return false;
    //         if((val_s>=1) && (val_emp<3)) return false;
    //         //if(val_emp>1 && val_emp<3)return false;
    //     }
    //     return true;
    // }
}