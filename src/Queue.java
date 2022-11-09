public class Queue<E> {
    /**
     * Inner class Node
     * @param <E>
     */
    private class Node<E> {
        Node<E> prev, next;
        E element;

        public Node(Node<E> prev, Node<E> next, E element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }

        public void clear() {
            this.element = null;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Constructor of queue
     */
    public Queue() {
    }

    /**
     * Constructor of queue
     * @param arr Array of elements to put values in a queue
     */
    public Queue(E[] arr) {
        this.addAll(arr);
    }

    private int size;
    private Node head, tail;

    /**
     * Add new element to tail
     * @param element A new element to insert into the queue
     */
    public void enqueue(E element) {
        Node<E> node = new Node<>(null, null, element);

        if(head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    /**
     * Put all the values in the array into the queue
     * @param arr Array of elements to put values in a queue
     */
    public void addAll(E[] arr) {
        for(E element : arr) {
            enqueue(element);
        }
    }

    /**
     * Remove head element from queue
     * @return Head element of queue
     */
    public E dequeue() {
        if(head == null) return null;

        final E element = (E) head.element;
        final Node<E> next = head.next;
        head.clear();
        head = next;

        if(head == null) tail = null;
        else head.prev = null;
        size--;

        return element;
    }

    /**
     * Get the value of the head without deleting it
     * @return Head element of queue
     */
    public E peekHead() {
        if(head == null) return null;
        else return (E) head.element;
    }

    /**
     * Get the value of the tail without deleting it
     * @return Tail element of queue
     */
    public E peekTail() {
        if(tail == null) return null;
        else return (E) tail.element;
    }

    /**
     * If size of queue is 0, return {@code true}. Else return {@code false}.
     * @return If size of queue is 0, return {@code true}. Else return {@code false}.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return size of queue
     * @return Size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Remove all elements
     */
    public void removeAll() {
        Node<E> node = head;
        while(node != null) {
            Node<E> next = node.next;
            node.clear();
            node = next;
        }
        head = tail = null;
        size = 0;
    }
}