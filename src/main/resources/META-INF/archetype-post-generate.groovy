import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// the path where the project got generated
Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

String atlassianApp = request.properties.get("atlassianApp")
Boolean generateBitbucketPipelines = Boolean.valueOf(request.properties.get("generateBitbucketPipelines"))

// delete the run configurations not needed by the respective app
if (atlassianApp == "jira") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_debug.xml")
} else if (atlassianApp == "confluence") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_debug.xml")
}

// delete bitbucket pipelines files if not desired
if (!generateBitbucketPipelines) {
    Files.deleteIfExists projectPath.resolve("pipelines/configure-maven-artifactory.sh")
    Files.deleteIfExists projectPath.resolve("pipelines/deploy.sh")
    Files.deleteIfExists projectPath.resolve("pipelines/release.sh")
    Files.deleteIfExists projectPath.resolve("pipelines")
    Files.deleteIfExists projectPath.resolve("bitbucket-pipelines.yml")
}
