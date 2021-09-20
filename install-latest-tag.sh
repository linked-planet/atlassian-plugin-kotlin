#!/bin/bash

set -e

install() {
  TAG=$1
  echo_demarcation "$TAG"
  git checkout "$TAG"
  mvn clean install
}

echo_demarcation() {
  TEXT=$1
  echo
  echo "--------------------------------------------------------------------------"
  echo "INSTALL: $TEXT"
  echo "--------------------------------------------------------------------------"
}

LATEST_TAG=$(git describe --tags --abbrev=0)

echo "Latest tag is: $LATEST_TAG"
echo "Proceeding with 'git checkout $LATEST_TAG && mvn clean install'"
read -r -p "Press any key to continue ..."

install "$LATEST_TAG"
