
public class Program {

	public static void main(String[] args) {
		 Conta conta = new Conta(1000); // Saldo inicial de 1000

	        Pessoa p1 = new Pessoa(conta, true, 500, "Arthur");  // True se for depósito
	        Pessoa p2 = new Pessoa(conta, false, 200, "Bernardo"); // False se for saque
	        Pessoa p3 = new Pessoa(conta, false, 800, "Carlos"); 
	        Pessoa p4 = new Pessoa(conta, true, 700, "Daniel"); 
	        Pessoa p5 = new Pessoa(conta, false, 300, "Estela");
	        Pessoa p6 = new Pessoa(conta, false, 100000, "Fátima");

	        p1.start();
	        p2.start();
	        p3.start();
	        p4.start();
	        p5.start();
	        p6.start();
	        	
	        try {
	        	
	            p1.join();  
	            p2.join();
	            p3.join();      
	            p4.join();
	            p5.join();
	            p6.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        conta.checar();
	}

}
