import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class KnownNumber {
    private int value;
    private Set<Character> segments;

    public KnownNumber(int v, String s) {
        value = v;
        segments = convertString(s);
    }

    public KnownNumber(int v, Set<Character> s) {
        value = v;
        segments = s;
    }

    private Set<Character> convertString(String s) {
        Set<Character> seg = new HashSet<>();
        for(int i=0; i<s.length(); i++) seg.add(s.charAt(i));
        return seg;
    }

    public int getValue() {
        return value;
    }

    public Set<Character> getSegments() {
        return segments;
    }

    public boolean equals(KnownNumber k) {
        return this.segments.equals(k.segments);
    }

    public boolean equals(String s) {
        Set<Character> k = convertString(s);
        return this.segments.equals(k);
    }

    public boolean contains(KnownNumber k) {
        return this.segments.containsAll(k.segments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnownNumber that = (KnownNumber) o;
        return Objects.equals(segments, that.segments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segments);
    }
}
