# Maven Archetype: Kotlin Atlassian Plugin
Creates a Jira or Confluence plugin to be implemented with the
[Kotlin](https://kotlinlang.org/) programming language.

## Usage

### Required Properties
| name | description |
| ---- | ----------- |
| atlassianApp | `confluence`, `jira` |
| groupId | Maven Group ID |
| artifactId | Maven Artifact ID |
| package | Name of the Java package the plugin source code will reside in |
| nameHumanReadable | Name of the project to be shown to users |
| description | A short description of the plugin |
| organizationNameHumanReadable | Name of the author organization |
| organizationUrl | URL of the author organization |
| inceptionYear | Year of inception |
| developerConnection | String to be written into Maven's SCM `developerConnection` tag |

### Example
```
mvn archetype:generate -B \
    -DarchetypeGroupId=com.linktime.maven.archetype \
    -DarchetypeArtifactId=atlassian-plugin-kotlin \
    -DarchetypeVersion=<VERSION-OF-ARCHETYPE> \
    -DatlassianApp=jira \
    -DgroupId=com.linktime.plugin.jira \
    -DartifactId=new-plugin \
    -Dpackage=com.linktime.plugin.jira.newplugin \
    "-DnameHumanReadable='New Plugin'" \
    "-Ddescription='Description of New Plugin'" \
    "-DorganizationNameHumanReadable='link-time GmbH'" \
    -DorganizationUrl=https://link-time.com \
    -DinceptionYear=2019 \
    -DdeveloperConnection=scm:git:https://github.com/link-time/new-plugin.git
```