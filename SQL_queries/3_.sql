/*

Write an SQL query to display the food_id and name of food items whose names 
contain at least two consecutive vowels (a, e, i, o, u).

Tables:
------
Customers (customer_id, first_name, last_name, email, phone, address) 
FoodItems (food_id, name, description, price, category, availability)
Orders (order_id, customer_id, food_id, quantity, status, total_amount, order_date, )

OUTPUT:
+---------+----------------------+
| food_id | name                 |
+---------+----------------------+
|       1 | Paneer Butter Masala |
+---------+----------------------+

*/

use GT;
select food_id, name from FoodItems
where name regexp '[aeiou]{2}';

/*
Write an SQL query to display the full name and email of customers, along with 
the food item name and its price, who ordered the most expensive food item(s) 
available.


Tables:
------
Customers (customer_id, first_name, last_name, email, phone, address) 
FoodItems (food_id, name, description, price, category, availability)
Orders (order_id, customer_id, food_id, quantity, status, total_amount, order_date, )

OUTPUT:

+-------------+-----------------------+-----------------+--------+
| full_name   | email                 | food_item       | price  |
+-------------+-----------------------+-----------------+--------+
| Amit Sharma | amit.sharma@gmail.com | Chicken Biryani | 300.00 |
+-------------+-----------------------+-----------------+--------+
*/

use GT;
select concat(c.first_name,' ',c.last_name) as full_name,
c.email, f.name as food_item, f.price as price from Customers c
join Orders o on c.customer_id=o.customer_id
join FoodItems f on o.food_id =f.food_id
where f.price =(select max(price) from FoodItems);


/*

Write an SQL query to display the names of food items along with the total 
quantity ordered for each item, showing only those items whose total ordered 
quantity is greater than the average quantity ordered across all food items.

Tables:
------
Customers (customer_id, first_name, last_name, email, phone, address) 
FoodItems (food_id, name, description, price, category, availability)
Orders (order_id, customer_id, food_id, quantity, status, total_amount, order_date, )

OUTPUT:
+-------------+----------------+
| name        | total_quantity |
+-------------+----------------+
| Masala Dosa |              5 |
| Samosa      |              9 |
+-------------+----------------+

*/

use GT;
select f.name, SUM(o.quantity) as total_quantity from FoodItems f
join Orders o on o.food_id =f.food_id
group by f.food_id, f.name
having sum(o.quantity) > (
    select avg(total_qty) from 
    (select sum(quantity) as total_qty from Orders group by food_id) t
);
