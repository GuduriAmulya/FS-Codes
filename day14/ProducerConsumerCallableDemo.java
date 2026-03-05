// You are developing a multi-threaded backend system for an e-commerce platform.

// The platform receives customer orders from multiple sources (mobile app, website, partner APIs).
// These orders must be queued, processed, and dispatched concurrently.

// To ensure scalability:
// 	- Order creation and order processing run in parallel
// 	- Orders are exchanged through a shared buffer with limited capacity

// Order Information: Each order contains the following details:

// --------------------------------------------------
// Field						Description
// --------------------------------------------------
// orderId					Unique order identifier
// customerName	        Name of the customer
// productName		            Product ordered
// quantity					Number of items
// pricePerUnit			    Cost per item
// totalAmount			        quantity × price
// --------------------------------------------------

// Input Format:
// -----------------
// BUFFER_CAPACITY
// NUMBER_OF_ORDERS, N
// Next N lines:
// orderId customerName productName quantity pricePerUnit


// Sample Input:
// -----------------
// 3
// 4
// 1001 Alice Laptop 1 75000
// 1002 Bob Phone 2 20000
// 1003 Charlie Tablet 1 30000
// 1004 Diana Headphones 2 5000

// Sample Output:
// -------------------
// PRODUCED Order[ID=1001, Customer=Alice, Product=Laptop, Qty=1, Total=75000.0]
// PRODUCED Order[ID=1002, Customer=Bob, Product=Phone, Qty=2, Total=40000.0]
// PRODUCED Order[ID=1003, Customer=Charlie, Product=Tablet, Qty=1, Total=30000.0]
// PRODUCED Order[ID=1004, Customer=Diana, Product=Headphones, Qty=2, Total=10000.0]
// CONSUMED Order[ID=1001, Total=75000.0]
// CONSUMED Order[ID=1002, Total=40000.0]
// CONSUMED Order[ID=1003, Total=30000.0]
// CONSUMED Order[ID=1004, Total=10000.0]






import java.util.*;
import java.util.concurrent.*;

/* ================= ORDER CLASS ================= */
class Order {
    int orderId;
    String customerName;
    String productName;
    int quantity;
    double pricePerUnit;

    Order(int orderId, String customerName,
          String productName, int quantity,
          double pricePerUnit) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    double getTotalAmount() {
        return quantity * pricePerUnit;
    }
}

/* ================= BUFFER (USING synchronized) ================= */
class OrderBuffer {

    private final Queue<Order> queue = new LinkedList<>();
    private final int capacity;

    OrderBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(Order order) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // buffer full → producer waits
        }
        queue.add(order);
        notifyAll(); // notify waiting consumers
    }

    public synchronized Order take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // buffer empty → consumer waits
        }
        Order order = queue.remove();
        notifyAll(); // notify waiting producers
        return order;
    }
}

/* ================= PRODUCER ================= */
class OrderProducer implements Callable<List<String>> {

    private final OrderBuffer buffer;
    private final List<Order> orders;

    OrderProducer(OrderBuffer buffer, List<Order> orders) {
        this.buffer = buffer;
        this.orders = orders;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> logs = new ArrayList<>();

        for (Order o : orders) {
            buffer.put(o);
            logs.add(
                "PRODUCED Order[ID=" + o.orderId +
                ", Customer=" + o.customerName +
                ", Product=" + o.productName +
                ", Qty=" + o.quantity +
                ", Total=" + o.getTotalAmount() + "]"
            );
        }
        return logs;
    }
}

/* ================= CONSUMER ================= */
class OrderConsumer implements Callable<List<String>> {

    private final OrderBuffer buffer;
    private final int count;

    OrderConsumer(OrderBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> logs = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Order o = buffer.take();
            logs.add(
                "CONSUMED Order[ID=" + o.orderId +
                ", Total=" + o.getTotalAmount() + "]"
            );
        }
        return logs;
    }
}

/* ================= MAIN ================= */
public class ProducerConsumerCallableDemo {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int bufferCapacity = sc.nextInt();
        int n = sc.nextInt();

        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            orders.add(new Order(
                    sc.nextInt(),
                    sc.next(),
                    sc.next(),
                    sc.nextInt(),
                    sc.nextDouble()
            ));
        }

        OrderBuffer buffer = new OrderBuffer(bufferCapacity);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<List<String>> producerFuture =
                executor.submit(new OrderProducer(buffer, orders));

        Future<List<String>> consumerFuture =
                executor.submit(new OrderConsumer(buffer, n));

        /* ---- PRINT PRODUCER LOGS ---- */
        for (String log : producerFuture.get()) {
            System.out.println(log);
        }

        /* ---- PRINT CONSUMER LOGS ---- */
        for (String log : consumerFuture.get()) {
            System.out.println(log);
        }

        executor.shutdown();
        sc.close();
    }
}






