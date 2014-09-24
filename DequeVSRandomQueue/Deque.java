import java.util.Iterator;

public class Deque <Item> implements Iterable <Item>{

	private class Node {
		Item value;
		Node next;
		Node prev;
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException();
			}

			Item item = current.value;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private int N;
	private Node first, last;

	public Deque() {
		N = 0;
		emptyList();
	}

	// is the deque empty?
	public boolean isEmpty() {
		return N == 0;
	}

	// return the number of items on the deque
	public int size() {
		return N;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (first == null) {
			handleEmptyList(item);
		} else {
			addFirstInNotEmptyDeque(item);
		}

		N++;
	}

	private void addFirstInNotEmptyDeque(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.next = oldFirst;
		first.prev = null;
		first.value = item;
		oldFirst.prev = first;
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (last == null) {
			handleEmptyList(item);
		} else {
			addLastInNotEmptyList(item);
		}

		N++;
	}

	private void addLastInNotEmptyList(Item item) {
		Node oldLast = last;
		last = new Node();
		last.value = item;
		last.next = null;
		last.prev = oldLast;
		oldLast.next = last;
	}

	private void handleEmptyList(Item item) {
		last = new Node();
		last.next = null;
		last.prev = null;
		last.value = item;
		first = last;
	}

	// delete and return the item at the front
	public Item removeFirst() {
		checkEmptyDeque();

		N--;
		Item item = first.value;
		// if there is only one element in the list
		if (hasOneElement()) {
			emptyList();
			return item;
		}

		first = first.next;
		first.prev = null;

		return item;
	}

	private void checkEmptyDeque() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
	}

	private boolean hasOneElement() {
		return first == last;
	}

	// delete and return the item at the end
	public Item removeLast() {
		checkEmptyDeque();

		N--;
		Item item = last.value;
		// if there is only one element in the list
		if (hasOneElement()) {
			emptyList();
			return item;
		}

		last = last.prev;
		last.next = null;

		return item;
	}

	private void emptyList() {
		first = null;
		last = null;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}


	public static void main(String[] args) {
		Deque<String> deque = new Deque<>();
		System.out.println(deque.size());
		deque.addFirst(new String("a"));
		deque.addFirst("b");
		deque.addLast("c");
		deque.addFirst("d");
		deque.addLast("e");
		for (String elem : deque) {
			System.out.print(elem + " ");
		}
		System.out.println();
		System.out.println(deque.removeLast());
		System.out.println(deque.removeLast());
		System.out.println(deque.removeLast());
		for (String elem : deque) {
			System.out.print(elem + " ");
		}
	}
}
