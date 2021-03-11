import Point;

class Triangle
{
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
        }

        return isChanged;
    }

    /********************************
    *        PRIVATE METHODS        *
    ********************************/
    private boolean isValid(Point point1, Point point2, Point point3)
    {
        boolean valid = false;

        int topPoint = getTopPoint(point1, point2, point3);
        int mostLeftPoint = getMostLeft(point1, point2, point3);

        if(topPoint != ALL_SAME && mostLeftPoint != ALL_SAME)
        {
            valid = true;
        }

        return valid;
    }

    private int getTopPoint(Point point1, Point point2, Point point3)
    {
        int top = ALL_SAME;

        if(point1.isAbove(point2) && point1.isAbove(point3))
        {
            top = ONE_TOP;
        }
        else if(point2.isAbove(point1) && point2.isAbove(point3))
        {
            top = TWO_TOP;
        }
        else if(point3.isAbove(point1) && point3.isAbove(point2))
        {
            top = THREE_TOP;
        }

        return top;
    }

    private int getMostLeft(Point point1, Point point2, Point point3)
    {
        int mostLeft = ALL_SAME;

        if(point1.isLeft(point2) && point1.isLeft(point3))
        {
            mostLeft = ONE_TOP;
        }
        else if(point2.isLeft(point1) && point2.isLeft(point3))
        {
            mostLeft = TWO_TOP;
        }
        else if(point3.isLeft(point1) && point3.isLeft(point2))
        {
            mostLeft = THREE_TOP;
        }

        return mostLeft;
    }

    static final double EPSILON = 0.0001;
    static final int ALL_SAME = 0;
    static final int ONE_TOP = 1;
    static final int TWO_TOP = 2;
    static final int THREE_TOP = 3;

    Point _point1;
    Point _point2;
    Point _point3;
}