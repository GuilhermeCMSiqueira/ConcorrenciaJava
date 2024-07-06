
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurante {
	private final ReentrantLock lock = new ReentrantLock();
    private final Condition lugarDisponivel = lock.newCondition();
    private final int cadeiras;
    private int clientesAtuais = 0;

    public Restaurante(int cadeiras) {
        this.cadeiras = cadeiras;
    }

    public void sentar() {
        lock.lock();
        try {
        	
            while (clientesAtuais == cadeiras) {
                System.out.println(Thread.currentThread().getName() + " está esperando por um lugar.");
                lugarDisponivel.await();
            }

            clientesAtuais++;
            System.out.println(Thread.currentThread().getName() + " sentou no lugar.");

            if (clientesAtuais == cadeiras) {
                lock.unlock(); 
                try {
                    Thread.sleep(3000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                lock.lock(); 
                clientesAtuais = 0; 
                
                lugarDisponivel.signalAll(); 
                
                System.out.println("Todos que estavam saíram.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
        	lock.unlock(); 
        }
    }


}