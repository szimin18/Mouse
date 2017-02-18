package mouse.list;

import java.util.Iterator;

import mouse.list.exception.MouseIndexOutOfBoudsException;

public class MouseArrayList<T> implements IMouseList<T> {

	private int size = 0;
	private Object[] array = new Object[16];

	@Override
	public void add(T element) {
		manageFullArray();
		array[size++] = element;
	}

	private void manageFullArray() {
		if (size == array.length) {
			Object[] oldArray = array;
			array = new Object[2 * array.length];
			for (int i = 0; i < oldArray.length; i++) {
				array[i] = oldArray[i];
			}
			// array = Arrays.copyOf(array, 2 * array.length);
		}
	}

	@Override
	public boolean remove(T element) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(element)) {
				for (; i < size - 1; i++) {
					array[i] = array[i + 1];
				}
				size--;
				return true;
			}
		}

		return false;
	}

	@Override
	public void addAt(T element, int index)
			throws MouseIndexOutOfBoudsException {
		if (index < 0 || index > size) {
			throw new MouseIndexOutOfBoudsException();
		}
		manageFullArray();
		for (int i = size - 1; i >= index; i--) {
			array[i + 1] = array[i];
		}
		array[index] = element;
	}

	@Override
	public boolean removeAt(int index) throws MouseIndexOutOfBoudsException {
		if (index >= size || index < 0) {
			throw new MouseIndexOutOfBoudsException();
		}
		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		size--;
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) throws MouseIndexOutOfBoudsException {
		if (index >= size || index < 0) {
			throw new MouseIndexOutOfBoudsException();
		}
		return (T) array[index];
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
		for (int i = 0; i < size; i++) {
			if (array[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int cursor = 0;

			@Override
			public boolean hasNext() {
				return cursor != size;
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if (hasNext()) {
					return (T) array[cursor++];
				}
				throw new IllegalStateException();
			}
		};
	}
}
