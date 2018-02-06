# guacamole-test-server

A WebSocket broker server for testing RDP connections to [guacd](https://guacamole.apache.org/doc/gug/guacamole-architecture.html)

## Running

Package:

```
mvn package
```

Build:

```
java -jar target/gts.jar --guacd-host 1.2.3.4 --guacd-port 4822 --port 8080
```

## Connection parameters

| Parameter    | Description                                                                                                                                                                                                                                                                                                                                                                                                  |
|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| hostname     | The hostname or IP address of the RDP server Guacamole should connect to.                                                                                                                                                                                                                                                                                                                                    |
| port         | The port the RDP server is listening on, usually 3389. This parameter is optional. If this is not specified, the default of 3389 will be used.                                                                                                                                                                                                                                                               |
| username     | The username to use to authenticate, if any.                                                                                                                                                                                                                                                                                                                                                                 |
| password     | The password to use when attempting authentication, if any.                                                                                                                                                                                                                                                                                                                                                  |
| domain       | The domain to use when attempting authentication, if any.                                                                                                                                                                                                                                                                                                                                                    |
| security     | The security mode to use for the RDP connection. This mode dictates how data will be encrypted and what type of authentication will be performed, if any. By default, standard RDP encryption is requested, as it is the most widely supported. Possible values are: rdp, nla, tls, any                                                                                                                      |
| ignore-cert  | If set to "true", the certificate returned by the server will be ignored, even if that certificate cannot be validated. This is useful if you universally trust the server and your connection to the server, and you know that the server's certificate cannot be validated (for example, if it is self-signed).                                                                                            |
| disable-auth | If set to "true", authentication will be disabled. Note that this refers to authentication that takes place while connecting. Any authentication enforced by the server over the remote desktop session (such as a login dialog) will still take place. By default, authentication is enabled and only used when requested by the server.If you are using NLA, authentication must be enabled by definition. |
| width        | The name of the parameter containing the desired display width in pixels                                                                                                                                                                                                                                                                                                                                     |
| height       | The name of the parameter containing the desired display height in pixels                                                                                                                                                                                                                                                                                                                                    |
| dpi          | The desired effective resolution of the client display, in DPI                                                                                                                                                                                                                                                                                                                                               |
| audio        | The name of the parameter specifying one supported audio mimetype. This will normally appear multiple times within a single tunnel request once for each mimetype.                                                                                                                                                                                                                                           |
| video        | The name of the parameter specifying one supported video mimetype. This will normally appear multiple times within a single tunnel request once for each mimetype.                                                                                                                                                                                                                                           |
| image        | The name of the parameter specifying one supported image mimetype. This will normally appear multiple times within a single tunnel request once for each mimetype.                                                                                                                                                                                                                                           |


Example connection string to an RDP server:

```
http://localhost:8080/ws?hostname=localhost&port=3389&width=1024&height=768&audio=audio/L16
```