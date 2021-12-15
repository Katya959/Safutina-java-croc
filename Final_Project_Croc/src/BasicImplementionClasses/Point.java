package BasicImplementionClasses;

public class Point extends Object {
    public int x;
    public int y;

    public Point(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Point) && (((Point)o).getX() == this.getX()) && (((Point)o).getY() == this.getY());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
