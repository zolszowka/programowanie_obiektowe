package agh.ics.oop;

public class World {

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

    public static void main(String[] args) {
        System.out.println("Start");
        Direction[] directions = convert(args);
        run(directions);
        System.out.println("Stop");
    }

}