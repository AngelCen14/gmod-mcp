# gmod-mcp

MCP server to allow an AI Agent to interact with a Garry’s Mod dedicated server. The goal is to provide an interface that translates natural language into actions within GMod.

## Features

- Support for communication between an AI Agent and GMod server via MCP.
- Includes an addon for GMod `gmod-mcp-addon`, providing custom console commands to automate actions in the game.
- MCP Server is implemented in Java using Spring AI to connect the AI Agent with GMod.

## Project Structure

    gmod-mcp
    ├── gmod-mcp-addon: Lua addon for Garry’s Mod server
    │   └── lua
    │       └── autorun
    │           └── server: addon server-side scripts
    ├── gmod-mcp-springboot: mcp server
    │   └── src
    │       └── main
    │           ├── java: Java source code
    │           │   └── io.github.angelcen14.gmodmcp
    │           │       ├── client: client classes for gmod rcon
    │           │       ├── config: configuration classes
    │           │       ├── service: service classes, mcp tools
    │           │       └── model: data models
    │           └── resources: application resources
    ├── build.py: build script
    └── .env.example: example environment file

## Building

### Requirements

- Java 21
- Python 3
- Maven 3
- Node.js 24 and Npx 11

### Environment Configuration

Create a `.env` file following `.env.example`

### 1. Run the build script

- Build mcp server
    ```cmd
    python build.py mcp
    ```
- Build mcp server skipping tests
    ```cmd
    python build.py mcp --skipTests
    ```
- Build mcp server and run it in mcp inspector
    ```cmd
    python build.py mcp --runInspector
    ```
- Future upgrades:
    - Move gmod addon to gmod server directory
        ```cmd
        python build.py addon
        ```
    - Build both mcp server and gmod addon
        ```cmd
        python build.py all
        ```
    - Add .env variable to customize output jar file location:
        ```env
        MCP_SERVER_JAR_OUT_DIR=custom/path/
        ```

## Usage

Add the following configuration to your `mcp.json` file:

```json
{
    "servers": {
        "test-mcp": {
            "command": "java",
            "args": [
                "-Dspring.ai.mcp.server.stdio=true",
                "-jar",
                "your/absolute/path/gmod-mcp-x.x.x.jar",
                "--gmodrcon.host=your-gmod-server-ip",
                "--gmodrcon.port=your-gmod-server-port(default-27015)",
                "--gmodrcon.password=your-gmod-rcon-password"
            ]
        }
    }
}
