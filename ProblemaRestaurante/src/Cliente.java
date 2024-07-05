public class Cliente extends Thread {
    private final Restaurante restaurante;

    public Cliente(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public void run() {
        restaurante.sentar();
    }
}
	