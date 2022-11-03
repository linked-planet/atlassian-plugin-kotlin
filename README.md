# Maven Archetype: Kotlin Atlassian Plugin

![Maven](https://github.com/linked-planet/atlassian-plugin-kotlin/actions/workflows/jira.yml/badge.svg)
![Maven](https://github.com/linked-planet/atlassian-plugin-kotlin/actions/workflows/confluence.yml/badge.svg)
[![GitHub License](https://img.shields.io/badge/license-CC0%201.0%20Universal-blue.svg?style=flat)](https://creativecommons.org/publicdomain/zero/1.0/legalcode)

Creates a Jira or Confluence plugin to be implemented with the
[Kotlin](https://kotlinlang.org/) programming language.

Also provides the ability to create SPA frontend modules using
[KotlinJS](https://kotlinlang.org/docs/js-overview.html) / [React](https://reactjs.org/)
via [JetBrains KotlinJS Wrappers](https://github.com/JetBrains/kotlin-wrappers),
utilizing [Atlaskit UI Widgets](https://atlaskit.atlassian.com/).

## Usage

Use [install-latest-tag.sh](install-latest-tag.sh) to install the latest available tag on your local machine.  
Once done, you can create new plugin projects using the commands documented below.

### Required Properties

| name | description |
| ---- | ----------- |
| atlassianApp | `confluence`, `jira`, `jira-insight` |
| atlassianAppVersion | Version of Jira / Confluence to be used |
| groupId | Maven Group ID |
| artifactId | Maven Artifact ID |
| package | Name of the Java package the plugin source code will reside in |
| nameHumanReadable | Name of the project to be shown to users |
| description | A short description of the plugin |
| organizationNameHumanReadable | Name of the author organization |
| organizationUrl | URL of the author organization |
| inceptionYear | Year of inception |
| developerConnection | String to be written into Maven's SCM `developerConnection` tag |
| generateGithubActions | Whether to generate a GitHub workflow |
| generateDockerEnvironment | Whether to generate a `docker-compose.yml` with a basic Atlassian-provided app container |
| generateStubs | Whether to generate stubs (all archetype files in `src`) |
| generateFrontend | Whether to generate a separate React/KotlinJS frontend  |
| frontendAppName | Name of the first app to generate, lower camel case |
| frontendAppNameUpperCamelCase | Name of the first app to generate, upper camel case |
| frontendAppNameKebabCase | Name of the first app to generate, kebab case |
| httpPort | The HTTP port Jira or Confluence shall bind to. **Attention:** Use 2990 for Jira and 1990 for Confluence when running locally! Otherwise, the frontend dev server integration won't work. This config only exists so it can be overridden during integration tests of the archetype itself. |

### Jira Example

```
mvn archetype:generate -B \
    "-DarchetypeGroupId=com.linked-planet.maven.archetype" \
    "-DarchetypeArtifactId=atlassian-plugin-kotlin" \
    "-DarchetypeVersion=<VERSION-OF-ARCHETYPE>" \
    "-DatlassianApp=jira" \
    "-DatlassianAppVersion=8.13.11" \
    "-DgroupId=com.linked-planet.plugin.jira" \
    "-DartifactId=new-plugin" \
    "-Dpackage=com.linkedplanet.plugin.jira.newplugin" \
    "-DnameHumanReadable=New Plugin" \
    "-Ddescription=Description of New Plugin" \
    "-DorganizationNameHumanReadable=linked-planet GmbH" \
    "-DorganizationUrl=https://linked-planet.com" \
    "-DinceptionYear=2021" \
    "-DdeveloperConnection=scm:git:https://github.com/linked-planet/new-plugin.git" \
    "-DgenerateGithubActions=true" \
    "-DgenerateDockerEnvironment=true" \
    "-DgenerateStubs=true" \
    "-DgenerateFrontend=true" \
    "-DfrontendAppName=exampleApp" \
    "-DfrontendAppNameUpperCamelCase=ExampleApp" \
    "-DfrontendAppNameKebabCase=example-app" \
    "-DhttpPort=2990" \
    "-Dgoals=license:update-file-header"
```

### Confluence Example

```
mvn archetype:generate -B \
    "-DarchetypeGroupId=com.linked-planet.maven.archetype" \
    "-DarchetypeArtifactId=atlassian-plugin-kotlin" \
    "-DarchetypeVersion=<VERSION-OF-ARCHETYPE>" \
    "-DatlassianApp=confluence" \
    "-DatlassianAppVersion=7.13.0" \
    "-DgroupId=com.linked-planet.plugin.confluence" \
    "-DartifactId=new-plugin" \
    "-Dpackage=com.linkedplanet.plugin.confluence.newplugin" \
    "-DnameHumanReadable=New Plugin" \
    "-Ddescription=Description of New Plugin" \
    "-DorganizationNameHumanReadable=linked-planet GmbH" \
    "-DorganizationUrl=https://linked-planet.com" \
    "-DinceptionYear=2021" \
    "-DdeveloperConnection=scm:git:https://github.com/linked-planet/new-plugin.git" \
    "-DgenerateGithubActions=true" \
    "-DgenerateDockerEnvironment=true" \
    "-DgenerateStubs=true" \
    "-DgenerateFrontend=true" \
    "-DfrontendAppName=exampleApp" \
    "-DfrontendAppNameUpperCamelCase=ExampleApp" \
    "-DfrontendAppNameKebabCase=example-app" \
    "-DhttpPort=1990" \
    "-Dgoals=license:update-file-header"
```

## Troubleshooting

Note that we start actual Jira / Confluence instances during GitHub CI,
also verifying that the React UI is integrated properly via a Selenium test.

- First, make sure you are building your plugin with Java 8 JDK!
  - Check your `JAVA_HOME` environment variable
- Second, if you are having trouble building with the IDE, try via `mvn` command line instead 


## License

### Template license

Written 2019-2021 by [linked-planet GmbH](https://www.linked-planet.com).

To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this
template to the public domain worldwide. This template is distributed without any warranty.
See <http://creativecommons.org/publicdomain/zero/1.0/>.

### Generated project license

The generated project will have Apache 2.0 pre-configured because this is the default for us. Change it as needed.
