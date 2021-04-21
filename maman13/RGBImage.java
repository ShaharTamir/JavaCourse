/**
 * RGBImage.java represents an image made of RGBColor pixels.<p>
 * RGBColor is for Red, Green and Blue.
 * 
 * @version 2021b
 * @author Shahar Tamir
 */
public class RGBImage
{
    private RGBColor[][] _image;
    
    /**
     * Construct a new black RGBImage with the given dimensions.
     * @param rows number of pixel rows in image
     * @param cols number of pixels in each row 
     */
    public RGBImage(int rows, int cols)
    {
        _image = new RGBColor[rows][cols];

        for(int r = 0; r < rows; ++r)
            for(int c = 0; c < cols; ++c)
                _image[r][c] = new RGBColor();
    }

    /**
     * Construct a new RGBImage from given pixels array.
     * @param pixels array of pixels containing RGB data for each pixel in image
     */
    public RGBImage(RGBColor[][] pixels)
    {
        _image = new RGBColor[pixels.length][pixels[0].length];
        
        for(int rows = 0; rows < pixels.length; ++rows)
            for(int cols = 0; cols < pixels[0].length; ++cols)
                _image[rows][cols] = new RGBColor(pixels[rows][cols]); // RGBColor copy constructor
    }

    /**
     * Copy constructor creates a new RGBImage from other image.
     * @param other image to be copied
     */
    public RGBImage(RGBImage other)
    {
        this(other._image);
    }

    /**
     * This method returns the image's height.
     * @return The image's height (number of rows in image)
     */
    public int getHeight()
    {
        return _image.length;
    }

    /**
     * This method returns the image's width.
     * @return The image's width (number of columns in image)
     */
    public int getWidth()
    {
        return _image[0].length;
    }

    /**
     * If position is valid, this method returns the value 
     * of the image's pixel in the specified position.<p>
     * If not - returns a black pixel.
     * @param row row pixel position (from 0 to image height - 1)
     * @param col coloumn pixel position (from 0 image width - 1)
     * @return return pixel values only if given position is valid. If not - return a black pixel
     */
    public RGBColor getPixel(int row, int col)
    {
        if(validPos(row, col))
            return new RGBColor(_image[row][col]);
        
        return new RGBColor();
    }

    /**
     * If valid, this method sets the value of the image's pixel in the specified position.
     * @param row row pixel position (from 0 to image height - 1)
     * @param col coloumn pixel position (from 0 image width - 1)
     * @param pixel to assign it's values
     */
    public void setPixel(int row, int col, RGBColor pixel)
    {
        if(validPos(row, col))
            _image[row][col] = new RGBColor(pixel);
    }

    /**
     * Check if the given image is equal to this image.
     * @param other the image we are checking equality with
     * @return true if the image <tt>other</tt> is equal to this image
     */
    public boolean equals(RGBImage other)
    {
        // verfiy other is not null and size equality
        if(null == other || other._image.length != _image.length ||
             other._image[0].length != _image[0].length)
            return false;

        // if one pair of pixels are not equal - the images are not equal
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                if(!_image[r][c].equals(other._image[r][c]))
                    return false;

        return true;
    }

    /**
    * This method flips the image horizontally.<p>
    * first column becomes last and so on
    */
    public void flipHorizontal()
    {
        for(int r = 0; r < _image.length; ++r)
        {
            int start = 0;
            int end = _image[0].length - 1; // last coloumn index

            while(start < end)
                swapPixels(r, start++, r, end--);
        }//for
    }

    /**
     * This method flips the image  vertically.<p>
     * first row becomes last and so on
     */
    public void flipVertical()
    {
        for(int c = 0; c < _image[0].length; ++c)
        {
            int start = 0;
            int end = _image.length - 1; // last row position

            while(start < end)
                swapPixels(start++, c, end--, c); 
        }//for
    }

