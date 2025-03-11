public class DeadlockFixed { 
    private final Integer a = 0;
    private final Integer b = 1;

    private final Thread th1 = new Thread(() -> {
        while (true) {
            synchronized (a) {
                synchronized (b) {
                    System.out.printf("%d - %d\n", a, b);
                }
            }
        }
    }, "Thread 1");

    private final Thread th2 = new Thread(() -> {
        while (true) {
            synchronized (a) { // Locking in the same order as th1
                synchronized (b) {
                    System.out.printf("%d - %d\n", a, b);
                }
            }
        }
    }, "Thread 2");

    public DeadlockFixed() {
        th1.start();
        th2.start();
    }

    public static void main(String[] args) {
        new DeadlockFixed();
    }
}
