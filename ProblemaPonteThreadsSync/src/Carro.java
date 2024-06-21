import java.util.concurrent.locks.ReentrantLock;

class Carro extends Thread {
	
	private Ponte ponte;
    private String direcao;
    private String nome; 
    private boolean passou = false;
    private static ReentrantLock lock = new ReentrantLock();

    public Carro(String direcao, String nome, Ponte ponte) {
    	this.ponte = ponte;
        this.direcao = direcao;
        this.nome = nome;
    }

    @Override
    public void run() {
    	System.out.println(this.nome + " está tentando entrar na ponte pela " + this.direcao);
        lock.lock(); 
        try {
        	System.out.println(this.nome + " está entrando na ponte pela " + this.direcao);
            ponte.ocupado = true;    
        } finally {
        	System.out.println(this.nome + " está saindo da ponte");
        	this.passou = true;
        	ponte.ocupado = false;
            lock.unlock(); 
        }
    	
    }
    
    public void checar() {
    	if (this.passou) {
    		System.out.println("Carro de " + nome + " passou");
    	}else {
    		System.out.println("Carro de " + nome + " não passou");
    	}
    }
}
	