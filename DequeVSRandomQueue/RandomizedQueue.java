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

	public void enqueue(Item item) {
		checkItem(item);
		checkItemsSize();
		addLast(item);
	}

	public Item dequeue() {
		// delete and return a random item
		checkListNotEmpty();
		return removeRandomItem();
	}

	public Item sample() {
		// return (but do not delete) a random item
		checkListNotEmpty();
		return getRandomItem();
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomizedQueueIterator();
	}

	public static void main(String[] args) {
		// unit testing
		RandomizedQueue<Integer> randomizeQueue = new RandomizedQueue<>();
		randomizeQueue.enqueue(1);
		randomizeQueue.enqueue(2);
		for (Integer item : randomizeQueue) {
//			System.out.println(item);
			System.out.println();
		}
		randomizeQueue.enqueue(3);
		for (Integer item : randomizeQueue) {
//			System.out.println(item);
			System.out.println();
		}
		randomizeQueue.enqueue(4);
		randomizeQueue.enqueue(5);
//		System.out.println(randomizeQueue.size());
//		System.out.println();
		for (Integer item : randomizeQueue) {
//			System.out.println(item);
			System.out.println();
		}
	}
	

	/*
	 * 
	 * ========================== Helper Methods ================================
	 * 
	 */
	
	private void resize(int capacity) {
    	Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }
    
    private void swapItemsAtIndices(int i, int j) {
    	Item aux;
    	aux = items[i];
    	items[i] = items[j];
    	items[j] = aux;
    }
    
    private void addLast(Item item) {
    	System.out.println("N= " + N);
    	items[N++] = item;
    }
    
	private Item getRandomItem() {
		int sampleIndex = StdRandom.uniform(N);
		return items[sampleIndex];
	}
    
    private Item removeRandomItem() {
		int indexToDelete = StdRandom.uniform(N);
		Item toDeleteValue = items[indexToDelete];
		
		removeItemAt(indexToDelete);
		
		return toDeleteValue;
	}
	
    private void removeItemAt(int indexToDelete) {
    	int indexOfLastElement = N - 1;
    	swapItemsAtIndices(indexToDelete, indexOfLastElement);
    	items[indexOfLastElement] = null;
    	N--;
    }
    
    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }
    
    private void checkListNotEmpty() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Empty list");
        }
    }
    
    private boolean checkListFull() {
    	return items.length == N;
    }
    
    private void checkItemsSize() {
    	if (checkListFull()) {
			resize(items.length * 2);
		}
    }
    
    
    /*
     * 
     * ========================== Iterator class =============================
     * 
     */
    
    private class RandomizedQueueIterator implements Iterator<Item> {
    	private Item[] iteratorItems;
    	private int current;
    	public RandomizedQueueIterator() {
    		iteratorItems = items;
    		StdRandom.shuffle(iteratorItems);
    		current = 0;
    	}
    	
		@Override
		public boolean hasNext() {
			return current < N;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			for (int i = 0; i < iteratorItems.length; i++) {
				System.out.print(iteratorItems[i] + " ");
			}
//			System.out.println("din it: " + current + " " + iteratorItems[current]);
			return iteratorItems[current++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}