package cl.crojas.previred.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.crojas.previred.exception.BusinessException;
import cl.crojas.previred.model.GenericResponse;
import cl.crojas.previred.model.NuevoPeriodo;
import cl.crojas.previred.model.Periodo;
import cl.crojas.previred.model.Test;
import cl.crojas.previred.service.ChallengeService;
import cl.crojas.previred.utils.DatesComparator;
import cl.crojas.previred.utils.LogUtils;
import cl.crojas.previred.utils.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@RestController
@RequestMapping(Routes.Challenges.BASE)
@Api(value = "Servicios REST para Desafío Previred")
@CrossOrigin
public class ChallengeController {

	private static final Logger log = LoggerFactory.getLogger(ChallengeController.class);

	@Autowired
	private ChallengeService challengeService;

	@GetMapping(value = Routes.Challenges.GET_PERIODO, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Método PROXY, que ejecuta el servicio de Periodos Api.", notes = "", response = Periodo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La solicitud fue exitosa", response = Periodo.class),
			@ApiResponse(code = 500, message = "Ha ocurrido un error interno", response = GenericResponse.class) })
	public ResponseEntity<Periodo> getPeriodo() throws BusinessException {

		final String methodName = "getPeriodo(): ";

		Periodo result = null;

		LogUtils.logInfoStarting(log, methodName);

		result = this.challengeService.getPeriodo();

		LogUtils.logInfoEnding(log, methodName);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping(value = Routes.Challenges.GET_SOLUCION, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Método SOLUCIÓN al desafío solicitado por Previred en donde llamará al servicio GDD y agregará las fechas faltantes", notes = "", response = NuevoPeriodo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La solicitud fue exitosa", response = NuevoPeriodo.class),
			@ApiResponse(code = 500, message = "Ha ocurrido un error interno", response = GenericResponse.class) })
	public ResponseEntity<NuevoPeriodo> getNuevoPeriodo() throws BusinessException {

		final String methodName = "getNuevoPeriodo(): ";

		NuevoPeriodo result = null;

		LogUtils.logInfoStarting(log, methodName);

		result = this.challengeService.getNuevoPeriodo();

		LogUtils.logInfoEnding(log, methodName);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping(value = Routes.Challenges.GET_TEST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Método de PRUEBA en donde retorna un objeto con fecha desde y hasta, cantidad de fechas dentro de esos periodos y una lista de fechas", notes = "", response = Test.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La solicitud fue exitosa", response = Test.class),
			@ApiResponse(code = 500, message = "Ha ocurrido un error interno", response = GenericResponse.class) })
	public ResponseEntity<Test> test() throws BusinessException {

		final String methodName = "test(): ";

		Test result = new Test();
		List<Date> fechas = new ArrayList<>();
		NuevoPeriodo nuevoPeriodo = null;

		LogUtils.logInfoStarting(log, methodName);

		nuevoPeriodo = this.challengeService.getNuevoPeriodo();

		fechas.addAll(nuevoPeriodo.getFechas());
		fechas.addAll(nuevoPeriodo.getFechasFaltantes());
		fechas.sort(new DatesComparator());

		result.setDesde(nuevoPeriodo.getFechaCreacion());
		result.setHasta(nuevoPeriodo.getFechaFin());
		result.setTotal(fechas.size());
		result.setFechas(fechas);

		LogUtils.logInfoEnding(log, methodName);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
