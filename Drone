import java.util.ArrayList;

public class Drone {

    private ArrayList<Move> command;

    public Drone(ArrayList<Move> m){
        assert m != null;
        command = m;
    }

    public void Update(ArrayList<Move> m) {
        command = m;
    }

    public void executeMove(){
        for (Move m : command){
            System.out.println(m);
        }
        System.out.println("\n");
    }
}
