public class Cliente extends Thread {
    private final Barbearia barbearia;

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        barbearia.entraCliente();
    }
}