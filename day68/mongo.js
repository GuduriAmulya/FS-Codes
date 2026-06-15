/* Write a query to Show Bill Amount Along With Patient Name

Sample Output:
--------------
[                                                                               
  {                                                                             
    totalAmount: 5500,                                                          
    patientName: 'Rahul Sharma'                                                 
  },                                                                            
  ......//more documents if any                                                                         
] 

*/
printjson(db.bills.aggregate([
    {
        $project:{
            _id:0,
            totalAmount:"$totalAmount",
            patientName:"$patientName"
        }
    }]).toArray());

/* Write a query to Show Appointment Details With Patient Information

Sample Output:
--------------
[                                                                               
  [                                                                               
  {                                                                             
    appointmentDate: ISODate('2026-06-15T00:00:00.000Z'),                       
    patientName: 'Rahul Sharma',                                                
    disease: 'Fever'                                                            
  },                                                                            
  ......//more documents if any                                                                         
] 

*/
printjson(db.appointments.aggregate([
    {
        $lookup:{
            from:"patients",
            localField:"patientName",
            foreignField:"patientName",
            as:"patientInfo"
        }
    },
    {
        $unwind:"$patientInfo"
    },{
        $project:{
            _id:0,
            appointmentDate:"$appointmentDate",
            patientName:"$patientName",
            disease:"$patientInfo.disease"
        }
    }
    ]).toArray());

/* Write a query to Show Appointment Details With Doctor Information

Sample Output:
--------------
[                                                                               
  {                                                                             
    appointmentDate: ISODate('2026-06-15T00:00:00.000Z'),                       
    doctorName: 'Dr. Sneha Rao',                                                
    specialization: 'General Medicine'                                          
  },                                                                            
  ......//more documents if any                                                                         
] 

*/
printjson(db.appointments.aggregate([
    {
        $lookup:{
            from:"doctors",
            localField:"doctorName",
            foreignField:"doctorName",
            as:"doctorInfo"
        }
    },{
        $unwind:"$doctorInfo"
    },{
        $project:{
            _id:0,
            appointmentDate:"$appointmentDate",
            doctorName:"$doctorName",
            specialization:"$doctorInfo.specialization",
            
        }
    }
    ]).toArray());



/* Write a query to Show Admission Details With Room Information

Sample Output:
--------------
[                                                                               
  {                                                                             
    patientName: 'Rahul Sharma',                                                
    roomNumber: 101,                                                            
    roomType: 'General'                                                         
  },                                                                            
  ......//more documents if any                                                                         
] 

*/
printjson(db.admissions.aggregate(
    [
    {
        $lookup:{
            from:"rooms",
            localField:"roomNumber",
            foreignField:"roomNumber",
            as:"roomInfo"
        }
    },{
        $unwind:"$roomInfo"
    },{
        $project:{
            _id:0,
            patientName:"$patientName",
            roomNumber:"$roomNumber",
            roomType:"$roomInfo.roomType"
        }
    }    
    ]
    ))
