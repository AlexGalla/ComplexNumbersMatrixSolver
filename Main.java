package solver;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //File inputFile = new File("G:\\in.txt");
        File inputFile = new File(args[1]);
        try (Scanner scanner = new Scanner(new FileInputStream(inputFile))) {
            File outputFile = new File(args[3]);
            //File outputFile = new File("G:\\out.txt");
            try (FileWriter writer = new FileWriter(outputFile)) {

                Matrix matrix = new Matrix(scanner);
                System.out.println("The initial matrix:");
                System.out.println(matrix.toString());



                String result = matrix.solveMatrix();
                System.out.println(result);
                writer.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
