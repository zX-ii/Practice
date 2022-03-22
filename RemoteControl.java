import DroneOperation.Move;
import DroneOperation.BasicMove;
import java.util.ArrayList;

public class RemoteControl {

    private ArrayList<Move> moveSeq;
    private boolean isfocus = false;

    public RemoteControl(){
        moveSeq = new ArrayList<>();
    }
    /**
     * add a new move to the command sequence
     * @param m a move
     * @param d move distance
     * @param f filename
     */
    public void addMove(String m, int d, String n, String f){moveSeq.add(new Move(m,d,n,f));}

    /**
     * remove a move from the command sequence
     * @param i index
     */
    public void removeMove(int i){
        moveSeq.remove(i-1);
    }

    /**
     * @return copy of moveSeq
     */
    public ArrayList<Move> getMoveSeq(){
        focusCheck();
        checkOrder();
        return new ArrayList<>(moveSeq);
    }

    public void printMove(){
        System.out.println("YOUR COMMANDS ARE:");
        for(Move m : moveSeq){
            System.out.print(m.getMove() + " ");
        }
        System.out.println("\n");
    }

    /**
     * regenerate an empty sequence for new moves
     */
    public void renewMove(){
        System.out.println("**NEW COMMANDS**");
        moveSeq = new ArrayList<>();
    }

    /**
     * Check if the drone already focused on the object
     * when user want to take a photo
     */
    public void focusCheck() {
        int c = 0;
        for (int i = 0; i < moveSeq.size(); i++) {
            if (moveSeq.get(i).getMove().equalsIgnoreCase("capture")) {
                c = i;
                for (int j = i; j > 0; j--) {
                    if (moveSeq.get(j).getMove().equalsIgnoreCase("focus")) {
                        isfocus = true;
                        break;
                    }
                }
            }
        }
        if(c == 0){
            isfocus =true;
        }
    }

    /**
     * check if the moves in the command sequence follow the rule
     */
    public void checkOrder(){
        if(moveSeq.size() != 0) {
            if (!isfocus) {
                throw new IllegalArgumentException("not focus on the target");
            }
            if (moveSeq.get(0).compareTo(BasicMove.TAKE_OFF) != 0) {
                throw new IllegalArgumentException("the drone is not taking off");
            }
            if (moveSeq.get(moveSeq.size() - 1).compareTo(BasicMove.LAND) != 0) {
                throw new IllegalArgumentException("the drone will not land");
            }
        }
    }

}
