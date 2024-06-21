
public class Ponte {

	boolean ocupado;
	
	public Ponte(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public void checar() {
		if (this.ocupado) {
			System.out.println("A ponte está ocupada");
		}else {
			System.out.println("A ponte está livre");
		}
	}


}
