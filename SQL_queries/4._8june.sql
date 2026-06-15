/*
Write an SQL query to display the details of customers who have never placed 
any order.

Output Fields: customer_id, name, email, city

Tables:
-------
customers ( customer_id, name, email, city, joined_date )
orders ( order_id, customer_id, order_date, total_amount, status )
order_items ( item_id, order_id, product_name, quantity, unit_price )


Sample Output:
--------------
customer_id	    name	        email	            city
5	            Kiran Mehta	    kiran@mail.com	    Delhi

*/
-- select * from orders;
select c.customer_id, c.name,c.email,c.city from customers c
left join orders o on o.customer_id=c.customer_id where o.order_id is NULL;

/*
Write an SQL query to calculate the total spending of each customer.

Output Fields: customer_id, name, city, total_orders, total_spent


Tables:
-------
customers ( customer_id, name, email, city, joined_date )
orders ( order_id, customer_id, order_date, total_amount, status )
order_items ( item_id, order_id, product_name, quantity, unit_price )


Sample Output:
--------------
customer_id	name	        city	    total_orders	total_spent
1	        Arjun Sharma	Hyderabad	2	            5900.00
4	        Sneha Reddy	    Hyderabad	1	            5600.00
2	        Priya Nair	    Mumbai	    2	            2150.00
3	        Rahul Gupta	    Bengaluru	1	            800.00

*/

use alpha;
select c.customer_id, c.name,c.city,COUNT(o.order_id) as total_orders, SUM(o.total_amount) as total_spent
from customers c
join orders o on c.customer_id=o.customer_id
group by c.customer_id
order by total_spent desc;

/*
Write an SQL query to find customers who have placed more than one order.

Output Fields: name, city, delivered_orders

Tables:
-------
customers ( customer_id, name, email, city, joined_date )
orders ( order_id, customer_id, order_date, total_amount, status )
order_items ( item_id, order_id, product_name, quantity, unit_price )


Sample Output:
--------------
name	        city	    delivered_orders
Arjun Sharma	Hyderabad	    2
Priya Nair	    Mumbai	        2



*/

use alpha;

select c.name, c.city,count(o.order_id) as delivered_orders from customers c
join orders o on c.customer_id=o.customer_id
-- where o.status="DELIVERED"
group by c.customer_id, c.city,c.name
having count(o.order_id)>1;

/*
Write an SQL query to display the products ordered by the highest-spending 
customer.

Output Fields: name, product_name, quantity, unit_price

Tables:
-------
customers ( customer_id, name, email, city, joined_date )
orders ( order_id, customer_id, order_date, total_amount, status )
order_items ( item_id, order_id, product_name, quantity, unit_price )


Sample Output:
--------------
name	        product_name	quantity	unit_price
Arjun Sharma	Laptop Stand	1	        1200.00
Arjun Sharma	Keyboard	    2	        650.00
Arjun Sharma	Monitor	        1	        3400.00

*/

use alpha;
select c.name, i.product_name, i.quantity, i.unit_price from customers c
join orders o on o.customer_id=c.customer_id
join order_items i on o.order_id=i.order_id
where c.customer_id=(
Select customer_id from orders group by customer_id order by sum(total_amount) desc limit 1
);
