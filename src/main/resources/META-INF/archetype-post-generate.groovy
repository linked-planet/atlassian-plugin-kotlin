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

if (atlassianApp == "jira") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_debug.xml")
} else if (atlassianApp == "confluence") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_debug.xml")
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
