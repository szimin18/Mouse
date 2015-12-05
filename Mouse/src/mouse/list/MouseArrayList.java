package mouse.list;

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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T get(int index) throws MouseIndexOutOfBoudsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(T element) {
		// TODO Auto-generated method stub
		return false;
	}

}
