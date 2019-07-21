package cl.crojas.previred.utils;

/**
 * Rutas de servicios web.
 * 
 * @author Christian Rojas N.
 *
 */
public class Routes {

	private Routes() {
		super();
	}

	public static final String INFO_VERSION = "${info.version}/";

	public class Home {

		private Home() {
			super();
		}

		public static final String BASE = "/";

	}

	public class Challenges {

		private Challenges() {
			super();
		}

		public static final String BASE = "challenges/" + INFO_VERSION;

		public static final String GET_PERIODO = "proxy";

		public static final String GET_SOLUCION = "solucion";

		public static final String GET_TEST = "test";

	}

}
