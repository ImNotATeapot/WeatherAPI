package classes;

import java.util.Comparator;

public class TempLowAscendingComparator implements Comparator<Weather> {

	@Override
	public int compare(Weather w0, Weather w1) {
		return w0.getDayLow() - w1.getDayLow();
	}

}
