/**
 * Triangle.java represents a triangle in the Euclidean space.
 * 
 * @version 2021b
 * @author Shahar Tamir
 */
public class Triangle
{
    static final double EPSILON = 0.0001;
    
    private static final int FIRST_EDGE = 0;
    private static final int SECOND_EDGE = 1;
    private static final int THIRD_EDGE = 2;

    private Point _point1;
    private Point _point2;
    private Point _point3;

    /********************************
    *          CONSTRACTORS         *
    ********************************/
    /**
     * Construct a new Triangle (default constructor) from 3 default Points:(1,0),(-1,0),(0,1)
     */
    public Triangle()
    {
        _point1 = new Point(1d, 0d);
        _point2 = new Point(-1d, 0d);
        _point3 = new Point(0d, 1d);
    }

    /**
     * Construct a new Triangle (from 3 points)
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Triangle(Point p1, Point p2, Point p3)
    {
        this();

        if(isValid(p1, p2, p3))
        {
            _point1 = new Point(p1);
            _point2 = new Point(p2);
            _point3 = new Point(p3);
        }
    }

    /**
     * Construct a new Triangle (from 6 reals)
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the x coordinate of the second point
     * @param x3 the x coordinate of the third point
     * @param y3 the y coordinate of the third point
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }

    /**
     * Copy constructor Creates a new triangle identical to the given triangle.
     * @param other the triangle to be copied
     */
    public Triangle(Triangle other)
    {
        _point1 = new Point(other._point1);
        _point2 = new Point(other._point2);
        _point3 = new Point(other._point3);
    }


    /********************************
    *            GETTERS            *
    ********************************/
    /**
     * This method returns the triangle's first point.
     * @return The first point of the triangle
     */
    public Point getPoint1()
    {
        return new Point(_point1);
    }

    /**
     * This method returns the triangle's second point.
     * @return The second point of the triangle
     */
    public Point getPoint2()
    {
        return new Point(_point2);
    }

    /**
     * This method returns the triangle's third point.
     * @return The third point of the triangle
     */
    public Point getPoint3()
    {
        return new Point(_point3);
    }

    /********************************
    *            SETTERS            *
    ********************************/

    /**
     * This method sets the first point of the triangle, <p>
     * only if it creates a valid triangle.
     * @param newPoint the new first point
     */
    public void setPoint1(Point newPoint)
    {
        if(isValid(newPoint, _point2, _point3))
        {
            _point1 = new Point(newPoint);
        }
    }

    /**
     * This method sets the second point of the triangle, <p>
     * only if it creates a valid triangle.
     * @param newPoint the new second point
     */
    public void setPoint2(Point newPoint)
    {
        if(isValid(_point1, newPoint, _point3))
        {
            _point2 = new Point(newPoint);
        }
    }

    /**
     * This method sets the third point of the triangle, <p>
     * only if it creates a valid triangle.
     * @param newPoint the new third point
     */
    public void setPoint3(Point newPoint)
    {
        if(isValid(_point1, _point2, newPoint))
        {
            _point3 = new Point(newPoint);
        }
    }

    /********************************
    *           QUALITIES           *
    ********************************/
    /**
     * This method checks if 3 points make a valid triangle. <p>
     * A triangle is valid if no sum of any two sides equals the third side (with precision EPSILON)
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     * @return true if the traingle(p1, p2, p3) is valid
     */
    public boolean isValid(Point p1, Point p2, Point p3)
    {
        double edge1 = p1.distance(p2);
        double edge2 = p1.distance(p3);
        double edge3 = p2.distance(p3);

        return !isDoubleEqual(edge1, edge2 + edge3) && 
                !isDoubleEqual(edge2, edge1 + edge3) &&
                !isDoubleEqual(edge3, edge1 + edge2);
    }

    /**
     * This method returns a String representation of thetTriangle. <p>
     * The format of the string should be: {(x1,y1),(x2,y2),(x3,y3)}
     * @return a String representation of the triangle
     */
    public String toString()
    {
        return "{" + _point1 + "," + _point2 + "," + _point3 + "}";
    }

    /**
     * This method returns the triangle's perimeter.
     * @return the triangle's perimeter
     */
    public double getPerimeter()
    {
        return getEdge(FIRST_EDGE) + getEdge(SECOND_EDGE) + getEdge(THIRD_EDGE);
    }

