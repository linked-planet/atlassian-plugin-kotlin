# Maven Archetype: Kotlin Atlassian Plugin
![Java CI](https://github.com/link-time/atlassian-plugin-kotlin/workflows/Java%20CI/badge.svg)
[![GitHub License](https://img.shields.io/badge/license-CC0%201.0%20Universal-blue.svg?style=flat)](https://creativecommons.org/publicdomain/zero/1.0/legalcode)

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


## License

### Template license
Written 2019-2020 by [link-time GmbH](https://www.link-time.com).

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

### Generated project license
The generated project will have Apache 2.0 pre-configured because this is the default
for us. Change it as needed.