package gbjemulator;
import java.util.Scanner;

public class Main {
		
		
		
		
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		float[][] gridspace = new float[3][3];
		outputGridspace(gridspace);
		System.out.println("x: ");
		int xin = Integer.parseInt(scanner.next());
		System.out.println("y: ");
		int yin = Integer.parseInt(scanner.next());
		System.out.println("value: ");
		float vin = Float.parseFloat(scanner.next());
		gridspace = setPos(gridspace, xin, yin, vin);
		outputGridspace(gridspace);
		//System.out.println(getDimensions(gridspace)[0]); // testing getdimensions and setrow/column
		gridspace = setRow(gridspace, 0, 3);
		outputGridspace(gridspace);
		gridspace = propagateField(gridspace, 3);
		outputGridspace(gridspace);
	}

	static void outputGridspace(float[][] igsp) {
		
		int w = igsp.length;
		int h = igsp[0].length;
		
		for(int j = 0; j < h; j++) { // searching height, j = current y
			
			for(int i = 0; i < w; i++) { // searching width, i = current x
				
				System.out.println(i + ", " + j + ": " + igsp[i][j]);
				
			}
			
		}
		
	}
	
	static int[] getDimensions(float[][] igsp) {
		int w = igsp.length;
		int h = igsp[0].length;
		int[] returnvalue = {w, h};
		return returnvalue;
	}
	
	static float[][] setPos(float[][] igsp, int x, int y, float v) {
		igsp[x][y] = v;
		return igsp;
	}
	
	static float[][] setRow(float[][] igsp, int y, float v) {
		int[] dim = getDimensions(igsp);
		int w = dim[0];
		//System.out.println(dim[0]); // Once again, testing getdimensions function interaction w/ setrow, probably gonna
		// call myself dumb later
		for(int i = 0; i < w; i++) {
			igsp[i][y] = v;
		}
		return igsp;
	}
	
	static float[][] setColumn(float[][] igsp, int x, float v) {
		int[] dim = getDimensions(igsp);
		int h = dim[1];
		for(int i = 0; i < h; i++) {
			igsp[x][i] = v;
		}
		return igsp;
	}
	
	static float[][] propagateField(float[][] igsp, int cycles){
		int[] dim = getDimensions(igsp);
		int w = dim[0];
		int h = dim[1];
		for(int c = 0; c <= cycles; c++) {
			
			for(int j = 0; j < h; j++) { // searching height, j = current y
				
				for(int i = 0; i < w; i++) { // searching width, i = current x
					
					int[][] srnd = findSurround(i, j);
					for(int a = 0; a < srnd.length; a++) {
						
						if(srnd[a][2] > 0) {
							igsp[srnd[a][0]][srnd[a][1]] = propValue(srnd[a][2], igsp[i][j]);
						}
					}
					
					
				}
				
			}
			
		}
		return igsp;
	}
	
	static float propValue(int td, float ivalue) { //value from propagation, td: total distance (x+y) ivalue = initial value
		float propd = 0.8f; //propagation constant for direct cell contact
		float propi = 0.6f; //propagation constant for indirect cell contact
		float tmp = 0;
		if(td == 1) {
			tmp = (ivalue*propd);
		} else if(td == 2) {
			tmp = (ivalue*propi);
		} else if(td == -1) {
			tmp = -1;
		} else {
			System.out.println("tell will he sucks ass at coding");
		}
		return tmp;
	}
	/* 0 1 2 Array values of relCoords in findSurround
	   7   3 Format of the array y is 0 - X 1 - Y 2 - TD
	   6 5 4 
	 */
	static int[][] findSurround(int x, int y){
		int[][] relCoords = new int[8][3];
		int[] r0 = {x-1, y-1, 2};
		relCoords[0] = r0;
		int[] r1 = {x, y-1, 1};
		relCoords[1] = r1;
		int[] r2 = {x+1, y-1, 2};
		relCoords[2] = r2;
		int[] r3 = {x+1, y, 1};
		relCoords[3] = r3;
		int[] r4 = {x+1, y+1, 2};
		relCoords[4] = r4;
		int[] r5 = {x, y+1, 1};
		relCoords[5] = r5;
		int[] r6 = {x-1, y+1, 2};
		relCoords[6] = r6;
		int[] r7 = {x-1, y, 1};
		relCoords[7] = r7;
		for(int i = 0; i < 8; i++) {
			
			if (relCoords[i][0] < 0 || relCoords[i][1] < 0) {
				relCoords[i][2] = -1;
				
			}
			
		}
		return relCoords;
		
	}
	
	
}
//













