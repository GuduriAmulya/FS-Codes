/*
Implement the Java class "EmpService" that retrieves all records from 
the emp table using JDBC and displays them in the required format.

You are given an emp table with the following columns:
    empno (INT) – Employee Number
    ename (VARCHAR) – Employee Name
    job (VARCHAR) – Job Role
    mgr (INT) – Manager ID
    hiredate (DATE) – Date of Joining
    sal (DOUBLE) – Salary
    comm (DOUBLE) – Commission
    deptno (INT) – Department Number

👉 You are NOT responsible for:
    - Creating database connection
    - Writing main() method

    
Fetch all records from the emp table using the query:
SELECT * FROM emp;


Output Format:
--------------

EMPNO | ENAME | JOB | MGR | HIREDATE | SAL | COMM | DEPTNO
---------------------------------------------------------
7369  | SMITH | CLERK | 7902 | 1980-12-17 | 800.0 | 0.0 | 20
...

NOTE:
Ensure proper handling of database resources: Close ResultSet, Statement, and 
Connection Handle exceptions appropriately.


*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

public class EmpService {

    public void fetchEmployees(Connection conn) {
        
        //Fetch the record and then print them like as follows:
        Statement st=null;
        ResultSet rs=null;
        try{
            st=conn.createStatement();
            rs=st.executeQuery("SELECT * from emp");
        
        System.out.println("EMPNO | ENAME | JOB | MGR | HIREDATE | SAL | COMM | DEPTNO");
        System.out.println("---------------------------------------------------------");
        while(rs.next()){
            int empno=rs.getInt("empno");
            String ename=rs.getString("ename");
            String job=rs.getString("job");
            int mgr=rs.getInt("mgr");
            Date hiredate=rs.getDate("hiredate");
            double sal=rs.getDouble("sal");
            double comm=rs.getDouble("comm");
            int deptno=rs.getInt("deptno");
            System.out.println(empno+" | "+ename+" | "+job+" | "+mgr+" | "+hiredate+" | "+sal+" | "+comm+" | "+deptno);
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
        // print the records from here 

    }
}