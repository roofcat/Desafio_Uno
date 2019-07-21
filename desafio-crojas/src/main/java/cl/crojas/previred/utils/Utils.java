package cl.crojas.previred.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.crojas.previred.model.Periodo;

/**
 * Clase utilitaria.
 * 
 * @author Christian Rojas N.
 *
 */
public class Utils {

	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	private Utils() {
		super();
	}

	public static boolean isNullOrEmpty(final Collection<?> c) {
		return (c == null || c.isEmpty());
	}

	public static boolean isNullOrEmpty(final Map<?, ?> c) {
		return (c == null || c.isEmpty());
	}

	public static String getObjToJSON(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	public static List<Date> buildPeriodos(Periodo periodo) {

		final String methodName = "buildPeriodos(): ";

		List<Date> result = new ArrayList<>();

		if (periodo != null && !isNullOrEmpty(periodo.getFechas())) {

			LocalDate temp = toLocalDate(periodo.getFechaCreacion());

			if (!periodo.getFechas().contains(localDateToDate(temp)))
				result.add(localDateToDate(temp));

			while (periodo.getFechaFin().after(localDateToDate(temp))) {

				temp = temp.plusMonths(1L);

				LogUtils.logInfo(log, methodName + temp);

				if (!periodo.getFechas().contains(localDateToDate(temp)))
					result.add(localDateToDate(temp));

			}

		}

		return result;

	}

	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate toLocalDate(Date date) {
		if (date != null)
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		else
			return null;
	}

}
