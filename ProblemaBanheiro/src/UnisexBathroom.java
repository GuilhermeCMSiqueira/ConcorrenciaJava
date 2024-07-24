import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class UnisexBathroom {

    private final Semaphore capacitySemaphore = new Semaphore(3);
    private final Lock genderLock = new ReentrantLock();
    private String currentGender = ""; 
    private int count = 0; 
    static Random random = new Random();
    private static int number;
    private static int sleepTime;

    public void enterBathroom(String gender) throws InterruptedException {
        genderLock.lock();
        try {
            while (!currentGender.isEmpty() && !currentGender.equals(gender)) {
                genderLock.unlock();
                Thread.sleep(10);
                genderLock.lock();
            }

            currentGender = gender;
            count++;
        } finally {
            genderLock.unlock();
        }

        capacitySemaphore.acquire();
    }

    public void leaveBathroom(String gender) {
        capacitySemaphore.release();
        
        genderLock.lock();
        try {
            count--;
            if (count == 0) {
                currentGender = "";
            }
        } finally {
            genderLock.unlock();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        UnisexBathroom bathroom = new UnisexBathroom();

        for (int i = 0; i < 100; i++) {
        	number = random.nextInt(100) + 1;
        	sleepTime = random.nextInt(2) + 1;
            String gender = i % 2 == 0 ? "Homem" : "Mulher";
            new Thread(new Person(gender, bathroom)).start();
            Thread.sleep(sleepTime*500);
        }
    }
}


