class Triangle
{
    enum Edges
    {
        FIRST_EDGE(0),
        SECOND_EDGE(1),
        THIRD_EDGE(2),
        NUM_EDGES(3);

        private int _value;

        private Edges(int value)
        {
            _value = value;
        }

        public int getValue()
        {
            return _value;
        }
    }

    static final double EPSILON = 0.0001;

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
        return getEdge(Edges.FIRST_EDGE) + getEdge(Edges.SECOND_EDGE) + getEdge(Edges.THIRD_EDGE);
    }

    public double getArea()
    {
        double halfPerimeter = getPerimeter() / 2;
        
        double diffEdgeA = halfPerimeter - getEdge(Edges.FIRST_EDGE);
        double diffEdgeB = halfPerimeter - getEdge(Edges.SECOND_EDGE);
        double diffEdgeC = halfPerimeter - getEdge(Edges.THIRD_EDGE);

        return Math.sqrt(halfPerimeter * diffEdgeA * diffEdgeB * diffEdgeC);   // Heron formula
    }

    public boolean isIsosceles()
    {
        // one or more pairs of edges are equal
        return isDoubleEqual(getEdge(Edges.FIRST_EDGE), getEdge(Edges.SECOND_EDGE)) || 
                isDoubleEqual(getEdge(Edges.FIRST_EDGE), getEdge(Edges.THIRD_EDGE)) || 
                isDoubleEqual(getEdge(Edges.SECOND_EDGE), getEdge(Edges.THIRD_EDGE));
    }

    public boolean isPythagorean()
    {
        double edge1 = getEdge(Edges.FIRST_EDGE);
        double edge2 = getEdge(Edges.SECOND_EDGE);
        double edge3 = getEdge(Edges.THIRD_EDGE);

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
        double distPoint1 = circleCenter.distance(_point1);

        if(isDoubleEqual(distPoint1, r) || distPoint1 < r)
        {
            double distPoint2 = circleCenter.distance(_point2);
            
            if(isDoubleEqual(distPoint2, r) || distPoint2 < r)
            {
                double distPoint3 = circleCenter.distance(_point3);
                
                if(isDoubleEqual(distPoint3, r) || distPoint3 < r)
                {
                    retVal = true;
                }
            }
        }

        return retVal;
    }

    public Point lowestPoint()
    {
        if(_point1.isUnder(_point2))
        {
            if(_point1.isUnder(_point3))
                return _point1;
            else if(_point3.isUnder(_point1))
                return _point3;
            else if(_point1.isLeft(_point3)) // point1 and 3 are at the same height and under point2
                return _point1;
            else
                return _point3;
        }
        else if(_point2.isUnder(_point1))
        { 
            if(_point2.isUnder(_point3))
                return _point2;
            else if(_point3.isUnder(_point2))
                return _point3;
            else if(_point2.isLeft(_point3)) // point2 and 3 are at the same height and under point1
                return _point2;
            else
                return _point3;
        }
        else if(_point3.isUnder(_point2)) //point1 and point2 are at the same height means point3 must be above or under.
            return _point3;
        else if(_point1.isLeft(_point2)) // point3 is above point1 and 2. check most left among them.
            return _point1;
        else
            return _point2;
    }

    public Point highestPoint()
    {
        if(_point1.isAbove(_point2))
        {
            if(_point1.isAbove(_point3))
                return _point1;
            else if(_point3.isAbove(_point1))
                return _point3;
            else if(_point1.isLeft(_point3)) // point1 and 3 are at the same height and above point2
                return _point1;
            else
                return _point3;
        }
        else if(_point2.isAbove(_point1))
        { 
            if(_point2.isAbove(_point3))
                return _point2;
            else if(_point3.isAbove(_point2))
                return _point3;
            else if(_point2.isLeft(_point3)) // point2 and 3 are at the same height and above point1
                return _point2;
            else
                return _point3;
        }
        else if(_point3.isAbove(_point2)) //point1 and 2 are at the same height, means point3 must be above or under.
            return _point3;
        else if(_point1.isLeft(_point2)) // point2 and 1 are at the same height and above point3
            return _point1;
        else
            return _point2;
    }

    public boolean isLocated()
    {
        return _point1.quadrant() == _point2.quadrant() && _point2.quadrant() == _point3.quadrant();
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
        boolean matchFound = false;
        Edges[] firstMatchCycle = {Edges.FIRST_EDGE, Edges.SECOND_EDGE, Edges.THIRD_EDGE};
        Edges[] secondMatchCycle = new Edges[2];

        int firstMatchIndex = 0;
        int secondMatchIndex = 0;

        while(!matchFound && firstMatchIndex < Edges.NUM_EDGES.getValue())
        {
            if(areEdgesEqual(Edges.FIRST_EDGE, firstMatchCycle[firstMatchIndex], other))
            {
                matchFound = true;

                for(secondMatchIndex = 0; secondMatchIndex < Edges.THIRD_EDGE.getValue(); ++secondMatchIndex)
                {
                    secondMatchCycle[secondMatchIndex] = 
                        firstMatchCycle[(firstMatchIndex + secondMatchIndex + 1) % Edges.NUM_EDGES.getValue()];
                }
                
            }

            ++firstMatchIndex;
        }

        if(matchFound)
        {
            matchFound = false;
            secondMatchIndex = 0;

            while(!matchFound && secondMatchIndex < Edges.THIRD_EDGE.getValue())
            {
                if(areEdgesEqual(Edges.SECOND_EDGE, secondMatchCycle[secondMatchIndex], other) && 
                    areEdgesEqual(Edges.THIRD_EDGE, secondMatchCycle[(secondMatchIndex + 1) % Edges.THIRD_EDGE.getValue()] , other))
                {
                    matchFound = true;
                }

                ++secondMatchIndex;
            }
        }

        return matchFound;
    }

    /********************************
    *        PRIVATE METHODS        *
    ********************************/

    private double getEdge(Edges edge)
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

    private boolean areEdgesEqual(Edges currEdge, Edges otherEdge, Triangle other)
    {
        boolean equal = false;
        double choosenEdge;
        
        // choose 'this' right edge for comparsion
        switch(currEdge)
        {
        case FIRST_EDGE:
        case SECOND_EDGE:
        case THIRD_EDGE:
            choosenEdge = getEdge(currEdge);
            
            // choose other right edge to compare to and compare
            switch(otherEdge)
            {
            case FIRST_EDGE:
            case SECOND_EDGE:
            case THIRD_EDGE:
                equal = isDoubleEqual(choosenEdge, other.getEdge(otherEdge));
                break;
            default:
            } // switch

            break;
        default:
            return false;
        } // switch

        return equal;
    } // areEdgesEqual

    private double pythagorean(double a, double b)
    {
        final double POWER = 2;

        return Math.sqrt(Math.pow(a, POWER) + Math.pow(b, POWER));
    }

} //class Triangle