/*
Write a query to find CTC to Company.

Note: CTC is annual salary of all employees.
Annual Salary is 12*sum(sal+comm); check if comm is null set to 0.

TABLES:
========
salgrade:
  grade int(4) primary key,  
  losal decimal(10,2),  
  hisal decimal(10,2)

dept:
  deptno int(2) primary key,
  dname varchar(50) not null,
  location varchar(50) not null

emp:
  empno int(4) primary key,
  ename varchar(50) not null,
  job varchar(50) not null,
  mgr int(4),
  hiredate date,
  sal decimal(10,2),
  comm decimal(10,2),
  deptno int(2)

  
Sample Output:
------------
CTC                                                                                                                     
374700.00 

*/

use fs;
select sum(12*(sal+coalesce(comm,0))) as CTC from emp;

/*
Write an SQL query to display the employee number and name of employee working 
as CLERK and earning highest salary among CLERKS.


TABLES:
========
salgrade:
  grade int(4) primary key,  
  losal decimal(10,2),  
  hisal decimal(10,2)

dept:
  deptno int(2) primary key,
  dname varchar(50) not null,
  location varchar(50) not null

emp:
  empno int(4) primary key,
  ename varchar(50) not null,
  job varchar(50) not null,
  mgr int(4),
  hiredate date,
  sal decimal(10,2),
  comm decimal(10,2),
  deptno int(2)


Sample Output:
-------------
empno   ename                                                                                                           
7934    FORD 

*/


use fs;
select empno, ename from emp where job="CLERK" and sal=(select max(sal) from emp where job="CLERK");
