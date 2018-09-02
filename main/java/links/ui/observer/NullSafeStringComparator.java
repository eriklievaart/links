package links.ui.observer;

import java.util.Comparator;

public class NullSafeStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1 == null) {
			return Integer.MIN_VALUE;
		}
		if (o2 == null) {
			return Integer.MAX_VALUE;
		}
		return o1.toLowerCase().compareTo(o2.toLowerCase());
	}
}
