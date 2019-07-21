package cl.crojas.previred.utils;

import java.util.Map;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Utilidad para ahorrar líneas de código en log.
 * 
 * @author Christian Rojas N.
 *
 */
public class LogUtils {

	public static final String SS = "%s%s";
	public static final String SSS = "%s%s %s";
	public static final String SSSS = "%s%s %s %s";
	public static final String DB_RESPONSE = "DB Response: \n ";
	public static final String JSON_OBJECT = "Json Object: \n ";

	private LogUtils() {
		super();
	}

	/**
	 * Log para Iniciando...
	 * 
	 * @param log
	 * @param methodName
	 */
	public static void logInfoStarting(Logger log, String methodName) {
		logInfo(log, methodName, Constants.INICIANDO);
	}

	/**
	 * Log para Proceso finalizado exitosamente...
	 * 
	 * @param log
	 * @param methodName
	 */
	public static void logInfoEndedProcess(Logger log, String methodName) {
		logInfo(log, methodName, Constants.PROCESO_FINALIZADO);
	}

	/**
	 * Log para Finalizando...
	 * 
	 * @param log
	 * @param methodName
	 */
	public static void logInfoEnding(Logger log, String methodName) {
		logInfo(log, methodName, Constants.FINALIZANDO);
	}

	/**
	 * Log para Errores de Validación...
	 * 
	 * @param log
	 * @param methodName
	 */
	public static void logInfoValidationErrors(Logger log, String methodName) {
		logInfo(log, methodName, Constants.EXISTEN_ERRORES_VALIDACION);
	}

	/**
	 * Log para Errores de Validación Detallado...
	 * 
	 * @param log
	 * @param methodName
	 * @param details
	 */
	public static void logInfoValidationErrors(Logger log, String methodName, Map<String, String> details) {

		StringBuilder errores = new StringBuilder();

		for (Map.Entry<String, String> entry : details.entrySet()) {

			errores.append(entry.getKey() + " - " + entry.getValue() + ", ");

		}

		logInfo(log, methodName, Constants.EXISTEN_ERRORES_VALIDACION + ": " + errores.toString());

	}

	/**
	 * Log para Sin errores de validación...
	 * 
	 * @param log
	 * @param methodName
	 */
	public static void logInfoValidationSuccess(Logger log, String methodName) {
		logInfo(log, methodName, Constants.SIN_ERRORES_VALIDACION);
	}

	/**
	 * Log info.
	 * 
	 * @param log
	 * @param methodName
	 * @param msg1
	 */
	public static void logInfo(Logger log, String methodName, String msg1) {
		if (log.isInfoEnabled())
			log.info(String.format(SS, methodName, msg1));
	}

	/**
	 * Log Debug.
	 * 
	 * @param log
	 * @param methodName
	 * @param msg1
	 */
	public static void logDebug(Logger log, String methodName, String msg1) {
		if (log.isDebugEnabled())
			log.debug(String.format(SS, methodName, msg1));
	}

	/**
	 * Log info.
	 * 
	 * @param log
	 * @param msg1
	 */
	public static void logInfo(Logger log, String msg1) {
		if (log.isInfoEnabled())
			log.info(msg1);
	}

	/**
	 * Log info.
	 * 
	 * @param log
	 * @param methodName
	 * @param msg1
	 * @param msg2
	 */
	public static void logInfo(Logger log, String methodName, String msg1, String msg2) {
		if (log.isInfoEnabled())
			log.info(String.format(SSS, methodName, msg1, msg2));
	}

	/**
	 * Log info.
	 * 
	 * @param log
	 * @param methodName
	 * @param msg1
	 * @param msg2
	 * @param msg3
	 */
	public static void logInfo(Logger log, String methodName, String msg1, String msg2, String msg3) {
		if (log.isInfoEnabled())
			log.info(String.format(SSSS, methodName, msg1, msg2, msg3));
	}

	/**
	 * Log info json.
	 * 
	 * @param logger
	 * @param methodName
	 * @param obj
	 * @throws JsonProcessingException
	 */
	public static void logInfoJson(Logger logger, String methodName, Object obj) throws JsonProcessingException {
		logInfo(logger, methodName, JSON_OBJECT + Utils.getObjToJSON(obj));

	}

	/**
	 * Log error.
	 * 
	 * @param log
	 * @param methodName
	 * @param msg1
	 * @param e
	 */
	public static void logError(Logger log, String methodName, String msg1, Exception e) {
		if (log.isInfoEnabled())
			log.error(String.format(SS, methodName, msg1), e);
	}

	/**
	 * Log error
	 * 
	 * @param log
	 * @param methodName
	 * @param msg1
	 */
	public static void logError(Logger log, String methodName, String msg1) {
		if (log.isInfoEnabled())
			log.error(String.format(SS, methodName, msg1));
	}

}
