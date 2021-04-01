class Triangle
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
    public Triangle()
    {
        _point1 = new Point(1d, 0d);
        _point2 = new Point(-1d, 0d);
        _point3 = new Point(0d, 1d);
    }

    public Triangle(Point point1, Point point2, Point point3)
    {
        this();

        if(isValid(point1, point2, point3))
        {
            setPoint(_point1, point1);
            setPoint(_point2, point2);
            setPoint(_point3, point3);
        }
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }

    public Triangle(Triangle other)
    {
        _point1 = new Point(other._point1);
        _point2 = new Point(other._point2);
        _point3 = new Point(other._point3);
    }

    public String toString()
    {
        return "{" + _point1 + "," + _point2 + "," + _point3 + "}";
    }

    /********************************
    *            GETTERS            *
    ********************************/
    public Point getPoint1()
    {
        return _point1;
    }

    public Point getPoint2()
    {
        return _point2;
    }

    public Point getPoint3()
    {
        return _point3;
    }

    /********************************
    *            SETTERS            *
    ********************************/
    public void setPoint1(Point newPoint)
    {
        if(isValid(newPoint, _point2, _point3))
        {
            setPoint(_point1, newPoint);
        }
    }

    public void setPoint2(Point newPoint)
    {
        if(isValid(_point1, newPoint, _point3))
        {
            setPoint(_point2, newPoint);
        }
    }

    public void setPoint3(Point newPoint)
    {
        if(isValid(_point1, _point2, newPoint))
        {
            setPoint(_point3, newPoint);
        }
    }

    /********************************
    *       TRIANGLE QUALITIES      *
    ********************************/
    public double getPerimeter()
    {
        return getEdge(FIRST_EDGE) + getEdge(SECOND_EDGE) + getEdge(THIRD_EDGE);
    }

    public double getArea()
    {
        double halfPerimeter = getPerimeter() / 2;
        
        double diffEdgeA = halfPerimeter - getEdge(FIRST_EDGE);
        double diffEdgeB = halfPerimeter - getEdge(SECOND_EDGE);
        double diffEdgeC = halfPerimeter - getEdge(THIRD_EDGE);

        return Math.sqrt(halfPerimeter * diffEdgeA * diffEdgeB * diffEdgeC);   // Heron formula
    }

    public boolean isIsosceles()
    {
        // one or more pairs of edges are equal
        return isDoubleEqual(getEdge(FIRST_EDGE), getEdge(SECOND_EDGE)) || 
                isDoubleEqual(getEdge(FIRST_EDGE), getEdge(THIRD_EDGE)) || 
                isDoubleEqual(getEdge(SECOND_EDGE), getEdge(THIRD_EDGE));
    }

    public boolean isPythagorean()
    {
        // variables for readability
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

    public boolean isContainedInCircle(double x, double y, double r)
    {
        boolean retVal = false;
        Point circleCenter = new Point(x ,y);
        double distPoint = circleCenter.distance(_point1);

        if(isDoubleEqual(distPoint, r) || distPoint < r)
        {
            distPoint = circleCenter.distance(_point2);
            
            if(isDoubleEqual(distPoint, r) || distPoint < r)
            {
                distPoint = circleCenter.distance(_point3);
                
                if(isDoubleEqual(distPoint, r) || distPoint < r)
                {
                    retVal = true;
                }
            }
        }

        return retVal;
    }

    public Point lowestPoint()
    {
        return lowestOfTwo(_point1, lowestOfTwo(_point2, _point3));
    }

    public Point highestPoint()
    {
        return heighestOfTwo(_point1, heighestOfTwo(_point2, _point3));
    }

    public boolean isLocated()
    {
        return (_point1.quadrant() == _point2.quadrant()) && (_point2.quadrant() == _point3.quadrant());
    }

    public boolean isAbove(Triangle other)
    {
        return lowestPoint().isAbove(other.highestPoint());
    }

    public boolean isUnder(Triangle other)
    {
        return other.isAbove(this);
    }

    public boolean isValid(Point point1, Point point2, Point point3)
    {
        double edge1 = point1.distance(point2);
        double edge2 = point1.distance(point3);
        double edge3 = point2.distance(point3);

        return !isDoubleEqual(edge1, edge2 + edge3) && 
                !isDoubleEqual(edge2, edge1 + edge3) &&
                !isDoubleEqual(edge3, edge1 + edge2);
    }

    public boolean equals(Triangle other)
    {
        return _point1.equals(other.getPoint1()) &&
                _point2.equals(other.getPoint2()) &&
                _point3.equals(other.getPoint3());
    }

    public boolean isCongruent(Triangle other)
    {   
        int edgeLeft1;
        int edgeLeft2;
        
        if(areEdgesEqual(FIRST_EDGE, FIRST_EDGE, other))
        {
            edgeLeft1 = SECOND_EDGE;
            edgeLeft2 = THIRD_EDGE;
        }
        else if(areEdgesEqual(FIRST_EDGE, SECOND_EDGE, other))
        {
            edgeLeft1 = FIRST_EDGE;
            edgeLeft2 = THIRD_EDGE;
        }
        else if(areEdgesEqual(FIRST_EDGE, THIRD_EDGE, other))
        {
            edgeLeft1 = FIRST_EDGE;
            edgeLeft2 = SECOND_EDGE;
        }
        else
            return false;

        return ((areEdgesEqual(SECOND_EDGE, edgeLeft1, other) && areEdgesEqual(THIRD_EDGE, edgeLeft2, other)) ||
                    (areEdgesEqual(SECOND_EDGE, edgeLeft2, other) && areEdgesEqual(THIRD_EDGE, edgeLeft1, other)));
    }

    /********************************
    *        PRIVATE METHODS        *
    ********************************/

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
            return 0d;
        }
    }

    private boolean isDoubleEqual(double a, double b)
    {
        return Math.abs(a - b) < EPSILON;
    }

    private void setPoint(Point dest, Point src)
    {
        dest.setX(src.getX());
        dest.setY(src.getY());
    }

    private boolean areEdgesEqual(int currEdge, int otherEdge, Triangle other)
    {
        if(currEdge > THIRD_EDGE || currEdge < FIRST_EDGE || 
            otherEdge > THIRD_EDGE || otherEdge < FIRST_EDGE)
        {
            return false;   
        }
        
        return isDoubleEqual(getEdge(currEdge), other.getEdge(otherEdge));
    }

    private Point heighestOfTwo(Point a, Point b)
    {
        if(a.isAbove(b))
            return a;
        if(b.isAbove(a))
            return b;
        if(a.isLeft(b))
            return a;
        else
            return b;
    }

    private Point lowestOfTwo(Point a, Point b)
    {
        if(a.isUnder(b))
            return a;
        if(b.isUnder(a))
            return b;
        if(a.isLeft(b))
            return a;
        else
            return b;
    }

    private double pythagorean(double a, double b)
    {
        final double POWER = 2;

        return Math.sqrt(Math.pow(a, POWER) + Math.pow(b, POWER));
    }
} //class Triangle