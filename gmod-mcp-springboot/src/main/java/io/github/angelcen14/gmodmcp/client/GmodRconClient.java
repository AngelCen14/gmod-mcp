package io.github.angelcen14.gmodmcp.client;

import io.github.angelcen14.gmodmcp.model.GmodResponse;
import nl.vv32.rcon.Rcon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GmodRconClient {

    @Value("${gmodrcon.host}")
    private String host;

    @Value("${gmodrcon.port}")
    private int port;

    @Value("${gmodrcon.password}")
    private String password;

    public GmodResponse sendCommand(String command) {
        String message;
        try(Rcon rcon = Rcon.open(host, port)) {
            if (rcon.authenticate(password)) {
                message = rcon.sendCommand(command);
            } else {
                message = "Authentication failed";
            }
        } catch (IOException e) {
            message = e.getLocalizedMessage();
        }
        return new GmodResponse(message);
    }
}
