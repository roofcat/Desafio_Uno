package cl.crojas.previred.utils;

import java.util.Comparator;
import java.util.Date;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class DatesComparator implements Comparator<Date> {

	@Override
	public int compare(Date o1, Date o2) {
		return o1.compareTo(o2);
	}

}
