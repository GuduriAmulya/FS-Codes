// You are given N customer orders that must be processed sequentially.
// Design a Java multithreading application using the Callable interface only, 
// with the following strict constraints:
//     - There is exactly ONE Producer task
//     - There is exactly ONE Consumer task
//     - The number of orders (N) and the list of orders are global shared objects
//     - The Producer and Consumer must not receive the orders directly
//     - The Producer and Consumer must communicate only through a shared handoff object
//     - There is NO buffer, queue, or collection used for intermediate storage

// Each order must be processed in the following strict order:
// Producer processes one order → Consumer processes the same order → then move to the next order

// The Producer must wait until the Consumer finishes processing the current order.
// The Consumer must wait until the Producer produces the next order.

// Order Information: Each order contains the following details:

// ----------------------------------------
// Field			Description
// ----------------------------------------
// orderId			Unique order identifier
// customerName	Name of the customer
// productName		Product ordered
// quantity		Number of items
// pricePerUnit	Cost per item
// totalAmount		quantity × price
// ----------------------------------------

// Input Format:
// -------------
// NUMBER_OF_ORDERS, N
// Next N lines: orderId customerName productName quantity pricePerUnit


// Sample Input:
// -------------
// 3
// 1001 Alice Laptop 1 75000
// 1002 Bob Phone 2 20000
// 1003 Charlie Tablet 1 30000

// Sample Output:
// --------------
// PRODUCED Order[ID=1001, Customer=Alice, Product=Laptop, Qty=1, Total=75000.0]
// CONSUMED Order[ID=1001, Total=75000.0]
// PRODUCED Order[ID=1002, Customer=Bob, Product=Phone, Qty=2, Total=40000.0]
// CONSUMED Order[ID=1002, Total=40000.0]
// PRODUCED Order[ID=1003, Customer=Charlie, Product=Tablet, Qty=1, Total=30000.0]
// CONSUMED Order[ID=1003, Total=30000.0]










import java.util.*;
import java.util.concurrent.*;

/* ================= ORDER ================= */
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

    double totalAmount() {
        return quantity * pricePerUnit;
    }
}

/* ================= GLOBAL DATA ================= */
class OrderData {
    static List<Order> orders = new ArrayList<>();
    static int N;
}

/* ================= HANDOFF (NO BUFFER) ================= */
class OrderHandoff {

    private Order currentOrder;
    private boolean produced = false;

    public synchronized void produce(Order order) throws InterruptedException {
        while (produced) {
            wait(); // wait for consumer
        }
        currentOrder = order;
        produced = true;
        notifyAll();
    }

    public synchronized Order consume() throws InterruptedException {
        while (!produced) {
            wait(); // wait for producer
        }
        Order o = currentOrder;
        produced = false;
        notifyAll();
        return o;
    }
}

/* ================= PRODUCER ================= */
class OrderProducer implements Callable<List<String>> {

    private final OrderHandoff handoff;

    OrderProducer(OrderHandoff handoff) {
        this.handoff = handoff;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> logs = new ArrayList<>();

        for (int i = 0; i < OrderData.N; i++) {
            Order o = OrderData.orders.get(i);

            handoff.produce(o);

            logs.add(
                "PRODUCED Order[ID=" + o.orderId +
                ", Customer=" + o.customerName +
                ", Product=" + o.productName +
                ", Qty=" + o.quantity +
                ", Total=" + o.totalAmount() + "]"
            );
        }
        return logs;
    }
}

/* ================= CONSUMER ================= */
class OrderConsumer implements Callable<List<String>> {

    private final OrderHandoff handoff;

    OrderConsumer(OrderHandoff handoff) {
        this.handoff = handoff;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> logs = new ArrayList<>();

        for (int i = 0; i < OrderData.N; i++) {
            Order o = handoff.consume();

            logs.add(
                "CONSUMED Order[ID=" + o.orderId +
                ", Total=" + o.totalAmount() + "]"
            );
        }
        return logs;
    }
}

/* ================= MAIN ================= */
public class ProducerConsumerCallableDemo {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        OrderData.N = sc.nextInt();

        for (int i = 0; i < OrderData.N; i++) {
            OrderData.orders.add(new Order(
                    sc.nextInt(),
                    sc.next(),
                    sc.next(),
                    sc.nextInt(),
                    sc.nextDouble()
            ));
        }

        OrderHandoff handoff = new OrderHandoff();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<List<String>> producerFuture =
                executor.submit(new OrderProducer(handoff));

        Future<List<String>> consumerFuture =
                executor.submit(new OrderConsumer(handoff));

        /* ---- PRINT STRICTLY ORDERED OUTPUT ---- */
        List<String> pLogs = producerFuture.get();
        List<String> cLogs = consumerFuture.get();

        for (int i = 0; i < OrderData.N; i++) {
            System.out.println(pLogs.get(i));
            System.out.println(cLogs.get(i));
        }

        executor.shutdown();
        sc.close();
    }
}
