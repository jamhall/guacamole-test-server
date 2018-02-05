package eu.jamiehall.gts;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebApplication {

    @Parameter(names = {"--guacd-host"}, required = true)
    private String guacdHost;

    @Parameter(names = {"--guacd-port"})
    private Integer guacdPort = 4822;

    @Parameter(names = {"--port"})
    private Integer port = 8080;


    public static void main(String... argv) throws Exception {
        final WebApplication application = new WebApplication();
        JCommander.newBuilder()
                .addObject(application)
                .build()
                .parse(argv);
        application.run();
    }

    public void run() throws Exception {
        final WebApplicationConfiguration configuration = new WebApplicationConfiguration(guacdHost, guacdPort);

        final Server server = new Server(port);
        final ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addEventListener(new WebServletContextListener(configuration));
        context.addFilter(GuiceFilter.class, "/*", null);
        context.addServlet(DefaultServlet.class, "/*");

        server.start();
    }
}