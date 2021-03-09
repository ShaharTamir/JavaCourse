

public class Point 
{
    public Point(double x, double y)
    {
        _x = x;
        _y = y;
    }

    public Point(Point other)
    {
        _x = other._x;
        _y = other._y;
    }

    public double getX()
    {
        return _x;
    }

    public double getY()
    {
        return _y;
    }

    public void setX(double num)
    {
        _x = num;
    }

    public void setY(double num)
    {
        _y = num;
    }

    public String toString()
    {
        // TODO

        return "TODO";
    }

    public boolean equals(Point other)
    {
        return (_x == other._x && _y == other._y);
    }

    public boolean isAbove(Point other)
    {
        return _y > other._y;
    }

    public boolean isUnder(Point other)
    {
        return other.isAbove(this);
    }

    public boolean isLeft(Point other)
    {
        return _x < other._x;
    }

    public boolean isRight(Point other)
    {
        return other.isLeft(this);
    }

    public double distance(Point p)
    {
        return Math.sqrt(Math.pow(p._y - _y, POWER_TWO) + Math.pow(p._x - _x, POWER_TWO));
    }
    
    public int quadrant()
    {
        //TODO

        return 0;

    }

    static final double POWER_TWO = 2.0d;
    double _x;
    double _y;    
}