    /**
    * This method inverts the image colors.
    */
    public void invertColors()
    {
        // run through the entire image and invert each pixel 
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                _image[r][c].invert();
    }

    /**
    * This method rotates the image 90 degrees clockwise.<p>
    * first row becomes the last column and so on
    */
    public void rotateClockwise()
    {
        // allocate a new black image with the rotated dimensions: 
        // curr width is new height, curr height is new width
        RGBColor[][] rotatedImage = new RGBColor[_image[0].length][_image.length];
        int newLastCol = rotatedImage[0].length - 1;

        // copy this image to the rotated image according to logic from description
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                rotatedImage[c][newLastCol - r] = new RGBColor(_image[r][c]);

        // this image now refers to the rotated image
        _image = rotatedImage;
    }

    /**
    * This method rotates the image 90 degrees counter clockwise.<p>
    * first column becomes the last row and so on
    */
    public void rotateCounterClockwise()
    {
        // allocate a new black image with the rotated dimensions: 
        // curr width is new height, curr height is new width
        RGBColor[][] rotatedImage = new RGBColor[_image[0].length][_image.length];
        int newLastRow = rotatedImage.length - 1;

        // copy this image to the rotated image according to logic from description
        for(int c = 0; c < _image[0].length; ++c)
            for(int r = 0; r < _image.length; ++r)
                rotatedImage[newLastRow - c][r] = new RGBColor(_image[r][c]);

        // this image now refers to the rotated image
        _image = rotatedImage;
    }
    
    /**
    * This method shifts the columns of the image according to <tt>offset</tt>.<p>
    * The pixels in the shifted positions will turn black.
    * @param offset possitive offset shifts right, negative shifts left
    */
    public void shiftCol(int offset)
    {
        if(Math.abs(offset) > _image[0].length || 0 == offset)
            return;
            
        int c = 0;

        if(offset < 0) // shift left
        {
            offset = -offset; // work with a possitive offset.
            
            /* shift from right to left. assign 'offset' column to the first column, 
               and move towards the last column. */
            while(c + offset < _image[0].length)
            {
                for(int r = 0; r < _image.length; ++r)
                {
                    _image[r][c] = _image[r][c + offset];
                    _image[r][c + offset] = new RGBColor(); // paint the shifted pixel position in black
                }//for
    
                ++c;
            }//while
        }
        else // shift right
        {
            c = _image[0].length - 1; // last coloumn index

            /* shift from left to right. assign 'last column - offset' to the last column,
               and move towards the first coloumn. */
            while(c - offset >= 0)
            {
                for(int r = 0; r < _image.length; ++r)
                {
                    _image[r][c] = _image[r][c - offset];
                    _image[r][c - offset] = new RGBColor(); // paint the shifted pixel position in black
                }//for
                
                --c;
            }//while
        }

        if(_image[0].length - offset < offset)
            // here means there are cols that did not shift because exceeded image boundaries 
            // and need to be painted black 
            paintAreaBlack(0, _image.length, _image[0].length - offset, offset);
    }

    /**
    * This method shifts the rows of the image according to <tt>offset</tt>.<p>
    * The pixels in the shifted positions will turn black.
    * @param offset possitive offset shifts down, negative shifts up
    */
    public void shiftRow(int offset)
    {
        if(Math.abs(offset) > _image.length || 0 == offset)
            return;

        int r = 0;
        if(offset < 0) // shift up
        {
            offset = -offset; // work with a possitive offset.
            
            /* shift from down up. assign 'offset' to the first row, 
                and move towards the last row. */
            while(r + offset < _image.length)
            {
                for(int c = 0; c < _image[0].length; ++c)
                {
                    _image[r][c] = _image[r + offset][c];
                    _image[r + offset][c] = new RGBColor(); // paint the shifted pixel position in black
                }//for

                ++r;
            }//while
        }//if
        else // shift down
        {
            r = _image.length - 1; // last row position

            /* shift from up down. assign 'last row - offset' to the last row,
                and move towards the first row. */
            while(r - offset >= 0)
            {
                for(int c = 0; c < _image[0].length; ++c)
                {
                    _image[r][c] = _image[r - offset][c];
                    _image[r - offset][c] = new RGBColor(); // paint the shifted pixel position in black
                }
                
                --r;
            }//while
        }//else

        if(_image.length - offset < offset)
            // here means there are rows that did not shift because exceeded image boundaries 
            // and need to be painted black
            paintAreaBlack(_image.length - offset, offset, 0, _image[0].length);
    }

