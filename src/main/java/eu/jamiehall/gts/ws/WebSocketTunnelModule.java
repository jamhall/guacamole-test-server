package eu.jamiehall.gts.ws;

import com.google.inject.servlet.ServletModule;
import eu.jamiehall.gts.WebApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads the Jetty 9 WebSocket tunnel implementation.
 */
public class WebSocketTunnelModule extends ServletModule {

    private final Logger logger = LoggerFactory.getLogger(WebSocketTunnelModule.class);

    private WebApplicationConfiguration configuration;

    public WebSocketTunnelModule(WebApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configureServlets() {
        bind(WebApplicationConfiguration.class).toInstance(this.configuration);
        logger.info("Loading Jetty 9 WebSocket support...");
        serve("/ws").with(WebSocketTunnelServlet.class);
    }

}