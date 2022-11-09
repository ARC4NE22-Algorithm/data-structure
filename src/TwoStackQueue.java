import java.util.Stack;

public class TwoStackQueue<E> {
    private int size;
    private final Stack<E> inputStack;
    private final Stack<E> outputStack;

    public TwoStackQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public void enqueue(E element) {
        inputStack.add(element);
        size++;
    }

    public E dequeue() {
        if (size == 0) return null;

        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.add(inputStack.pop());
            }
        }

        E result = outputStack.pop();
        size--;

        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}