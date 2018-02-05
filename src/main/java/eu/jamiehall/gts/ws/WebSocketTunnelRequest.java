package eu.jamiehall.gts.ws;

import eu.jamiehall.gts.tunnel.TunnelRequest;
import org.eclipse.jetty.websocket.api.UpgradeRequest;

import java.util.List;
import java.util.Map;

/**
 * Jetty 9 WebSocket-specific implementation of TunnelRequest.
 */
public class WebSocketTunnelRequest extends TunnelRequest {

    /**
     * All parameters passed via HTTP to the WebSocket handshake.
     */
    private final Map<String, List<String>> handshakeParameters;

    /**
     * Creates a TunnelRequest implementation which delegates parameter and
     * session retrieval to the given UpgradeRequest.
     *
     * @param request The UpgradeRequest to wrap.
     */
    public WebSocketTunnelRequest(UpgradeRequest request) {
        this.handshakeParameters = request.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        // Pull list of values, if present
        List<String> values = getParameterValues(name);
        if (values == null || values.isEmpty()) {
            return null;
        }

        // Return first parameter value arbitrarily
        return values.get(0);

    }

    @Override
    public List<String> getParameterValues(String name) {
        List<String> values = handshakeParameters.get(name);
        if (values == null) {
            return null;
        }
        return values;
    }

}