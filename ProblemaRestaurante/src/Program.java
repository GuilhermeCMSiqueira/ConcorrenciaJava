
public class Program {

	public static void main(String[] args) throws InterruptedException {
		Restaurante restaurante = new Restaurante(5);
		
		for (int i = 0; i < 15; i++) {
            Thread cliente = new Cliente(restaurante);
            cliente.setName("Cliente " + (i + 1));
            Thread.sleep(200);
            cliente.start();
        }
        
        
	}

}
