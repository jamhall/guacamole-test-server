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
     * The ip address of remote server to establish a connection to
     */
    public static final String IP_PARAMETER = "ip";


    /**
     * The remote desktop port to connect to
     */
    public static final String PORT_PARAMETER = "port";

    /**
     * The remote desktop protocol to connect to
     */
    public static final String TYPE_PARAMETER = "type";

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


    /**
     * Returns the identifier of the destination of the tunnel being requested.
     * As there are multiple types of destination objects available, and within
     * multiple data sources, the associated object type and data source are
     * also necessary to determine what this identifier refers to.
     *
     * @return The identifier of the destination of the tunnel being requested.
     * @throws GuacamoleException If the identifier was not present in the request.
     */
    public String getIdentifier() throws GuacamoleException {
        return getParameter(IDENTIFIER_PARAMETER);
    }

    /**
     * Returns the display width desired for the Guacamole session over the
     * tunnel being requested.
     *
     * @return The display width desired for the Guacamole session over the tunnel
     * being requested, or null if no width was given.
     * @throws GuacamoleException If the width specified was not a valid integer.
     */
    public Integer getWidth() throws GuacamoleException {
        return getIntegerParameter(WIDTH_PARAMETER);
    }

    /**
     * Returns the display height desired for the Guacamole session over the
     * tunnel being requested.
     *
     * @return The display height desired for the Guacamole session over the tunnel
     * being requested, or null if no width was given.
     * @throws GuacamoleException If the height specified was not a valid integer.
     */
    public Integer getHeight() throws GuacamoleException {
        return getIntegerParameter(HEIGHT_PARAMETER);
    }

    /**
     * Returns the display resolution desired for the Guacamole session over
     * the tunnel being requested, in DPI.
     *
     * @return The display resolution desired for the Guacamole session over the
     * tunnel being requested, or null if no resolution was given.
     * @throws GuacamoleException If the resolution specified was not a valid integer.
     */
    public Integer getDPI() throws GuacamoleException {
        return getIntegerParameter(DPI_PARAMETER);
    }

    /**
     * Returns a list of all audio mimetypes declared as supported within the
     * tunnel request.
     *
     * @return A list of all audio mimetypes declared as supported within the
     * tunnel request, or null if no mimetypes were specified.
     */
    public List<String> getAudioMimetypes() {
        return getParameterValues(AUDIO_PARAMETER);
    }

    /**
     * Returns a list of all video mimetypes declared as supported within the
     * tunnel request.
     *
     * @return A list of all video mimetypes declared as supported within the
     * tunnel request, or null if no mimetypes were specified.
     */
    public List<String> getVideoMimetypes() {
        return getParameterValues(VIDEO_PARAMETER);
    }

    /**
     * Returns a list of all image mimetypes declared as supported within the
     * tunnel request.
     *
     * @return A list of all image mimetypes declared as supported within the
     * tunnel request, or null if no mimetypes were specified.
     */
    public List<String> getImageMimetypes() {
        return getParameterValues(IMAGE_PARAMETER);
    }

    /**
     * Returns the ip address of the remote desktop
     */
    public String getIp() throws GuacamoleException {
        return getParameter(IP_PARAMETER);
    }

    /**
     * Returns the port of the remote desktop
     */
    public Integer getPort() throws GuacamoleException {
        return getIntegerParameter(PORT_PARAMETER);
    }

    /**
     * Returns the type (vnc, rdp) of the remote desktop
     */
    public String getType() throws GuacamoleException {
        return getParameter(TYPE_PARAMETER);
    }


}