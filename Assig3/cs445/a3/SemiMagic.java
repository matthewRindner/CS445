package cs445.a3;

import java.util.*;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.FileNotFoundException;

public class SemiMagic {
	static int size = 0; 
	static ArrayList<Integer> permNumsArr = new ArrayList<Integer>(size);
   

    public static boolean isFullSolution(int[][] square) {
        // TODO: Complete this method
        //this checks whether each row and col totals to the magic num of n(n^2+1)/2

    	int n = square.length;
    	int magicNum = (n*((int)Math.pow(n,2) + 1))/2;
    	int sumRow;
    	int sumCol;

    	for (int row = 0; row < square.length; row++) 
    		{
    			sumRow = 0;
            	for (int col = 0; col < square[row].length; col++) 
           		{
                	sumRow = sumRow + square[row][col];
           		}
       			if(sumRow != magicNum)
       			{
   					return false;
   				}
       		}
       	for (int row = 0; row < square.length; row++) 
    		{
    			sumCol = 0;
            	for (int col = 0; col < square[row].length; col++) 
           		{
                	sumCol = sumCol + square[col][row];
           		}
           		if(sumCol != magicNum)
           		{
       				return false;
       			}
       		}

       	return true;

    }

    public static boolean reject(int[][] square) {
       	// TODO: Complete this method
      	//this checks if numbers [1,n^2] have been used more than once
    	//if the frequency of a number is 2, reject returns true
       int freqOfNum = 0;
       for(int num = 1; num < Math.pow(square.length, 2); num++)
       {
       		for (int row = 0; row < square.length; row++) 
    		{
            	for (int col = 0; col < square[row].length; col++) 
           		{

                	if(square[row][col] == num)
                		freqOfNum++;
                	if(freqOfNum == 2 )
                		return true;
           		}
       		}
       		freqOfNum = 0;
       	}
    
		
    	return false;
    }

    public static int[][] extend(int[][] square) {
        // TODO: Complete this method
		//will set the next zero to a number that is higher than nextSemiSmallNum

    	
    	int[][] newSquare = copy2DArray(square);

        int nextSemiSmallNum = 1;
        for (int row = 0; row < newSquare.length; row++) 
    	{
            for (int col = 0; col < newSquare[row].length; col++) 
            {
            	if(newSquare[row][col] == nextSemiSmallNum)
                	nextSemiSmallNum++;

                if(newSquare[row][col] == 0)
                {
                	newSquare[row][col] = nextSemiSmallNum;
                	return newSquare;
                }
                
            }
        }
        

        return null;
    }
    
    public static int[][] next(int[][] square) {
        // TODO: Complete this method

    	int[][] newSquare = copy2DArray(square);
    	
    	int rowLastPos = 0;
    	int colLastPos = 0;

    	for (int row = 0; row < newSquare.length; row++) 
    	{
            for (int col = 0; col < newSquare[row].length; col++) 
            {
            	if(newSquare[row][col] == 0)
            	{
            		if(newSquare[rowLastPos][colLastPos] < Math.pow(newSquare.length, 2))
            		{
            			newSquare[rowLastPos][colLastPos]++;
                  /*
                  if(!permNumsArr.contains(newSquare[rowLastPos][colLastPos]++))
                  {
                    newSquare[rowLastPos][colLastPos]++;
                    return newSquare;
                  }
                  */
            			return newSquare;
            		}
            		//return null; 
            	}
            	else if(!permNumsArr.contains(newSquare[row][col]) && newSquare[row][col]!= 0)	//!permNums.contains(newSquare[row][col])
            	{
            		rowLastPos = row;
            		colLastPos = col;
            	}
            }
      	}
      	if(newSquare[rowLastPos][colLastPos] < Math.pow(newSquare.length, 2))
      	{
      		newSquare[newSquare.length-1][newSquare.length-1]++;	//the final index in the 2-d array
      		return newSquare;
      	}
      
        return null;
        
    }
   
    private static int[][] copy2DArray(int[][] oldSquare)
    {
    	int[][] newSquare = new int[oldSquare.length][oldSquare.length];
      	for (int row = 0; row < oldSquare.length; row++) 
		{
        	for (int col = 0; col < oldSquare[row].length; col++) 
       		{
       			newSquare[row][col] = oldSquare[row][col];
       		}
       	}
        return newSquare;
    }
    
