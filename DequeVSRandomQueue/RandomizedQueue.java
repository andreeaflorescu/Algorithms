import java.util.Iterator;
@SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int N;
	private Item[] items;
	
	public RandomizedQueue() {
		// construct an empty randomized queue
		N = 0;
		items = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		// is the queue empty?
		return N == 0;
	}

	public int size() {
		// return the number of items on the queue
		return N;
	}
	
	// resize the underlying array holding the elements
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < items.length; i++) {
        	if (items[i] != null) {
        		temp[i] = items[i];
        	}
        }
        
        items = temp;
    }

	public void enqueue(Item item) {
		// add the item
		N++;
	}

	public Item dequeue() {
		// delete and return a random item
		N--;
		return null;
	}

	public Item sample() {
		// return (but do not delete) a random item
		return null;
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return null;
	}

	public static void main(String[] args) {
		// unit testing
	}
}