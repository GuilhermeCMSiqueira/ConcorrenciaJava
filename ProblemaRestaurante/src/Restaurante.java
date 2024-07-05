import java.util.concurrent.Semaphore;

public class Restaurante {
    private final Semaphore lugaresDisponiveis = new Semaphore(5);
    
    public void sentar() {
        try {
        	System.out.println(Thread.currentThread().getName() + " vai tentar entrar no restaurante");
            lugaresDisponiveis.acquire();
            System.out.println(Thread.currentThread().getName() + " entrou no restaurante");
            jantar();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
            	System.out.println(Thread.currentThread().getName() + " saiu do restaurante.");
                lugaresDisponiveis.release();    
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void jantar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " está jantando.");
        Thread.sleep(2000); 
    }

}
