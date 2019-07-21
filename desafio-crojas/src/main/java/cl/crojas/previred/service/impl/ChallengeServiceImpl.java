package cl.crojas.previred.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import cl.crojas.previred.exception.BusinessException;
import cl.crojas.previred.model.NuevoPeriodo;
import cl.crojas.previred.model.Periodo;
import cl.crojas.previred.service.ChallengeService;
import cl.crojas.previred.utils.LogUtils;
import cl.crojas.previred.utils.Utils;

/**
 * Capa de Servicios para Desafío Previred.
 * 
 * @author Christian Rojas N.
 *
 */
@Service
public class ChallengeServiceImpl extends BaseService implements ChallengeService {

	private static final Logger log = LoggerFactory.getLogger(ChallengeServiceImpl.class);

	@Value("${api.periodo}")
	private String apiPeriodo;

	@Override
	public Periodo getPeriodo() throws BusinessException {
		final String methodName = "getPeriodo(): ";
		Periodo periodo = null;
		LogUtils.logInfoStarting(log, methodName);
		periodo = super.callServiceByGetDelete(methodName, apiPeriodo, HttpMethod.GET, Periodo.class);
		LogUtils.logInfoEnding(log, methodName);
		return periodo;
	}

	@Override
	public NuevoPeriodo getNuevoPeriodo() throws BusinessException {
		final String methodName = "getPeriodo(): ";
		LogUtils.logInfoStarting(log, methodName);
		NuevoPeriodo nuevoPeriodo = new NuevoPeriodo();
		Periodo periodo = null;
		periodo = this.getPeriodo();
		BeanUtils.copyProperties(periodo, nuevoPeriodo);
		nuevoPeriodo.setFechasFaltantes(Utils.buildPeriodos(periodo));
		LogUtils.logInfoEnding(log, methodName);
		return nuevoPeriodo;
	}

}
