package mouse.list;

import java.util.Iterator;

import mouse.list.exception.MouseIndexOutOfBoudsException;

public class MouseLinkedList<T> implements IMouseList<T> {

	private final Node head = new Node(null, null);

	private int size = 0;

	@Override
	public void add(T element) {
		getLastNode().setNext(new Node(element, null));
		size++;
	}

	private final Node getLastNode() {
		Node node = head;

		while (node.hasNext()) {
			node = node.getNext();
		}

		return node;
	}

	@Override
	public boolean remove(T element) {
		Node node = head;
		while (node.hasNext()) {
			if (node.getNext().getData().equals(element)) {
				if (node.getNext().hasNext()) {
					node.getNext().setNext(null);
					node.setNext(node.getNext().getNext());
				} else {
					node.setNext(null);
				}

				size--;
				return true;
			}

			node = node.getNext();

		}
		return false;
	}

	@Override
	public void addAt(T element, int index)
			throws MouseIndexOutOfBoudsException {
		if (index < 0 || index > size) {
			throw new MouseIndexOutOfBoudsException();
		}
		Node node = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				Node newNode = new Node(null, null);
				if (node.hasNext()) {
					newNode.setNext(node.getNext());
					node.setNext(newNode);
				} else {
					node.setNext(newNode);
				}
				size++;
				return;
			}
			node.getNext();
		}
	}

	@Override
	public boolean removeAt(int index) throws MouseIndexOutOfBoudsException {
		if (index < 0 || index >= size) {
			throw new MouseIndexOutOfBoudsException();
		}
		Node node = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				if (node.getNext().hasNext()) {
					node.setNext(node.getNext().getNext());
					node.getNext().setNext(null);
				} else {
					node.setNext(null);
				}
				size--;
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	@Override
	public T get(int index) throws MouseIndexOutOfBoudsException {
		if (index < 0 || index >= size) {
			throw new MouseIndexOutOfBoudsException();
		}

		Node node = head;

		for (int i = 0; i < index; i++) {
			node.getNext();
		}

		return node.getNext().getData();
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
	public boolean contains(T element) {
		Node node = head;
		while (node.hasNext()) {
			if (node.getNext().getData().equals(element)) {
				return true;
			}
			node = node.getNext();
		}
		return false;
	}

	private final class Node {
		private final T data;

		private Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public boolean hasNext() {
			return next != null;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}
	}

	@Override
	public void clear() {
		head.setNext(null);
		size = 0;

	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node node = head;

			@Override
			public boolean hasNext() {
				return head.hasNext();
			}

			@Override
			public T next() {
				return null;
			}

		};
	}
}
