package cl.crojas.previred.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.google.gson.Gson;

import cl.crojas.previred.exception.BusinessException;
import cl.crojas.previred.utils.Constants;
import cl.crojas.previred.utils.LogUtils;
import cl.crojas.previred.utils.RestUtils;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class BaseService {

	private static final Logger log = LoggerFactory.getLogger(BaseService.class);

	@Autowired
	protected RestUtils restTemplate;

	/**
	 * Método estándar para ejecutar solicitudes GET y DELETE.
	 * 
	 * @param <T>
	 * @param serviceMethod
	 * @param url
	 * @param method
	 * @param clazz
	 * @return
	 * @throws BusinessException
	 */
	protected <T> T callServiceByGetDelete(String serviceMethod, String url, HttpMethod method, Class<T> clazz)
			throws BusinessException {

		final String methodName = "callServiceByGetDelete(): ";

		T result = null;

		try {

			LogUtils.logInfo(log, methodName + serviceMethod + Constants.INICIANDO);

			result = new Gson().fromJson(this.restTemplate.callServiceByGetDelete(url, method), clazz);

			LogUtils.logInfo(log, methodName + serviceMethod + Constants.PROCESO_FINALIZADO);

		} catch (Exception e) {

			throw new BusinessException(e.getMessage(), e);

		}

		LogUtils.logInfo(log, methodName + serviceMethod + Constants.FINALIZANDO);

		return result;

	}

	/**
	 * Método estándar para ejecutar solicitudes POST y PUT
	 * 
	 * @param <T>
	 * @param serviceMethod
	 * @param url
	 * @param method
	 * @param request
	 * @param clazz
	 * @return
	 * @throws BusinessException
	 */
	protected <T> T callServiceByPostPut(String serviceMethod, String url, HttpMethod method, Object request,
			Class<T> clazz) throws BusinessException {

		final String methodName = "callServiceByPostPut(): ";

		T result = null;

		try {

			LogUtils.logInfo(log, methodName + serviceMethod + Constants.INICIANDO);

			result = new Gson().fromJson(
					this.restTemplate.callServicePostPut(url, this.restTemplate.transformToJsonString(request), method),
					clazz);

			LogUtils.logInfo(log, methodName + serviceMethod + Constants.PROCESO_FINALIZADO);

		} catch (Exception e) {

			throw new BusinessException(e.getMessage(), e);

		}

		LogUtils.logInfo(log, methodName + serviceMethod + Constants.FINALIZANDO);

		return result;

	}

}
