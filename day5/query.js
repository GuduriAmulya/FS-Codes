/*


Collection Name: orders

Sample Document:
----------------
{
  "_id": {
    "$oid": "6970698a212a45531c13cba1"
  },
  "userId": "u1",
  "status": "PAID",
  "provider": "stripe",
  "amount": 650,
  "items": [
    {
      "sku": "A1",
      "qty": 2
    },
    {
      "sku": "B2",
      "qty": 1
    }
  ],
  "createdAt": {
    "$date": "2026-01-20T09:00:00.000Z"
  }
}


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/

//status:paid and provider: stripe..
printjson(
    db.orders.find({status:"PAID",provider:"stripe"})
)

//all orders whose amount>500
printjson(
    db.orders.find({amount:{$gt: 500}})
)
//when 2 conditions then comma is sufficient
printjson(
    db.orders.find({amount:{$lt: 1000,$gt:500}})
)

//disjunction=>OR
//create 2 different {} embedded inside []=>{$or:[{status: },{status: }]}
printjson(
    db.orders.find({$or:[{status:"PAID"},{status:"REFUNDED"}]})
)
  db.orders.find({$or:[{status:"PAID"},{amount:{$lt:3000,$gt:1000}}]})
//using in
//provider can be stripe or razorpay,, using In
printjson(
    db.orders.find({provider:{$in:["stripe","razorpay"]}})
)
//find items where sku="A!"=>its inside array!
// anything which has dot notation should be inside quotes.. ""
printjson(
    db.orders.find({ "items.sku" : "A1" })
)

//find all orders where item with sku="A1" and qty>=2 in the same array element
//each record in its item field it it has sku="A1"and qty>=2
//elemMatch
printjson(
    db.orders.find({ items: { $elemMatch: {sku:"A1",qty:{$gte:2}}  } })
)
//sql=>db should be 1NF, any field should be atomic.. =>restriction. therefore cannot store an array 
// or collection in relational db(sql)
//no rel db(mongodb)=>array allowed...!!

//find all ordrs where num of items is more than 5
//use $expr + $size
//expr:is a conjunction or disjunction of operataor. each operator should be told whic
//combination of operators..=expr
printjson(
    db.orders.find({ $expr: { $gt:[{$size: "$items"} ,5 ] } })
)

//return only userid,status for orders where status=paid
printjson(
                //condition         projection
    db.orders.find({ status:"PAID" },{userId:1,amount:1,status:1,_id:0})
)
