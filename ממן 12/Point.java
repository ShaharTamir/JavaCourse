

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
        String retVal = "(" + _x + "," + _y + ")";
        return retVal;
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
        if(HEAD == _x || HEAD == _y)
        {
            return HEAD;
        }
        if(_x > HEAD)
        {
            if(_y > HEAD)
            {
                return FIRST_QUAD;
            }

            return FOURTH_QUAD;
        }
        if(_y > HEAD)
        {
            return SECOND_QUAD;
        }
        
        return THIRD_QUAD;
    }

    static final double POWER_TWO = 2.0d;
    static final int HEAD = 0;
    static final int FIRST_QUAD = 1;
    static final int SECOND_QUAD = 2;
    static final int THIRD_QUAD = 3;
    static final int FOURTH_QUAD = 4;

    double _x;
    double _y;    
}
