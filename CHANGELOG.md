# Change Log

## 3.3.0 - 2022-11-08

### Changed

- Upgrade Jira and Confluence versions
  - Jira 8.20.13 
  - Confluence 7.13.8
- Upgrade versions of all dependencies, Maven plugins & Gradle plugins
- Upgrade Kotlin version to 1.7.20
- Maven license plugin now also processed frontend code
- Update license notice for 2022
- Add atlassian-jira.log to IntelliJ run configurations
- React Frontend: Use [ui-kit-lib](https://github.com/linked-planet/ui-kit)
- React Frontend: Removed obsolete Gradle build workarounds
- Removed obsolete code
- Removed publishing to Artifactory (recommend to use e.g. AWS CodeArtifact instead)
- Removed developerConnection & scm settings from Maven build (no longer used)
- Dropped support for Bitbucket Pipelines (recommend to use GitHub Actions instead)

## 3.2.0 - 2021-09-20

### Added

- **KotlinJS / React Integration**
    - It is now possible to generate build infrastructure & stubs that enable creation of modern SPA modules that can be
      integrated into the plugin wherever you like to use them.
    - The frontend build is based on Gradle, but is integrated into the encompassing Maven plugin build, so there is a
      single pipeline to build everything.
- **GitHub CI Improvements**
    - We now start parallel pipelines for both Jira and Confluence on every push to the archetype.
    - During these pipelines, we start up Jira / Confluence and test whether the React UI is reachable via Selenium Test
- **Local Maven Settings for handling Atlassian Maven Dependencies**
    - The `.mvn/local-settings.xml` will make sure that the necessary Atlassian dependencies are resolved during the
      build.
    - Note that while this works out of the box with Maven, you must still configure IntelliJ to make use of that file (
      see [IDEA-197658](https://youtrack.jetbrains.com/issue/IDEA-197658)).
- **New Archetype Properties**
   - `atlassianAppVersion` allows to set the Jira / Confluence version to be used

### Changed

- The `install-all-tags.sh` was changed to `install-latest-tag.sh`, because only the latest version is usually needed
  and because it takes a lot of time to install all tags.
- Upgraded Jira and Confluence versions to the latest LTS versions
  - Jira 8.13.11
  - Confluence 7.13.0

