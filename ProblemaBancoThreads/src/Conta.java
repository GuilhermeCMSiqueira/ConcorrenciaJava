
public class Conta {

	int saldo;
	
	public Conta(int saldo) {
		this.saldo = saldo;
	}
	
	public void depositar(int quantia) {
		this.saldo += quantia;
	}
	
	public void saque(int quantia) {
		if (quantia <= this.saldo) {
			this.saldo -= quantia;
		}else {
			System.out.println("Não é possível sacar R$" + quantia +
					", pois esse valor é superior ao saldo da conta.");
		}
		
	}
	
	public void checar() {
		System.out.println("O saldo da conta é: R$" + this.saldo);
	}


}
