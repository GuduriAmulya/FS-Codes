/*
A university maintains a file named students.txt containing student records in 
the following format:

SNo-RollNo-Name
1-23bd1a0502-Adepu Raviteja
2-23bd1a0504-Adithya Kandala
...
201-23P81A0509-BOJJA NIKHILESH

Each Roll Number encodes important information about the student such as college, 
admission type, and department.

You are required to analyze the roll numbers and compute several statistics.

Roll Number Encoding Rules: YYCCCCDDSS, Where specific character positions 
represent:

Position	        Meaning
----------------------------
3rd–4th characters	College Code
5th–6th characters	Admission Type
7th–8th characters	Department Code

College Codes:
--------------
Code	College
---------------
BD	    KMIT
P8	    KMCE

Admission Type Codes:
---------------------
Code	Type
-------------
1A	    Regular
5A	    Lateral Entry

Department Codes:
-----------------
Code	Department
------------------
05	    CSE
12	    IT
66	    CSM
67	    CSD
62	    CSC
69	    CSI

Write a Java program thatrReads an integer N from the user.
Processes only the first N students from the file "students.txt"
Extracts information from each student's roll number.

Input Format
Integer N

Output Format:
As described in sample.

Sample Input:
-------------
50

Sample Output:
--------------
College Wise Students:
KMCE : 7
KMIT : 43
Department Wise Students:
KMCE
CSC : 1
CSE : 4
CSD : 2
KMIT
CSE : 28
CSD : 3
IT : 3
CSM : 9
Admission Type:
Regular Students : 49
Lateral Students : 1



Constraints:
1 ≤ N ≤ 236
*/

import java.util.*;
import java.io.*;
import java.util.regex.*;
class Solution{
    public static void main(String[] args)throws Exception{
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        BufferedReader br=new BufferedReader(new FileReader("students.txt"));
        String line;
        int c=0;
        int kmit=0;
        int kmce=0;
        int regular=0,lateral=0;
        Map<String,Map<String,Integer>>deptMap=new HashMap<>();
        deptMap.put("KMIT",new HashMap<>());
        deptMap.put("KMCE",new HashMap<>());
        Pattern pattern=Pattern.compile("\\d{2}([a-zA-Z0-9]{2})([a-zA-Z0-9]{2})(\\d{2}).*");
        while((line=br.readLine())!=null && c<n){
            String[]parts=line.split("-");
            //System.out.println(Arrays.toString(parts));
            String roll=parts[1].toLowerCase();
            Matcher matcher=pattern.matcher(roll);
            if(matcher.matches()){
                String collegeCode=matcher.group(1).toLowerCase();
                String admission=matcher.group(2).toLowerCase();
                String deptCode=matcher.group(3).toLowerCase();
                String college="";
                if(collegeCode.equals("bd")){
                    college="KMIT";
                    kmit++;
                }
                else if(collegeCode.equals("p8")){
                    college="KMCE";
                    kmce++;
                }
                if(admission.equals("1a")){
                    regular++;
                }
                else if(admission.equals("5a")){
                    lateral++;
                }
                String dept="";
                switch(deptCode){
                    case "05": dept="CSE"; break;
                    case "12": dept="IT"; break;
                    case "67": dept="CSD"; break;
                    case "66": dept="CSM"; break;
                    case "62": dept="CSC"; break;
                    case "69": dept="CSI"; break;
                }
                Map<String,Integer>deptCount=deptMap.get(college);
                deptCount.put(dept,deptCount.getOrDefault(dept,0)+1);
            }
            c++;
            //System.out.println(line);
        }
        System.out.println("College Wise Students:");
        System.out.println("KMCE : "+kmce);
        System.out.println("KMIT : "+kmit);
        System.out.println("Department Wise Students: ");
        for(String college: deptMap.keySet()){
            System.out.println(college);
            Map<String,Integer>mp=deptMap.get(college);
            for(String dept: mp.keySet()){
                System.out.println(dept+" : "+mp.get(dept));
            }
        }
        System.out.println("Admission Type:");
        System.out.println("Regular Students: "+regular);
        System.out.println("Lateral Students: "+lateral);
        
    }
}