
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            int[][] cave = new int[100][100];
            File myObj = new File("input");
            Scanner myReader = new Scanner(myObj);

            // read the input file and store in an array of integers 100x100
            int count=0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for(int i=0;i<100;i++) {
                    cave[i][count] = line.charAt(i) - 48;       // getting the digit from ASCI code
                }
                count++;
            }

            int sum_risk = 0;
            ArrayList<Integer> basin_sizes = new ArrayList<>();     // list to store basin sizes

            // search for low points and calculate the size of each basin starting from the low point
            for(int y=0; y<100; y++) {
                for(int x=0; x<100; x++) {
                    if(x>0) {
                        if(cave[x][y]>=cave[x-1][y]) continue;      // continue if this is not a low point
                    }
                    if(x<99) {
                        if(cave[x][y]>=cave[x+1][y]) continue;      // continue if this is not a low point
                    }
                    if(y>0) {
                        if(cave[x][y]>=cave[x][y-1]) continue;      // continue if this is not a low point
                    }
                    if(y<99) {
                        if(cave[x][y]>=cave[x][y+1]) continue;      // continue if this is not a low point
                    }
                    // if we got here, then this is a low point
                    int risk = 1 + cave[x][y];                      // calculate the risk of this low point
                    sum_risk += risk;                               // sum
                    basin_sizes.add(evaluate_basin(cave, x,y));     // calculate the size of basin and store in a list
                }
            }

            // sort the basin sizes in order to find the three highest values
            ArrayList<Integer> sorted = (ArrayList<Integer>) basin_sizes.stream().sorted().collect(Collectors.toList());
            int result=1;

            // get three highest values and multiply them
            for(int i=sorted.size()-3;i<sorted.size();i++) {
                result *= sorted.get(i);
            }

            // print results for day 9
            System.out.println("Total risk: "+ sum_risk);
            System.out.println("Basin sizes product: "+ result);
            myReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // return the size of basin. Start recursively from a low point
    // use boolean array to mark the points that were already tested
    private static int evaluate_basin(int[][] cave, int x, int y) {
        boolean[][] checked = new boolean[100][100];
        for(int i=0;i<100;i++) {
            for(int j=0; j<100; j++) {
                checked[j][i]=false;
            }
        }
        checked[x][y]=true;
        test_neighbours(cave, checked, x, y);
        int count = 0;
        for(int i=0;i<100;i++) {
            for(int j=0; j<100; j++) {
                if(checked[j][i]) count++;
            }
        }
        return count;
    }

    // recursive function to identify neighbours that belong to the same basin
    // check if neighbour exists and if it was already tested or not using the empty array
    private static void test_neighbours(int[][] cave, boolean[][] checked, int x, int y) {
        if(x>0 && cave[x][y] < cave[x-1][y] && cave[x-1][y] != 9 && !checked[x-1][y]) {
                checked[x - 1][y] = true;
                test_neighbours(cave, checked, x-1, y);
        }
        if(x<99 && cave[x][y] < cave[x+1][y] && cave[x+1][y] != 9 && !checked[x+1][y]) {
                checked[x + 1][y] = true;
                test_neighbours(cave, checked, x+1, y);
        }
        if(y>0 && cave[x][y] < cave[x][y-1] && cave[x][y-1] != 9 && !checked[x][y-1]) {
                checked[x][y-1] = true;
                test_neighbours(cave, checked, x, y-1);
        }
        if(y<99 && cave[x][y] < cave[x][y+1] && cave[x][y+1] != 9 && !checked[x][y+1]) {
                checked[x][y+1] = true;
                test_neighbours(cave, checked, x, y+1);
        }
    }
}
