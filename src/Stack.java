import java.util.Arrays;

class Stack<E> {
    private final int DEFAULT_CAPACITY = 24;
    private int size;
    private int maxCapacity;
    Object[] elements;

    /**
     * Constructor of Stack
     * Initialize the array to the default capacity.
     */
    public Stack() {
        maxCapacity = DEFAULT_CAPACITY;
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor of Stack
     * Initialize the array to the argument capacity.
     */
    public Stack(int capacity) {
        maxCapacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * Resize (grow the size) the elements array
     */
    private void resize() {
        if(maxCapacity < 64) maxCapacity = maxCapacity + (maxCapacity / 2);
        else maxCapacity *= 2;
        elements = Arrays.copyOf(elements, maxCapacity);
    }

    /**
     * Append new element to Stack
     */
    public void append(E element) {
        if(size >= maxCapacity) resize();
        elements[size++] = element;
    }

    /**
     * Remove top elements from stack
     * If the size of Stack is 0, pop the top element. Else return -1
     */
    public E pop() {
        if(size > 0) return (E) elements[--size];
        else return null;
    }

    /**
     * Returns the size of stack
     * @return Size of stack
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the stack is empty or not.
     * @return Stack is empty (size == 0), return true. Else return false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get top element from stack. (Don't remove from stack)
     * @return If stack is not empty, return top element. Else return '\0'
     */
    public E top() {
        if(size > 0) return (E) elements[size - 1];
        else return null;
    }

    /**
     * Remove all elements from stack. (Set size to 0)
     */
    public void removeAll() {
        size = 0;
    }
}