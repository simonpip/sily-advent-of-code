package dk.simonpip.aoc.util;

import com.google.common.base.Objects;

public class Coordinate3D {
    private final int x;
    private final int y;
    private final int z;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Coordinate3D(String coords) {
        String[] split = coords.split(",");

        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
        this.z = Integer.parseInt(split[2]);
    }

    public double distance(Coordinate3D other) {
        return Math.sqrt(powDifference(x, other.x) + powDifference(y, other.y) + powDifference(z, other.z));
    }

    private static double powDifference(int a, int b) {
        return Math.pow(Math.abs(a - b), 2);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate3D coordinate3D = (Coordinate3D) o;
        return x == coordinate3D.x && y == coordinate3D.y && z == coordinate3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y, z);
    }
}
