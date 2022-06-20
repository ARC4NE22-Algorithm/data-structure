import java.util.Arrays;
import java.util.Comparator;

class Heap<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private final Comparator comparator;

    private int size = 0;
    private Object[] elements;

    /**
     * Create a Heap (default Capacity, default comparator)
     */
    public Heap() {
        this(DEFAULT_CAPACITY, null);
    }

    /**
     * Create a Heap (new capacity, default comparator)
     * @param capacity new initial capacity
     */
    public Heap(int capacity) {
        this(capacity, null);
    }

    /**
     * Create a Heap (default capacity, new comparator)
     * @param comparator new comparator
     */
    public Heap(Comparator<? extends E> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    /**
     * Create a Heap (new capacity, new comparator)
     * @param capacity new initial capacity
     * @param comparator new comparator
     */
    public Heap(int capacity, Comparator<? extends E> comparator) {
        this.elements = new Object[capacity];
        this.comparator = comparator;
    }

    /**
     * Get parent's index
     * @param idx Index value of self
     * @return Index value of parent
     */
    private int getParentIdx(int idx) {
        return (idx - 1) >> 1;
    }

    /**
     * Get parent's value
     * @param idx Index value of self
     * @return Value of parent
     */
    private E getParent(int idx) {
        return (E) elements[getParentIdx(idx)];
    }

    /**
     * Get left child's index
     * @param idx Index value of self
     * @return Index value of left child
     */
    private int getLeftChildIdx(int idx) {
        return idx * 2 + 1;
    }

    /**
     * Get left child's value
     * @param idx Index value of self
     * @return Value of left child
     */
    private E getLeftChild(int idx) {
        return (E) elements[getLeftChildIdx(idx)];
    }

    /**
     * Get right child's index
     * @param idx Index value of self
     * @return Index value of right child
     */
    private int getRightChildIdx(int idx) {
        return this.getLeftChildIdx(idx) + 1;
    }

    /**
     * Get right child's value
     * @param idx Index value of self
     * @return Value of right child
     */
    private E getRightChild(int idx) {
        return (E) elements[getRightChildIdx(idx)];
    }

    private void resize() {
        int newCapacity = 0;
        int oldCapacity = elements.length;

        if(oldCapacity < 72) newCapacity = oldCapacity + (oldCapacity >> 1);
        else newCapacity = 2 * oldCapacity;
        this.resize(newCapacity);
    }

    private void resize(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void add(E e) {

    }

    /**
     * Get root value
     * @return
     */
    public E peek() {
        if(this.isEmpty()) return null;
        else return (E) elements[0];
    }

    public E poll() {
        if(this.isEmpty()) return null;
        return null;
    }

    /**
     * Get size of heap
     * @return Size of heap
     */
    public int size() {
        return this.size;
    }

    /**
     * Check whether the heap is empty or not
     * @return If size == 0, return true. Else return false.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Make new element's array
     * @return Array of elements.
     */
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }
}