    static void testIsFullSolution() {
        // TODO: Complete this method
        System.out.println("test IsFullSolution()");

        //test case 1, should return false since nothing = 15
        int [][] testIsFullSolution_1 = new int [][] { 	{5, 6, 8},
        												{4, 9, 1},
        												{7, 3, 2}, }; 
       	printSquare(testIsFullSolution_1);
        boolean result_1 = isFullSolution(testIsFullSolution_1);
        System.out.println("result of isFullSolution() on testIsFullSolution_1 is: " + result_1);
        System.out.println();

        //test case 2, should return true since everything  = 15
        int [][] testIsFullSolution_2 = new int [][] { 	{2, 7, 6},
        												{9, 5, 1},
        												{4, 3, 8}, }; 
       	printSquare(testIsFullSolution_2);
        boolean result_2 = isFullSolution(testIsFullSolution_2);
        System.out.println("result of isFullSolution() on testIsFullSolution_2 is: " + result_2);
        System.out.println();


        System.out.println("--------------end of testIsFullSolution-----------------");

    }
	
    static void testReject() {
        // TODO: Complete this method
        System.out.println("test reject()");

        //test case 1, should return true since there are dupes (#2)
        int [][] testReject_1 = new int [][] { 	{2, 2, 2},
												{2, 2, 2},
												{2, 2, 2}, }; 
		printSquare(testReject_1);
        boolean result_1 = reject(testReject_1);
        System.out.println("result of reject() on testReject_1 is: " + result_1);
        System.out.println();

        //test case 2, should return false since no dupes, semi-perfect square
        int [][] testReject_2 = new int [][] { 	{2, 7, 6},
												{9, 5, 1},
												{4, 3, 8}, }; 
       	printSquare(testReject_2);
        boolean result_2 = reject(testReject_2);
        System.out.println("result of reject() on testReject_2 is: " + result_2);
        System.out.println();

        //test case 3, should return true because dupes
        int [][] testReject_3 = new int [][] { 	{2, 0, 3},
												{9, 2, 1},
												{3, 3, 2}, }; 
       	printSquare(testReject_3);
        boolean result_3 = reject(testReject_3);
        System.out.println("result of reject() on testReject_3 is: " + result_3);
        System.out.println();

        //test case 4, should true since its not possible to solve without dupes
        int [][] testReject_4 = new int [][] { 	{2, 3},
        										{3, 2}, }; 
       	printSquare(testReject_4);
        boolean result_4 = reject(testReject_4);
        System.out.println("result of reject() on testReject_4 is: " + result_4);
        System.out.println();

        //test case 5, should false since dupes are present
        int [][] testReject_5 = new int [][] { 	{1, 2, 3, 4},
        										{5, 11, 7, 8}, 
        										{9, 10, 11, 12},
        										{11, 14, 15, 16}, }; 
       	printSquare(testReject_5);
        boolean result_5 = reject(testReject_5);
        System.out.println("result of reject() on testReject_5 is: " + result_5);
        System.out.println();

       	System.out.println("--------------end of testReject-----------------");

    }

    static void testExtend() {
        // TODO: Complete this method
        //test case 1, should return 1
        int [][] testExtend_1 = new int [][] { 	{2, 6, 7},
												{0, 0, 0},
												{0, 0, 0}, }; 
       	printSquare(testExtend_1);
        int[][] testExtend_1_result = extend(testExtend_1);

        boolean answer1;
        if(testExtend_1_result[1][0] == 1)
        	answer1 = true;
        else
        	answer1 = false;

        System.out.println("result of extends() on testExtend_1 is: " + answer1);
        printSquare(testExtend_1_result);
        System.out.println();

        //test case 2, should return 7
        int [][] testExtend_2 = new int [][] { 	{1, 2, 3},
												{4, 5, 6},
												{0, 0, 0}, }; 
       	printSquare(testExtend_2);
        int[][] testExtend_2_result = extend(testExtend_2);

        boolean answer2;
        if(testExtend_2_result[2][0] == 7)
        	answer2 = true;
        else
        	answer2 = false;

        System.out.println("result of extends() on testExtend_2 is: " + answer2);
        printSquare(testExtend_2_result);
        System.out.println();

        //test case 3, should return 1
        int [][] testExtend_3 = new int [][] { 	{9, 0, 0},
                        												{0, 0, 0},
                        												{0, 0, 0}, }; 

       	printSquare(testExtend_3);
        int[][] testExtend_3_result = extend(testExtend_3);

         boolean answer3;
        if(testExtend_3_result[0][1] == 1)
        	answer3 = true;
        else
        	answer3 = false;

        System.out.println("result of extends() on testExtend_3 is: " + answer3);
        printSquare(testExtend_3_result);
        System.out.println();

        //test case 4, should return 1
        int [][] testExtend_4 = new int [][] { 	{9, 8, 7},
												{6, 5, 4},
												{3, 2, 0}, }; 
       	printSquare(testExtend_4);
        int[][] testExtend_4_result = extend(testExtend_4);

         boolean answer4;
        if(testExtend_4_result[2][2] == 1)
        	answer4 = true;
        else
        	answer4 = false;

        System.out.println("result of extends() on testExtend_4 is: " + answer4);
        printSquare(testExtend_4_result);
        System.out.println();

        System.out.println("-----------end of testExtend-----------------");
    }

