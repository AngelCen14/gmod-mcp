package io.github.angelcen14.gmodmcp.config;

import io.github.angelcen14.gmodmcp.service.GModService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfiguration {
    @Bean
    public ToolCallbackProvider gmodTools(GModService gmodService) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(gmodService)
                .build();
    }
}
