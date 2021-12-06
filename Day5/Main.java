import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Line> lines = new ArrayList<>();
        short[][] mapa = new short[1000][1000];

        try {
            File myObj = new File("input");
            Scanner myReader = new Scanner(myObj);

            int h=0,v=0,d=0,o=0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().replace(',',' ').replace(" -> ", " ");
                String values[] = line.split(" ");
                Line l = new Line(Integer.valueOf(values[0]), Integer.valueOf(values[1]),Integer.valueOf(values[2]),Integer.valueOf(values[3]));
                lines.add(l);
                if(l.isHorizontal()) h++;
                if(l.isVertical()) v++;
                if(l.isDiagonal()) d++;
                if(!l.isVertical() && !l.isHorizontal() && !l.isDiagonal()) o++;
            }
            System.out.println("We have "+ lines.size() + " lines.");
            System.out.println("Horizontals: "+ h);
            System.out.println("Verticals: "+ v);
            System.out.println("Diagonals: "+ d);
            System.out.println("Other: "+ o);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        for(Line l : lines) {
            if(l.isHorizontal()) {
                for(int i=Math.min(l.getX1(),l.getX2()); i<=Math.max(l.getX1(),l.getX2()); i++) {
                    mapa[i][l.getY1()]++;
                }
                continue;
            }
            if(l.isVertical()) {
                for(int i=Math.min(l.getY1(),l.getY2()); i<=Math.max(l.getY1(),l.getY2()); i++) {
                    mapa[l.getX1()][i]++;
                }
                continue;
            }
            if(l.isDiagonal()) {
                int zacatekX = l.getX1();
                int zacatekY = l.getY1();
                int krokX, krokY;
                krokX = (l.getX1()<l.getX2()) ? 1 : (-1);
                krokY = (l.getY1()<l.getY2()) ? 1 : (-1);
                for(int i=0; i<=Math.abs(l.getX1()-l.getX2());i++) {
                    mapa[zacatekX+(i*krokX)][zacatekY+(i*krokY)]++;
                }
            }
        }

        int points = 0;

        for(int y=0; y<1000; y++) {
            for (int x = 0; x < 1000; x++) {
                if(mapa[x][y]>1) points++;
            }
        }

        System.out.println(points);
    }
}
