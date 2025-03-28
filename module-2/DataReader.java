// Name: Nima Memarzadeh
// Date: 04/28/2025
// Assignment: M2: Programming Assignment

// Description: This program reads a file containing integer and double arrays,
//              parses the data, and displays it in a formatted manner.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class DataReader {

    public static void main(String[] args) {
        String filename = "nimamemarzadehdatafile.dat"; 
        DecimalFormat df = new DecimalFormat("0.00");

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String intLine;
            int dataSetCount = 1;
            while ((intLine = reader.readLine()) != null) {
                String doubleLine = reader.readLine();

                if (doubleLine != null) {
                    String[] intStrings = intLine.split(",");
                    String[] doubleStrings = doubleLine.split(",");

                    int[] intArray = new int[intStrings.length];
                    double[] doubleArray = new double[doubleStrings.length];

                    // Parse integer array
                    for (int i = 0; i < intStrings.length; i++) {
                        intArray[i] = Integer.parseInt(intStrings[i]);
                    }

                    // Parse double array
                    for (int i = 0; i < doubleStrings.length; i++) {
                        doubleArray[i] = Double.parseDouble(doubleStrings[i]);
                    }

                    // Display arrays
                    System.out.println("Data Set " + dataSetCount + ":");
                    System.out.print("Integer Array: ");
                    for (int i = 0; i < intArray.length; i++) {
                        System.out.print(intArray[i] + " ");
                    }
                    System.out.println();

                    System.out.print("Double Array: ");
                    for (int i = 0; i < doubleArray.length; i++) {
                        System.out.print(df.format(doubleArray[i]) + " ");
                    }
                    System.out.println("\n");

                    dataSetCount++;
                } else {
                    System.out.println("File is incomplete.");
                    break; // Exit the loop if a double line is missing
                }
            }
        } catch (IOException e) {
            // Handle file reading errors
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handle number parsing errors
            System.err.println("Error parsing numbers from file: " + e.getMessage());
        }
    }
}
