package eu.jamiehall.gts.ws;

import eu.jamiehall.gts.tunnel.TunnelRequestService;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;

/**
 * WebSocketCreator which selects the appropriate WebSocketListener
 * implementation if the "guacamole" subprotocol is in use.
 */
public class WebSocketCreator implements org.eclipse.jetty.websocket.servlet.WebSocketCreator {

    /**
     * Service for handling tunnel requests.
     */
    private final TunnelRequestService tunnelRequestService;

    /**
     * Creates a new WebSocketCreator which uses the given TunnelRequestService
     * to create new GuacamoleTunnels for inbound requests.
     *
     * @param tunnelRequestService The service to use for inbound tunnel
     *                             requests.
     */
    public WebSocketCreator(TunnelRequestService tunnelRequestService) {
        this.tunnelRequestService = tunnelRequestService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest request, ServletUpgradeResponse response) {
        // Validate and use "guacamole" subprotocol
        for (String subprotocol : request.getSubProtocols()) {
            if ("guacamole".equals(subprotocol)) {
                response.setAcceptedSubProtocol(subprotocol);
                return new WebSocketTunnelListener(tunnelRequestService);
            }

        }

        // Invalid protocol
        return null;
    }
}