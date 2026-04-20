/*
You are building an Order Processing System where multiple services (Email, SMS, Logging) 
must be notified when an order is processed.

To achieve loose coupling, you must use Delegates and Events.

Requirements:
-------------
1. Input
Take input from user:
OrderId (integer)
Amount (decimal)

2. Core Functionality
Create an Order class
Create an OrderProcessor:
Define a delegate
Define an event
Process order and trigger event

3. Services
Create at least 3 services:
    EmailService
    SMSService
    LoggingService

Each service must:
Subscribe to the event
Perform its own action

Edge Case Requirements (MANDATORY)
-----------------------------------
Your implementation must handle:
A. Invalid Input
Non-numeric values should not crash the program

Expected output:
Invalid input! Please enter numeric values.

B. Invalid Amount
Amount <= 0 -> reject order

Expected output:
Invalid Order Amount. Must be greater than 0.

C. Very Large Amount
If amount > 1,000,000,000, reject with message

Expected output:
Amount too large. Requires manual approval.

D. Duplicate Orders
Same OrderId should not be processed twice

Expected output:
Order <OrderId> is already processed. Skipping duplicate.

E. No Subscribers
System should not crash if no services are attached

Expected output:
Processing Order <OrderId>...
Order processed successfully


F. Valid Order
Amount > 0
Amount ≤ 1,000,000,000
OrderId not processed before

Expected output:
Processing Order <OrderId>...
Order processed successfully
Email sent for Order <OrderId>
SMS sent for Order <OrderId>
Order <OrderId> logged


Bonus (Optional but High Weightage)
Add two events:
OrderSuccess
OrderFailed.

Sample Output:
---------------
Enter Order ID: 101
Enter Amount: 2500
Processing Order 101...
Order processed successfully
Email sent
SMS sent
Order logged


*/

using System;
using System.Collections.Generic;

namespace OrderEventTest
{
    //Order class
    public class Order
    {
        public int OrderId { get; set; }
        public decimal Amount { get; set; }
    }

    public class OrderProcessor
    {
        // TODO: Define delegate
        

        // TODO: Define event
        

        // BONUS: Define success & failure events
        

        // TODO: Maintain processed order IDs
        

        public void ProcessOrder(Order order)
        {
            // TODO: Validate amount (<= 0)
            

            // TODO: Check very large amount (> 1B)
            

            // TODO: Check duplicate order
            

            Console.WriteLine($"Processing Order {order.OrderId}...");

            // TODO: Mark order as processed
            

            Console.WriteLine("Order processed successfully");

            // TODO: Trigger event safely
            

            // BONUS: Trigger success event
        }
    }

    public class EmailService
    {
        public void SendEmail(Order order)
        {
            Console.WriteLine($"Email sent for Order {order.OrderId}");
        }
    }

    public class SMSService
    {
        public void SendSMS(Order order)
        {
            Console.WriteLine($"SMS sent for Order {order.OrderId}");
        }
    }

    public class LoggingService
    {
        public void Log(Order order)
        {
            Console.WriteLine($"Order {order.OrderId} logged");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            var processor = new OrderProcessor();

            var email = new EmailService();
            var sms = new SMSService();
            var log = new LoggingService();

            // TODO: Subscribe services to event
            

            try
            {
                Console.Write("Enter Order ID: ");
                int orderId = int.Parse(Console.ReadLine());

                Console.Write("Enter Amount: ");
                decimal amount = decimal.Parse(Console.ReadLine());

                var order = new Order
                {
                    OrderId = orderId,
                    Amount = amount
                };

                processor.ProcessOrder(order);

                // TODO: Call again to test duplicate scenario
                

            }
            catch (FormatException)
            {
                Console.WriteLine("Invalid input! Please enter numeric values.");
            }

            Console.ReadLine();
        }
    }
}