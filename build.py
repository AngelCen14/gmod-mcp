import atexit
import os
import subprocess
import sys

from dotenv import load_dotenv

mcp_inspector_subprocess = None

def cleanup():
    print("Running cleanup...")
    global mcp_inspector_subprocess

    if mcp_inspector_subprocess is not None and mcp_inspector_subprocess.poll() is None:
        mcp_inspector_subprocess.terminate()
    print("Cleanup finished.")

atexit.register(cleanup)

build_type = sys.argv[1]
load_dotenv()

if build_type == "mcp":
    MAVEN_LOCATION = os.getenv("MAVEN_LOCATION")
    print(MAVEN_LOCATION)

    print("Building for MCP...")

    project_dir = "./gmod-mcp-springboot"
    cmd = f'"{MAVEN_LOCATION}" clean package -DskipTests'

    os.chdir(project_dir)
    os.system(cmd)

    target_dir = "target" # join not needed as we are already in project_dir

    jar_file = None
    for file in os.listdir(target_dir):
        if file.endswith(".jar"):
            jar_file = os.path.abspath(os.path.join(target_dir, file))

    print(f"Built JAR file located at: {jar_file}")


    if len(sys.argv) > 2 and sys.argv[2] == "--runInspector":
        GMOD_HOST = os.getenv("GMOD_HOST")
        GMOD_PORT = os.getenv("GMOD_PORT")
        GMOD_RCON_PASSWORD = os.getenv("GMOD_RCON_PASSWORD")

        inspector_cmd = f'npx -y @modelcontextprotocol/inspector '
        inspector_params = f'java -Dspring.ai.mcp.server.stdio=true -jar {jar_file}'
        gmod_params = f'--gmodrcon.host={GMOD_HOST} --gmodrcon.port={GMOD_PORT} --gmodrcon.password={GMOD_RCON_PASSWORD}'

        mcp_inspector_subprocess = subprocess.Popen(f'{inspector_cmd} {inspector_params} {gmod_params}', shell=True)

        try:
            mcp_inspector_subprocess.wait()
        except KeyboardInterrupt:
           print("KeyboardInterrupt received. Terminating MCP Inspector subprocess...")
        finally:
            cleanup()

elif build_type == "addon":
    print("Building for Addon...")
else:
    print("Unknown build type. Please specify 'mcp' or 'addon'.")

