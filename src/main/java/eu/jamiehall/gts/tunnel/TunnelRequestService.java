package eu.jamiehall.gts.tunnel;

import eu.jamiehall.gts.WebApplicationConfiguration;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleClientInformation;
import org.apache.guacamole.protocol.GuacamoleConfiguration;

import javax.inject.Inject;
import java.util.List;

public class TunnelRequestService {

    private WebApplicationConfiguration configuration;

    @Inject
    TunnelRequestService(WebApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    private GuacamoleClientInformation getClientInformation(final TunnelRequest request)
            throws GuacamoleException {

        // Get client information
        GuacamoleClientInformation info = new GuacamoleClientInformation();

        // Set width if provided
        Integer width = request.getWidth();
        if (width != null) {
            info.setOptimalScreenWidth(width);
        }

        // Set height if provided
        Integer height = request.getHeight();
        if (height != null) {
            info.setOptimalScreenHeight(height);
        }

        // Set resolution if provided
        Integer dpi = request.getDPI();
        if (dpi != null) {
            info.setOptimalResolution(dpi);
        }

        // Add audio mimetypes
        List<String> audioMimetypes = request.getAudioMimetypes();
        if (audioMimetypes != null) {
            info.getAudioMimetypes().addAll(audioMimetypes);
        }
        // Add video mimetypes
        List<String> videoMimetypes = request.getVideoMimetypes();
        if (videoMimetypes != null) {
            info.getVideoMimetypes().addAll(videoMimetypes);
        }
        // Add image mimetypes
        List<String> imageMimetypes = request.getImageMimetypes();
        if (imageMimetypes != null) {
            info.getImageMimetypes().addAll(imageMimetypes);
        }
        return info;
    }

    public GuacamoleTunnel createTunnel(final TunnelRequest request) throws GuacamoleException {

        final GuacamoleClientInformation info = getClientInformation(request);
        final GuacamoleConfiguration config = new GuacamoleConfiguration();

        config.setProtocol(request.getType());
        config.setParameter("hostname", request.getIp());
        config.setParameter("port", request.getPort().toString());

        if (request.getIdentifier() != null) {
            config.setConnectionID(request.getIdentifier());
        }
        // Connect to guacd, proxying a connection to the RDP server above
        final GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(this.configuration.getGuacdHost(), this.configuration.getGuacdPort()),
                config, info
        );

        // Create tunnel from now-configured socket
        return new SimpleGuacamoleTunnel(socket);
    }

}
