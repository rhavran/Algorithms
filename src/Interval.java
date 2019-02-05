import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Interval implements Comparable<Interval> {
    private long start;
    private long end;

    public Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public int compareTo(@NotNull Interval o) {
        if (this.start < o.start) {
            return -1;
        }

        if (this.start == o.start) {
            if (this.end < o.end) {
                return -1;
            } else if (this.end == o.end) {
                return 0;
            }
        }

        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return start == interval.start &&
                end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public static void main(String[] args) {
        Interval a = new Interval(0, 1);
        Interval b = new Interval(0, 2);
        System.out.println("a " + compareRes(a.compareTo(b)) + " b");

        a = new Interval(0, 1);
        b = new Interval(0, 1);
        System.out.println("a " + compareRes(a.compareTo(b)) + " b");

        a = new Interval(0, 2);
        b = new Interval(0, 1);
        System.out.println("a " + compareRes(a.compareTo(b)) + " b");
    }

    private static String compareRes(int res) {
        if (res < 0) {
            return "smaller";
        } else if (res > 0) {
            return "bigger";
        } else {
            return "equal";
        }
    }
}
