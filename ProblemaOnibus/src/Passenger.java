
public class Passenger implements Runnable {
    private static int idCounter = 0;
    private int id;

    public Passenger() {
        this.id = idCounter++;
    }

    @Override
    public void run() {
        System.out.println("Passageiro " + id + " subiu no ônibus.");
    }
}
