/*
Write a SQL query to find the average salary and number of employees per 
department, ordered by average salary in descending order.

Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:

+--------+-------------+----------+                                                                                                                   
| deptno | AVG(sal)    | COUNT(*) |                                                                                                                   
+--------+-------------+----------+                                                                                                                   
|     40 | 3125.000000 |        2 |                                                                                                                   
|     20 | 1970.833333 |        6 |                                                                                                                   
+--------+-------------+----------+ 

*/


use fs;
select deptno,AVG(sal),Count(*)
from emp
group by deptno
order by avg(sal)desc;


-- //if use join
select e.deptno,d.dname,AVG(e.sal) as AVG_sal, count(*) as emp_count
from emp e
join dept d
on e.deptno=d.deptno
group by e.deptno,d.dname
order by AVG_sal desc;
/*
Write a SQL query to list the total salary and employee count per job, 
excluding clerks.


Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)

OUTPUT:
+-----------+----------+----------+
| job       | SUM(sal) | COUNT(*) |
+-----------+----------+----------+
| SALESMAN  |  5600.00 |        4 |
| MANAGER   |  8275.00 |        3 |
+-----------+----------+----------+

*/
use fs;
select job, sum(sal),count(*)
from emp
where job!='clerk'
group by job


/*
Write a SQL query to show the total salary per department where the total salary 
exceeds 5000, ordered by department number.


Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)


OUTPUT:
+--------+----------+
| deptno | SUM(sal) |
+--------+----------+
|     10 |  5350.00 |
|     20 | 11825.00 |
+--------+----------+

*/
use fs;
-- select * from emp;
select deptno, sum(sal)
from emp
group by deptno
having sum(sal)>5000;




/*
Write a SQL query to list all departments and their employee counts, including 
departments with no employees, using RIGHT JOIN.


Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)

OUTPUT:
+--------+------------+-----------+
| deptno | dname      | emp_count |
+--------+------------+-----------+
|     10 | Accounting |         3 |
|     20 | Research   |         6 |
|     50 | Finance    |         0 |
+--------+------------+-----------+

*/
use fs;

select d.deptno ,d.dname,count(e.empno) as emp_count
from emp e
right join dept d on d.deptno=e.deptno
group by d.deptno;


/*
Write a SQL query to list employee names and department names where 
the department is in 'Chicago' using INNER JOIN.

Tables:
------
salgrade ==> grade int(4) primary key, losal decimal(10,2),  hisal decimal(10,2) 


dept==>   deptno int(2) primary key, dname varchar(50) not null, location varchar(50) not null

emp ==>   empno int(4) primary key, ename varchar(50) not null,
          job varchar(50) not null,  mgr int(4),  hiredate date,
          sal decimal(10,2),  comm decimal(10,2),  deptno int(2)

OUTPUT:
+-------+-------+
| ename | dname |
+-------+-------+
| ALLEN | Sales |
| BLAKE | Sales |
+-------+-------+



*/

use fs;
select e.ename,d.dname
from emp e
inner join dept d on d.deptno=e.deptno
where d.location='Chicago';
