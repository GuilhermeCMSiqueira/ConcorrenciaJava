
class Carro extends Thread {
	
	private Ponte ponte;
    private String direcao;
    private String nome; 
    private boolean passou = false;

    public Carro(String direcao, String nome, Ponte ponte) {
    	this.ponte = ponte;
        this.direcao = direcao;
        this.nome = nome;
    }

    @Override
    public void run() {
    		System.out.println("Carro de " + nome + " tentou entrar na ponte pela " + direcao);
    		System.out.println("Carro de " + nome + " vindo da " + direcao + " está atravessando a ponte."); 
    		System.out.println("Carro de " + nome + " vindo da " + direcao + " saiu da ponte.");
        	this.passou = true;	
    }
    
    public void checar() {
    	if (this.passou) {
    		System.out.println("Carro de " + nome + " passou");
    	}else {
    		System.out.println("Carro de " + nome + " não passou");
    	}
    }
}

