import java.util.ArrayList;

public class Que {
    ArrayList queue;

    public Que() {
        queue = new ArrayList<Integer>();
    }

    public void add(int i) {
        queue.add(i);
    }

    public int getNext() {
        int i = (int) queue.get(0);
        queue.remove(0);
        return i;
    }

    public boolean hasNext() {
        return queue.size()>0;
    }
}