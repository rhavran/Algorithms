import java.util.Objects;

public class MaxInterval extends Interval {
    private long max;

    public MaxInterval(Interval interval) {
        this(interval.getStart(), interval.getEnd());
    }

    public MaxInterval(long start, long end) {
        super(start, end);
        max = end;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MaxInterval that = (MaxInterval) o;
        return max == that.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), max);
    }

    @Override
    public String toString() {
        return "MaxInterval{" +
                "super=" + super.toString() +
                "max=" + max +
                '}';
    }
}
