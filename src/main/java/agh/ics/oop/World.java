package agh.ics.oop;

public class World {
/*
    public static void run(Direction[] directions) {
        for (int i = 0; i < directions.length; i++) {
            String message = switch (directions[i]) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case LEFT -> "Zwierzak skręca w lewo";
                case RIGHT -> "Zwierzak skręca w prawo";
                case UNDEFINED -> null;
            };
            if (message != null)
                System.out.println(message);
        }
    }

    public static Direction[] convert(String[] args) {
        Direction[] directions = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            directions[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> Direction.UNDEFINED;
            };
        }
        return directions;
    }
*/
    public static void main(String[] args) {
        //lab3
        //3.
        Animal zwierzak = new Animal();
        System.out.println(zwierzak.toString());

        //5.
        zwierzak.move(MoveDirection.RIGHT);
        zwierzak.move(MoveDirection.FORWARD);
        zwierzak.move(MoveDirection.FORWARD);
        zwierzak.move(MoveDirection.FORWARD);
        System.out.println(zwierzak.toString());

        //7.
        MoveDirection[] directions = OptionsParser.parse(args);
        for (MoveDirection step: directions){
            zwierzak.move(step);
        }
        System.out.println(zwierzak.toString());

        //10.
        /* Aby wykluczyć możliwość pojawienia się dwóch zwierząt w tym samym miejscu należy stworzyć klasę,
        która dla każdych koordynatów x i y na mapie będzie przyechowywać wartość Boolean. Dla pola zajętego będzie ona
        wynosić True, a dla wolnego False.*/

        /*
        //lab2
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));


        //lab1
        System.out.println("Start");
        Direction[] directions = convert(args);
        run(directions);
        System.out.println("Stop");*/
    }

}