/**
 * 
 */

/**
 * @author Course tutors
 *
 */
public class StudentTester 
{

	public static void main(String[] args) {
		
		System.out.println("Black Image Constructor:");
		RGBImage rgbImg0 = new RGBImage(3,4);		
		System.out.println(rgbImg0);	
		
		System.out.println("Constructor with RGBColor[][] Array Parameter:");
		RGBColor[][] rgbArray1 = new RGBColor[3][4];
		for (int i=0; i<rgbArray1.length - 1;i++)
			for (int j=0; j<rgbArray1[0].length;j++)	
				rgbArray1[i][j] = new RGBColor(j + i, j + i, j + i);						
		RGBImage startingImage = new RGBImage(rgbArray1);
		System.out.println(startingImage);
				
		//startingImage testing
		testSize(new RGBImage(startingImage));
		testRotation(new RGBImage(startingImage));
		testFlipping(new RGBImage(startingImage));
		testShifting(new RGBImage(startingImage));
		testColorArr(new RGBImage(startingImage));
		
		// rgbImg2 testing
		RGBImage rgbImg2 = new RGBImage(startingImage);
		System.out.println("invertColors:");
		rgbImg2.invertColors();
		System.out.println(rgbImg2);
		
		System.out.println("setPixel + getPixel:");
		//next 4 lines set last line of rgbImg2 to be (0,0,0)
		rgbImg2.setPixel(2, 0, new RGBColor(1,0,0));
		rgbImg2.setPixel(2, 1, new RGBColor(0,0,0));
		rgbImg2.setPixel(2, 2, new RGBColor(0,0,0));
		rgbImg2.setPixel(2, 3, new RGBColor(0,0,0));
		rgbImg2.setPixel(6, 0, new RGBColor(7,7,7));
		rgbImg2.setPixel(0, 7, new RGBColor(0,0,0));
		System.out.println(rgbImg2.getPixel(2,0));
		System.out.println(rgbImg2.getPixel(6,0));
		System.out.println();
		
		System.out.println("toGrayscaleArray:");
		double[][] grayscaleArray = rgbImg2.toGrayscaleArray();
		for(int i=0;i<grayscaleArray.length;i++){
			for(int j=0;j<grayscaleArray[0].length;j++)
				System.out.print(grayscaleArray[i][j]+"  ");
			System.out.println();
		}	
		System.out.println();
		
		System.out.println("equals:\n image1:\n" + startingImage + "image2:\n" + rgbImg2);
		//compare startingImage with rgbImg2
		System.out.println("result:" + (startingImage.equals(rgbImg2)?true:false));
		
		
		System.out.println("\n\n fin \n\n");
	}

	public static void testSize(RGBImage image)
	{
		System.out.println("getHeight:");
		System.out.println(image.getHeight()+"\n");
		System.out.println("getWidth:");
		System.out.println(image.getWidth()+"\n");
	}

	public static void testRotation(RGBImage image)
	{
		System.out.println("\n\n *** test rotation *** \n");
		System.out.println("starting image");
		System.out.println(image);
		System.out.println("rotateClockwise:");
		image.rotateClockwise();
		System.out.println(image);
		System.out.println("rotateClockwise:");
		image.rotateClockwise();
		System.out.println(image);
		System.out.println("rotateClockwise:");
		image.rotateClockwise();
		System.out.println(image);
		System.out.println("rotateClockwise:");
		image.rotateClockwise();
		System.out.println(image);
		System.out.println("rotateCounterClockwise:");
		image.rotateCounterClockwise();
		System.out.println(image);
		System.out.println("rotateCounterClockwise:");
		image.rotateCounterClockwise();
		System.out.println(image);
		System.out.println("rotateCounterClockwise:");
		image.rotateCounterClockwise();
		System.out.println(image);
		System.out.println("rotateCounterClockwise:");
		image.rotateCounterClockwise();
		System.out.println(image);
	}

	public static void testShifting(RGBImage image)
	{
		System.out.println("\n\n *** test shifting *** \n");
		System.out.println("starting image");
		System.out.println(image);
		int offset = 1;
		System.out.println("shiftRow :" + offset);
		image.shiftRow(offset);
		System.out.println(image);
		offset = -2;
		System.out.println("shiftRow :" + offset);
		image.shiftRow(offset);
		System.out.println(image);
		offset = 1;
		System.out.println("shiftRow :" + offset);
		image.shiftRow(offset);
		System.out.println(image);
		offset = 0;
		System.out.println("shiftRow :" + offset);
		image.shiftRow(offset);
		System.out.println(image);
		offset = 1;
		System.out.println("shiftCol :" + offset);
		image.shiftCol(offset);
		System.out.println(image);
		offset = -2;
		System.out.println("shiftCol :" + offset);
		image.shiftCol(offset);
		System.out.println(image);
		offset = 1;
		System.out.println("shiftCol :" + offset);
		image.shiftCol(offset);
		System.out.println(image);
		offset = 0;
		System.out.println("shiftCol :" + offset);
		image.shiftCol(offset);
		System.out.println(image);
	}

	public static void testFlipping(RGBImage image)
	{
		System.out.println("\n\n *** test flipping *** \n");
		System.out.println("starting image");
		System.out.println(image);
		System.out.println("flipHorizontal:");
		image.flipHorizontal();
		System.out.println(image);
		System.out.println("flipHorizontal:");
		image.flipHorizontal();
		System.out.println(image);
		System.out.println("flipVertical:");
		image.flipVertical();
		System.out.println(image);
		System.out.println("flipVertical:");
		image.flipVertical();
		System.out.println(image);
	}

	public static void testColorArr(RGBImage image)
	{
		System.out.println("toRGBColorArray:");
		RGBColor[][] rgbArray = image.toRGBColorArray();
		for(int i=0;i<rgbArray.length;i++)
		{
			for(int j=0;j<rgbArray[0].length;j++)
				System.out.print(rgbArray[i][j]+"  ");
			System.out.println();
		}
		System.out.println();
	}
}
