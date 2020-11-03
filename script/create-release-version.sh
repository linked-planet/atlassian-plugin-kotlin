#!/bin/bash

set -e

SCRIPT_DIR="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 version"
  exit 1
fi

VERSION=$1

get_version_from_maven() {
  mvn help:evaluate -Dexpression=project.version -q -DforceStdout | tail -n 1
}

set_commit_version() {
  OLD_V=$1
  NEW_V=$2
  COMMIT_MSG=$3
  mvn versions:set "-DnewVersion=$NEW_V" versions:commit
  sed -i "s/$OLD_V/$NEW_V/g" "$SCRIPT_DIR/../src/main/resources/archetype-resources/regenerate.sh"
  git ls-files . | grep 'pom.xml' | xargs git add &&
    git ls-files . | grep 'regenerate.sh' | xargs git add &&
    git commit -m "$COMMIT_MSG"
}

echo_demarcation() {
  TEXT=$1
  echo
  echo "--------------------------------------------------------------------------"
  echo "RELEASE: $TEXT"
  echo "--------------------------------------------------------------------------"
}

# set non-snapshot version number as specified in parameter
echo_demarcation "Set & commit version $VERSION"
CURRENT_VERSION=$(get_version_from_maven)
set_commit_version "$CURRENT_VERSION" "$VERSION" "release v$VERSION"
git tag "v${VERSION}" && git push && git push --tags

# prepare for next snapshot version
MAJOR_VERSION=$(echo "$VERSION" | cut -d . -f1)
MINOR_VERSION=$(echo "$VERSION" | cut -d . -f2)
INCREMENT_VERSION=$(echo "$VERSION" | cut -d . -f3)
NEXT_INCREMENT_VERSION=$((INCREMENT_VERSION + 1))
NEXT_SNAPSHOT_VERSION="$MAJOR_VERSION.$MINOR_VERSION.$NEXT_INCREMENT_VERSION-SNAPSHOT"
echo_demarcation "Prepare next snapshot version $NEXT_SNAPSHOT_VERSION"
set_commit_version "$VERSION" "$NEXT_SNAPSHOT_VERSION" "[skip ci] set development version $NEXT_SNAPSHOT_VERSION"
git push
