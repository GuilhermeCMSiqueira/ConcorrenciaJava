import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BusStop {
    private static final int BUS_CAPACITY = 50;
    private Semaphore busSeats = new Semaphore(BUS_CAPACITY, true);
    private Lock lock = new ReentrantLock();
    private List<Passenger> waitingPassengers = new ArrayList<>();

    public void arrivePassenger(Passenger passenger) {
        lock.lock();
        try {
            waitingPassengers.add(passenger);
        } finally {
            lock.unlock();
        }
    }

    public void busArrives() {
        List<Passenger> remainingPassengers = new ArrayList<>();
        List<Thread> boardingPassengers = new ArrayList<>();
        lock.lock();
        try {
            System.out.println("Ônibus chegou na parada.");
            int passengersBoarded = 0;

            for (Passenger passenger : waitingPassengers) {
                if (busSeats.tryAcquire()) {
                    boardingPassengers.add(new Thread(passenger));
                    passengersBoarded++;
                } else {
                    remainingPassengers.add(passenger);
                }
            }

            waitingPassengers = remainingPassengers;
            System.out.println("Ônibus saiu com " + passengersBoarded + " passageiros.");
        } finally {
            lock.unlock();
        }

        for (Thread thread : boardingPassengers) {
            thread.start();
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        busSeats.release(boardingPassengers.size());
    }
    
    public static void main(String[] args) {
        BusStop busStop = new BusStop();
        Random random = new Random();

        Thread passengerGenerator = new Thread(() -> {
            for (int i = 0; i < 150; i++) { 
                Passenger passenger = new Passenger();
                busStop.arrivePassenger(passenger);

                try {
                    Thread.sleep(random.nextInt(100)); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread busGenerator = new Thread(() -> {
            for (int i = 0; i < 6; i++) { 
                try {
                    Thread.sleep((1 + random.nextInt(3)) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                busStop.busArrives();
            }
        });

        passengerGenerator.start();
        busGenerator.start();

        try {
            passengerGenerator.join();
            busGenerator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



