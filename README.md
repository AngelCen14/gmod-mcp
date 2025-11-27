# gmod-mcp

MCP server to allow an AI Agent to interact with a Garry’s Mod dedicated server. The goal is to provide an interface that translates natural language into actions within GMod.

## ✅ Key Features

- Support for communication between an AI Agent and GMod server via MCP.  
- Includes an addon for GMod `gmod-mcp-addon`, providing custom console commands to automate son actions in the game.
- MPC Server is implemented in Java using Spring AI to connect the AI Agent with GMod.

## 📦 Repository Structure
<p>/gmod-mcp</p>
<p>|— gmod-mcp-addon/ # Lua add-on for Garry’s Mod (server-side game interface)</p>
<p>|— gmod-mcp-springboot/ # Java (Spring Boot) backend implementing the MCP server</p>
<p>|— build.py # Build script</p>
<p>|— .gitignore</p>
<p>|— (other config files)</p>

## 🛠 Building
### Requirements
- Java 21
- Python 3
- Maven 3

### Environment Configuration
Create a .env file following `.env.example`

### 1. Run the build script
``` bash
python build.py mcp --runInspector
```
Currently it does not save the addon in the GMod server, it will do it in a future

### 2. Connect your AI / agent to the MCP server

``` json
{
	"servers": {
      "test-mcp": {
        "command": "java",
        "args": [
          "-Dspring.ai.mcp.server.stdio=true",
          "-jar",
          "generated-mcp-server.jar",
          "--gmodrcon.host=your-gmod-server-ip",
          "--gmodrcon.port=your-gmod-server-port(default-27015)",
          "--gmodrcon.password=your-gmod-rcon-password"
        ]
      }
    }
}
```
