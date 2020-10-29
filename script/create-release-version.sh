#!/bin/sh

set -e

if [ "$#" -lt 1 ]; then
    echo "Usage: $0 version"
    exit 1
fi

VERSION=$1

# set non-snapshot version number as specified in parameter
mvn versions:set "-DnewVersion=${VERSION}" versions:commit
git add pom.xml && git commit -m "release v${VERSION}" && git tag "v${VERSION}" && git push && git push --tags

# prepare for next snapshot version
MAJOR_VERSION=$(echo "$VERSION" | cut -d . -f1)
MINOR_VERSION=$(echo "$VERSION" | cut -d . -f2)
INCREMENT_VERSION=$(echo "$VERSION" | cut -d . -f3)
NEXT_INCREMENT_VERSION=$((INCREMENT_VERSION + 1))
NEXT_SNAPSHOT_VERSION="$MAJOR_VERSION.$MINOR_VERSION.$NEXT_INCREMENT_VERSION-SNAPSHOT"
mvn versions:set "-DnewVersion=${NEXT_SNAPSHOT_VERSION}" versions:commit
git add pom.xml && git commit -m "[skip ci] set development version $NEXT_SNAPSHOT_VERSION" && git push
