# Change Log

## [Unreleased] - yyyy-mm-dd

### Added

- **KotlinJS / React integration**
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
- Upgraded Jira and Confluence versions to latest LTS versions

