import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Barbearia{
	private final int cadeiras;
	private int clientesEsperando = 0;
	private final Semaphore semaforoClientes = new Semaphore(0);
	private final Semaphore semaforoBarbeiro = new Semaphore(0);
	private final ReentrantLock lock = new ReentrantLock(true);
	
	public Barbearia (int cadeiras) {
		this.cadeiras = cadeiras;
	}
	public void cortarCabelo() {
		try {
			semaforoClientes.acquire();
			System.out.println("Atendendo novo cliente.");
			lock.lock();
			clientesEsperando--;
			semaforoBarbeiro.release();
			Thread.sleep(2000);
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
		
	public void entraCliente() {
		lock.lock();
		if (clientesEsperando <= cadeiras) {
			System.out.println(Thread.currentThread().getName() + " chegou.");
			if (clientesEsperando == 0) {
				System.out.println("Barbeiro acordou.");
			}
			clientesEsperando++;
			semaforoClientes.release();
			lock.unlock();
			try {
				semaforoBarbeiro.acquire();
				System.out.println(Thread.currentThread().getName() +" está cortando cabelo. Clientes esperando: " + clientesEsperando);
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName() + " saiu porque terminou de cortar.");
				if (clientesEsperando == 0) {
					System.out.println("Sem clientes esperando, barbeiro foi dormir.");
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " saiu porque a barbearia está cheia.");
		}
	}
}