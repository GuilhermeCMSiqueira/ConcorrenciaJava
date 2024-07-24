public class Program {
    public static void main(String[] args) {
        int cadeiras = 5;
        Barbearia barbearia = new Barbearia(cadeiras);
        Barbeiro barbeiro = new Barbeiro(barbearia);
        System.out.println("Sem clientes esperando, barbeiro foi dormir.");
        barbeiro.start();

        for (int i = 0; i < 100; i++) {
            Cliente cliente = new Cliente (barbearia);
            cliente.setName("Cliente " + (i + 1));
            cliente.start();
            try {
                Thread.sleep (1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}