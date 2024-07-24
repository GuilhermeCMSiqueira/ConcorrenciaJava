
public class Person implements Runnable {

    private final String gender;
    private final UnisexBathroom bathroom;
    private final int id;
    private static int idCounterM = 0;
    private static int idCounterF = 0;

    public Person(String gender, UnisexBathroom bathroom) {
        this.gender = gender;
        this.bathroom = bathroom;
        if (gender == "Homem") {
        	this.id = idCounterM++;
        }else {
        	this.id = idCounterF++;
        }
    }

    @Override
    public void run() {
        try {
            bathroom.enterBathroom(gender);
            System.out.println(gender + " " + id + " entrou no banheiro.");
            Thread.sleep((long) (Math.random() * 1750)); 
            bathroom.leaveBathroom(gender);
            System.out.println(gender + " " + id  + " saiu do banheiro.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

