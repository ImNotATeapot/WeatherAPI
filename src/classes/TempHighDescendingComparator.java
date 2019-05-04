package classes;

import java.util.Comparator;

public class TempHighDescendingComparator implements Comparator<Weather> {

	@Override
	public int compare(Weather w0, Weather w1) {
		return w1.getDayHigh() - w0.getDayHigh();
	}

}
