
import java.io.File; 
import java.io.IOException; 
import java.awt.image.BufferedImage; 
import java.awt.image.Raster;

import javax.imageio.ImageIO; 


public class RunLengthCode {

    //main encoding method
	private static String ComputeCode(int[][] imgArr){
		String output="";
		for(int i=0;i<imgArr.length;i++){
			output+=ComputeCodeHelper(imgArr[i],i);
		}
		return output;


	}

    //helper method
	public static String ComputeCodeHelper(int[] imgArr,int x){
		String tmp="";
		boolean flagi=false;
		boolean flagj=false;
		for(int i=0;i<imgArr.length;i++){
			if(imgArr[i]==1){
				flagi=true;
				tmp+=i;
				for(int j=i+1;j<imgArr.length;j++){
					if(imgArr[j]!=1){
						tmp+=(j-1);
						flagj=true;
						break;
					}
					i=j;
				}
			}
		}
		if (!flagj&&flagi){
			return "("+x+tmp+""+(imgArr.length-1)+")";
		} else if(!flagi){
			return "";
		} else{
			return "("+x+tmp+")";
		}
	}
	
	public static void main(String [] args) {
		//write image path 
		String path = "images/binary_line.jpg";
		//String path++ = "images/binary_triangle.jpg";
		BufferedImage image = null;
		
		try {
			File input_image = new File(path);
			// Reading input image 
		    image = ImageIO.read(input_image);
		    System.out.println("Reading complete."); 
		} 
		
		catch (IOException e) {
		}
		
		// convert image to 2D array 
		int width = image.getWidth();
	    int height = image.getHeight();
	    int[][] imgArr = new int[height][width];
	    Raster raster = image.getData();
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            imgArr[i][j] = raster.getSample(j, i, 0);
	    }
	   }

	   String output=ComputeCode(imgArr);//Encoding method returns string to variable output.
	   System.out.println(output);//display the encoded variable.
	}
	
} 


