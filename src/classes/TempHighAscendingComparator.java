package classes;

import java.util.Comparator;

public class TempHighAscendingComparator implements Comparator<Weather> {

	@Override
	public int compare(Weather w0, Weather w1) {
		return w0.getDayHigh() - w1.getDayHigh();
	}

}	
