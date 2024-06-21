import java.util.concurrent.locks.ReentrantLock;

public class Pessoa extends Thread {

	private Conta conta;
	private String nome;
    private boolean depositar;
    private int valor;
    private static ReentrantLock lock = new ReentrantLock();
	
	public Pessoa(Conta conta, boolean depositar, int valor, String nome) {
        this.conta = conta;
        this.depositar = depositar;
        this.valor = valor;
        this.nome = nome;
    }
	
	@Override
    public void run() {
		System.out.println(this.nome + " está tentando acessar a conta");
        lock.lock(); 
        try {
        	System.out.println("Acesso liberado para " + this.nome);
            if (depositar) {
                conta.depositar(valor);
            } else {
                conta.saque(valor);
            }
        } finally {
        	System.out.println(this.nome + " está liberando o acesso");
            lock.unlock(); 
        }
    }
}
