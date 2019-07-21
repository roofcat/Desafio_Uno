package cl.crojas.previred.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cl.crojas.previred.utils.Constants;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class NuevoPeriodo extends Periodo {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_PATTERN)
	private List<Date> fechasFaltantes;

	public NuevoPeriodo() {
		super();
	}

	public List<Date> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<Date> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

}