    /**
     * This method returns the triangle's area.
     * @return the triangle's area
     */
    public double getArea()
    {
        // split calculation into parameters for readability and reuse.
        double halfPerimeter = getPerimeter() / 2;
        
        double diffEdgeA = halfPerimeter - getEdge(FIRST_EDGE);
        double diffEdgeB = halfPerimeter - getEdge(SECOND_EDGE);
        double diffEdgeC = halfPerimeter - getEdge(THIRD_EDGE);

        return Math.sqrt(halfPerimeter * diffEdgeA * diffEdgeB * diffEdgeC);   // Heron's formula
    }

    /**
     * This method returns true if the triangle is an isosceles triangle(with precision EPSILON)
     * @return true if the triangle is an isosceles triangle
     */
    public boolean isIsosceles()
    {
        // one or more pairs of edges are equal
        return isDoubleEqual(getEdge(FIRST_EDGE), getEdge(SECOND_EDGE)) || 
                isDoubleEqual(getEdge(FIRST_EDGE), getEdge(THIRD_EDGE)) || 
                isDoubleEqual(getEdge(SECOND_EDGE), getEdge(THIRD_EDGE));
    }

    /**
     * This method returns true if the triangle is a right-angled triangle.
     * @return true if the triangle is a right-angled triangle
     */
    public boolean isPythagorean()
    {
        // variables for readability and reduce edge calculations
        double edge1 = getEdge(FIRST_EDGE);
        double edge2 = getEdge(SECOND_EDGE);
        double edge3 = getEdge(THIRD_EDGE);

        return isDoubleEqual(edge1, pythagorean(edge2, edge3)) || 
                isDoubleEqual(edge2, pythagorean(edge1, edge3)) || 
                isDoubleEqual(edge3, pythagorean(edge1, edge2));
    }
    
