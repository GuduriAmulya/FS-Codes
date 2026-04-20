/*
POINTS TO REMEMBER:
| Function     | Duplicate Values    | Output Example |
| ------------ | ------------------- | -------------- |
| ROW_NUMBER() | ❌ No duplicates     | 1,2,3,4        |
| RANK()       | ✅ Same rank + gaps  | 1,2,2,4        |
| DENSE_RANK() | ✅ Same rank no gaps | 1,2,2,3        |

WINDOW FN:A window function performs calculations across a set of rows related to the current row, 
without collapsing rows (unlike GROUP BY).
👉 Key idea:
GROUP BY → reduces rows
Window function → keeps all rows + adds extra computed column
WHY WINDOW FN?
sometimes we want:
Ranking (1st, 2nd, 3rd…)
Running totals
Compare with previous/next rows
Group-wise calculations without losing individual rows
*/

/*
Management wants to compare all employees across the company.
Assign a rank to employees based on salary across the entire company 
(not departmentwise). Employees with the same salary should share the same rank, 
and the next rank should be skipped.


Tables:
------
emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)

OUTPUT:
+--------+---------+-------------+                                                                                                                    
| ename  | sal     | global_rank |                                                                                                                    
+--------+---------+-------------+                                                                                                                    
| KEVIN  | 5000.00 |           1 |                                                                                                                    
| SCOTT  | 3000.00 |           2 |                                                                                                                    
| FORD   | 3000.00 |           2 |                                                                                                                    
+--------+---------+-------------+ 

*/

use fs;
select ename, sal, RANK() over (order by sal desc) as global_rank from emp;


/*
Within each department, assign ranks to employees based on salary such that employees
with equal salaries share the same rank, and ranks are consecutive without gaps.


Tables:
------

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:
+--------+--------+---------+----------------+
| ename  | deptno | sal     | dept_dense_ran |
+--------+--------+---------+----------------+
| CLARK  |     10 | 2450.00 |              1 |
| ALLEN  |     10 | 1600.00 |              2 |
| FORD   |     10 | 1300.00 |              3 |
+--------+--------+---------+----------------+

*/

use fs; 
-- select * from emp;
select ename, deptno, sal, DENSE_RANK() over(partition by deptno order by sal desc) as dept_dense_ran from emp;


/*
Assign ranks to employees based on salary such that if multiple employees have 
the same salary, they receive the same rank, and no ranks are skipped.


Tables:
------

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:
+--------+---------+------------+
| ename  | sal     | dense_rank |
+--------+---------+------------+
| KEVIN  | 5000.00 |          1 |
| SCOTT  | 3000.00 |          2 |
| FORD   | 3000.00 |          2 |
+--------+---------+------------+

*/

use fs;
select ename, sal, DENSE_RANK() over(order by sal desc) as "dense_rank" from emp;

/*
Identify employees who earn the lowest salary in each department.
If multiple employees share the same lowest salary, include all of them.


Tables:
------

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:
+--------+--------+---------+-----+
| ename  | deptno | sal     | rnk |
+--------+--------+---------+-----+
| FORD   |     10 | 1300.00 |   1 |
| SMITH  |     20 |  800.00 |   1 |
+--------+--------+---------+-----+

*/

use fs;
select ename,deptno, sal,rnk 
from (select ename, deptno, sal,RANK() over (partition by deptno order by sal ) as "rnk" 
from emp) t where rnk=1;

/*
Find employees who fall under the same salary rank category (rank 1 or rank 2) 
within their respective departments. This helps identify top and mid-level 
performers across all departments.


Tables:
------
emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:
+--------+--------+---------+-----+
| ename  | deptno | sal     | rnk |
+--------+--------+---------+-----+
| CLARK  |     10 | 2450.00 |   1 |
| ALLEN  |     10 | 1600.00 |   2 |
+--------+--------+---------+-----+


*/

use fs;
select ename, deptno,sal, rnk from (select ename, deptno,sal, DENSE_RANK() over (partition by deptno order by sal desc) as "rnk" from emp) t 
where rnk in(1,2);