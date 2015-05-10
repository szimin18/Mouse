package mouse;

import java.util.function.Consumer;

import mouse.list.IMouseList;

// TODO More tests
// TODO Iterable
// TODO SkipList

public class Mouse {
	public static void main(String[] args) {
		testIMouseList(new MouseArrayList<Integer>());
		testIMouseList(new MouseLinkedList<Integer>());
	}

	private void testIMouseList(IMouseList<Integer> list) {
		performChecked((x) -> {
			list.add(1);
		});
		performChecked((x) -> {
			assert list.remove(1);
		});
		performChecked((x) -> {
			assert !list.remove(0);
		});
		performChecked((x) -> {
			list.add(2);
		});
		performChecked((x) -> {
			list.add(3);
		});
		performChecked((x) -> {
			assert list.size() == 2;
		});
		performChecked((x) -> {
			assert list.contains(2);
		});
	}

	private void performChecked(Consumer<Void> action) {
		try {
			action.accept(null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
