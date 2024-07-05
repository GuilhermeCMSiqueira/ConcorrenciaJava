
public class Barbeiro extends Thread {
    private final Barbearia barbearia;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (true) {
            barbearia.cortarCabelo();
        }
    }
}