package eu.jamiehall.gts.ws;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import eu.jamiehall.gts.tunnel.TunnelRequestService;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@Singleton
public class WebSocketTunnelServlet extends WebSocketServlet {

    @Inject
    private TunnelRequestService tunnelRequestService;

    @Override
    public void configure(WebSocketServletFactory factory) {
        // Register WebSocket implementation
        factory.setCreator(new WebSocketCreator(tunnelRequestService));
    }
}
