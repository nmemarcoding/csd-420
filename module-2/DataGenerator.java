import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        String filename = "nimamemarzadehdatafile.dat"; // Replace with your name
        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        Random random = new Random();

        // Generate random integer array
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(100); // Generates random integers between 0 and 99
        }

        // Generate random double array
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = random.nextDouble() * 100; // Generates random doubles between 0.0 and 99.99...
        }

        // Write data to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Write integer array
            for (int i = 0; i < intArray.length; i++) {
                writer.write(Integer.toString(intArray[i]));
                if (i < intArray.length - 1) {
                    writer.write(","); // Use comma as a separator
                }
            }
            writer.newLine();

            // Write double array
            for (int i = 0; i < doubleArray.length; i++) {
                writer.write(Double.toString(doubleArray[i]));
                if (i < doubleArray.length - 1) {
                    writer.write(","); // Use comma as a separator
                }
            }
            writer.newLine();

            System.out.println("Data written to " + filename);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
