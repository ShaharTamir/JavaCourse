
public class RGBImage
{
    private RGBColor[][] _image;

    public RGBImage(int rows, int cols)
    {
        _image = new RGBColor[rows][cols];

        for(int r = 0; r < rows; ++r)
            for(int c = 0; c < cols; ++c)
                _image[r][c] = new RGBColor();
    }

    public RGBImage(RGBColor[][] pixels)
    {
        _image = new RGBColor[pixels.length][pixels[0].length];
        
        for(int r = 0; r < pixels.length; ++r)
            for(int c = 0; c < pixels[r].length; ++c)
                _image[r][c] = new RGBColor(pixels[r][c]);
    }

    public RGBImage(RGBImage other)
    {
        this(other._image);
    }

    public int getHeight()
    {
        return _image.length;
    }

    public int getWidth()
    {
        return _image[0].length;
    }

    public RGBColor getPixel(int row, int col)
    {
        if(validPos(row, col))
            return new RGBColor(_image[row][col]);
        
        return new RGBColor();
    }

    public void setPixel(int row, int col, RGBColor pixel)
    {
        if(validPos(row, col))
            _image[row][col] = new RGBColor(pixel);
    }

    public boolean equals(RGBImage other)
    {
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                if(!_image[r][c].equals(other._image[r][c]))
                    return false;

        return true;
    }

    public void flipHorizontal()
    {
        for(int c = 0; c < _image[0].length; ++c)
        {
            int start = 0;
            int end = lastRowPos();

            while(start < end)
                swapPixels(start++, c, end--, c);
        }
    }

    public void flipVertical()
    {
        for(int r = 0; r < _image.length; ++r)
        {
            int start = 0;
            int end = lastColPos();

            while(start < end)
                swapPixels(r, start++, r, end--);
        }
    }

    public void invertColors()
    {
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                _image[r][c].invert();
    }

    public void rotateClockwise()
    {
        RGBColor[][] newImage = new RGBColor[getWidth()][getHeight()];
        int lastCol = getHeight() - 1;

        for(int r = 0; r < _image.length; ++r)
            copyRowToOtherColumn(r, lastCol - r, newImage);
            //_image row length (num of columns) is equal to newImage column length (num of rows)

        _image = newImage;
    }

    public void rotateCounterClockwise()
    {
        RGBColor[][] newImage = new RGBColor[getWidth()][getHeight()];
        int lastRow = getWidth() - 1;

        for(int c = 0; c < _image[0].length; ++c)
            copyColumnToOtherRow(c, lastRow - c, newImage);
            //_image column length (num of rows) and newImage row length (num of columns) are equal

        _image = newImage;
    }

    public void shiftCol(int offset)
    {
        if(offset < 0)
        {
            offset = -offset;
            shiftColLeft(offset);
        }
        else if(offset > 0)
            shiftColRight(offset);
        
        offset = offset < _image[0].length ? offset : _image[0].length;
        if(_image[0].length - offset < offset)
            paintAreaBlack(0, _image.length, _image[0].length - offset, offset);
    }

    public void shiftRow(int offset)
    {
        if(offset < 0)
        {
            offset = -offset;
            shiftRowUp(offset);
        }
        else if(offset > 0)
            shiftRowDown(offset);

        offset = offset < _image.length ? offset : _image.length;
        if(_image.length - offset < offset)
            paintAreaBlack(_image.length - offset, offset, 0, _image[0].length);
    }

    public double[][] toGrayscaleArray()
    {
        double[][] greyScaleImage = new double[_image.length][_image[0].length];

        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                greyScaleImage[r][c] = _image[r][c].convertToGrayscale();

        return greyScaleImage;
    }

    public String toString()
    {
        // TODO: use string methods to build up the string
        String pixelArrString = new String();

        for(int r = 0; r < _image.length; ++r)
        {
            for(int c = 0; c < lastColPos(); ++c)
            {
                pixelArrString += _image[r][c].toString();
                pixelArrString += " ";
            }

            pixelArrString += _image[r][lastColPos()].toString();
            pixelArrString += "\n";
        }

        return pixelArrString;
    }

    public RGBColor[][] toRGBColorArray()
    {
        RGBImage retImage = new RGBImage(this);

        return retImage._image;
    }

    private boolean validPos(int row, int col)
    {
        return row < _image.length && row >= 0 && col < _image[0].length && col >= 0;
    }

    private int lastColPos()
    {
        return _image[0].length - 1;
    }

    private int lastRowPos()
    {
        return _image.length - 1;
    }

    private void copyRowToOtherColumn(int row, int col, RGBColor[][] other)
    {
        // assuming params are valid because method is private
        for(int i = 0; i < _image[0].length; ++i)
            other[i][col] = new RGBColor(_image[row][i]);
    }

    private void copyColumnToOtherRow(int row, int col, RGBColor[][] other)
    {
        // assuming params are valid because method is private
        for(int i = 0; i < _image.length; ++i)
            other[row][i] = new RGBColor(_image[i][col]);
    }

    private void swapPixels(int aRow, int aCol, int bRow, int bCol)
    {
        // assuming params are valid because method is private
        RGBColor tmp = _image[aRow][aCol];
        _image[aRow][aCol] = _image[bRow][bCol];
        _image[bRow][bCol] = tmp;
    }

    private void shiftColLeft(int offset)
    {
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c + offset < _image[0].length; ++c)
            {
                _image[r][c] = _image[r][c + offset];
                _image[r][c + offset] = new RGBColor();
            }
    }

    private void shiftColRight(int offset)
    {
        for(int r = 0; r < _image.length; ++r)
            for(int c = lastColPos(); c - offset >= 0; --c)
            {
                _image[r][c] = _image[r][c - offset];
                _image[r][c - offset] = new RGBColor();
            }
    }

    private void shiftRowUp(int offset)
    {
        for(int r = 0; r + offset < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
            {
                _image[r][c] = _image[r + offset][c];
                _image[r + offset][c] = new RGBColor();
            }
    }

    private void shiftRowDown(int offset)
    {
        for(int r = lastRowPos(); r - offset >= 0; --r)
            for(int c = 0; c < _image[0].length; ++c)
            {
                _image[r][c] = _image[r - offset][c];
                _image[r - offset][c] = new RGBColor();
            }
    }

    private void paintAreaBlack(int rowStart, int rowEnd, int colStart, int colEnd)
    {
        for(int rowRunner = rowStart; rowRunner < rowEnd; ++rowRunner)
            for(int colRunner = colStart; colRunner < colEnd; ++colRunner)
                _image[rowRunner][colRunner] = new RGBColor();
    }

} // class RGCImage