package cl.crojas.previred.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.crojas.previred.exception.BusinessException;
import cl.crojas.previred.model.GenericResponse;
import cl.crojas.previred.utils.Constants;
import cl.crojas.previred.utils.LogUtils;
import cl.crojas.previred.utils.Utils;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@ControllerAdvice
public class AdviceController {

	private static final Logger log = LoggerFactory.getLogger(AdviceController.class);

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<GenericResponse> handleException(BusinessException e) {
		final String methodName = "handleException(): ";
		LogUtils.logInfoStarting(log, methodName);
		ResponseEntity<GenericResponse> response = null;
		GenericResponse result = new GenericResponse(500, Constants.ERROR_INTERNO + e.getMessage());
		if (Utils.isNullOrEmpty(e.getErrors())) {
			LogUtils.logError(log, methodName, Constants.ERROR_INTERNO + e.getMessage(), e);
			response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			result.setCode(HttpStatus.BAD_REQUEST.value());
			result.setMessage(Constants.EXISTEN_ERRORES_VALIDACION);
			result.setErrors(e.getErrors());
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		LogUtils.logInfoEnding(log, methodName);
		return response;
	}

}
