import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MaxIntervalTreeNode {

    @NotNull
    private MaxInterval val;

    @Nullable
    private MaxIntervalTreeNode right;

    @Nullable
    private MaxIntervalTreeNode left;

    public MaxIntervalTreeNode(@NotNull MaxInterval val) {
        this.val = val;
    }

    @NotNull
    public MaxInterval getVal() {
        return val;
    }

    @Nullable
    public MaxIntervalTreeNode getRight() {
        return right;
    }

    public void setRight(@Nullable MaxIntervalTreeNode right) {
        this.right = right;
    }

    @Nullable
    public MaxIntervalTreeNode getLeft() {
        return left;
    }

    public void setLeft(@Nullable MaxIntervalTreeNode left) {
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaxIntervalTreeNode that = (MaxIntervalTreeNode) o;
        return Objects.equals(val, that.val) &&
                Objects.equals(right, that.right) &&
                Objects.equals(left, that.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, right, left);
    }
}
