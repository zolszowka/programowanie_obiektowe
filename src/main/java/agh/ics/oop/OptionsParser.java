package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int size = 0;
        for (int i = 0; i < args.length; i++){
            int x = switch (args[i]){
                case "f", "forward", "b", "backward", "l", "left", "r", "right" -> 1;
                default -> 0;
            };
            size+=x;
        }

        MoveDirection[] directions= new MoveDirection[size];

        int j=0;
        for(int i = 0; i<args.length; i++){
            MoveDirection res = switch(args[i]){
                case "f", "forward" -> MoveDirection.FORWARD;
                case "b", "backward" -> MoveDirection.BACKWARD;
                case "l", "left" -> MoveDirection.LEFT;
                case "r", "right" -> MoveDirection.RIGHT;
                default -> null;
            };

            if(res != null){
                directions[j] = res;
                j++;
            }
        }

        return directions;
    }
}
