import java.util.ArrayList;

public class Display {
    String[] values;
    KnownNumbers knownNumbers;

    public Display(String[] values) {
        this.values = values;
        knownNumbers = new KnownNumbers();
        for(int i=0;i<4;i++) knownNumbers.tryRecognize(values);
    }

    public void print() {
        for(int i=0; i<10;i++) {
            String v = values[i];
            System.out.print(knownNumbers.getNumberValue(v));
        }
        System.out.print(" | "+outputValue());
        System.out.println();
    }

    public int outputValue() {
        return  (knownNumbers.getNumberValue(values[10])*1000) +
                (knownNumbers.getNumberValue(values[11])*100) +
                (knownNumbers.getNumberValue(values[12])*10) +
                (knownNumbers.getNumberValue(values[13]));
    }
}
