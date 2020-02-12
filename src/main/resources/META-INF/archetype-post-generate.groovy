import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// the path where the project got generated
Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

String atlassianApp = request.properties.get("atlassianApp")

// delete the run configurations not needed by the respective app
if (atlassianApp == "jira") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/confluence_debug.xml")
} else if (atlassianApp == "confluence") {
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_run.xml")
    Files.deleteIfExists projectPath.resolve("runConfigurations/jira_debug.xml")
}
