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
            switch(args[i]){
                case "f", "forward" -> directions[j] = MoveDirection.FORWARD;
                case "b", "backward" -> directions[j] = MoveDirection.BACKWARD;
                case "l", "left" -> directions[j] = MoveDirection.LEFT;
                case "r", "right" -> directions[j] = MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(args[i] + " is not a legal move exceptions");
            }

            j++;
        }

        return directions;
    }
}
