package io.github.angelcen14.gmodmcp.service;

import io.github.angelcen14.gmodmcp.client.GmodRconClient;
import io.github.angelcen14.gmodmcp.model.GmodCommandRequest;
import io.github.angelcen14.gmodmcp.model.GmodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GModService {
    private final GmodRconClient gmodRconClient;

    @Tool(description = "Send a command to the Garry's Mod Server")
    public GmodResponse sendCommand(@ToolParam(description = "Command to send to Garry's Mod") GmodCommandRequest gmodCommandRequest) {
        return gmodRconClient.sendCommand(gmodCommandRequest.command());
    }
}
