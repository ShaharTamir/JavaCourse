import Point;

class Triangle
{
    enum Edges
    {
        FIRST_EDGE,
        SECOND_EDGE,
        THIRD_EDGE
    }

    enum EPointGeoId   // relative location to other points
    {
        ALL_SAME,
        ONE_TOP,
        TWO_TOP,
        THREE_TOP,
        ONE_TWO_TOP,
        ONE_THREE_TOP, 
        TWO_THREE_TOP,
        ONE_LOW,
        TWO_LOW,
        THREE_LOW,
        ONE_TWO_LOW,
        ONE_THREE_LOW,
        TWO_THREE_LOW,
        ONE_LEFT,
        TWO_LEFT,
        THREE_LEFT,
        ONE_TWO_LEFT,
        ONE_THREE_LEFT,
        TWO_THREE_LEFT,
        ONE_RIGHT,
        TWO_RIGHT,
        THREE_RIGHT,
        ONE_TWO_RIGHT,
        ONE_THREE_RIGHT,
        TWO_THREE_RIGHT
    }

    /********************************
    *          CONSTRACTORS         *
    ********************************/
    public Triangle()
    {
        _point1 = new Point(1d, 0d);
        _point2 = new Point(-1d, 0d);
        _point3 = new Point(0d, 1d);

        setEdgeLength(Edges.FIRST_EDGE);
        setEdgeLength(Edges.SECOND_EDGE);
        setEdgeLength(Edges.THIRD_EDGE);

        _pointsGeoMap = new EPointGeoId[4];
        _pointsGeoMap[0] = EPointGeoId.THREE_TOP;
        _pointsGeoMap[1] = EPointGeoId.ONE_TWO_LOW;
        _pointsGeoMap[2] = EPointGeoId.ONE_RIGHT;
        _pointsGeoMap[3] = EPointGeoId.TWO_LEFT;
    }

    public Triangle(Point point1, Point point2, Point point3)
    {
        if(isValid(point1, point2, point3))
        {
            _point1 = point1;
            _point2 = point2;
            _point3 = point3;
        }
        else
        {
            Triangle();
        }
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3));
    }

    public Triangle(Triangle other)
    {
        _point1 = other._point1;
        _point2 = other._point2;
        _point3 = other._point3;
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
    public boolean setPoint1(Point newPoint)
    {
        boolean isChanged = false;

        if(isValid(newPoint, _point2, _point3))
        {
            _point1 = newPoint;
            isChanged = true; 

            setEdgeLength(Edges.FIRST_EDGE);
            setEdgeLength(Edges.SECOND_EDGE);
        }

        return isChanged;
    }

    public boolean setPoint2(Point newPoint)
    {
        boolean isChanged = false;

        if(isValid(_point1, newPoint, _point3))
        {
            _point2 = newPoint;
            isChanged = true; 

            setEdgeLength(Edges.FIRST_EDGE);
            setEdgeLength(Edges.THIRD_EDGE);
        }

        return isChanged;
    }

    public boolean setPoint3(Point newPoint)
    {
        boolean isChanged = false;

        if(isValid(_point1, _point2, newPoint))
        {
            _point3 = newPoint;
            isChanged = true; 

            setEdgeLength(Edges.SECOND_EDGE);
            setEdgeLength(Edges.THIRD_EDGE);
        }

        return isChanged;
    }

    public double getPerimeter()
    {
        return _edge1 + _edge2 + _edge3;
    }

    public double getArea()
    {
        double halfPerimeter = getPerimeter() / 2;
        
        double diffEdgeA = halfPerimeter - _edge1;
        double diffEdgeB = halfPerimeter - _edge2;
        double diffEdgeC = halfPerimeter - _edge3;

        return Math.sqrt(halfPerimeter * diffEdgeA * diffEdgeB * diffEdgeC);   // Heron formula
    }

    public boolean isIsosceles()
    {
        // one or more pair of edges is equal
        return isDoubleEqual(_edge1, _edge2) || 
                isDoubleEqual(_edge1, _edge3) || 
                isDoubleEqual(_edge2, _edge3);
    }

    public boolean isPythagorean()
    {
        return isDoubleEqual(_edge1, _edge2) && isDoubleEqual(_edge1, _edge3);
    }

    public boolean isContainedInCircle(double x, double y, double r)
    {
        boolean retVal = false;
        //TODO

        return retVal;
    }

    public Point lowestPoint()
    {
        // TODO
    }

    public Point highestPoint()
    {
       // TODO 
    }

    public boolean isValid(Point point1, Point point2, Point point3)
    {
        boolean valid = false;
        
        if(!isDoubleEuqal(point1, point2) && !isDoubleEuqal(point1, point3) &&
             !isDoubleEuqal(point2, point3))
        {
            
        }

        return valid;
    }

    /********************************
    *        PRIVATE METHODS        *
    ********************************/

    private void setEdgeLength(Edges edge)
    {
        switch(edge)
        {
        case FIRST_EDGE:
            _edge1 = getPoint1().distance(getPoint2());
            break;
        case SECOND_EDGE:
            _edge2 = getPoint1().distance(getPoint3());
            break;
        case THIRD_EDGE:
            _edge3 = getPoint2().distance(getPoint3());
            break;
        default:
            break;
        }
    }

    private bool isDoubleEqual(double a, double b)
    {
        return Math.abs(a - b) < EPSILON;
    }

    static final double EPSILON = 0.0001;

    Point _point1;
    Point _point2;
    Point _point3;

    double _edge1;
    double _edge2;
    double _edge3;

    EPointGeoId[] _pointsGeoMap;
}