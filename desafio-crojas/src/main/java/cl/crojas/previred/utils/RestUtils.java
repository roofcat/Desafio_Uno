package cl.crojas.previred.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.crojas.previred.exception.BusinessException;

/**
 * Clase utilitaria de servicios REST.
 * 
 * @author Christian Rojas N.
 *
 */
@Service
public class RestUtils {

	private static final Logger log = LoggerFactory.getLogger(RestUtils.class);

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Metodo encargado de llamar a los servicios rest con verbo get o delete
	 * 
	 * @param url   a llamar.
	 * @param verbo GET/DELETE
	 * @return estado del servicio.
	 * @throws BusinessException
	 */
	public String callServiceByGetDelete(String url, HttpMethod verbo) throws BusinessException {
		HttpEntity<String> entity = definirHeadersGetDelete();
		final String methodName = "callServiceByGetDelete(): ";
		String resultado = "";
		try {
			LogUtils.logInfoStarting(log, methodName);
			URI uri = UriComponentsBuilder.fromUriString(url).build().encode().toUri();
			ResponseEntity<String> result = restTemplate.exchange(uri, verbo, entity, String.class);
			resultado = result.getBody();
			LogUtils.logInfo(log, methodName, "JsonBody: " + result.getBody());
			LogUtils.logInfoEndedProcess(log, methodName);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
		LogUtils.logInfoEnding(log, methodName);
		return resultado;
	}

	/**
	 * Metodo define los headers de los servicios rest verbos GET/DETELE.
	 * 
	 * @return Headers.
	 */
	public HttpEntity<String> definirHeadersGetDelete() {
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(headers);
	}

	/**
	 * Metodo encargado de llamar a los servicios rest de la aplicacion Post y Put.
	 * 
	 * @param urlService del servicio.
	 * @param json       json a pasar pasar en la url.
	 * @param verbo      POST/PUT
	 * @return estado del servicio.
	 * @throws BusinessException Exception.
	 */
	public String callServicePostPut(String urlService, String json, HttpMethod verbo) throws BusinessException {
		final String methodName = "callServicePostPut(): ";
		String resultado = "";
		try {
			LogUtils.logInfoStarting(log, methodName);
			LogUtils.logInfo(log, methodName, "JsonConsulta: " + json);
			HttpEntity<String> entity = definirHeadersPostPut(json);
			URI uri = UriComponentsBuilder.fromUriString(urlService).build().encode().toUri();
			ResponseEntity<String> result = restTemplate.exchange(uri, verbo, entity, String.class);
			resultado = result.getBody();
			LogUtils.logInfo(log, methodName, "JsonBody: " + result.getBody());
			LogUtils.logInfoEndedProcess(log, methodName);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
		LogUtils.logInfoEnding(log, methodName);
		return resultado;
	}

	/**
	 * Metodo encargado de definir la cabezera de las peticiones POST/PUT.
	 * 
	 * @param json informacion de la peticion en json.
	 * @return headers de la peticion.
	 */
	private HttpEntity<String> definirHeadersPostPut(String json) {
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		// Prepare header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(json, headers);
	}

	/**
	 * Metodo transforma objeto json a string.
	 * 
	 * @param obj parametro de entrada.
	 * @return String del objeto.
	 */
	public String transformToJsonString(final Object obj) {
		final String methodName = "transformToJsonString(): ";
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LogUtils.logError(log, methodName, e.getCause().toString());
		}
		return null;
	}

}
