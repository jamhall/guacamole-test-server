package eu.jamiehall.gts;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import eu.jamiehall.gts.ws.WebSocketTunnelModule;

public class WebServletContextListener extends GuiceServletContextListener {

    private WebApplicationConfiguration configuration;

    WebServletContextListener(WebApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new WebSocketTunnelModule(configuration));
    }
}