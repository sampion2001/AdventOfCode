import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
                File myObj = new File("input");
                Scanner myReader = new Scanner(myObj);
                int sum = 0;
                while (myReader.hasNextLine()) {
                    Display d = new Display(myReader.nextLine().replace(" | "," ").split(" "));
                    sum += d.outputValue();
                    d.print();
                }
                System.out.println("The total value is "+sum);
                myReader.close();
        } catch (Exception e) {
            System.out.println("PROBLEM: " + e.getMessage());
        }
    }
}
