package eu.jamiehall.gts;

public class WebApplicationConfiguration {

    private String guacdHost;
    private Integer guacdPort;

    public WebApplicationConfiguration(final String guacdHost, final Integer guacdPort) {
        this.guacdHost = guacdHost;
        this.guacdPort = guacdPort;
    }

    public String getGuacdHost() {
        return guacdHost;
    }

    public Integer getGuacdPort() {
        return guacdPort;
    }

}
