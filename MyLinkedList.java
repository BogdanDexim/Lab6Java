import java.util.*;

/**
 * Клас, що представляє двозв'язний список.
 */
class MyLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Внутрішній клас для представлення вузлів списку
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
        }
    }

    // Порожній конструктор
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Конструктор, що приймає один об'єкт
    public MyLinkedList(E element) {
        this();
        add(element);
    }

    // Конструктор, що приймає колекцію
    public MyLinkedList(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> current = nodeAt(index);
        return current.data;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> current = nodeAt(index);
        E oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (index == size) {
            add(element);
        } else {
            Node<E> newNode = new Node<>(element);
            Node<E> current = nodeAt(index);
            Node<E> prevNode = current.prev;

            if (prevNode == null) {
                head = newNode;
            } else {
                prevNode.next = newNode;
                newNode.prev = prevNode;
            }
            newNode.next = current;
            current.prev = newNode;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> current = nodeAt(index);
        E removedData = current.data;
        Node<E> prevNode = current.prev;
        Node<E> nextNode = current.next;

        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.next = nextNode;
        }

        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prev = prevNode;
        }

        size--;
        return removedData;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Node<E> current = head; current != null; current = current.next) {
            if (current.data.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    // Допоміжний метод для отримання вузла за індексом
    private Node<E> nodeAt(int index) {
        Node<E> current;
        if (index < (size / 2)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> current = head; current != null; current = current.next) {
            if (current.data.equals(o)) {
                remove(current);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    private void remove(Node<E> node) {
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.next = nextNode;
        }

        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prev = prevNode;
        }

        size--;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            add(e);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> current = head; current != null; current = current.next) {
            result[i++] = current.data;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Node<E> current = head; current != null; current = current.next) {
            a[i++] = (T) current.data;
        }
        return a;
    }
}