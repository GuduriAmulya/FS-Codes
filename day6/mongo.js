//aggregate=>array of operations [{},{}]

//$=>value from collection
//$$=>variable developed by code's value 
([
    {$project:{
        orderId:1,
        bulkItems:{
            $filter:{
                input: "$items" , as :"i",
                cond:{$gte:["$$i.qty",2]}
            }
        }
    }}
])


/*
Collection Name: orders

Sample Document:
----------------
{
    _id: ObjectId('6970698a212a45531c13cba1'),
    userId: 'u1',
    status: 'PAID',
    provider: 'stripe',
    amount: 650,
    items: [ { sku: 'A1', qty: 2 }, { sku: 'B2', qty: 1 } ],
    createdAt: ISODate('2026-01-20T09:00:00.000Z')
}


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	->  db -> Refers to the database.
    	->  <collection> -> Your collection name
	
*/

printjson(
    db.orders.aggregate([
        {$project:{orderId:1,skuList:
            {
                $map:{input:"$items", as: "i",in:"$$i.sku"}
            }
        }}
        ])
)
//reduce..

printjson(
    db.orders.aggregate([
        {$project:{orderId:1,
            orderTotal:{$reduce:{input:"$items",initialValue:0,
                in:{
                    $add:["$$value" ,{ $multiply: ["$$this.qty","$$this.price"] } ]
                }
            }}
        }
        }
        ])
)
//add is accumulation the multiplication od qty,price  and saving in var =value.. 



//filter +map
//item with price>=200 name it as highSkus output only each sku

printjson(
    db.orders.aggregate([
        {
            $project:{
                orderId:1,_id:0,highSkus:{
                    $map: {
                        input:{$filter: {input: "$items", as : "i", cond:{
                            $gte:["$$i.price",200]
                        }}}, as: "x", in: "$$x.sku"
                    }
                }
            }
        }
        ])
)


