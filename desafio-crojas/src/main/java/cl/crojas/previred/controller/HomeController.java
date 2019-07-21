package cl.crojas.previred.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.crojas.previred.utils.LogUtils;
import cl.crojas.previred.utils.Routes;
import io.swagger.annotations.Api;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Controller
@RequestMapping(Routes.Home.BASE)
@Api(value = "Redirect para Swagger UI")
@CrossOrigin
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	private static final String REDIRECT_TO_SWAGGER = "redirect:swagger-ui.html";

	@GetMapping
	public String index() {
		final String methodName = "index(): ";
		LogUtils.logInfoStarting(log, methodName);
		LogUtils.logInfoEnding(log, methodName);
		return REDIRECT_TO_SWAGGER;
	}

}
