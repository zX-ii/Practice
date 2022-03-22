public class Move implements Comparable<BasicMove> {

    private String move;
    private String format;
    private String name;
    private int distance;
    private BasicMove M;


    public Move(String m, int d, String n, String f){
        assert m != null;
        distance = d;
        move = m.toUpperCase();
        name = n;
        format = f.toUpperCase();
        /**
         * check if the move input is valid for drone
         */
        try{
            M = BasicMove.valueOf(this.move);
        }catch(Exception e){
            System.out.println("move doesn't exist");
        }
        checkValidity();
    }

    public void checkValidity(){
        if(move.equalsIgnoreCase("capture") || move.equalsIgnoreCase("focus")){
            if(format.equals("") && move.equalsIgnoreCase("capture")){
                throw new IllegalArgumentException("You should provide a format");
            }
            if(distance != 0){
                throw new IllegalArgumentException("wrong input");
            }
        }else{
            if(!format.equals("")){
                throw new IllegalArgumentException("wrong input");
            }
        }
    }

    public String getMove(){
        return move;
    }

    @Override
    public String toString(){
        if(move.equals(BasicMove.FOCUS.name())){
            return String.format("%sED on the target", move);
        }else if(move.equals(BasicMove.CAPTURE.name())){
            return String.format("Image %sED, safes as %s.%s", move, name, format);
        }else if(move.equals(BasicMove.LAND.name()) || move.equals(BasicMove.TAKE_OFF.name())){
            return String.format("Drone now %s", move);
        }else{
            return String.format("Drone now moves %s for %dm", move, distance);
        }
    }

    @Override
    public int compareTo(BasicMove o) {
        if(M.compareTo(o) == 0){
            return 0;
        }else{
            return 1;
        }
    }

}
