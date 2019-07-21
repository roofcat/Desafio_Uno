package cl.crojas.previred.service;

import cl.crojas.previred.exception.BusinessException;
import cl.crojas.previred.model.NuevoPeriodo;
import cl.crojas.previred.model.Periodo;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public interface ChallengeService {

	Periodo getPeriodo() throws BusinessException;

	NuevoPeriodo getNuevoPeriodo() throws BusinessException;

}
