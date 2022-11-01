
import java.util.concurrent.Semaphore;

public class PetrolStation implements Runnable {
    private volatile Double amount = 0.0;
    private volatile Double requestedFuel;
    private static final Semaphore SEMAPHORE = new Semaphore(3, true);

    public void doRefuel(Double requestedFuel) {
        this.requestedFuel = requestedFuel;
        new Thread(this).start();
    }

    public void addFuelToStation(Double amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        int refuelingTime = (int) ((3 + Math.random() * 8)) * 1000;
        try {
            SEMAPHORE.acquire();
            synchronized (amount) {
                if (amount >= requestedFuel) {
                    amount = amount - requestedFuel;
                    System.out.printf("\n" +
                            "Refueling completed, at the gas station left " + amount + " gas");
                    System.out.println(" " + Thread.currentThread().getName());
                } else {
                    System.out.printf("\n" +
                            "Not enough gas at the gas station " + amount + " gas ");
                    System.out.printf(" " + Thread.currentThread().getName());
                }
            }
            Thread.sleep(refuelingTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SEMAPHORE.release();
        }
    }
}




