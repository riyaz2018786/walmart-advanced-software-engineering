import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {

    private final int childExponent;
    private final int childrenPerNode;
    private final ArrayList<T> heap;

    public PowerOfTwoMaxHeap(int childExponent) {
        if (childExponent < 0 || childExponent > 30) {
            throw new IllegalArgumentException("childExponent must be between 0 and 30.");
        }
        this.childExponent = childExponent;
        this.childrenPerNode = 1 << childExponent;
        this.heap = new ArrayList<>();
    }

    public void insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot insert null element.");
        }
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public T popMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }

        T maxItem = heap.get(0);
        T lastItem = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastItem);
            siftDown(0);
        }

        return maxItem;
    }

    private void siftUp(int index) {
        T target = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) >>> childExponent;
            T parent = heap.get(parentIndex);

            if (target.compareTo(parent) > 0) {
                heap.set(index, parent);
                index = parentIndex;
            } else {
                break;
            }
        }
        heap.set(index, target);
    }

    private void siftDown(int index) {
        int size = heap.size();
        T target = heap.get(index);

        while (true) {
            long firstChildLong = ((long) index << childExponent) + 1L;
            if (firstChildLong >= size) {
                break;
            }

            int firstChild = (int) firstChildLong;
            int lastChild = (int) Math.min((long) size - 1, firstChildLong + childrenPerNode - 1);

            int maxChildIndex = firstChild;
            T maxChild = heap.get(firstChild);

            for (int i = firstChild + 1; i <= lastChild; i++) {
                T candidate = heap.get(i);
                if (candidate.compareTo(maxChild) > 0) {
                    maxChild = candidate;
                    maxChildIndex = i;
                }
            }

            if (maxChild.compareTo(target) > 0) {
                heap.set(index, maxChild);
                index = maxChildIndex;
            } else {
                break;
            }
        }
        heap.set(index, target);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap<Integer> unaryHeap = new PowerOfTwoMaxHeap<>(0);
        unaryHeap.insert(10);
        unaryHeap.insert(45);
        unaryHeap.insert(23);
        System.out.println(unaryHeap.popMax());

        PowerOfTwoMaxHeap<Integer> quadHeap = new PowerOfTwoMaxHeap<>(2);
        int[] values = {15, 80, 42, 99, 7, 64, 31, 105, 50};
        for (int v : values) {
            quadHeap.insert(v);
        }

        while (!quadHeap.isEmpty()) {
            System.out.print(quadHeap.popMax() + " ");
        }
        System.out.println();
    }
}
