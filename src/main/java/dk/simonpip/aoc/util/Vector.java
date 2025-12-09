package dk.simonpip.aoc.util;

public class Vector extends Pair<Coordinate, Coordinate> {
    public Vector(Coordinate coordinate1, Coordinate coordinate2) {
        super(coordinate1, coordinate2);
    }

    public int getX1() {
        return getA().getX();
    }

    public int getY1() {
        return getA().getY();
    }

    public int getX2() {
        return getB().getX();
    }

    public int getY2() {
        return getB().getY();
    }
}
