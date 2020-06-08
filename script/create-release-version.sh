#!/bin/sh

set -e

if [ "$#" -lt 1 ]; then
    echo "Usage: $0 <version>"
    exit 1
fi

VERSION=$1

get_version_from_maven() {
    mvn help:evaluate -Dexpression=project.version -q -DforceStdout | tail -n 1
}

mvn build-helper:parse-version versions:set "-DnewVersion=${VERSION}" versions:commit
git add pom.xml && git commit -m "release v${VERSION}" && git tag "v${VERSION}" && git push && git push --tags
mvn build-helper:parse-version versions:set "-DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion}-SNAPSHOT" versions:commit
next_version=$(get_version_from_maven)
git add pom.xml && git commit -m "[skip ci] set development version ${next_version}" && git push
