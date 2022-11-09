public class Deque<E> {
    private boolean isReversed;  // false: forward, true: backward

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

    private int size;
    private Node<E> head, tail;

    /**
     * Add new element to deque
     * @param element A new element to insert into the deque
     * @param isReversed
     */
    private void add(E element, boolean isReversed)  {
        Node<E> node = new Node<E>(null, null, element);
        if(isReversed) {
            if(head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        } else {
            if(head == null) {
                head = tail = node;
            } else {
                head.prev = node;
                node.next = head;
                head = node;
            }
        }
        size++;
    }

    /**
     * Add new element to head
     * @param element A new element to insert into the deque
     */
    public void addToHead(E element) {
        add(element, this.isReversed);
    }

    /**
     * Add new element to tail
     * @param element A new element to insert into the deque
     */
    public void addToTail(E element) {
        add(element, !this.isReversed);
    }

    /**
     * Remove head element from deque
     * @param isReversed
     * @return Head element of deque
     */
    private E poll(boolean isReversed) {
        if((isReversed ? tail : head) == null)
            return null;

        final E element = isReversed ? tail.element : head.element;
        if(isReversed) {
            final Node<E> prev = tail.prev;
            tail.clear();
            tail = prev;

            if(tail == null) head = null;
            else tail.next = null;
        } else {
            final Node<E> next = head.next;
            head.clear();
            head = next;

            if(head == null) tail = null;
            else head.prev = null;
        }
        size--;
        return element;
    }

    /**
     * Remove head element from deque
     * @return Head element of deque
     */
    public E pollHead() {
        return poll(this.isReversed);
    }

    /**
     * Remove tail element from deque
     * @return Head element of deque
     */
    public E pollTail() {
        return poll(!this.isReversed);
    }

    /**
     * Get the value of deque without deleting it
     * @param isReversed
     * @return Head element of deque
     */
    private E peek(boolean isReversed) {
        if(isReversed) {
            if(tail == null) return null;
            else return tail.element;
        } else {
            if(head == null) return null;
            else return head.element;
        }
    }

    /**
     * Get the value of the head without deleting it
     * @return Head element of deque
     */
    public E peekHead() {
        return peek(this.isReversed);
    }

    /**
     * Get the value of the tail without deleting it
     * @return Tail element of deque
     */
    public E peekTail() {
        return peek(!this.isReversed);
    }

    /**
     * Get the index value of element
     * @return Index value of element (If the element does not exist in the deque, return -1)
     */
    private int getIndex(E element, boolean isReversed)  {
        int idx = 0;
        Node<E> node = isReversed ? tail : head;

        while(node != null) {
            if(element == null) {
                if(node.element == null) {
                    return idx;
                }
            } else {
                if(node.element.equals(element)) {
                    return idx;
                }
            }
            node = isReversed ? node.prev : node.next;
            idx++;
        }

        return -1;
    }

    /**
     * Get the index value of element
     * @return Index value of element (If the element does not exist in the deque, return -1)
     */
    public int indexOf(E element) {
        return getIndex(element, this.isReversed);
    }

    /**
     * Get the index value of element
     * @return Index value of element (If the element does not exist in the deque, return -1)
     */
    public int lastIndexOf(E element) {
        return getIndex(element, !this.isReversed);
    }

    /**
     * reverse the deque (slow method, O(N))
     */
    @Deprecated
    private void reverseOld() {
        if(head != null) {
            Node<E> temp, node;
            temp = head;
            node = head = tail;
            tail = temp;

            while(node != null) {
                // swap prev & tail node
                temp = node.prev;
                node.prev = node.next;
                node.next = temp;
                node = node.next;
            }
        }
    }

    /**
     * reverse the deque direction
     */
    public void reverse() {
        isReversed = !isReversed;
    }


    /**
     * If size of deque is 0, return {@code true}. Else return {@code false}.
     * @return If size of deque is 0, return {@code true}. Else return {@code false}.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return size of deque
     * @return Size of deque
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

    @Override
    public String toString() {
        Node<E> node;
        StringBuilder sb = new StringBuilder();

        if(isReversed) {
            node = tail;
            sb.append('[');
            while(node != null) {
                sb.append(node.element);
                node = node.prev;
                if(node != null) sb.append(',');
            }
            sb.append(']');
        } else {
            node = head;
            sb.append('[');
            while(node != null) {
                sb.append(node.element);
                node = node.next;
                if(node != null) sb.append(',');
            }
            sb.append(']');
        }

        return sb.toString();
    }
}