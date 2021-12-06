import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Que q = new Que();
        ArrayList<Board> boards = new ArrayList<>();

        try {
            File myObj = new File("boards");
            Scanner myReader = new Scanner(myObj);

            String numbers[] = myReader.nextLine().split(",");
            Arrays.stream(numbers).forEach(i -> q.add(Integer.valueOf(i)));

            while (myReader.hasNextInt()) {
                Board b = new Board();
                for(int y=0;y<5;y++) {
                    for(int x=0;x<5;x++) {
                        b.add(x,y, myReader.nextInt());
                    }
                }
                boards.add(b);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        while(q.hasNext()) {
            int i = q.getNext();
            for(Board b : boards) {
                b.mark(i);
                if(b.checkTable()) {
                    b.print();
                    System.out.println();
                    System.out.println(i*b.sumOfUnmarked());
                }
            }
        }
    }
}
