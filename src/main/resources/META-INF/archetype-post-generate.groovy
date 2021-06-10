import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// the path where the project got generated
Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

void deleteRecursive(Path path) throws IOException {
    File file = path.toFile()
    if (file.isDirectory()) {
        for (File c : file.listFiles())
            deleteRecursive c.toPath()
    } else if (file.isFile()) {
        file.delete()
    }
}

String atlassianApp = request.properties.get("atlassianApp")
Boolean generateBitbucketPipelines = Boolean.valueOf(request.properties.get("generateBitbucketPipelines"))
Boolean generateDockerEnvironment = Boolean.valueOf(request.properties.get("generateDockerEnvironment"))
Boolean generateGithubActions = Boolean.valueOf(request.properties.get("generateGithubActions"))
Boolean generateStubs = Boolean.valueOf(request.properties.get("generateStubs"))
Boolean generateFrontend = Boolean.valueOf(request.properties.get("generateFrontend"))

if (atlassianApp.contains("jira")) {
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_run.run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_debug.run.xml")
} else if (atlassianApp == "confluence") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_run.run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_debug.run.xml")
}

if (!generateGithubActions) {
    deleteRecursive projectPath.resolve(".github")
}

if (!generateBitbucketPipelines) {
    deleteRecursive projectPath.resolve("pipelines")
    Files.deleteIfExists projectPath.resolve("bitbucket-pipelines.yml")
}

if (!generateDockerEnvironment) {
    deleteRecursive projectPath.resolve("docker-env")
    Files.deleteIfExists projectPath.resolve("run-docker-env.sh")
}

if (!generateStubs) {
    deleteRecursive projectPath.resolve("src")
}

def frontendPath = projectPath.resolve("frontend")
if (!generateFrontend) {
    deleteRecursive frontendPath
    Files.deleteIfExists projectPath.resolve("src/main/resources/templates/action/module.vm")
    Files.deleteIfExists projectPath.resolve("runConfigurations/frontend_debug.run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/frontend_run.run.xml")
} else {
    frontendPath.resolve("gradlew").toFile().setExecutable(true)
    frontendPath.resolve("gradlew.bat").toFile().setExecutable(true)
}
