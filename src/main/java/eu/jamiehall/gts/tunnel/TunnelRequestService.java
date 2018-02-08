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

    private GuacamoleClientInformation getClientInformation(final TunnelRequest request) throws GuacamoleException {

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

    private GuacamoleConfiguration buildConfig(final TunnelRequest request) throws GuacamoleException {

        final GuacamoleConfiguration config = new GuacamoleConfiguration();

        config.setProtocol("rdp");
        config.setParameter("hostname", request.getHostname());
        config.setParameter("port", request.getPort().toString());

        if (request.getUsername() != null) {
            config.setParameter("username", request.getUsername());
        }

        if (request.getPassword() != null) {
            config.setParameter("password", request.getPassword());
        }

        if (request.getIdentifier() != null) {
            config.setConnectionID(request.getIdentifier());
        }

        if (request.getDomain() != null) {
            config.setParameter("domain", request.getDomain());
        }

        if (request.getSecurity() != null) {
            config.setParameter("security", request.getSecurity());
        }

        if (request.getIgnoreCert() != null) {
            config.setParameter("ignore-cert", request.getIgnoreCert());

        }

        if (request.getDisableAuth() != null) {
            config.setParameter("disable-auth", request.getDisableAuth());
        }

        return config;

    }

    public GuacamoleTunnel createTunnel(final TunnelRequest request) throws GuacamoleException {

        final GuacamoleClientInformation info = getClientInformation(request);
        final GuacamoleConfiguration config = buildConfig(request);

        // Connect to guacd, proxying a connection to the RDP server above
        final GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(this.configuration.getGuacdHost(), this.configuration.getGuacdPort()),
                config, info
        );

        // Create tunnel from now-configured socket
        return new SimpleGuacamoleTunnel(socket);
    }

}
