package mouse.list;

import mouse.list.exception.MouseIndexOutOfBoudsException;

public interface IMouseList<T> {
	/**
	 * Adds element at the end.
	 * 
	 * @param element
	 *            to add
	 */
	public void add(T element);

	/**
	 * Removes element if present.
	 * 
	 * @param element
	 *            to remove
	 * @return true if the element was removed, false otherwise
	 */
	public boolean remove(T element);

	/**
	 * Adds element at the given position.
	 * 
	 * @param element
	 *            to add
	 * @param index
	 *            at which to add
	 * @throws MouseIndexOutOfBoudsException
	 *             when index is negative or greater than or equals to the
	 *             collection size
	 */
	public void addAt(T element, int index)
			throws MouseIndexOutOfBoudsException;

	/**
	 * Removes element at given position.
	 * 
	 * @param index
	 * @return true if the element was removed, false otherwise
	 * @throws MouseIndexOutOfBoudsException
	 *             when index is negative or greater than or equals to the
	 *             collection size
	 */
	public boolean removeAt(int index) throws MouseIndexOutOfBoudsException;

	/**
	 * Obtains element at the given position
	 * 
	 * @param index
	 *            of element to get
	 * @return element at the given index
	 * @throws MouseIndexOutOfBoudsException
	 *             when index is negative or greater than or equals to the
	 *             collection size
	 */
	public T get(int index) throws MouseIndexOutOfBoudsException;

	/**
	 * Gets number of elements currently held by this list.
	 * 
	 * @return number of elements in this instance of list
	 */
	public int size();

	/**
	 * Checks if this list is empty.
	 * 
	 * @return true if this list is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Checks if this list contains the given element.
	 * 
	 * @param element
	 *            to be checked
	 * @return true if this list contains the given element, false otherwise
	 */
	public boolean contains(T element);
}