    /********************************
    *            GEO ID             *
    ********************************/
    /**
     * This method returns true if the triangle is in a given circle.
     * @param x the x value of the point which is the center of the circle
     * @param y the y value of the point which is the center of the circle
     * @param r the radius of the circle
     * @return true if the triangle is in a given circle
     */
    public boolean isContainedInCircle(double x, double y, double r)
    {
        Point circleCenter = new Point(x ,y); // create a point object from given (x,y) to use it's API.
        double distPoint = circleCenter.distance(_point1);  // put distance in a variable to calculate once.

        if(isDoubleEqual(distPoint, r) || distPoint < r)
        {
            distPoint = circleCenter.distance(_point2);
            
            if(isDoubleEqual(distPoint, r) || distPoint < r)
            {
                distPoint = circleCenter.distance(_point3);
                
                if(isDoubleEqual(distPoint, r) || distPoint < r)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method returns the lowest vertex of the triangle. <p>
     * If two points are at the same height - return the most left.
     * @return the lowest vertex of the triangle. <p>
     * If two points are at the same height - return the most left
     */
    public Point lowestPoint()
    {
        return lowestOfTwo(_point1, lowestOfTwo(_point2, _point3));
    }

    /**
     * This method returns the highest vertex of the triangle. <p>
     * If two points are at the same height - return the most left.
     * @return the highest vertex of the triangle. <p>
     * If two points are at the same height - return the most left
     */
    public Point highestPoint()
    {
        return highestOfTwo(_point1, highestOfTwo(_point2, _point3));
    }

    /**
     * This method returns true if the triangle is entirely in one quadrant.
     * @return true if the triangle is entirely in one quadrant
     */
    public boolean isLocated()
    {
        return (_point1.quadrant() == _point2.quadrant()) && (_point2.quadrant() == _point3.quadrant());
    }

    /**
     * Check if this triangle is completely above a received triangle.
     * @param other the triangle to check if this triangle is above
     * @return true if this triangle is above the other triangle
     */
    public boolean isAbove(Triangle other)
    {
        return lowestPoint().isAbove(other.highestPoint());
    }

    /**
     * Check if this triangle is completely under a received triangle.
     * @param other the triangle to check if this triangle is under
     * @return true if this triangle is under the other triangle
     */
    public boolean isUnder(Triangle other)
    {
        return other.isAbove(this);
    }

    /********************************
    *            EQUALITY           *
    ********************************/

    /**
     * Check if the given triangle is equal to this triangle.
     * @param other the triangle we are checking equality with
     * @return true if the triangle <tt>other</tt> is equal to this triangle
     */
    public boolean equals(Triangle other)
    {
        return _point1.equals(other.getPoint1()) &&
                _point2.equals(other.getPoint2()) &&
                _point3.equals(other.getPoint3());
    }

    /**
     * Check if the given triangle is congruent to this triangle.
     * @param other the triangle we are checking congruency with
     * @return true if the triangle <tt>other</tt> is congruent to this triangle
     */
    public boolean isCongruent(Triangle other)
    {   
        int remainingEdge1;
        int remainingEdge2;
        
        if(areEdgesEqual(FIRST_EDGE, FIRST_EDGE, other))
        {
            // here means this first edge is equal to other first edge
            remainingEdge1 = SECOND_EDGE;
            remainingEdge2 = THIRD_EDGE;
        }
        else if(areEdgesEqual(FIRST_EDGE, SECOND_EDGE, other))
        {
            // here means this first edge is equal to other second edge
            remainingEdge1 = FIRST_EDGE;
            remainingEdge2 = THIRD_EDGE;
        }
        else if(areEdgesEqual(FIRST_EDGE, THIRD_EDGE, other))
        {
            // here means this first edge is equal to other third edge
            remainingEdge1 = FIRST_EDGE;
            remainingEdge2 = SECOND_EDGE;
        }
        else
            return false; // here means this first edge has no matching edge on given other triangle

        // check if the remaining edges of this triangle have a matching edges on other triangle
        return ((areEdgesEqual(SECOND_EDGE, remainingEdge1, other) && areEdgesEqual(THIRD_EDGE, remainingEdge2, other)) ||
                    (areEdgesEqual(SECOND_EDGE, remainingEdge2, other) && areEdgesEqual(THIRD_EDGE, remainingEdge1, other)));
    }

    /********************************
    *        PRIVATE METHODS        *
    ********************************/
    
    /*
    *   this method calculates and returns the edges lengths of this triangle 
    *   according to final variables - *_EDGE.
    *   if given a wrong edge number - return value is 0.
    */
    private double getEdge(int edge)
    {
        switch(edge)
        {
        case FIRST_EDGE:
            return _point1.distance(_point2);
        case SECOND_EDGE:
            return _point1.distance(_point3);
        case THIRD_EDGE:
            return _point2.distance(_point3);
        default:
            return 0d; // invalid edge number
        }
    }

    /*
    *   this method returns if two real numbers are equal. 
    *   difference smaller than EPSILON returns true.
    */
    private boolean isDoubleEqual(double a, double b)
    {
        return Math.abs(a - b) < EPSILON;
    }

    /*
    *   this method returns true if this edge number is equal (with precision EPSILON) to other edge number
    *   parameters:
    *       edgeNum - this triangle edge number
    *       otherEdgeNum - other triangle edge number to compare to
    *       other - other triangle to compare it's edge to this edge
    *   edgeNum parameters are according to *_EDGE variables
    */
    private boolean areEdgesEqual(int edgeNum, int otherEdgeNum, Triangle other)
    {
        if(edgeNum > THIRD_EDGE || edgeNum < FIRST_EDGE || 
            otherEdgeNum > THIRD_EDGE || otherEdgeNum < FIRST_EDGE)
            return false;   // means one or both given edge numbers are invalid   
        
        return isDoubleEqual(getEdge(edgeNum), other.getEdge(otherEdgeNum));
    }

    /*
    *   this method returns the highest point - a or b.
    *   if the two points are at the same height - return most left.
    */
    private Point highestOfTwo(Point a, Point b)
    {
        if(a.isAbove(b))
            return a;
        if(b.isAbove(a))
            return b;
        if(a.isLeft(b))
            return a;
        return b;
    }

    /*
    *   this method returns the lowest point - a or b.
    *   if the two points are at the same height - return most left.
    */
    private Point lowestOfTwo(Point a, Point b)
    {
        if(a.isUnder(b))
            return a;
        if(b.isUnder(a))
            return b;
        if(a.isLeft(b))
            return a;
        return b;
    }

    /*
    *   this method returns the calculated value of 
    *   pythagorean formula with the given numbers a and b.
    */
    private double pythagorean(double a, double b)
    {
        final double POWER = 2;

        return Math.sqrt(Math.pow(a, POWER) + Math.pow(b, POWER));
    }
} //class Triangle