
public class Program {

	public static void main(String[] args) {
		Restaurante restaurante = new Restaurante();
		
		for (int i = 0; i < 100; i++) {
            Thread cliente = new Cliente(restaurante);
            cliente.setName("Cliente " + (i + 1));
            cliente.start();
        }
        
        
	}

}
