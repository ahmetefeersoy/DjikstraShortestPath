
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;



public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }


    public boolean isEmpty() {
        return first == null;
    }


    public int size() {
        return n;
    }


    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    public boolean contains(Item itemToFind) {
        for (Item item : this) {
            if (itemToFind.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    }



class MinPQ<T> {

    private PriorityQueue<T> priorityQueue;

    public MinPQ(Iterable<T> iterable, Comparator<T> comparator) {
        priorityQueue = new PriorityQueue<>(comparator);
        for (T item : iterable) {
            priorityQueue.offer(item);
        }
    }

    public void insert(T item) {
        priorityQueue.offer(item);
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return priorityQueue.poll();
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public int size() {
        return priorityQueue.size();
    }
}


 class UF {
    private int[] parent;

    public UF(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // Her eleman kendi kümesinin temsilcisidir (kendi köküdür).
        }
    }

    public int find(int element) {
        // Elementin kökünü bulma
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }

    public void union(int element1, int element2) {
        // İki kümenin birleştirilmesi
        int root1 = find(element1);
        int root2 = find(element2);

        if (root1 != root2) {
            parent[root1] = root2; // Bir kümenin kökü, diğer kümenin köküne bağlanır.
        }
    }

    public boolean isConnected(int element1, int element2) {
        // İki elemanın aynı kümede olup olmadığını kontrol etme
        return find(element1) == find(element2);
    }



}