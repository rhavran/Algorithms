import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AugmentedIntervalTree {

    @Nullable
    private MaxIntervalTreeNode root;

    public void insert(@NotNull Interval val) {
        if (root == null) {
            root = new MaxIntervalTreeNode(new MaxInterval(val));
        } else {
            insert(root, new MaxInterval(val));
        }
    }

    private static void insert(@Nullable MaxIntervalTreeNode pointer, @NotNull MaxInterval val) {
        if (pointer == null) {
            return;
        }
        // Modify max value in nodes while we go.
        if (pointer.getVal().getMax() < val.getMax()) {
            pointer.getVal().setMax(val.getMax());
        }

        if (val.compareTo(pointer.getVal()) < 0) {
            MaxIntervalTreeNode left = pointer.getLeft();
            if (left != null) {
                insert(left, val);
            } else {
                pointer.setLeft(new MaxIntervalTreeNode(val));
            }
        } else {
            MaxIntervalTreeNode right = pointer.getRight();
            if (right != null) {
                insert(right, val);
            } else {
                pointer.setRight(new MaxIntervalTreeNode(val));
            }
        }
    }

    public void searchIntersection(@NotNull Interval val, @NotNull List<Interval> results) {
        if (root == null) {
            return;
        }

        searchIntersection(root, val, results);
    }

    private static void searchIntersection(@Nullable MaxIntervalTreeNode pointer, @NotNull Interval val,
                                           @NotNull List<Interval> results) {
        if (pointer == null) {
            return;
        }

        if (!(val.getStart() > pointer.getVal().getEnd() || val.getEnd() < pointer.getVal().getStart())) {
            /*results.add(new Interval(
                    Math.max(val.getStart(), pointer.getVal().getStart()),
                    Math.min(val.getEnd(), pointer.getVal().getEnd()))
            );*/
            results.add(pointer.getVal());
        }

        // If left child max is bigger then val.start then we need to check left subtree because it can have intersections.
        if (pointer.getLeft() != null && pointer.getLeft().getVal().getMax() > val.getStart()) {
            searchIntersection(pointer.getLeft(), val, results);
        }

        // Also we need to check right sub tree.
        searchIntersection(pointer.getRight(), val, results);
    }

    public static void main(String[] args) {
        AugmentedIntervalTree tree = new AugmentedIntervalTree();
        tree.insert(new MaxInterval(0, 3));
        tree.insert(new MaxInterval(0, 5));
        tree.insert(new MaxInterval(2, 15));
        tree.insert(new MaxInterval(3, 10));

        LinkedList<Interval> res = new LinkedList<>();
        tree.searchIntersection(new Interval(1, 2), res);

        for (Interval re : res) {
            System.out.println("" + re.toString());
        }
        System.out.println(tree.printTree());
    }

    public String printTree() {
        if (root == null) {
            return "{}";
        }

        StringBuilder builder = new StringBuilder("{");

        Queue<MaxIntervalTreeNode> q = new LinkedList<>();
        q.offer(root);
        MaxIntervalTreeNode node;
        while (!q.isEmpty()) {
            node = q.poll();

            builder.append("[")
                    .append(node.getVal().getStart())
                    .append(",")
                    .append(node.getVal().getEnd())
                    .append("]");

            if (node.getLeft() != null) {
                q.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                q.offer(node.getRight());
            }

            if (!q.isEmpty()) {
                builder.append(";");
            }
        }

        builder.append("}");

        return builder.toString();
    }
}
