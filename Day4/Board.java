public class Board {
    private int[][][] brd;
    private boolean completed;

    public Board() {
        brd = new int[5][5][2];
        completed = false;
    }

    public void add(int x, int y, int val) {
        brd[x][y][0] = val;
        brd[x][y][1] = 0;
    }

    public void mark(int val) {
        for(int y=0;y<5;y++) {
            for(int x=0;x<5;x++) {
                if(brd[x][y][0]==val) brd[x][y][1] = 1;
            }
        }
    }

    private boolean checkRows() {
        for(int y=0;y<5;y++) {
            int count = 0;
            for(int x=0;x<5;x++) {
                if(brd[x][y][1]==1) count++;
            }
            if(count==5) return true;
        }
        return false;
    }

    private boolean checkColumns() {
        for(int x=0;x<5;x++) {
            int count = 0;
            for(int y=0;y<5;y++) {
                if(brd[x][y][1]==1) count++;
            }
            if(count==5) return true;
        }
        return false;
    }

    public boolean checkTable() {
        if(completed) return false;

        if(checkRows() || checkColumns()) {
            completed = true;
            return true;
        } else {
            return false;
        }
    }

    public void print() {
        for(int y=0;y<5;y++) {
            for(int x=0;x<5;x++) {
                System.out.print(brd[x][y][0]);
                if(brd[x][y][1]==1) System.out.print("!");
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public int sumOfUnmarked() {
        int sum = 0;
        for(int y=0;y<5;y++) {
            for(int x=0;x<5;x++) {
                if(brd[x][y][1]==0) sum += brd[x][y][0];
            }
        }
        return sum;
    }
}
