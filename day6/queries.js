/*
Create largeOrdersItems: keep only items where qty ≥ 3


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

Sample Output:
--------------
[                                                                                                                                                     
  {                                                                                                                                                   
    userId: 'u1',                                                                                                                                     
    largeOrdersItems: []                                                                                                                              
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    largeOrdersItems: [                                                                                                                               
      {                                                                                                                                               
        sku: 'C3',                                                                                                                                    
        qty: 5                                                                                                                                        
      }                                                                                                                                               
    ]                                                                                                                                                 
  }                                                                                                                                                  
]                                                                                                                                                     

*/

printjson(
    db.orders.aggregate([
        {
            $project:{
                userId:1,_id:0,largeOrdersItems:{
                    $filter:{
                        input:"$items", as :"i",
                        cond:{$gte:["$$i.qty",3]}
                    }
                }
            }
        }
        ])
)



/*
Create itemSummary: map each item to { sku, isBulk }
(where isBulk = true if qty ≥ 2)


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

Sample Output:
--------------
[                                                                                                                                                     
  {                                                                                                                                                   
    userId: 'u1',                                                                                                                                     
    itemSummary: [                                                                                                                                    
      {                                                                                                                                               
        sku: 'A1',                                                                                                                                    
        isBulk: true                                                                                                                                  
      },                                                                                                                                              
      {                                                                                                                                               
        sku: 'B2',                                                                                                                                    
        isBulk: false                                                                                                                                 
      }                                                                                                                                               
    ]                                                                                                                                                 
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    itemSummary: [                                                                                                                                    
      {                                                                                                                                               
        sku: 'C3',                                                                                                                                    
        isBulk: true                                                                                                                                  
      }                                                                                                                                               
    ]                                                                                                                                                 
  }                                                                                                                                                   
]  
	
*/

printjson(
    db.orders.aggregate([
        {
            $project:{
                userId:1,_id:0,itemSummary:{
                    $map:{
                        input:"$items", 
                        as : "i",
                        in:{sku:"$$i.sku", isBulk:{$gte:["$$i.qty",2]}}
                    }
                }
            }
        }
        ])
)



/*
Compute totalUnits: total quantity of all items in each order


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

Sample Output:
--------------
[                                                                                                                                                     
  {                                                                                                                                                   
    userId: 'u1',                                                                                                                                     
    totalUnits: 3                                                                                                                                     
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    totalUnits: 5                                                                                                                                     
  }
]

*/

printjson(
    db.orders.aggregate([
        {
          $project:{
            userId:1,_id:0,totalUnits:{
              $sum:"$items.qty"
            }
          }
        }
    ])
)


printjson(
  db.orders.aggregate([
    {
      $project:{userId:1,_id:0,totalUnits:{
        $reduce:{
          input:"$items",
          initialValue:0,
          in:{$add:["$$value","$$this.qty"]}
        }
      }
    }
    }
  ])
)

/*
Create bulkSkuList: list SKUs where qty ≥ 2

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

Sample Output:
--------------
[                                                                                                                                                     
  {                                                                                                                                                   
    userId: 'u1',                                                                                                                                     
    bulkSkuList: [                                                                                                                                    
      'A1'                                                                                                                                            
    ]                                                                                                                                                 
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    bulkSkuList: [                                                                                                                                    
      'C3'                                                                                                                                            
    ]                                                                                                                                                 
  }
]

*/

printjson(
    db.orders.aggregate([
        {
            $project:{userId:1,_id:0,bulkSkuList:{
              $map:{
                input:{
                    $filter:{// filter the objects whose qty>=2
                      input:"$items",as:"i",cond:{$gte:["$$i.qty",2]}
                    }
                  },
                  as:"i",
                  in:"$$i.sku"
                }
            }}
        }
        ])
)



/*
Compute bulkItemCount: total quantity of items where qty ≥ 2


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

Sample Output:
--------------
[                                                                                                                                                     
  {                                                                                                                                                   
    userId: 'u1',                                                                                                                                     
    bulkItemCount: 2                                                                                                                                  
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u2',                                                                                                                                     
    bulkItemCount: 5                                                                                                                                  
  },                                                                                                                                                  
  {                                                                                                                                                   
    userId: 'u3',                                                                                                                                     
    bulkItemCount: 0                                                                                                                                  
  }
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
        $project:{userId:1,_id:0,bulkItemCount:{
          $sum:{
            $map:{
              input:{
                $filter:{// filter the objects whose qty>=2
                  input:"$items",as:"i",cond:{$gte:["$$i.qty",2]}
                }
              }
            },
            as:"i",
            in:"$$i.qty"
          }
        }}
      }
    ])
)

printjson(
  db.order.aggregate([
    {
      project:{userId:1,_id:0,bulkItemCount:{
        $reduce:{
          input:"$items",initialValue:0,
          $cond:[
            {$gte:["$$this.qty",2]},
            {$add:["$$value","$$this.qty"]},"$$value"
          ]
        }
      }}
    }
  ])
)