    static void testNext() {
        // TODO: Complete this method
        //test case 1, should return 4 where 3 currently is
        int [][] testNext_1 = new int [][] { 	{2, 6, 7},
												{9, 5, 3},
												{0, 0, 0}, }; 
       	printSquare(testNext_1);
        int[][] testNext_1_result = next(testNext_1);

        boolean answer1;
        if(testNext_1_result != null)
        	answer1 = true;
        else
        	answer1 = false;

        System.out.println("result of reject() on testNext_1 is: " + answer1);
        printSquare(testNext_1_result);
        System.out.println();

       	//test case 2, should return error 
        int [][] testNext_2 = new int [][] { 	{2, 6, 7},
												{8, 5, 3},
												{4, 1, 9}, }; 
       	printSquare(testNext_2);
        int[][] testNext_2_result = next(testNext_2);
        System.out.println("result of reject() on testNext_2 is: ");
        printSquare(testNext_2_result);
        System.out.println();

     	System.out.println("-----------end of testReject-----------------");

    }

    /**
     * Returns a string representation of a number, padded with enough zeros to
     * align properly for the current size square.
     * @param num the number to pad
     * @param size the size of the square that we are padding to
     * @return the padded string of num
     */
    static String pad(int num, int size) {
        // Determine the max value for a square of this size
        int max = size * size;	//gets range, n^2
        // Determine the length of the max value
        int width = Integer.toString(max).length();	//get n
        // Convert num to string
        String result = Integer.toString(num);
        // Pad string with 0s to the desired length
        while (result.length() < width) {
            result = " " + result;
        }
        return result;
    }

    /**
     * Prints a square
     * @param square the square to print
     */
    public static void printSquare(int[][] square) {
        if (square == null) {
            System.out.println("Null (no solution)");
            return;
        }
        int size = square.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(pad(square[i][j], size) + " ");
            }
            System.out.print("\n");
        }
        //System.out.print("\n");
    }

    /**
     * Reads a square of a specified size from a plaintext file
     * @param filename the name of the file to read from
     * @param size the size of the square in the file
     * @return the square
     * @throws FileNotFoundException if the named file is not found
     */
    public static int[][] readSquare(String filename, int size)			
                throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int[][] square = new int[size][size];
        int val = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                square[i][j] = scanner.nextInt();
            }
        }
        return square;
    }

    /**
     * Solves the magic square
     * @param square the partial solution
     * @return the solution, or null if none
     */
    public static int[][] solve(int[][] square) {			
        if (reject(square)) return null;
        if (isFullSolution(square)) return square;
        int[][] attempt = extend(square);   

        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);

        }
        
        return null;
    }

    public static void main(String[] args) {
    	
        long startTime = System.currentTimeMillis();

        if (args.length >= 1 && args[0].equals("-t")) {
            System.out.println("Running tests...");
            testIsFullSolution();	//success
            testReject();			//success
            testExtend();			//success
            testNext();				//success
        } else if (args.length >= 1) {
            try {
                // First get the specified size
                int size = Integer.parseInt(args[0]);

                int[][] square;

                //int n = square.length;
              	//int[] permNums = new int[(int)Math.pow(n,2) - 1]; 
                if (args.length >= 2) {
                    // Read the square from the file
                    square = readSquare(args[1], size);

                    //code that I the student added in create an arr of permNums, it fills the permNums arr in readSquare()
                    
    				for (int i = 0; i < square.length; i++) {
           				for (int j = 0; j < square[i].length; j++) {
	            			if(square[i][j] != 0 )
	            			{
	            				//permNumsArr[i++] = square[i][j];
	            				permNumsArr.add(square[i][j]);
	            				size++;
	            			}
	            		}
	            	}
                } else {
                    // Initialize to a blank square
                    square = new int[size][size];
                }

                System.out.println("Initial square:");
                printSquare(square);

                System.out.println("\nSolution:");
                int[][] result = solve(square);
                printSquare(result);

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println();
                System.out.println(duration/60000 + " minutes long");   // 60000ms = 1minute 
                System.out.println(TimeUnit.MILLISECONDS.toMinutes(duration));


            } catch (NumberFormatException e) {
                // This happens if the first argument isn't an int
                System.err.println("First argument must be the size");
            } catch (FileNotFoundException e) {
                // This happens if the second argument isn't an existing file
                System.err.println("File " + args[1] + " not found");
            }
        } else {
            System.err.println("See usage in assignment description");
        }
    }
}

