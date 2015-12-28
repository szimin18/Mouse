package mouse;

import mouse.list.IMouseList;
import mouse.list.MouseArrayList;
import mouse.list.MouseLinkedList;
import mouse.list.exception.MouseIndexOutOfBoudsException;

// TODO More tests
// TODO Iterable
// TODO SkipList

public class Mouse {
	public static void main(String[] args) {
		testIMouseList(new MouseArrayList<Integer>());
		testIMouseList(new MouseLinkedList<Integer>());
	}

	private static void testIMouseList(IMouseList<Integer> list) {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(String.format("General tests on: %s", list.getClass().getSimpleName()));
		System.out.println("--------------------------------------------------");
		System.out.println();
		System.out.flush();

		performChecked(() -> {
			assertThat(list.isEmpty());
		}, "list should initially be empty");

		performChecked(() -> {
			list.add(1);
		}, "I should be able to add an element to list");

		performChecked(() -> {
			assertThat(!list.isEmpty());
		}, "after adding an element a list should not be empty");

		performChecked(() -> {
			assertThat(list.remove(1));
		}, "I should be able to remove an element from list and it should be present (remove returns true)");

		performChecked(() -> {
			assertThat(list.isEmpty());
		}, "after removing all elements list should be empty");

		performChecked(() -> {
			assertThat(!list.remove(0));
		}, "element 0 should not be present if I don't add it (remove returns false)");

		performChecked(() -> {
			assertThat(list.isEmpty());
		}, "after an attempt to remove from an empty list it should remain empty");

		performChecked(() -> {
			list.add(2);
		}, "I should be able to add 2 to a list");

		performChecked(() -> {
			assertThat(list.get(0) == 2);
		}, "a single element list should have single element at 0 index");

		performChecked(() -> {
			list.add(3);
		}, "I should be able to add 3 to a list");

		performExpecting(() -> {
			list.addAt(2, -1);
		}, MouseIndexOutOfBoudsException.class, "adding at index -1 should throw exception");

		performExpecting(() -> {
			list.addAt(2, 3);
		}, MouseIndexOutOfBoudsException.class, "adding at index 3 to a list of size 2 should throw exception");

		performExpecting(() -> {
			list.removeAt(-1);
		}, MouseIndexOutOfBoudsException.class, "removing from index -1 should throw exception");

		performExpecting(() -> {
			list.removeAt(3);
		}, MouseIndexOutOfBoudsException.class, "removing from index 3 in a list of size 2 should throw exception");

		performChecked(() -> {
			assertThat(list.size() == 2);
		}, "list [2, 3] should have size 2");

		performChecked(() -> {
			assertThat(list.contains(2));
		}, "list [2, 3] should contain 2");

		performChecked(() -> {
			assertThat(!list.isEmpty());
		}, "list [2, 3] should not be empty");

		performChecked(() -> {
			assertThat(list.removeAt(1));
		}, "I should be able to remove from index 1 from a list of size 2");

		performChecked(() -> {
			assertThat(list.contains(2));
		}, "single element list should contain it's only value");

		performChecked(() -> {
			assertThat(!list.contains(3));
		}, "list should not contain a value after removing it");

		performExpecting(() -> {
			list.addAt(4, 2);
		}, MouseIndexOutOfBoudsException.class, "adding at index 2 to a list of size 1 should throw an exception");

		performChecked(() -> {
			list.addAt(4, 1);
		}, "I should be able to add element at last index");

		performChecked(() -> {
			assertThat(list.get(0) == 2);
		}, "after adding an element to a list it should be present at certain position");

		performChecked(() -> {
			list.clear();
		}, "I should be able to clear a list");

		performChecked(() -> {
			assertThat(list.size() == 0);
		}, "after clearing a list should be empty");

		performChecked(() -> {
			for (int i = 0; i < 1000; i++) {
				list.add(1);
			}
		}, "");

		performChecked(() -> {
			assertThat(list.size() == 1000);
		}, "after adding 1000 elemtns a list should hava size 1000");
	}

	private static final void assertThat(boolean condition) {
		if (!condition) {
			throw new AssertionError("assertion failed");
		}
	}

	private static void performChecked(Action action, String message) {
		System.out.println("# Testing...");
		try {
			action.perform();
			System.out.println("Test passed.");
			System.out.flush();
		} catch (Throwable e) {
			System.out.println(String.format("Test failed: %s.", message));
			e.printStackTrace();
			System.out.flush();
			System.err.flush();
		}
		System.out.println();
		System.out.flush();
	}

	private static void performExpecting(Action action, Class<? extends Throwable> expectedThrowableClass, String message) {
		System.out.println("# Testing...");
		try {
			action.perform();
			System.out.println(String.format("Test failed: %s.", message));
			System.out.flush();
		} catch (Throwable e) {
			Class<? extends Throwable> actualThrowableClass = e.getClass();
			if (expectedThrowableClass.isAssignableFrom(actualThrowableClass)) {
				System.out.println("Test passed.");
			} else {
				e.printStackTrace();
			}
			System.out.flush();
			System.err.flush();
		}
		System.out.println();
		System.out.flush();
	}

	private static interface Action {
		void perform() throws Throwable;
	}
}
