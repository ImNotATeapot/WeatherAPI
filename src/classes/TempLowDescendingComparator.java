package classes;

import java.util.Comparator;

public class TempLowDescendingComparator implements Comparator<Weather> {

	@Override
	public int compare(Weather w0, Weather w1) {
		return w1.getDayLow() - w0.getDayLow();
	}
	
}