    /**
    * This method returns a gray scale array that represents the image.
    * @return a gray scale array that represents the image
    */
    public double[][] toGrayscaleArray()
    {
        double[][] greyScaleImage = new double[_image.length][_image[0].length];

        // convert each pixel to grey scale and assign the value
        // to the matching possition in greyScaleImage.
        for(int r = 0; r < _image.length; ++r)
            for(int c = 0; c < _image[0].length; ++c)
                greyScaleImage[r][c] = _image[r][c].convertToGrayscale();

        return greyScaleImage;
    }

    /**
     * This method returns a String representation of the image.<p>
     * The format of the string should be as followed:<p>
     * 1. single space between each pixel.<p>
     * 2. no single space at the end of a row.<p>
     * 3. each pixel is presented: (r,g,b)
     * @return a String representation of the image
     */
    public String toString()
    {
        String pixelArrString = new String();

        for(int r = 0; r < _image.length; ++r)
        {
            for(int c = 0; c < _image[0].length - 1; ++c)   // until the last column, not included.
                pixelArrString += _image[r][c].toString() + ' ';

            // handle last column - no ' ' at end of the row. 
            pixelArrString += _image[r][_image[0].length - 1].toString() + '\n';
        }//for

        return pixelArrString;
    }

    /**
    * This method returns a RGBColor array that represents the image.
    * @return a RGBColor array that represents the image
    */
    public RGBColor[][] toRGBColorArray()
    {
        return (new RGBImage(this))._image;
    }

    /********************************
    *        PRIVATE METHODS        *
    ********************************/
    
    /*
    * This method returns if a position is valid
    *  parameters:
    *   row - row position in image
    *   col - coloumn position in image
    *  return: true if position is within image boundaries
    */
    private boolean validPos(int row, int col)
    {
        return row < _image.length && row >= 0 && col < _image[0].length && col >= 0;
    }

    /*
    * This method swaps to pixels inside this image.
    *  parameters:
    *   aRow - first row position
    *   aCol - first column position
    *   bRow - second row position
    *   bCol - second column position
    */
    private void swapPixels(int aRow, int aCol, int bRow, int bCol)
    {
        // assuming params are valid because method is private
        RGBColor tmp = _image[aRow][aCol];       // tmp refers to A value.
        _image[aRow][aCol] = _image[bRow][bCol]; // A cell refers to B value 
        _image[bRow][bCol] = tmp;                // B value refers to tmp (A) value.
    }

    /*
    * This method 'paint' a specified area in image in black.
    *   notice! - rowEnd and colEnd are not included.
    *  parameters:
    *   rowStart - starting row (from 0 to image.length - 1)
    *   rowEnd - paint until this row, NOT included(!)
    *   colStart - starting column at each row (from 0 to image[0].length - 1)
    *   colEnd - paint each row until this column, NOT included(!)
    */
    private void paintAreaBlack(int rowStart, int rowEnd, int colStart, int colEnd)
    {
        if(colStart < colEnd)   // verify to not waste useless iterations
        {
            while(rowStart < rowEnd)
            {
                int colRunner = colStart; // use a runner so start value is not lost
                
                while(colRunner < colEnd)
                    _image[rowStart][colRunner++] = new RGBColor();
    
                ++rowStart;
            }//while
        }//if
    }

} // class RGBImage