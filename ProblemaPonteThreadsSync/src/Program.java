
public class Program {

	public static void main(String[] args) {
		Ponte ponte = new Ponte(false); 

        Carro c1 = new Carro("esquerda", "Arthur", ponte);
        Carro c2 = new Carro("direita", "Bernardo", ponte);
        Carro c3 = new Carro("direita", "Cristiano", ponte);
        Carro c4 = new Carro("esquerda", "Daniela", ponte);
        Carro c5 = new Carro("direita", "Estela", ponte);
        Carro c6 = new Carro("esquerda", "Fernando", ponte);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();

        
        try {
        	c1.join();
        	c2.join();
        	c3.join();
        	c4.join();
        	c5.join();
        	c6.join();
        
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
	}

}
