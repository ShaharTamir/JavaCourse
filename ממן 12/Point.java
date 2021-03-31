/**
 * Class Point - represents a point in the cartesian coordinate system
 * 
 * @author Shahar Tamir
 * @version 2021b
 */
public class Point 
{
    private double _x;
    private double _y;   

    /**
    * Constructs and initializes a point at the specified (x,y) location
    * in the coordinate space.
    * @param x the X coordinate of this point
    * @param y the Y coordinate of this point
    */
    public Point(double x, double y)
    {
        _x = x;
        _y = y;
    }

    /**
    * Copy Constructor
    * @param other other point to copy it's coordinates
    */
    public Point(Point other)
    {
        _x = other._x;
        _y = other._y;
    }

    /**
    * Get the X value of a coordinate point
    * @return the X value of this coordinate point
    */
    public double getX()
    {
        return _x;
    }

    /**
    * Get the Y value of a coordinate point
    * @return the Y value of this coordinate point
    */
    public double getY()
    {
        return _y;
    }

    /**
    * Set the X value of a coordinate point
    * @param num new value for X coordinate  
    */
    public void setX(double num)
    {
        _x = num;
    }

    /**
    * Set the Y value of a coordinate point
    * @param num new value for Y coordinate  
    */
    public void setY(double num)
    {
        _y = num;
    }

    /**
    * @return String that represents this point
    * in the following format:
    * (0.0,0.0)
    */
    public String toString()
    {
        return "(" + _x + "," + _y + ")";
    }

    /**
    * Check if two points are equal
    * @param other point to compare to
    * @return true if this point is equal to other point
    */
    public boolean equals(Point other)
    {
        return (_x == other._x && _y == other._y);
    }

    /**
    * Check if one point is above another
    * @param other point to compare to
    * @return true if this point is above other point
    */
    public boolean isAbove(Point other)
    {
        return _y > other._y;
    }

    /**
    * Check if one point is under another
    * @param other point to compare to
    * @return true if this point is under other point
    */
    public boolean isUnder(Point other)
    {
        return other.isAbove(this);
    }

    /**
    * Check if one point is left to another
    * @param other point to compare to
    * @return true if this point is left to other point
    */
    public boolean isLeft(Point other)
    {
        return _x < other._x;
    }

    /**
    * Check if one point is right to another
    * @param other point to compare to
    * @return true if this point is right to other point
    */
    public boolean isRight(Point other)
    {
        return other.isLeft(this);
    }

    /**
    * Calculates the distance between two points
    * @param p point to calculate the distance to
    * @return distance from this point to p point
    */
    public double distance(Point p)
    {
        final double POWER_TWO = 2.0d;
        
        return Math.sqrt(Math.pow(p._y - _y, POWER_TWO) + Math.pow(p._x - _x, POWER_TWO));
    }

    /**
    * determines the quadrant of a point
    * @return the quadrant the point is located.
    * the results are as following: <p>
    *   0 - point is on one of the axes or at the origin <p>
    *   1 - first  quad <p>
    *   2 - second quad <p>
    *   3 - third  quad <p>
    *   4 - fourth quad
    */
    public int quadrant()
    {
        final int AXES = 0;
        final int FIRST_QUAD = 1;
        final int SECOND_QUAD = 2;
        final int THIRD_QUAD = 3;
        final int FOURTH_QUAD = 4;

        if(AXES == _x || AXES == _y)
        {
            return AXES;
        }
        if(_x > AXES)
        {
            if(_y > AXES)
            {
                return FIRST_QUAD;
            }

            return FOURTH_QUAD;
        }
        if(_y > AXES)
        {
            return SECOND_QUAD;
        }
        
        return THIRD_QUAD;
    }
}
