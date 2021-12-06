import java.util.Arrays;

public class Fish {
    private long fish[];

    public Fish(int...i) {
        fish = new long[10];
        Arrays.stream(i).forEach(a -> fish[a]++);
    }

    public void iterate() {
        fish[9] = fish[0];
        for(int i=0; i<9;i++) fish[i] = fish[i+1];
        fish[6] += fish[9];
        fish[9]=0;
    }

    public long count() {
        return Arrays.stream(fish).sum();
    }
}
