import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class Heap<T extends Comparable<T>> {

    private Object[] heap;
    private int size = 0;
    private final Comparator<T> comparator;

    public Heap() {
        this.heap = new Object[1];
        this.comparator = Comparable::compareTo;
    }

    public Heap(int heapSize, Comparator<T> comparator) {
        this.heap = new Object[heapSize];
        this.comparator = comparator;
    }

    public Heap(Comparator<T> comparator) {
        this.heap = new Object[1];
        this.comparator = comparator;
    }

    public void offer(T object) {
        heap[size] = object;
        int currentIndex = size;
        size++;

        heapifyUp(currentIndex);
        sizeUp();
    }

    public T peek() {
        return getRoot();
    }

    public T pool() {
        T root = getRoot();
        if (root == null) {
            return null;
        }
        // Swap last item with root to keep heap without null.
        swap(0, size - 1);
        heap[size - 1] = null;
        size--;

        heapifyDown(0);
        sizeDown();

        return root;
    }

    public int size() {
        return size;
    }

    private void sizeUp() {
        if (size == heap.length) {
            Object[] newHeap = new Object[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
    }

    private void sizeDown() {
        if (size == heap.length / 4) {
            Object[] newHeap = new Object[heap.length / 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length / 2);
            heap = newHeap;
        }
    }

    private void heapifyUp(int currentIndex) {
        // If current is smaller/greater then child then swap it with parent.
        while (getParent(currentIndex) != null && comparator.compare(getCurrent(currentIndex), getParent(currentIndex)) < 0) {
            swap(currentIndex, getParentIndex(currentIndex));
            currentIndex = getParentIndex(currentIndex);
        }
    }

    private void heapifyDown(int fromIndex) {
        int currentIndex = fromIndex;
        boolean isBrokenHeap = true;
        while (currentIndex < size && isBrokenHeap) {
            T leftItem = getLeftChild(currentIndex);
            T rightItem = getRightChild(currentIndex);
            if (leftItem == null && rightItem == null) {
                break;
            }

            // If right is smaller/greater then left  then compare
            if ((leftItem != null && rightItem == null)
                    || comparator.compare(leftItem, rightItem) < 0) {
                if (comparator.compare(leftItem, getCurrent(currentIndex)) < 0) {
                    swap(currentIndex, getLeftIndex(currentIndex));
                    currentIndex = getLeftIndex(currentIndex);
                } else {
                    isBrokenHeap = false;
                }
            } else {
                if (comparator.compare(rightItem, getCurrent(currentIndex)) < 0) {
                    swap(currentIndex, getRightIndex(currentIndex));
                    currentIndex = getRightIndex(currentIndex);
                } else {
                    isBrokenHeap = false;
                }
            }
        }
    }

    @Nullable
    private T getRoot() {
        return (T) heap[0];
    }

    @Nullable
    private T getParent(int i) {
        if (i - 1 < 0) {
            return null;
        }
        return (T) heap[getParentIndex(i)];
    }

    @NotNull
    private T getCurrent(int i) {
        return (T) heap[i];
    }

    @Nullable
    private T getLeftChild(int i) {
        if ((getLeftIndex(i)) >= heap.length) {
            return null;
        }
        return (T) heap[getLeftIndex(i)];
    }

    @Nullable
    private T getRightChild(int i) {
        if ((getRightIndex(i)) >= heap.length) {
            return null;
        }
        return (T) heap[getRightIndex(i)];
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return i * 2 + 1;
    }

    private int getRightIndex(int i) {
        return i * 2 + 2;
    }

    private void swap(int i, int j) {
        Object cur = heap[i];
        heap[i] = heap[j];
        heap[j] = cur;
    }

    public static void main(String[] args) {
        int MAX = 1000;
        Heap<Integer> maxHeap = new Heap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        long startMillis = System.currentTimeMillis();
        System.out.println("Start insert: " + startMillis);
        for (int i = 0; i < MAX; i++) {
            maxHeap.offer(i);
        }
        System.out.println("End  insert: " + (System.currentTimeMillis() - startMillis));

        Integer temp = MAX;
        while (maxHeap.size() > 0) {
            startMillis = System.currentTimeMillis();
            System.out.println("Start pool: " + startMillis);
            Integer remove = maxHeap.pool();
            if (remove + 1 != temp) {
                System.out.println("Error: " + remove);
                break;
            }
            temp = remove;
            System.out.println("System Out " + remove);
            System.out.println("End  pool: " + (System.currentTimeMillis() - startMillis));
        }
    }
}
