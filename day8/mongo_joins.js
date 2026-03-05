/*
Identify customers who have placed both PAID and CANCELLED orders.


Collection Names:customers, orders

Sample Document in customers Collections:
-----------------------------------------
{
  _id: ObjectId('69799f4032713a3c10683245'),
  customerId: 'C101',
  name: 'Arjun',
  city: 'Hyderabad',
  membership: 'PREMIUM'
}

Sample Document in orders Collections:
--------------------------------------
{
  _id: ObjectId('69799f2132713a3c1068323b'),
  orderId: 'O001',
  customerId: 'C101',
  amount: 4500,
  status: 'PAID',
  createdAt: ISODate('2026-01-10T00:00:00.000Z')
}

Sample Output:
--------------
[
  { _id: 'C106', orderStatuses: [ 'CANCELLED', 'PAID' ] },
  ....
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/

printjson(
    
)
/*
Find the total number of orders placed by each membership type.

Collection Names:customers, orders

Sample Document in customers Collections:
-----------------------------------------
{
  _id: ObjectId('69799f4032713a3c10683245'),
  customerId: 'C101',
  name: 'Arjun',
  city: 'Hyderabad',
  membership: 'PREMIUM'
}

Sample Document in orders Collections:
--------------------------------------
{
  _id: ObjectId('69799f2132713a3c1068323b'),
  orderId: 'O001',
  customerId: 'C101',
  amount: 4500,
  status: 'PAID',
  createdAt: ISODate('2026-01-10T00:00:00.000Z')
}

Sample Output:
--------------
[
  { totalOrders: 6, membership: 'PREMIUM' },
  ....
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/





printjson(
    db.orders.aggregate([
        {
            $lookup:{
                from:"customers",
                localField:"customerId",
                foreignField:"customerId",
                as:"customer"
            }
        },
        {
            $unwind:"$customer"
        },
        {
            $group:{
                _id:"$customer.membership",
                totalOrders:{$sum:1}
            }
        },
        {
            $project:{
                _id:0,
                totalOrders:1,
                membership:"$_id"
            }
        }
        ])
);





/*
Among customers from Hyderabad, find the top 2 customers based on total order amount.


Collection Names:customers, orders

Sample Document in customers Collections:
-----------------------------------------
{
  _id: ObjectId('69799f4032713a3c10683245'),
  customerId: 'C101',
  name: 'Arjun',
  city: 'Hyderabad',
  membership: 'PREMIUM'
}

Sample Document in orders Collections:
--------------------------------------
{
  _id: ObjectId('69799f2132713a3c1068323b'),
  orderId: 'O001',
  customerId: 'C101',
  amount: 4500,
  status: 'PAID',
  createdAt: ISODate('2026-01-10T00:00:00.000Z')
}

Sample Output:
--------------
[
  { _id:'C103', totalAmount: 10000 },
  ....
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/
//if u want to rename the _id to something then at last..
// ${
//   project:{
//     _id:0,
//     col:"$_id",
//   }
// }
printjson(
    db.orders.aggregate([
      {
          $lookup:{
            from:"customer",
            foreignField:"customerId",
            localField:"customerId",
            as:"customer"
          }
      },
      {
        $unwind:"$customer"
      },
      {
        $match:{
          "customer.city":"Hyderabad"
        }
      },
      {
        $group:{
          _id:"customerId",
          totalAmount:{$sum:"$amount"}
        }
      },
      {
        $sort:{totalAmount:-1}
      },
      {
        limit:2
      }
      
    ])
)
