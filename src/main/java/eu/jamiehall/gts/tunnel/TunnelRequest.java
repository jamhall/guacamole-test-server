package eu.jamiehall.gts.tunnel;

import org.apache.guacamole.GuacamoleClientException;
import org.apache.guacamole.GuacamoleException;

import java.util.List;

/**
 * A request object which provides only the functions absolutely required to
 * retrieve and connect to a tunnel.
 */
public abstract class TunnelRequest {

    /**
     * The hostname or IP address of the RDP server Guacamole should connect to.
     */
    public static final String HOSTNAME = "hostname";

    /**
     * The remote desktop port to connect to
     */
    public static final String PORT_PARAMETER = "port";

    /**
     * The remote desktop domain
     */
    public static final String DOMAIN_PARAMETER = "domain";

    /**
     * The remote desktop security (rdp, nla, tls, any)
     */
    public static final String SECURITY_PARAMETER = "security";

    /**
     * If set to "true", the certificate returned by the server will be ignored, even if that certificate cannot be validated.
     * This is useful if you universally trust the server and your connection to the server,
     * and you know that the server's certificate cannot be validated (for example, if it is self-signed).
     */
    public static final String IGNORE_CERT_PARAMETER = "ignore-cert";

    /**
     * If set to "true", authentication will be disabled.
     * Note that this refers to authentication that takes place while connecting.
     * Any authentication enforced by the server over the remote desktop session (such as a login dialog) will still take place.
     * By default, authentication is enabled and only used when requested by the server.
     * If you are using NLA, authentication must be enabled by definition.
     */
    public static final String DISABLE_AUTH_PARAMETER = "disable-auth";

    /**
     * The remote desktop username
     */
    public static final String USERNAME_PARAMETER = "username";

    /**
     * The remote desktop password
     */
    public static final String PASSWORD_PARAMETER = "password";

    /**
     * The name of the parameter containing the unique identifier of the object
     * to which a tunnel is being requested.
     */
    public static final String IDENTIFIER_PARAMETER = "id";

    /**
     * The name of the parameter containing the desired display width, in
     * pixels.
     */
    public static final String WIDTH_PARAMETER = "width";

    /**
     * The name of the parameter containing the desired display height, in
     * pixels.
     */
    public static final String HEIGHT_PARAMETER = "height";

    /**
     * The name of the parameter containing the desired display resolution, in
     * DPI.
     */
    public static final String DPI_PARAMETER = "dpi";

    /**
     * The name of the parameter specifying one supported audio mimetype. This
     * will normally appear multiple times within a single tunnel request -
     * once for each mimetype.
     */
    public static final String AUDIO_PARAMETER = "audio";

    /**
     * The name of the parameter specifying one supported video mimetype. This
     * will normally appear multiple times within a single tunnel request -
     * once for each mimetype.
     */
    public static final String VIDEO_PARAMETER = "video";

    /**
     * The name of the parameter specifying one supported image mimetype. This
     * will normally appear multiple times within a single tunnel request -
     * once for each mimetype.
     */
    public static final String IMAGE_PARAMETER = "image";

    /**
     * Returns the value of the parameter having the given name.
     *
     * @param name The name of the parameter to return.
     * @return The value of the parameter having the given name, or null if no such
     * parameter was specified.
     */
    public abstract String getParameter(String name);

    /**
     * Returns a list of all values specified for the given parameter.
     *
     * @param name The name of the parameter to return.
     * @return All values of the parameter having the given name , or null if no
     * such parameter was specified.
     */
    public abstract List<String> getParameterValues(String name);

    /**
     * Returns the integer value of the parameter having the given name,
     * throwing an exception if the parameter cannot be parsed.
     *
     * @param name The name of the parameter to return.
     * @return The integer value of the parameter having the given name, or null if
     * the parameter is missing.
     * @throws GuacamoleException If the parameter is not a valid integer.
     */
    public Integer getIntegerParameter(String name) throws GuacamoleException {

        // Pull requested parameter
        String value = getParameter(name);
        if (value == null) {
            return null;
        }

        // Attempt to parse as an integer
        try {
            return Integer.parseInt(value);
        }

        // Rethrow any parsing error as a GuacamoleClientException
        catch (NumberFormatException e) {
            throw new GuacamoleClientException("Parameter \"" + name + "\" must be a valid integer.", e);
        }
    }

    public String getIdentifier() throws GuacamoleException {
        return getParameter(IDENTIFIER_PARAMETER);
    }

    public Integer getWidth() throws GuacamoleException {
        return getIntegerParameter(WIDTH_PARAMETER);
    }

    public Integer getHeight() throws GuacamoleException {
        return getIntegerParameter(HEIGHT_PARAMETER);
    }

    public Integer getDPI() throws GuacamoleException {
        return getIntegerParameter(DPI_PARAMETER);
    }

    public List<String> getAudioMimetypes() {
        return getParameterValues(AUDIO_PARAMETER);
    }

    public List<String> getVideoMimetypes() {
        return getParameterValues(VIDEO_PARAMETER);
    }

    public List<String> getImageMimetypes() {
        return getParameterValues(IMAGE_PARAMETER);
    }

    public String getHostname() throws GuacamoleException {
        return getParameter(HOSTNAME);
    }

    public Integer getPort() throws GuacamoleException {
        return getIntegerParameter(PORT_PARAMETER);
    }

    public String getUsername() throws GuacamoleException {
        return getParameter(USERNAME_PARAMETER);
    }

    public String getPassword() throws GuacamoleException {
        return getParameter(PASSWORD_PARAMETER);
    }

    public String getDomain() throws GuacamoleException {
        return getParameter(DOMAIN_PARAMETER);
    }

    public String getSecurity() throws GuacamoleException {
        return getParameter(SECURITY_PARAMETER);
    }

    public String getIgnoreCert() throws GuacamoleException {
        return getParameter(IGNORE_CERT_PARAMETER);
    }

    public String getDisableAuth() throws GuacamoleException {
        return getParameter(DISABLE_AUTH_PARAMETER);
    }

}