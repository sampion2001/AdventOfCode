import java.util.*;

public class KnownNumbers {
    HashSet<KnownNumber> kn;

    public KnownNumbers() {
        kn = new HashSet<>();
    }

    public void addNumber(KnownNumber n) {
        kn.add(n);
    }

    public boolean isKnown(String s) {
        for(KnownNumber n : kn) {
            if(n.equals(s)) return true;
        }
        return false;
    }

    public boolean isKnown(int i) {
        for(KnownNumber n : kn) {
            if(n.getValue()==i) return true;
        }
        return false;
    }

    public int getNumberValue(String s) {
        for(KnownNumber n : kn) {
            if(n.equals(s)) return n.getValue();
        }
        return (-1);
    }

    private boolean isNumberContainedInString(String s, int i) {
        KnownNumber pokus = new KnownNumber(-1, s);
        for(KnownNumber n : this.kn) {
            if(n.getValue()==i) {
                if(pokus.contains(n)) return true;
            }
        }
        return false;
    }

    public int size() {
        return kn.size();
    }

    public void tryRecognize(String[] values) {
        for(String v : values) {
            if(isKnown(v)) continue;
            if(v.length()==2) { addNumber(new KnownNumber(1,v)); continue; }
            if(v.length()==4) { addNumber(new KnownNumber(4,v)); continue; }
            if(v.length()==3) { addNumber(new KnownNumber(7,v)); continue; }
            if(v.length()==7) { addNumber(new KnownNumber(8,v)); continue; }
            if(v.length()==5) {
                if(isKnown(1)) {
                    if(isNumberContainedInString(v,1)) {
                        addNumber(new KnownNumber(3,v)); continue;
                    }
                }
            }
            if(v.length()==6) {
                if(isKnown(1)) {
                    if(!isNumberContainedInString(v,1)) {
                        addNumber(new KnownNumber(6,v)); continue;
                    }
                }
            }
            if(v.length()==5 && isKnown(1)) {
                if (!isNumberContainedInString(v,1) && isKnown(6) && isKnown(8)) {
                    KnownNumber pokus = new KnownNumber(-1, v);
                    KnownNumber delta = new KnownNumber(-1, deduct(8, 6));
                    if(pokus.contains(delta)) {
                        addNumber(new KnownNumber(2,v));
                        continue;
                    }
                    else {
                        addNumber(new KnownNumber(5,v));
                        continue;
                    }
                }
            }

            if(v.length()==6 && isKnown(1) && isKnown(3)) {
                if(isNumberContainedInString(v,1)) {                 // 0 OR 9
                    KnownNumber pokus = new KnownNumber(-1, v);
                    KnownNumber delta = new KnownNumber(-1, deduct(3, 1));
                    if(pokus.contains(delta)) {
                        addNumber(new KnownNumber(9,v));
                        continue;
                    }
                    else {
                        addNumber(new KnownNumber(0,v));
                        continue;
                    }
                }
            }
        }
    }

    private Set<Character> deduct(int a, int b) {
        Set<Character> x=null, y=null;
        for(KnownNumber n : this.kn) {
            if(n.getValue()==a) x = n.getSegments();
            if(n.getValue()==b) y = n.getSegments();
        }
        for(Character ch : y) x.remove(ch);
        return x;
    }
}
